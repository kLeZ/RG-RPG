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
package it.d4nguard.rgrpg.util.dynacast;

import java.util.HashMap;
import java.util.Map;

public class TypeCaster
{
	private final Map<Class<?>, AdapterFactory> factories;

	public TypeCaster()
	{
		factories = new HashMap<Class<?>, AdapterFactory>();
	}

	private <T> AdapterFactory newFactory(final Class<T> type)
	{
		return new AdapterFactory()
		{
			@Override
			public <T> Adapter<T> create(Class<T> type)
			{
				return factories.get(type).create(type);
			}

			@Override
			public String toString()
			{
				return String.format("Adapter for %s", type.getSimpleName());
			}
		};
	}
}
