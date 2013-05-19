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

import java.util.Set;
import java.util.HashSet;

public class Player
{
	public static final String LF = System.getProperty("line.separator");

	private String name;
	private float experience;
	private int level;
	private int tacticPoints;
	private int dialecticPoints;
	private int interpretativePoints;
	private int masteredParametersPoints;
	private final Set<Character> characters;

	public Player()
	{
		this.characters = new HashSet<Character>();
	}

	public String getName()
	{
		return name;
	}

	public void setName(String name)
	{
		this.name = name;
	}

	public float getExperience()
	{
		return experience;
	}

	public void setExperience(float experience)
	{
		this.experience = experience;
	}

	public int getLevel()
	{
		return level;
	}

	public void setLevel(int level)
	{
		this.level = level;
	}

	public int getTacticPoints()
	{
		return tacticPoints;
	}

	public void setTacticPoints(int tacticPoints)
	{
		this.tacticPoints = tacticPoints;
	}

	public int getDialecticPoints()
	{
		return dialecticPoints;
	}

	public void setDialecticPoints(int dialecticPoints)
	{
		this.dialecticPoints = dialecticPoints;
	}

	public int getInterpretativePoints()
	{
		return interpretativePoints;
	}

	public void setInterpretativePoints(int interpretativePoints)
	{
		this.interpretativePoints = interpretativePoints;
	}

	public int getMasteredParametersPoints()
	{
		return masteredParametersPoints;
	}

	public void setMasteredParametersPoints(int masteredParametersPoints)
	{
		this.masteredParametersPoints = masteredParametersPoints;
	}

	public Set<Character> getCharacters()
	{
		return characters;
	}

	@Override
	public boolean equals(Object o)
	{
		if (o == null) return false;
		if (!(o instanceof Player)) return false;
		return ((Player) o).getName().equals(this.name);
	}

	@Override
	public String toString()
	{
		StringBuilder sb = new StringBuilder();
		sb.append("PLAYER").append(LF);
		sb.append("======").append(LF);
		sb.append("Name: ").append(this.name).append(LF);
		sb.append("Level: ").append(this.level).append(LF);
		sb.append("Experience: ").append(this.experience).append(LF);
		sb.append("Tactic Points: ").append(this.tacticPoints).append(LF);
		sb.append("Dialectic Points: ").append(this.dialecticPoints).append(LF);
		sb.append("Interpretative Points: ").append(this.interpretativePoints).append(LF);
		sb.append("Mastered Parameters Points: ").append(this.masteredParametersPoints).append(LF);
		sb.append("= CHARACTERS").append(LF);
		sb.append("------------").append(LF);
		for (Character c : this.characters)
		{
			sb.append(c).append(LF);
		}
		sb.append("------------").append(LF);
		return sb.toString();
	}
}
