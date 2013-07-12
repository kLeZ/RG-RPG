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
package it.d4nguard.rgrpg.util.dynacast.adapters;

import it.d4nguard.rgrpg.util.StringUtils;
import it.d4nguard.rgrpg.util.dynacast.Adapter;
import it.d4nguard.rgrpg.util.dynacast.Provider;
import it.d4nguard.rgrpg.util.dynacast.TypeAdapter;
import it.d4nguard.rgrpg.util.dynacast.factories.AdapterFactory;

import java.lang.reflect.Array;
import java.lang.reflect.GenericArrayType;
import java.lang.reflect.Type;
import java.util.HashMap;
import java.util.Map;
import java.util.StringTokenizer;

/**
 * Implements an {@link Adapter} and a {@link Provider} of
 * {@link AdapterFactory} for Arrays conversion.<br>
 * A string representation of an array, to be understood by this adapter class,
 * must be in the form of:<br>
 * <code>[element1|element2|...|elementN]</code><br>
 * The separator is defined in the {@link Adapter#ARRAY_JOINER} constant.
 * 
 * @author kLeZ-hAcK
 * @param <T>
 *            The component type of the array to adapt.
 */
public class ArrayAdapter<T> implements Adapter<T>, Provider<AdapterFactory<?>>
{
	private final ArrayAdapter<T> myself = this;
	private Type adaptedType;

	/**
	 * {@inheritDoc}
	 */
	@Override
	@SuppressWarnings("unchecked")
	public T adapt(String value)
	{
		Adapter<T> a = TypeAdapter.getAdapter(getType());
		String str = StringUtils.getBetween(value, '[', ']').getCenter().trim();
		StringTokenizer st = new StringTokenizer(str, ARRAY_JOINER);
		Object ret = Array.newInstance((Class<?>) getType(), st.countTokens());
		for (int i = 0; st.hasMoreTokens(); i++)
			Array.set(ret, i, a.adapt(st.nextToken().trim()));
		return (T) ret;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	@SuppressWarnings("unchecked")
	public Map<Class<?>, AdapterFactory<?>> get()
	{
		HashMap<Class<?>, AdapterFactory<?>> ret = new HashMap<>();
		ret.put(Array.class, new AdapterFactory<T>()
		{
			@Override
			public Adapter<T> create(Type type)
			{
				if (type instanceof Class) adaptedType = ((Class<?>) type).getComponentType();
				else adaptedType = ((GenericArrayType) type).getGenericComponentType();
				return myself;
			}
		});
		return ret;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public Type getType()
	{
		return adaptedType;
	}
}
