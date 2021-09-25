/*
 * Copyright (C) 2021 Alessandro 'kLeZ' Accardo
 *
 * This file is part of RG-RPG.
 *
 * RG-RPG is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * RG-RPG is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with RG-RPG.  If not, see <http://www.gnu.org/licenses/>.
 *
 */
package it.d4nguard.rgrpg.util;

import it.d4nguard.rgrpg.Context;

import java.lang.reflect.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class GenericsUtils {
	private static final HashMap<String, Class<?>> primitives = new HashMap<>();

	static {
		primitives.put("boolean", Boolean.class);
		primitives.put("byte", Byte.class);
		primitives.put("char", Character.class);
		primitives.put("double", Double.class);
		primitives.put("float", Float.class);
		primitives.put("int", Integer.class);
		primitives.put("long", Long.class);
		primitives.put("short", Short.class);
	}

	public static <T> T valueOf(final Class<T> valueType, final String value) {
		return unsafeValueOf(valueType, value, null);
	}

	public static <T> T valueOf(final Class<T> valueType, final String value, final Object defaultValue) {
		return unsafeValueOf(valueType, value, defaultValue);
	}

	@SuppressWarnings("unchecked")
	private static <T> T unsafeValueOf(final Class<T> valueType, final String value, final Object defaultValue) {
		T ret = null;
		if (valueType.isInstance(value))
			ret = (T) value;
		else if (isPrimitiveOrPrimitiveWrapper(valueType) && !valueType.equals(Character.class))
			try {
				// Character has only valueOf(char)
				// All primitives have valueOf(String) except Character
				ret = (T) valueType.getMethod("valueOf", String.class)
						.invoke(null, value);
			} catch (final IllegalAccessException | SecurityException | NoSuchMethodException | InvocationTargetException | IllegalArgumentException e) {
				Context.printThrowable(e);
			}
		else if (valueType.equals(Character.class)) {
			final String v = String.valueOf(value);
			ret = (T) Character.valueOf(v.isEmpty() ? 0 : v.charAt(0));
		} else if (valueType.equals(String.class))
			ret = (T) String.valueOf(value);

		if (ret == null)
			ret = (T) defaultValue;
		return ret;
	}

	/**
	 * @param type
	 * 		a class that may be a masked primitive type
	 *
	 * @return true if this class is a primitive wrapper type, false otherwise
	 */
	public static boolean isPrimitiveOrPrimitiveWrapper(final Class<?> type) {
		return type.isPrimitive() || primitives.containsValue(type);
	}

	public static Class<?> getFieldType(final Class<?> fieldContainer, final String fieldName) {
		Class<?> t = null;
		final Field[] fields = fieldContainer.getDeclaredFields();
		for (final Field field : fields)
			if (field.getName()
					.equalsIgnoreCase(fieldName)) {
				if (field.getType()
						.isPrimitive())
					t = primitives.get(field.getType()
							.getName());
				else
					t = field.getType();
				break;
			}
		return t;
	}

	public static <T> T safeGetter(final T value, final Class<T> type) {
		T ret;
		try {
			ret = value == null ? type.getDeclaredConstructor()
					.newInstance() : value;
		} catch (final InstantiationException | IllegalAccessException | NoSuchMethodException | InvocationTargetException e) {
			final String fmt = "Type %s cannot be instantiated. Please ensure this call is well-formed.";
			throw new RuntimeException(String.format(fmt, type.getName()));
		}
		return ret;
	}

	/**
	 * Get the underlying class for a type, or null if the type is a variable
	 * type.
	 *
	 * @param type
	 * 		the type
	 *
	 * @return the underlying class
	 */
	public static Class<?> getClass(final Type type) {
		if (type instanceof Class)
			return (Class<?>) type;
		else if (type instanceof ParameterizedType pt)
			return getClass(pt.getRawType());
		else if (type instanceof GenericArrayType gat) {
			final Type componentType = gat.getGenericComponentType();
			final Class<?> componentClass = getClass(componentType);
			if (componentClass != null)
				return Array.newInstance(componentClass, 0)
						.getClass();
			else
				return null;
		} else if (type instanceof TypeVariable<?> tv)
			return tv.getGenericDeclaration()
					.getClass();
		else
			return null;
	}

	/**
	 * Get the actual type arguments a child class has used to extend a generic
	 * base class.
	 *
	 * @param baseClass
	 * 		the base class
	 * @param childClass
	 * 		the child class
	 *
	 * @return a list of the raw classes for the actual type arguments.
	 */
	public static <T> List<Class<?>> getTypeArguments(final Class<T> baseClass, final Class<? extends T> childClass) {
		final Map<Type, Type> resolvedTypes = new HashMap<>();
		Type type = childClass;
		// start walking up the inheritance hierarchy until we hit baseClass
		while (!getClass(type).equals(baseClass)) {
			if (type instanceof Class<?> cls)
				type = cls.getGenericSuperclass();
			else {
				final ParameterizedType parameterizedType = (ParameterizedType) type;
				final Class<?> rawType = (Class<?>) parameterizedType.getRawType();

				final Type[] actualTypeArguments = parameterizedType.getActualTypeArguments();
				final TypeVariable<?>[] typeParameters = rawType.getTypeParameters();
				for (int i = 0; i < actualTypeArguments.length; i++)
					resolvedTypes.put(typeParameters[i], actualTypeArguments[i]);

				if (!rawType.equals(baseClass))
					type = rawType.getGenericSuperclass();
			}
		}

		// finally, for each actual type argument provided to baseClass, determine (if possible)
		// the raw class for that type argument.
		Type[] actualTypeArguments;
		if (type instanceof Class<?> cls)
			actualTypeArguments = cls.getTypeParameters();
		else
			actualTypeArguments = ((ParameterizedType) type).getActualTypeArguments();
		final List<Class<?>> typeArgumentsAsClasses = new ArrayList<>();
		// resolve types by chasing down type variables.
		for (Type baseType : actualTypeArguments) {
			while (resolvedTypes.containsKey(baseType)) {
				baseType = resolvedTypes.get(baseType);
			}
			typeArgumentsAsClasses.add(getClass(baseType));
		}
		return typeArgumentsAsClasses;
	}

	public static Class<?> getFirstGenericType(Class<?> c) {
		return getFirstGenericType((ParameterizedType) c.getGenericSuperclass());
	}

	public static Class<?> getFirstGenericType(ParameterizedType pt) {
		Class<?> ret = null;
		for (Type t : getGenericTypes(pt)) {
			ret = getClassFromType(t);
			if (ret != null)
				break;
		}
		if (ret == null)
			ret = (Class<?>) pt.getRawType();
		return ret;
	}

	public static Type[] getGenericTypes(ParameterizedType t) {
		if (t == null)
			return new Type[] { };
		else
			return t.getActualTypeArguments();
	}

	public static Class<?> getClassFromType(Type t) {
		if (t instanceof GenericArrayType gat) {
			return getClass(gat.getGenericComponentType());
		} else if (t instanceof ParameterizedType pt) {
			return getClass(pt.getRawType());
		} else if (t instanceof TypeVariable<?> tv) {
			return getClass(tv.getBounds()[0]);
		} else if (t instanceof WildcardType wt) {
			return wt.getClass();
		} else
			return (Class<?>) t;
	}

	public static Field safeGetDeclaredField(Class<?> clazz, String fieldName) {
		Field f;
		try {
			f = clazz.getDeclaredField(fieldName);
		} catch (NoSuchFieldException | SecurityException e) {
			if (clazz.getSuperclass() != null) {
				f = safeGetDeclaredField(clazz.getSuperclass(), fieldName);
			} else
				f = null;
		}
		return f;
	}
}
