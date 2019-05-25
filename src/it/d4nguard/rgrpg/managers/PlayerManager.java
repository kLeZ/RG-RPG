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
package it.d4nguard.rgrpg.managers;

import it.d4nguard.rgrpg.Context;
import it.d4nguard.rgrpg.Main;
import it.d4nguard.rgrpg.profile.Player;
import it.d4nguard.rgrpg.util.StringUtils;

import java.util.Iterator;

public class PlayerManager implements Manager<Player> {
	@Override
	public Player create(String name, Object... args) {
		Player p = new Player(name);
		return Context.getPlayers().add(p) ? p : null;
	}

	@Override
	public boolean delete(String name) {
		return Context.getPlayers().remove(get(name));
	}

	@Override
	public Player rename(String name, String newName) {
		Player p = get(name);
		Context.getPlayers().remove(p);
		p = p.copyRename(newName);
		Context.getPlayers().add(p);
		return p;
	}

	@Override
	public Player use(String name) {
		Player p = get(name);
		Context.setCurrentPlayer(p);
		return p;
	}

	@Override
	public Player current() {
		return Context.getCurrentPlayer();
	}

	@Override
	public Player get(String name) {
		Iterator<Player> it = Context.getPlayers().iterator();
		while (it.hasNext()) {
			Player curr = it.next();
			if (curr.getName().equals(name))
				return curr;
		}
		return null;
	}

	@Override
	public String availables() {
		return StringUtils.prettyPrint(Main.class.getPackage().getName(), Player.class);
	}
}
