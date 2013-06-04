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
package it.d4nguard.rgrpg.managers;

public interface Manager<E>
{
	/**
	 * Creates an object of the given type E
	 * 
	 * @param name
	 *            Logical Name of the entity
	 * @param args
	 *            Some technical varargs that can help object construction
	 * @return An object of type E, formally built.
	 */
	public E create(String name, Object... args);

	/**
	 * Deletes an object in an hypotetical list, named as passed
	 * 
	 * @param name
	 *            Logical Name of the entity
	 * @return true if the object has been deleted, false otherwise.
	 */
	public boolean delete(String name);

	/**
	 * Renames an object
	 * 
	 * @param name
	 *            Logical Name of the entity
	 * @param newName
	 *            New Logical Name of the entity
	 * @return the same object, just renamed.
	 */
	public E rename(String name, String newName);

	/**
	 * Sets an object as current, choosing it in a collection of other objects
	 * of the same type E
	 * 
	 * @param name
	 *            Logical Name of the entity
	 * @return The chosen object.
	 */
	public E use(String name);

	/**
	 * Gets the current object, in a collection of other objects of the same
	 * type E
	 * 
	 * @return The current object
	 */
	public E current();

	/**
	 * Gets the specified object
	 * 
	 * @param name
	 *            Logical Name of the entity
	 * @return The requested object
	 */
	public E get(String name);

	/**
	 * Gets a String representation of all the possible child types extending or
	 * implementing E, managed by this manager, and all the fields for
	 * each type listed in this way in a human-readable manner.
	 * 
	 * @return A human-readable String representation of the child types
	 *         extending or implementing E
	 */
	public String availables();
}
