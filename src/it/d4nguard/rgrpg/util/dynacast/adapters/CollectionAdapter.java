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
import java.util.Collection;
import java.util.List;
import java.util.Queue;
import java.util.Set;

public class CollectionAdapter<T> extends AbstractAdapter<Collection<T>>
{
	private Class<T> type;

	@Override
	public Collection<T> adapt(String value)
	{
		Collection<T> ret = null;
		Adapter<T> a = TypeAdapter.getAdapter(type);
		String str = StringUtils.getBetween(value, '[', ']').getCenter().trim();
		String[] split = str.split(",");
		Object o = Array.newInstance(getType(), split.length);
		for (int i = 0; i < split.length; i++)
			Array.set(o, i, a.adapt(split[i].trim()));

		int mod = getType().getModifiers();
		if (Modifier.isInterface(mod))
		{
			if (getType().equals(List.class))
			{
			}
			else if (getType().equals(Set.class))
			{

			}
			else if (getType().equals(Queue.class))
			{

			}
		}
		return ret;
	}

	@Override
	@SuppressWarnings("unchecked")
	public void beforeCreateAdapter(Class<Collection<T>> type)
	{
		this.type = (Class<T>) GenericsUtils.getFirstGenericType(type);
	}
}
