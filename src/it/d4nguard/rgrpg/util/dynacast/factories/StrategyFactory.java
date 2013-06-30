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

import it.d4nguard.rgrpg.util.Utils;
import it.d4nguard.rgrpg.util.dynacast.Strategy;

import java.lang.reflect.Modifier;
import java.lang.reflect.Type;
import java.util.Iterator;
import java.util.Set;

public class StrategyFactory
{
	public static Strategy getStrategy(final Type type)
	{
		Strategy ret = null;
		Set<Class<? extends Strategy>> subTypes = Utils.getSubTypesOf(Strategy.class);
		Iterator<Class<? extends Strategy>> it = subTypes.iterator();
		while (it.hasNext() && ret == null)
		{
			Class<? extends Strategy> e = it.next();
			if (!Modifier.isAbstract(e.getModifiers()))
			{
				try
				{
					Strategy s = e.newInstance();
					if (s.isMine(type)) ret = s;
				}
				catch (InstantiationException | IllegalAccessException ex)
				{
					ex.printStackTrace();
				}
			}
		}

		if (ret != null)
		{
			return ret;
		}
		else return new Strategy() // The dummy one
		{
			@Override
			public boolean isMine(Type type)
			{
				return false;
			}

			@Override
			public Type apply(Type type)
			{
				return type;
			}

			@Override
			public Class<?> getMine(Type type)
			{
				return Object.class;
			}
		};
	}
}
