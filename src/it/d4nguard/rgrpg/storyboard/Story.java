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
package it.d4nguard.rgrpg.storyboard;

import it.d4nguard.rgrpg.profile.RPGCharacter;
import it.d4nguard.rgrpg.util.StringUtils;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

public class Story implements Serializable
{
	private static final long serialVersionUID = 7740473809178561351L;

	private final Set<RPGCharacter> npcs;
	private final Set<Chapter> chapters;
	private World world;

	public Story()
	{
		npcs = new HashSet<RPGCharacter>();
		chapters = new HashSet<Chapter>();
	}

	public World getWorld()
	{
		return world;
	}

	public void setWorld(World world)
	{
		this.world = world;
	}

	public Set<RPGCharacter> getNpcs()
	{
		return npcs;
	}

	public Set<Chapter> getChapters()
	{
		return chapters;
	}

	@Override
	public String toString()
	{
		return StringUtils.genericToString(getClass(), this);
	}
}
