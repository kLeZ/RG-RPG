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
package it.d4nguard.rgrpg.util.dynacast.adapters;

import it.d4nguard.rgrpg.util.GenericsUtils;
import it.d4nguard.rgrpg.util.dynacast.Adapter;
import it.d4nguard.rgrpg.util.dynacast.Provider;
import it.d4nguard.rgrpg.util.dynacast.factories.AdapterFactory;

import java.lang.reflect.Type;
import java.util.HashMap;
import java.util.Map;

/**
 * This class implements an abstract adapter, which should be a base (except for
 * some rare cases) for all the adapters that will be implemented out there.
 * Actually it implements an {@link Adapter} and a {@link Provider} of
 * {@link AdapterFactory}, in this way much of the work (and classes) that
 * should be implemented are grouped in a single implementation, useful and
 * generic.
 *
 * @param <T>
 * 		The type to adapt, passed to {@link Adapter}
 *
 * @author kLeZ-hAcK
 */
@SuppressWarnings("rawtypes")
public abstract class AbstractAdapter<T> implements Adapter<T>, Provider<AdapterFactory> {
	private final AbstractAdapter<T> myself = this;
	private Type adaptedType;

	/**
	 * {@inheritDoc}
	 */
	@Override
	@SuppressWarnings("unchecked")
	public Map<Class<?>, AdapterFactory> get() {
		HashMap<Class<?>, AdapterFactory> ret = new HashMap<>();
		ret.put(GenericsUtils.getFirstGenericType(getClass()), new AdapterFactory<T>() {
			/**
			 * {@inheritDoc}
			 */
			@Override
			public Adapter<T> create(Type type) {
				adaptedType = type;
				beforeCreateAdapter(type);
				return myself;
			}
		});
		return ret;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public Type getType() {
		return adaptedType;
	}

	/**
	 * This method acts as a custom interaction in the
	 * {@link AdapterFactory#create(Type)} method, right before the return
	 * instruction.
	 *
	 * @param type
	 * 		The exact type, without modifictions, passed to
	 *        {@link AdapterFactory#create(Type)}
	 */
	public abstract void beforeCreateAdapter(Type type);
}
