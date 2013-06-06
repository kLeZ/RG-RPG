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
import it.d4nguard.rgrpg.managers.CharacterManager;
import it.d4nguard.rgrpg.managers.PlayerManager;
import it.d4nguard.rgrpg.util.CommandLine;
import it.d4nguard.rgrpg.util.StringUtils;

import java.util.StringTokenizer;

import ognl.Ognl;
import ognl.OgnlException;

public class SetCommand implements Command
{
	public static void qotprn(Object arg)
	{
		System.out.println(String.format("'%s'", arg));
	}

	@Override
	public void execute(String... args)
	{
		CommandLine cmd = StringUtils.getArgs(args);
		String str = StringUtils.join(" ", cmd.getArgs());
		int start = str.indexOf('"'), end = str.lastIndexOf('"');
		String name = StringUtils.join(" ", cmd.getArgs()).substring(0, start).trim();
		String tokenizerFeed = str.substring(start + 1, end);
		if (Context.isDebug())
		{
			qotprn(cmd);
			qotprn(str);
			qotprn(String.format("%d:%d", start, end));
			qotprn(name);
			qotprn(tokenizerFeed);
		}
		StringTokenizer st = new StringTokenizer(tokenizerFeed, "=", false);
		if (Context.isDebug()) System.out.println(st.countTokens());
		String exp = st.nextToken();
		Object val = st.nextToken();
		if (Context.isDebug())
		{
			qotprn(exp);
			qotprn(val);
		}
		try
		{
			Object root = null;
			switch (cmd.getProc())
			{
				case "player":
				{
					CommandLine descCmd = StringUtils.getArgs(cmd.getArgs());
					if (descCmd.getProc().equals("availables"))
					{
						System.out.println(new PlayerManager().availables());
					}
					else root = new PlayerManager().get(name);
					break;
				}
				case "character":
				{
					CommandLine descCmd = StringUtils.getArgs(cmd.getArgs());
					if (descCmd.getProc().equals("availables"))
					{
						System.out.println(new CharacterManager().availables());
					}
					else root = new CharacterManager().get(name);
					break;
				}
			}
			Ognl.setValue(exp, root, val);
		}
		catch (OgnlException e)
		{
			e.printStackTrace();
		}
	}

	@Override
	public String getHelp()
	{
		return Context.getString("set.help");
	}

	@Override
	public String getDescription()
	{
		return Context.getString("set.description");
	}
}
