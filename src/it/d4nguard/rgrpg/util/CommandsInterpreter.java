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
package it.d4nguard.rgrpg.util;

import it.d4nguard.rgrpg.Context;
import it.d4nguard.rgrpg.ExitRuntimeException;
import it.d4nguard.rgrpg.commands.Command;

import java.lang.reflect.InvocationTargetException;
import java.util.Arrays;

public class CommandsInterpreter implements Runnable {
	public static Command newCommand(Class<?> clazz) {
		Command cmd = null;
		try {
			cmd = (Command) clazz.getDeclaredConstructor()
					.newInstance();
		} catch (InstantiationException | IllegalAccessException | NoSuchMethodException | InvocationTargetException e) {
			Context.printThrowable(e);
		}
		return cmd;
	}

	public static Command resolveCommand(String cmdLn) throws ClassNotFoundException {
		Command cmd = null;
		String className = String.format("%s.%sCommand", Command.class.getPackage()
				.getName(), StringUtils.capitalize(cmdLn));
		try {
			Class<?> clazz = Class.forName(className);
			cmd = newCommand(clazz);
		} catch (SecurityException e) {
			Context.printThrowable(e);
		}
		return cmd;
	}

	@Override
	public void run() {
		boolean exit = false;
		while (!exit) {
			Context.print(new PromptFeeder().get());
			String cmdLn = Context.readLine();
			if (cmdLn != null && !cmdLn.isEmpty()) {
				CommandLine cmd = StringUtils.getArgs(cmdLn);
				cmdLn = cmd.getProc();
				String[] args = cmd.getArgs();
				try {
					if (Context.isDebug())
						Context.println(String.format("Command: %s%nArgs: %s", cmdLn, Arrays.toString(args)));
					CommandsInterpreter.resolveCommand(cmdLn)
							.execute(args);
				} catch (ClassNotFoundException e) {
					Context.println(Context.getString("commandsinterpreter.warn.commandnotfound"));
				} catch (ExitRuntimeException e) {
					Context.println(Context.getString("exit.msg"));
					exit = true;
				} catch (Throwable e) {
					Context.printThrowable(e);
				}
			}
		}
	}
}
