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

import it.d4nguard.rgrpg.util.dynacast.Adapter;
import it.d4nguard.rgrpg.util.dynacast.Provider;

import java.util.HashMap;
import java.util.Map;

public class PrimitivesAdapterProvider implements Provider<AdapterFactory<?>>
{
	@Override
	@SuppressWarnings("unchecked")
	public Map<Class<?>, AdapterFactory<?>> get()
	{
		Map<Class<?>, AdapterFactory<?>> ret = new HashMap<Class<?>, AdapterFactory<?>>();
		ret.put(Boolean.class, new AdapterFactory<Boolean>()
		{
			@Override
			public Adapter<Boolean> create(final Class<Boolean> type)
			{
				return new Adapter<Boolean>()
				{
					@Override
					public Boolean adapt(String value)
					{
						return Boolean.valueOf(value);
					}
				};
			}
		});
		ret.put(Byte.class, new AdapterFactory<Byte>()
		{
			@Override
			public Adapter<Byte> create(Class<Byte> type)
			{
				return new Adapter<Byte>()
				{
					@Override
					public Byte adapt(String value)
					{
						return Byte.valueOf(value, 2);
					}
				};
			}
		});
		ret.put(Character.class, new AdapterFactory<Character>()
		{
			@Override
			public Adapter<Character> create(Class<Character> type)
			{
				return new Adapter<Character>()
				{
					@Override
					public Character adapt(String value)
					{
						return value.isEmpty() ? '\0' : value.charAt(0);
					}
				};
			}
		});
		ret.put(Double.class, new AdapterFactory<Double>()
		{
			@Override
			public Adapter<Double> create(Class<Double> type)
			{
				return new Adapter<Double>()
				{
					@Override
					public Double adapt(String value)
					{
						return Double.valueOf(value);
					}
				};
			}
		});
		ret.put(Float.class, new AdapterFactory<Float>()
		{
			@Override
			public Adapter<Float> create(Class<Float> type)
			{
				return new Adapter<Float>()
				{
					@Override
					public Float adapt(String value)
					{
						return Float.valueOf(value);
					}
				};
			}
		});
		ret.put(Integer.class, new AdapterFactory<Integer>()
		{
			@Override
			public Adapter<Integer> create(Class<Integer> type)
			{
				return new Adapter<Integer>()
				{
					@Override
					public Integer adapt(String value)
					{
						return Integer.valueOf(value);
					}
				};
			}
		});
		ret.put(Long.class, new AdapterFactory<Long>()
		{
			@Override
			public Adapter<Long> create(Class<Long> type)
			{
				return new Adapter<Long>()
				{
					@Override
					public Long adapt(String value)
					{
						return Long.valueOf(value);
					}
				};
			}
		});
		ret.put(Short.class, new AdapterFactory<Short>()
		{
			@Override
			public Adapter<Short> create(Class<Short> type)
			{
				return new Adapter<Short>()
				{
					@Override
					public Short adapt(String value)
					{
						return Short.valueOf(value);
					}
				};
			}
		});
		ret.put(String.class, new AdapterFactory<String>()
		{
			@Override
			public Adapter<String> create(Class<String> type)
			{
				return new Adapter<String>()
				{
					@Override
					public String adapt(String value)
					{
						return value;
					}
				};
			}
		});
		return ret;
	}
}
