/*
 * Copyright (C) 2019 Alessandro 'kLeZ' Accardo
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

import it.d4nguard.rgrpg.util.dynacast.strategies.EnumStrategy;

import java.lang.reflect.Type;

/**
 * This interface provides a starting poit to implement a Strategy of conversion
 * between complex types.<br>
 * It is needed because of subtypes conversions.<br>
 * <br>
 * A simple example of usage could be the {@link EnumStrategy}, in which the
 * mapped type in the {@link Provider} is {@link Enum} and the adapting types
 * are all the enums available.
 *
 * @author kLeZ-hAcK
 */
public interface Strategy {
	/**
	 * Gets the {@link Class} represented by this {@link Strategy} instance.
	 *
	 * @param type
	 * 		A type that could be useful for certain type euristics.
	 *
	 * @return The {@link Class} represented by this {@link Strategy} instance.
	 */
	Class<?> getMine(Type type);

	/**
	 * Checks if the given type could be converted to, or it is esplicitly, the
	 * type managed by this {@link Strategy} object.<br>
	 * In most cases, when working with an {@link Object}, it can be simply
	 * implemented as:<br>
	 * <code>return &lt;YourObject&gt;.class.isAssignableFrom(someTypecastingToClass(type));</code>
	 *
	 * @param type
	 * 		Type to be checked.
	 *
	 * @return True if and only if the given type could be converted to, or it
	 * is esplicitly, the type managed by this {@link Strategy} object,
	 * otherwise false.
	 */
	boolean isMine(Type type);

	/**
	 * Applies strategic conversion between the given type and the type managed
	 * by this {@link Strategy} object.
	 *
	 * @param type
	 * 		A type to be converted from.
	 *
	 * @return The type managed by this {@link Strategy} object, retrieved
	 * through the given type.
	 */
	Type apply(Type type);
}
