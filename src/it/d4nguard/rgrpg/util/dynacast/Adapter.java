// RG-RPG is a Java-based text, roleplaying-gal game, in which you
// have to carry many girls. The RG-RPG acronym is a recursive one and
// it means "RG-RPG is a Gal Role playing game Pointing on Girls."
// Copyright (C) 2013 by Alessandro Accardo <julius8774@gmail.com>
// 
// This program is free software: you can redistribute it and/or modify
// it under the terms of the GNU General Public License as published by
// the Free Software Foundation, either version 2 of the License, or (at
// your option) any later version.
// 
// This program is distributed in the hope that it will be useful, but
// WITHOUT ANY WARRANTY; without even the implied warranty of
// MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the GNU
// General Public License for more details.
// 
// You should have received a copy of the GNU General Public License
// along with this program.  If not, see <http://www.gnu.org/licenses/>.
// 
package it.d4nguard.rgrpg.util.dynacast;

import java.lang.reflect.Type;

/**
 * This is the main interface to write an adapter.
 * 
 * @author kLeZ-hAcK
 * @param <T>
 */
public interface Adapter<T>
{
	public static final String ARRAY_JOINER = "|";

	/**
	 * Adapts the string representation of a value to its related object.
	 * 
	 * @param value
	 *            The string representation of the value to adapt as object.
	 * @return An object that can keep the value represented by the input
	 *         string.
	 */
	T adapt(String value);

	/**
	 * The type that will be adapted from the value passed to the component.
	 * 
	 * @return
	 */
	Type getType();
}
