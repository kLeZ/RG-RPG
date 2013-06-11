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

import it.d4nguard.rgrpg.util.dynacast.adapters.EnumAdapter;
import it.d4nguard.rgrpg.util.dynacast.adapters.PrimitivesAdapters;

import java.util.HashMap;
import java.util.Map;

public class TypeCaster
{
	private static final HashMap<String, Class<?>> primitives = new HashMap<String, Class<?>>();
	static
	{
		primitives.put("boolean", Boolean.class);
		primitives.put("byte", Byte.class);
		primitives.put("char", Character.class);
		primitives.put("double", Double.class);
		primitives.put("float", Float.class);
		primitives.put("int", Integer.class);
		primitives.put("long", Long.class);
		primitives.put("short", Short.class);
	}

	private static final Map<Class<?>, AdapterFactory<?>> factories;
	static
	{
		factories = new HashMap<Class<?>, AdapterFactory<?>>();
		factories.putAll(PrimitivesAdapters.getAll());
		factories.put(Enum.class, new AdapterFactory<Enum<?>>()
		{
			@Override
			public Adapter<Enum<?>> create(Class<Enum<?>> type)
			{
				return new EnumAdapter<>(type);
			}
		});
	}

	/**
	 * This static method gets a type adapter from its factory class. <br>
	 * Call example:<br>
	 * <code>TypeCaster.getAdapter(Integer.class).adapt("5");</code>
	 * 
	 * @param type
	 *            is the type you want to adapt to.
	 * @return An adapter class that can adapt a string to the recalled object.
	 */
	@SuppressWarnings({ "rawtypes", "unchecked" })
	public static Adapter<?> getAdapter(Class type)
	{
		return factories.get(unbox(type)).create(type);
	}

	private static Class<?> unbox(Class<?> type)
	{
		if (type.isPrimitive()) return primitives.get(type.getName());
		else return type;
	}
}
