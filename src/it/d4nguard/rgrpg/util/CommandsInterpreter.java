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

import it.d4nguard.rgrpg.Context;
import it.d4nguard.rgrpg.ExitRuntimeException;
import it.d4nguard.rgrpg.commands.Command;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.PrintStream;
import java.util.Arrays;

public class CommandsInterpreter implements Runnable
{
	private InputStream in;
	private PrintStream out;
	private PrintStream err;

	public CommandsInterpreter(InputStream in, PrintStream out, PrintStream err)
	{
		this.in = in;
		this.out = out;
		this.err = err;
	}

	@Override
	public void run()
	{
		Context.mapStreams(in, out, err);
		try
		{
			boolean exit = false;
			BufferedReader reader = new BufferedReader(
							new InputStreamReader(in));
			while (!exit)
			{
				out.print(new PromptFeeder().get());
				String cmdLn = reader.readLine();
				if (cmdLn != null && !cmdLn.isEmpty())
				{
					CommandLine cmd = StringUtils.getArgs(cmdLn);
					cmdLn = cmd.getProc();
					String[] args = cmd.getArgs();
					try
					{
						if (Context.isDebug()) out.println(String.format(
										"Command: %s%nArgs: %s", cmdLn,
										Arrays.toString(args)));
						CommandsInterpreter.resolveCommand(cmdLn).execute(args);
					}
					catch (ClassNotFoundException e)
					{
						err.println(Context.getString("commandsinterpreter.warn.commandnotfound"));
					}
					catch (ExitRuntimeException e)
					{
						out.println(Context.getString("exit.msg"));
						exit = true;
					}
					catch (Throwable e)
					{
						err.println(e.getLocalizedMessage());
					}
				}
			}
		}
		catch (IOException e)
		{
			e.printStackTrace();
		}
	}

	public static Command newCommand(Class<?> clazz)
	{
		Command cmd = null;
		try
		{
			cmd = (Command) clazz.newInstance();
		}
		catch (InstantiationException e)
		{
			e.printStackTrace();
		}
		catch (IllegalAccessException e)
		{
			e.printStackTrace();
		}
		return cmd;
	}

	public static Command resolveCommand(String cmdLn)
					throws ClassNotFoundException
	{
		Command cmd = null;
		String className = String.format("%s.%sCommand",
						Command.class.getPackage().getName(),
						StringUtils.capitalize(cmdLn));
		try
		{
			Class<?> clazz = Class.forName(className);
			cmd = newCommand(clazz);
		}
		catch (SecurityException e)
		{
			e.printStackTrace();
		}
		return cmd;
	}
}
