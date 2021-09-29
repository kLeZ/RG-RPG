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

import java.lang.reflect.Type;

/**
 * This is the main interface to write an adapter.
 *
 * @param <T>
 *
 * @author kLeZ
 */
public interface Adapter<T> {
	/**
	 * Adapts the string representation of a value to its related object.
	 *
	 * @param value
	 * 		The string representation of the value to adapt as object.
	 *
	 * @return An object that can hold the value represented by the input
	 * string.
	 */
	T adapt(String value);

	/**
	 * The type that will be adapted from the value passed to the component.
	 *
	 * @return The type that will be adapted from the value passed to the component.
	 */
	Type getType();
}
