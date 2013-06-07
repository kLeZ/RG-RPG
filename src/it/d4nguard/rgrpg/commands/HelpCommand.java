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

import it.d4nguard.rgrpg.Context;
import it.d4nguard.rgrpg.util.CommandsInterpreter;
import it.d4nguard.rgrpg.util.StringUtils;

import java.util.Set;

import org.reflections.Reflections;

public class HelpCommand implements Command
{
	@Override
	public void execute(String... args)
	{
		if (args.length == 0)
		{
			System.out.println();
			System.out.println(Context.getString("help.listavailable"));
			Reflections reflections = Context.getReflections();
			Set<Class<? extends Command>> commands = reflections.getSubTypesOf(Command.class);
			for (Class<? extends Command> cmd : commands)
			{
				System.out.println(String.format(
								"%s: %s",
								StringUtils.decapitalize(cmd.getSimpleName().replace(
												"Command", "")),
								CommandsInterpreter.newCommand(cmd).getDescription()));
			}
			System.out.println();
		}
		else
		{
			try
			{
				System.out.println(CommandsInterpreter.resolveCommand(args[0]).getHelp());
				System.out.println();
			}
			catch (ClassNotFoundException e)
			{
				System.out.println(String.format(
								Context.getString("help.err.nohelp"), args[0]));
			}
		}
	}

	@Override
	public String getHelp()
	{
		return Context.getString("help.help");
	}

	@Override
	public String getDescription()
	{
		return Context.getString("help.description");
	}
}
