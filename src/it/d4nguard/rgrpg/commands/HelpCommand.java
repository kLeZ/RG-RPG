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

import java.util.Set;

import it.d4nguard.rgrpg.commands.Command;

import org.reflections.Reflections;

public class HelpCommand implements Command
{
    public HelpCommand()
    {
    }

    @Override
    public void execute(String... args)
    {
	if (args.length == 0)
	{
	    System.out.println();
	    System.out.println("Listing available commands:");
	    Reflections reflections = new Reflections("it.d4nguard.rgrpg.commands");
	    Set<Class<? extends Command>> commands = reflections.getSubTypesOf(Command.class);
	    for (Class<? extends Command> cmd : commands)
	    {
		String cmdLn = cmd.getSimpleName().replace("Command", "");
		try
		{
		    System.out.println(String.format("%s: %s", cmdLn, CommandsInterpreter.resolveCommand(cmdLn).getHelp()));
		}
		catch (ClassNotFoundException e)
		{
		    System.err.println(String.format("There was an error reading '%s' command: '%s'", cmdLn, e.getMessage()));
		}
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
		System.out.println(String.format("There is no help for specified command '%s'", args[0]));
	    }
	}
    }

    @Override
    public String getHelp()
    {
	return "Prints the help of the passed command or lists all available commands.";
    }
}
