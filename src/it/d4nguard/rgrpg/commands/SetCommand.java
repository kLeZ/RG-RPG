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
	@Override
	public void execute(String... args)
	{
		CommandLine cmd = StringUtils.getArgs(args);
		String str = StringUtils.join(" ", cmd.getArgs());
		int start = str.indexOf('"'), end = str.lastIndexOf('"');
		String name = StringUtils.join(" ", cmd.getArgs()).substring(0, start);
		String exp = str.substring(start, end);
		try
		{
			Object root = null;
			switch (cmd.getProc())
			{
				case "player":
				{
					root = new PlayerManager().get(name);
					break;
				}
				case "character":
				{
					root = new CharacterManager().get(name);
					break;
				}
			}
			StringTokenizer st = new StringTokenizer("=", exp, false);
			Ognl.setValue(st.nextToken(), root, st.nextToken());
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
