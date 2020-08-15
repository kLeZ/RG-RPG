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
package it.d4nguard.rgrpg.util.dynacast.factories;

import it.d4nguard.rgrpg.Context;
import it.d4nguard.rgrpg.util.Delegate;
import it.d4nguard.rgrpg.util.Utils;
import it.d4nguard.rgrpg.util.dynacast.Provider;
import org.reflections.Reflections;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Modifier;
import java.util.AbstractMap;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

/**
 * This class implements a wise {@link Map} in the form of {@link AbstractMap}<
 * {@link Class}<?>, {@link AdapterFactory}<?>>.<br>
 * Initializes this {@link AbstractMap} with all the sub types of
 * {@link Provider}.class, found by {@link Reflections}.
 *
 * @author kLeZ-hAcK
 */
public class AdapterFactoryMap extends AbstractMap<Class<?>, AdapterFactory<?>> {
	private static AdapterFactoryMap instance;
	private final Map<Class<?>, AdapterFactory<?>> __map = new HashMap<>();

	/**
	 * Initializes this {@link AbstractMap} with all the sub types of
	 * {@link Provider}.class, found by {@link Reflections}.
	 */
	public AdapterFactoryMap() {
		init();
	}

	/**
	 * This static method acts as the entry point of a singleton pattern through
	 * which the unique instance of this {@link Map} can be retrieved. <br>
	 * Initializes this {@link AbstractMap} with all the sub types of
	 * {@link Provider}.class, found by {@link Reflections}.
	 *
	 * @return The singleton of this {@link Map}.
	 */
	public static AdapterFactoryMap current() {
		if (instance == null)
			instance = new AdapterFactoryMap();
		return instance;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public Set<Entry<Class<?>, AdapterFactory<?>>> entrySet() {
		return __map.entrySet();
	}

	/**
	 * Initializes this {@link AbstractMap} with all the sub types of
	 * {@link Provider}.class, found by {@link Reflections}.
	 */
	private void init() {
		Set<Class<? extends Provider>> subTypes = Utils.getSubTypesOf(Provider.class);
		Utils.doAll(subTypes, (Delegate<Class<? extends Provider>, Void>) t -> {
			try {
				if (!Modifier.isAbstract(t.getModifiers())) {
					Provider p = t.getDeclaredConstructor()
							.newInstance();
					__map.putAll(p.get());
				}
			} catch (InstantiationException | IllegalAccessException | NoSuchMethodException | InvocationTargetException e) {
				Context.printThrowable(e);
			}
			return null;
		});
	}
}
