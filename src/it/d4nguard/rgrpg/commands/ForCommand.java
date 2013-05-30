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
package it.d4nguard.rgrpg.commands;

import it.d4nguard.rgrpg.util.CommandLine;
import it.d4nguard.rgrpg.util.CommandsInterpreter;
import it.d4nguard.rgrpg.util.StringCompiler;
import it.d4nguard.rgrpg.util.StringUtils;
import it.d4nguard.rgrpg.util.Utils;

import java.util.Arrays;

public class ForCommand implements Command
{
	private static final String INDEX_TOKEN = "$i";

	@Override
	public void execute(String... args)
	{
		int min = Integer.parseInt(args[0]), max = Integer.parseInt(args[1]);
		CommandLine cmd = StringUtils.getArgs(StringUtils.join(" ",
						Arrays.copyOfRange(args, 2, args.length)));
		try
		{
			for (int i = min; i < max; i++)
			{
				String[] a = Utils.replace(INDEX_TOKEN, String.valueOf(i),
								cmd.getArgs());
				CommandsInterpreter.resolveCommand(cmd.getProc()).execute(a);
			}
		}
		catch (ClassNotFoundException e)
		{
			e.printStackTrace();
		}
	}

	@Override
	public String getHelp()
	{
		StringCompiler sb = new StringCompiler();
		sb.appendln("Executes the specified command iteratively n times.");
		sb.appendln("\tSyntax: for [min] [max] command {args}");
		sb.appendln("\tYou can specify a '%s' as a parameter to use the index of the cycle.",
						INDEX_TOKEN);
		return sb.toString();
	}
}
