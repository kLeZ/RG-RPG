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
package it.d4nguard.rgrpg.managers;

import it.d4nguard.rgrpg.Context;
import it.d4nguard.rgrpg.profile.Player;

import java.util.Iterator;

public class PlayerManager implements Manager<Player>
{
	@Override
	public Player create(String name, Object... args)
	{
		Player p = new Player(name);
		if (Context.getPlayers().add(p)) System.out.println(p);
		else System.out.println(String.format(
						Context.getString("new.err.player.exists"), name));
		return p;
	}

	@Override
	public boolean delete(String name)
	{
		return Context.getPlayers().remove(get(name));
	}

	@Override
	public Player rename(String name, String newName)
	{
		Player p = get(name);
		Context.getPlayers().remove(p);
		p = p.copyRename(newName);
		Context.getPlayers().add(p);
		return p;
	}

	@Override
	public Player use(String name)
	{
		Player p = get(name);
		Context.setCurrentPlayer(p);
		return p;
	}

	@Override
	public Player current()
	{
		return Context.getCurrentPlayer();
	}

	@Override
	public Player get(String name)
	{
		Iterator<Player> it = Context.getPlayers().iterator();
		while (it.hasNext())
		{
			Player curr = it.next();
			if (curr.getName().equals(name)) return curr;
		}
		return null;
	}
}
