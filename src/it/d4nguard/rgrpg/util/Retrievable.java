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
package it.d4nguard.rgrpg.util;

import java.lang.reflect.Field;
import java.util.Collections;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;

public abstract class Retrievable<THIS>
{
	private final Set<Field> registeredFields;

	public Retrievable()
	{
		registeredFields = new HashSet<Field>();
	}

	public abstract THIS get();

	//TODO: Create an annotation to place onto the retrievable fields,
	//		And a method called in the constructor to retrieve all the retrievable fields.
	//		In this way I don't have to implement a repository and an obscure logic.

	public void registerField(String fieldName) throws SecurityException,
					NoSuchFieldException
	{
		registeredFields.add(get().getClass().getDeclaredField(fieldName));
	}

	public Set<Field> getRegisteredFields()
	{
		return Collections.unmodifiableSet(registeredFields);
	}

	public Field getRegisteredField(String fieldName)
	{
		Field ret = null;
		Iterator<Field> it = registeredFields.iterator();
		while (ret == null && it.hasNext())
		{
			Field f = it.next();
			if (f.getName().equals(fieldName)) ret = f;
		}
		return ret;
	}
}
