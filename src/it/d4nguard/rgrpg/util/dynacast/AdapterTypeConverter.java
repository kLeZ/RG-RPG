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
package it.d4nguard.rgrpg.util.dynacast;

import ognl.TypeConverter;

import java.lang.reflect.Member;
import java.lang.reflect.Method;
import java.lang.reflect.Type;
import java.util.Map;
import java.util.Objects;

/**
 * An implementation of the {@link TypeConverter} class that implements dynacast
 * logic.
 *
 * @author kLeZ
 */
public class AdapterTypeConverter implements TypeConverter {
	/**
	 * Converts the given value to a given type.  The OGNL context, target, member and
	 * name of property being set are given.  This method should be able to handle
	 * conversion in general without any context, target, member or property name specified.
	 *
	 * @param context
	 * 		OGNL context under which the conversion is being done
	 * @param target
	 * 		target object in which the property is being set
	 * @param member
	 * 		member (Constructor, Method or Field) being set
	 * @param propertyName
	 * 		property name being set
	 * @param value
	 * 		value to be converted
	 * @param toType
	 * 		type to which value is converted
	 *
	 * @return Converted value of type toType or TypeConverter.NoConversionPossible to indicate that the
	 * conversion was not possible.
	 */
	@Override
	public Object convertValue(Map context, Object target, Member member, String propertyName, Object value, Class toType) {
		Type type = toType;
		if (member instanceof Method) {
			Method m = (Method) member;
			Type[] types = m.getGenericParameterTypes();
			if (types.length == 1) {
				// I'm a setter, probably
				type = types[0];
			}
		}
		Adapter<?> a = TypeAdapter.getAdapter(type);
		return Objects.requireNonNull(a)
				.adapt(String.valueOf(value));
	}
}
