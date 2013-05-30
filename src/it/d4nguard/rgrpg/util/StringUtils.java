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

import java.util.Arrays;
import java.util.Scanner;

public class StringUtils
{
	public static String decapitalize(String s)
	{
		StringCompiler sb = new StringCompiler();
		Scanner scn = new Scanner(s);
		scn.useDelimiter("\\s");
		while (scn.hasNext())
		{
			String curr = scn.next();
			sb.append(curr.substring(0, 1).toLowerCase().concat(
							curr.substring(1)));
		}
		scn.close();
		return sb.toString();
	}

	public static String capitalize(String s)
	{
		StringCompiler sb = new StringCompiler();
		Scanner scn = new Scanner(s);
		scn.useDelimiter("\\s");
		while (scn.hasNext())
		{
			String curr = scn.next();
			sb.append(curr.substring(0, 1).toUpperCase().concat(
							curr.substring(1)));
		}
		scn.close();
		return sb.toString();
	}

	public static CommandLine getArgs(String cmdLn)
	{
		CommandLine ret = new CommandLine();
		String[] args = cmdLn.split("\\s");
		// System.out.println(String.format("Command: %s%nArgs: %s%n#Args: %d", cmdLn, Arrays.toString(args), args.length));
		cmdLn = args[0];
		if (args.length > 1) args = Arrays.<String> copyOfRange(args, 1,
						args.length);
		else args = new String[] {};
		ret.setProc(cmdLn);
		ret.setArgs(args);
		return ret;
	}

	public static String join(String sep, String... args)
	{
		StringCompiler sb = new StringCompiler();
		for (int i = 0; i < args.length; i++)
		{
			sb.append(args[i]);
			if (i + 1 < args.length) sb.append(sep);
		}
		return sb.toString();
	}
}
