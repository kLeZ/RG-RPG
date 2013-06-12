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

import it.d4nguard.rgrpg.util.dynacast.Adapter;
import it.d4nguard.rgrpg.util.dynacast.Provider;
import it.d4nguard.rgrpg.util.dynacast.factories.AdapterFactory;

import java.util.HashMap;
import java.util.Map;

public class EnumAdapter<E extends Enum<?>> implements Adapter<E>, Provider<AdapterFactory<E>>
{
	private final Class<E> type;

	/**
	 * This constructor is used only for the Provider part of this concrete
	 * class.
	 * Please do not use if you need an adapter, because it will not work.
	 */
	public EnumAdapter()
	{
		this.type = null;
	}

	public EnumAdapter(Class<E> type)
	{
		this.type = type;
	}

	@SuppressWarnings({ "unchecked", "rawtypes" })
	@Override
	public E adapt(String value)
	{
		Class<? extends Enum> ce = (Class<? extends Enum>) type;
		return (E) Enum.valueOf(ce, value);
	}

	@Override
	@SuppressWarnings({ "rawtypes", "unchecked" })
	public Map<Class<Enum>, AdapterFactory<Enum<?>>> get()
	{
		HashMap<Class<Enum>, AdapterFactory<Enum<?>>> ret = new HashMap<>();
		ret.put(Enum.class, new AdapterFactory<Enum<?>>()
		{
			@Override
			public Adapter<Enum<?>> create(Class<Enum<?>> type)
			{
				return new EnumAdapter<>(type);
			}
		});
		return ret;
	}
}
