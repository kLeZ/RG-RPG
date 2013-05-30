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
import it.d4nguard.rgrpg.profile.Player;
import it.d4nguard.rgrpg.util.CommandLine;
import it.d4nguard.rgrpg.util.StringCompiler;
import it.d4nguard.rgrpg.util.StringUtils;

public class NewCommand implements Command
{
	@Override
	public void execute(String... args)
	{
		CommandLine cmd = StringUtils.getArgs(StringUtils.join(" ", args));
		String name = StringUtils.join(" ", cmd.getArgs());
		switch (cmd.getProc())
		{
			case "player":
			{
				if (!Context.playerExists(name))
				{
					Player p = Context.newPlayer(name);
					System.out.println(p);
				}
				else System.out.println(String.format(
								"User '%s' already exists!", name));
				break;
			}
			case "character":
			{
				break;
			}
		}
	}

	@Override
	public String getHelp()
	{
		StringCompiler sc = new StringCompiler();
		sc.append("Creates a new player, with the specified name");
		return sc.toString();
	}
}
