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

import it.d4nguard.rgrpg.util.Delegate;
import it.d4nguard.rgrpg.util.Utils;
import it.d4nguard.rgrpg.util.dynacast.Strategy;

import java.lang.reflect.Modifier;
import java.util.LinkedList;
import java.util.Set;

import org.reflections.Reflections;
import org.reflections.scanners.SubTypesScanner;

public class StrategyFactory
{
	public static Strategy getStrategy(final Class<?> type)
	{
		final LinkedList<Strategy> ret = new LinkedList<Strategy>();
		String pkg = StrategyFactory.class.getPackage().getName().split("\\.")[0];
		Reflections r = new Reflections(pkg, new SubTypesScanner(false));
		Set<Class<? extends Strategy>> subTypes = r.getSubTypesOf(Strategy.class);
		Utils.doAll(subTypes, new Delegate<Class<? extends Strategy>>()
		{
			@Override
			public void execute(Class<? extends Strategy> t)
			{
				if (!Modifier.isAbstract(t.getModifiers()))
				{
					try
					{
						Strategy s = t.newInstance();
						if (s.isMine(type)) ret.add(s);
					}
					catch (InstantiationException | IllegalAccessException e)
					{
						e.printStackTrace();
					}
				}
			}
		});
		if (!ret.isEmpty()) return ret.getFirst();
		else return new Strategy() // The dummy one
		{
			@Override
			public boolean isMine(Class<?> type)
			{
				return false;
			}

			@Override
			public Class<?> apply(Class<?> type)
			{
				return type;
			}

			@Override
			public Class<?> getMine(Class<?> type)
			{
				return Object.class;
			}
		};
	}
}
