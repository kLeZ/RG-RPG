/*
 * Copyright (C) 2019 Alessandro 'kLeZ' Accardo
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
import it.d4nguard.rgrpg.managers.PlayerManager;
import it.d4nguard.rgrpg.profile.Character;
import it.d4nguard.rgrpg.profile.Player;
import it.d4nguard.rgrpg.util.CommandLine;
import it.d4nguard.rgrpg.util.StringUtils;

public class ListCommand implements Command {

	@Override
	public void execute(String... args) {
		CommandLine cmd = StringUtils.getArgs(args);
		switch (cmd.getProc()) {
			case "players": {
				for (Player p : Context.getPlayers())
					Context.println(p);
				break;
			}
			case "characters": {
				Player p = new PlayerManager().get(StringUtils.join(" ", cmd.getArgs()));
				for (Character c : p.getCharacters().keySet())
					Context.println(c);
				break;
			}
			default:
				Context.println(getHelp());
		}
	}

	@Override
	public String getHelp() {
		return Context.getString("list.help");
	}

	@Override
	public String getDescription() {
		return Context.getString("list.description");
	}
}
