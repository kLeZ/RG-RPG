/*
 * Copyright (C) 2021 Alessandro 'kLeZ' Accardo
 *
 * This file is part of RG-RPG.
 *
 * RG-RPG is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * RG-RPG is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with RG-RPG.  If not, see <http://www.gnu.org/licenses/>.
 *
 */
package it.d4nguard.rgrpg.commands;

import it.d4nguard.rgrpg.Context;
import it.d4nguard.rgrpg.util.CommandsInterpreter;
import it.d4nguard.rgrpg.util.StringUtils;
import org.reflections.Reflections;

import java.util.Set;

public class HelpCommand implements Command {
	@Override
	public void execute(String... args) {
		if (args.length == 0) {
			Context.println();
			Context.println(Context.getString("help.listavailable"));
			Reflections reflections = Context.getReflections();
			Set<Class<? extends Command>> commands = reflections.getSubTypesOf(Command.class);
			for (Class<? extends Command> cmd : commands) {
				Context.println(String.format("%s: %s", StringUtils.decapitalize(cmd.getSimpleName()
						.replace("Command", "")), CommandsInterpreter.newCommand(cmd)
						.getDescription()));
			}
			Context.println();
		} else {
			try {
				Context.println(CommandsInterpreter.resolveCommand(args[0])
						.getHelp());
				Context.println();
			} catch (ClassNotFoundException e) {
				Context.println(String.format(Context.getString("help.err.nohelp"), args[0]));
			}
		}
	}

	@Override
	public String getHelp() {
		return Context.getString("help.help");
	}

	@Override
	public String getDescription() {
		return Context.getString("help.description");
	}
}
