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

public class Utils
{
	public static void swap(int op1, int op2)
	{
		int tmp = op2;
		op2 = op1;
		op1 = tmp;
	}

	public static boolean isInteger(String s)
	{
		boolean ret = true;
		try
		{
			Integer.valueOf(s);
		}
		catch (NumberFormatException e)
		{
			ret = false;
		}
		return ret;
	}

	public static ArrayList<String> splitEncolosed(String s, char open_tag,
					char close_tag)
	{
		ArrayList<String> ret = new ArrayList<String>();
		char[] chars = s.toCharArray();
		String contents = "";
		for (char c : chars)
		{
			if (c == open_tag)
			{
				if (!contents.isEmpty())
				{
					ret.add(contents);
				}
				contents = "";
			}
			else if (c == close_tag)
			{
				ret.add(contents);
				contents = "";
			}
			else
			{
				contents += c;
			}
		}
		if (!contents.isEmpty())
		{
			ret.add(contents);
			contents = "";
		}
		return ret;
	}

	public static String[] replace(String target, String replacement,
					String... args)
	{
		ArrayList<String> ret = new ArrayList<String>();
		for (String s : args)
			ret.add(s.replace(target, replacement));
		return ret.toArray(new String[] {});
	}
}
