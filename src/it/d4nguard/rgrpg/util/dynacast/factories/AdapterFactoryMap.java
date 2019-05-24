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
package it.d4nguard.rgrpg.util.dynacast.factories;

import it.d4nguard.rgrpg.Context;
import it.d4nguard.rgrpg.util.Delegate;
import it.d4nguard.rgrpg.util.Utils;
import it.d4nguard.rgrpg.util.dynacast.Provider;

import java.lang.reflect.Modifier;
import java.util.AbstractMap;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

import org.reflections.Reflections;

/**
 * This class implements a wise {@link Map} in the form of {@link AbstractMap}<
 * {@link Class}<?>, {@link AdapterFactory}<?>>.<br>
 * Initializes this {@link AbstractMap} with all the sub types of
 * {@link Provider}.class, found by {@link Reflections}.
 * 
 * @author kLeZ-hAcK
 */
public class AdapterFactoryMap extends AbstractMap<Class<?>, AdapterFactory<?>>
{
	private final Map<Class<?>, AdapterFactory<?>> __map = new HashMap<Class<?>, AdapterFactory<?>>();

	/**
	 * Initializes this {@link AbstractMap} with all the sub types of
	 * {@link Provider}.class, found by {@link Reflections}.
	 */
	public AdapterFactoryMap()
	{
		init();
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public Set<Entry<Class<?>, AdapterFactory<?>>> entrySet()
	{
		return __map.entrySet();
	}

	/**
	 * Initializes this {@link AbstractMap} with all the sub types of
	 * {@link Provider}.class, found by {@link Reflections}.
	 */
	@SuppressWarnings("rawtypes")
	private void init()
	{
		Set<Class<? extends Provider>> subTypes = Utils.getSubTypesOf(Provider.class);
		Utils.doAll(subTypes, new Delegate<Class<? extends Provider>, Void>()
		{
			@Override
			@SuppressWarnings("unchecked")
			public Void execute(Class<? extends Provider> t)
			{
				try
				{
					if (!Modifier.isAbstract(t.getModifiers()))
					{
						Provider p = t.newInstance();
						__map.putAll(p.get());
					}
				}
				catch (InstantiationException | IllegalAccessException e)
				{
					Context.printThrowable(e);
				}
				return null;
			}
		});
		/*
		for (Entry<Class<?>, AdapterFactory<?>> e : __map.entrySet())
		{
			System.out.println(String.format("[%s] %s", e.getKey(),
							e.getValue()));
		}
		*/
	}

	private static AdapterFactoryMap instance;

	/**
	 * This static method acts as the entry point of a singleton pattern through
	 * which the unique instance of this {@link Map} can be retrieved. <br>
	 * Initializes this {@link AbstractMap} with all the sub types of
	 * {@link Provider}.class, found by {@link Reflections}.
	 * 
	 * @return The singleton of this {@link Map}.
	 */
	public static AdapterFactoryMap current()
	{
		if (instance == null) instance = new AdapterFactoryMap();
		return instance;
	}
}
