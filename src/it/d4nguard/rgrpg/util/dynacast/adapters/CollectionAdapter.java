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

import it.d4nguard.rgrpg.util.GenericsUtils;
import it.d4nguard.rgrpg.util.StringUtils;
import it.d4nguard.rgrpg.util.dynacast.Adapter;
import it.d4nguard.rgrpg.util.dynacast.TypeAdapter;

import java.lang.reflect.Array;
import java.lang.reflect.Modifier;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.StringTokenizer;

public class CollectionAdapter<T> extends AbstractAdapter<Collection<T>>
{
	private Class<T> type;
	private Class<Collection<T>> collType;

	@Override
	@SuppressWarnings("unchecked")
	public Collection<T> adapt(String value)
	{

		Collection<T> ret = null;
		Adapter<T> a = TypeAdapter.getAdapter(type);
		String str = StringUtils.getBetween(value, '[', ']').getCenter().trim();
		StringTokenizer st = new StringTokenizer(str, ARRAY_JOINER);
		Object arr = Array.newInstance(collType, st.countTokens());
		for (int i = 0; st.hasMoreTokens(); i++)
			Array.set(arr, i, a.adapt(st.nextToken().trim()));

		int mod = collType.getModifiers();
		if (Modifier.isInterface(mod))
		{
			if (collType.equals(List.class)) ret = new ArrayList<T>();
			else if (collType.equals(Set.class)) ret = new HashSet<T>();
		}
		else if (!Modifier.isAbstract(mod))
		{
			try
			{
				ret = collType.newInstance();
			}
			catch (InstantiationException | IllegalAccessException e)
			{
				e.printStackTrace();
			}
		}
		Collections.addAll(ret, (T[]) arr);
		return ret;
	}

	@Override
	@SuppressWarnings("unchecked")
	public void beforeCreateAdapter(Type type)
	{
		this.collType = (Class<Collection<T>>) GenericsUtils.getClassFromType(type);
		this.type = (Class<T>) GenericsUtils.getFirstGenericType(GenericsUtils.getClassFromType(type));
	}
}
