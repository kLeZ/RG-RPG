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
import it.d4nguard.rgrpg.managers.CharacterManager;
import it.d4nguard.rgrpg.managers.PlayerManager;
import it.d4nguard.rgrpg.profile.Player;
import it.d4nguard.rgrpg.util.CommandLine;
import it.d4nguard.rgrpg.util.StringUtils;

public class NewCommand implements Command {
	@Override
	public void execute(String... args) {
		CommandLine cmd = StringUtils.getArgs(args);
		String name;
		switch (cmd.getProc()) {
			case "player": {
				name = StringUtils.join(" ", cmd.getArgs());
				Player p = new PlayerManager().create(name);
				if (p != null)
					Context.println(p);
				else
					Context.println(String.format(Context.getString("new.err.player.exists"), name));

				break;
			}
			case "character": {
				CommandLine cmd_c = StringUtils.getArgs(cmd.getArgs());
				name = StringUtils.join(" ", cmd_c.getArgs());
				Context.println(new CharacterManager().create(name, cmd_c.getProc()));
				break;
			}
		}
	}

	@Override
	public String getHelp() {
		return Context.getString("new.help");
	}

	@Override
	public String getDescription() {
		return Context.getString("new.description");
	}
}
