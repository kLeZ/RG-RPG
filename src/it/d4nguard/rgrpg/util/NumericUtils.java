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

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;

public class NumericUtils
{
	public static int sum(int a, int b)
	{
		return sum(new int[] { a, b });
	}

	public static int sum(final Collection<Integer> ints)
	{
		return sum(0, ints);
	}

	public static int sum(final int[] ints)
	{
		return sum(0, ints);
	}

	public static int sum(final int base, final Collection<Integer> ints)
	{
		int ret = base;
		for (Integer i : ints)
			ret += i;
		return ret;
	}

	public static int sum(final int base, final int[] ints)
	{
		return sum(base, toIntegerCollection(ints));
	}

	public static Collection<Integer> toIntegerCollection(int[] ints)
	{
		ArrayList<Integer> ret = new ArrayList<Integer>();
		for (int i : ints)
			ret.add(new Integer(i));
		return ret;
	}

	public static int min(int... ints)
	{
		return Collections.min(toIntegerCollection(ints));
	}
}
