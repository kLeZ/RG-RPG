/*
 * Copyright (C) 2020 Alessandro 'kLeZ' Accardo
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
import it.d4nguard.rgrpg.util.CommandLine;
import it.d4nguard.rgrpg.util.CommandsInterpreter;
import it.d4nguard.rgrpg.util.StringUtils;
import it.d4nguard.rgrpg.util.Utils;

import java.util.Arrays;

public class ForCommand implements Command {
	private static final String INDEX_TOKEN = "$i";

	@Override
	public void execute(String... args) {
		int min = Integer.parseInt(args[0]), max = Integer.parseInt(args[1]);
		CommandLine cmd = StringUtils.getArgs(Arrays.copyOfRange(args, 2, args.length));
		try {
			for (int i = min; i < max; i++) {
				String[] a = Utils.replace(INDEX_TOKEN, String.valueOf(i), cmd.getArgs());
				CommandsInterpreter.resolveCommand(cmd.getProc())
						.execute(a);
			}
		} catch (ClassNotFoundException e) {
			Context.printThrowable(e);
		}
	}

	@Override
	public String getHelp() {
		return String.format(Context.getString("for.help"), INDEX_TOKEN);
	}

	@Override
	public String getDescription() {
		return Context.getString("for.description");
	}
}
