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
package it.d4nguard.rgrpg.util;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.Collection;
import java.util.Iterator;

public class BooleanUtils
{
	public static boolean xnor(boolean x, boolean y)
	{
		return !xor(x, y);
	}

	public static boolean xor(boolean x, boolean y)
	{
		return (x || y) && !(x && y);
	}

	public static <T> boolean all(Collection<T> items, Method method,
					Object... args) throws IllegalAccessException,
					IllegalArgumentException, InvocationTargetException
	{
		boolean ret = true;
		for (T t : items)
		{
			ret &= ((Boolean) method.invoke(t, args)).booleanValue();
		}
		return ret;
	}

	public static <T> boolean any(Collection<T> items, Method method,
					Object... args) throws IllegalAccessException,
					IllegalArgumentException, InvocationTargetException
	{
		boolean ret = false;
		Iterator<T> it = items.iterator();
		while (it.hasNext() && ret)
		{
			T t = it.next();
			ret |= ((Boolean) method.invoke(t, args)).booleanValue();
		}
		return ret;
	}
}
