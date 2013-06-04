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
package it.d4nguard.rgrpg.profile;

import it.d4nguard.rgrpg.Context;
import it.d4nguard.rgrpg.d20.D20Character;

public abstract class RPGCharacter
{
	private final Player owner;
	private final GeneralInfo info;

	public RPGCharacter(Player owner, GeneralInfo info)
	{
		this.owner = owner;
		this.info = info;
	}

	public Player getOwner()
	{
		return owner;
	}

	public GeneralInfo getInfo()
	{
		return info;
	}

	@Override
	public int hashCode()
	{
		return info.getName().hashCode();
	}

	@Override
	public boolean equals(Object obj)
	{
		if (obj == null) return false;
		if (!(obj instanceof RPGCharacter)) return false;
		return info.getName().equals(((RPGCharacter) obj).getInfo().getName());
	}

	@Override
	public String toString()
	{
		StringBuilder builder = new StringBuilder();
		builder.append("RPGCharacter [info=");
		builder.append(info);
		builder.append("]");
		return builder.toString();
	}

	public static RPGCharacter build(String name, Object... args)
	{
		RPGCharacter ret = null;
		Player current = Context.getCurrentPlayer();
		GeneralInfo gi = new GeneralInfo();
		gi.setName(name);
		String charType = String.valueOf(args[0]);

		switch (charType)
		{
			case "d20":
			{
				ret = new D20Character(current, gi);
				break;
			}
			default:
				break;
		}
		return ret;
	}
}
