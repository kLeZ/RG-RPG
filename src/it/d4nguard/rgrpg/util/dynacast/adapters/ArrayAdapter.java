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
package it.d4nguard.rgrpg.util.dynacast.adapters;

import it.d4nguard.rgrpg.util.Utils;
import it.d4nguard.rgrpg.util.dynacast.Adapter;
import it.d4nguard.rgrpg.util.dynacast.Provider;
import it.d4nguard.rgrpg.util.dynacast.TypeAdapter;
import it.d4nguard.rgrpg.util.dynacast.factories.AdapterFactory;

import java.lang.reflect.Array;
import java.lang.reflect.GenericArrayType;
import java.lang.reflect.Type;
import java.util.HashMap;
import java.util.Map;

/**
 * Implements an {@link Adapter} and a {@link Provider} of
 * {@link AdapterFactory} for Arrays conversion.<br>
 * A string representation of an array, to be understood by this adapter class,
 * must be in the form of:<br>
 * <code>[element1|element2|...|elementN]</code><br>
 *
 * @param <T>
 * 		The component type of the array to adapt.
 *
 * @author kLeZ-hAcK
 */
public class ArrayAdapter<T> implements Adapter<T>, Provider<AdapterFactory<?>> {
	private final ArrayAdapter<T> myself = this;
	private Type adaptedType;

	/**
	 * {@inheritDoc}
	 */
	@Override
	@SuppressWarnings("unchecked")
	public T adapt(String value) {
		Adapter<T> a = TypeAdapter.getAdapter(getType());
		Object ret = Utils.convertToArray(value, a, (Class<?>) getType());
		return (T) ret;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	@SuppressWarnings("unchecked")
	public Map<Class<?>, AdapterFactory<?>> get() {
		HashMap<Class<?>, AdapterFactory<?>> ret = new HashMap<>();
		ret.put(Array.class, new AdapterFactory<T>() {
			@Override
			public Adapter<T> create(Type type) {
				if (type instanceof Class)
					adaptedType = ((Class<?>) type).getComponentType();
				else
					adaptedType = ((GenericArrayType) type).getGenericComponentType();
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
}
