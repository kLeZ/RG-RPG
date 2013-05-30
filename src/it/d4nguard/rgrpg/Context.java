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
package it.d4nguard.rgrpg;

import it.d4nguard.rgrpg.profile.Player;

import java.util.HashSet;
import java.util.Set;

public class Context
{
	private static enum Singleton
	{
		Current;

		private final Set<Player> players;
		private Player current;

		private Singleton()
		{
			this.players = new HashSet<Player>();
		}

		private Set<Player> getPlayers()
		{
			return players;
		}

		private Player getCurrent()
		{
			return current;
		}

		private void setCurrent(Player current)
		{
			this.current = current;
		}
	};

	public static Set<Player> getPlayers()
	{
		return Singleton.Current.getPlayers();
	}

	public static boolean playerExists(String name)
	{
		for (Player p : getPlayers())
			if (p.getName().equals(name)) return true;
		return false;
	}

	public static Player newPlayer(String name)
	{
		Player p = new Player(name);
		getPlayers().add(p);
		return p;
	}

	public static Player getCurrentPlayer()
	{
		return Singleton.Current.getCurrent();
	}

	public static void setCurrentPlayer(Player current)
	{
		Singleton.Current.setCurrent(current);
	}
}
