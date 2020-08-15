/*
 * Copyright (C) 2020 Alessandro 'kLeZ' Accardo
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
package it.d4nguard.rgrpg.util.dynacast.strategies;

import it.d4nguard.rgrpg.util.GenericsUtils;

import java.lang.reflect.Type;
import java.util.HashMap;

/**
 * This class implements an {@link AbstractStrategy} that applies to
 * primitive types.<br>
 * The {@link #getMine(Type)} method returns a wrapper object of the given
 * primitive type.
 *
 * @author kLeZ-hAcK
 */
public class PrimitiveStrategy extends AbstractStrategy {
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

	/**
	 * {@inheritDoc}
	 */
	@Override
	public boolean isMine(Type type) {
		return (type instanceof Class && ((Class<?>) type).isPrimitive());
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public Class<?> getMine(Type type) {
		return primitives.get(GenericsUtils.getClassFromType(type)
				.getName());
	}
}
