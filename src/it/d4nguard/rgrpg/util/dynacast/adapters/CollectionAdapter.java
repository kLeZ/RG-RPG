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

import it.d4nguard.rgrpg.Context;
import it.d4nguard.rgrpg.util.GenericsUtils;
import it.d4nguard.rgrpg.util.Utils;
import it.d4nguard.rgrpg.util.dynacast.Adapter;
import it.d4nguard.rgrpg.util.dynacast.TypeAdapter;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Modifier;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.util.*;

/**
 * Implements an {@link AbstractAdapter} of {@link Collection} for Collections
 * conversion. Currently, it adapts {@link List}s and {@link Set}s.<br>
 * A string representation of a collection, to be understood by this adapter
 * class, must be in the form of:<br>
 * <code>[element1|element2|...|elementN]</code><br>
 *
 * @param <T>
 * 		The type of the objects handled by the {@link Collection}
 *
 * @author kLeZ
 */
public class CollectionAdapter<T> extends AbstractAdapter<Collection<T>> {
	private Class<T> type;
	private Class<Collection<T>> collType;

	/**
	 * {@inheritDoc}
	 */
	@Override
	@SuppressWarnings("unchecked")
	public Collection<T> adapt(String value) {
		Collection<T> ret = null;
		Adapter<T> a = TypeAdapter.getAdapter(type);
		Object arr = Utils.convertToArray(value, a, type);

		int mod = collType.getModifiers();
		if (Modifier.isInterface(mod)) {
			if (collType.equals(List.class))
				ret = new ArrayList<>();
			else if (collType.equals(Set.class))
				ret = new HashSet<>();
		} else if (!Modifier.isAbstract(mod)) {
			try {
				ret = collType.getDeclaredConstructor()
						.newInstance();
			} catch (InstantiationException | IllegalAccessException | NoSuchMethodException | InvocationTargetException e) {
				Context.printThrowable(e);
			}
		}
		assert ret != null;
		Collections.addAll(ret, (T[]) arr);
		return ret;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	@SuppressWarnings("unchecked")
	public void beforeCreateAdapter(Type type) {
		this.collType = (Class<Collection<T>>) GenericsUtils.getClassFromType(type);
		if (type instanceof ParameterizedType) {
			ParameterizedType pt = (ParameterizedType) type;
			this.type = (Class<T>) GenericsUtils.getFirstGenericType(pt);
		} else if (type instanceof Class<?>) {
			Class<?> cls = (Class<?>) type;
			this.type = (Class<T>) cls;
		}
	}
}
