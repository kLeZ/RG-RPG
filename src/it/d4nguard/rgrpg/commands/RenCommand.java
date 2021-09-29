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
import it.d4nguard.rgrpg.util.CommandLine;
import it.d4nguard.rgrpg.util.StringUtils;

import java.util.StringTokenizer;

public class RenCommand implements Command {
	@Override
	public void execute(String... args) {
		// ren player kLeZ :: kLeZ-hAcK
		CommandLine cmd = StringUtils.getArgs(args);
		String names = StringUtils.join(" ", cmd.getArgs());
		//noinspection StringTokenizerDelimiter
		StringTokenizer st = new StringTokenizer(names, "::", false);
		String name = st.nextToken();
		String newName = st.nextToken();
		switch (cmd.getProc()) {
			case "player" -> new PlayerManager().rename(name, newName);
			case "character" -> new CharacterManager().rename(name, newName);
		}
	}

	@Override
	public String getHelp() {
		return Context.getString("ren.help");
	}

	@Override
	public String getDescription() {
		return Context.getString("ren.description");
	}
}
