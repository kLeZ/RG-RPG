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

import org.joda.time.DateMidnight;

public class GeneralInfo
{
	private String description;
	private Race race;
	private String skinColor;
	private String hairColor;
	private String eyesColor;
	private short height;
	private short weight;
	private Size size;
	private DateMidnight dateOfBirth;
	private Gender gender;
	private Alignment alignment;
	private String deity;

	public GeneralInfo()
	{
	}

	public String getDescription()
	{
		return description;
	}

	public void setDescription(String description)
	{
		this.description = description;
	}

	public Race getRace()
	{
		return race;
	}

	public void setRace(Race race)
	{
		this.race = race;
	}

	public String getSkinColor()
	{
		return skinColor;
	}

	public void setSkinColor(String skinColor)
	{
		this.skinColor = skinColor;
	}

	public String getHairColor()
	{
		return hairColor;
	}

	public void setHairColor(String hairColor)
	{
		this.hairColor = hairColor;
	}

	public String getEyesColor()
	{
		return eyesColor;
	}

	public void setEyesColor(String eyesColor)
	{
		this.eyesColor = eyesColor;
	}

	public short getHeight()
	{
		return height;
	}

	public void setHeight(short height)
	{
		this.height = height;
	}

	public short getWeight()
	{
		return weight;
	}

	public void setWeight(short weight)
	{
		this.weight = weight;
	}

	public Size getSize()
	{
		return size;
	}

	public void setSize(Size size)
	{
		this.size = size;
	}

	public DateMidnight getDateOfBirth()
	{
		return dateOfBirth;
	}

	public void setDateOfBirth(DateMidnight dateOfBirth)
	{
		this.dateOfBirth = dateOfBirth;
	}

	public Gender getGender()
	{
		return gender;
	}

	public void setGender(Gender gender)
	{
		this.gender = gender;
	}

	public Alignment getAlignment()
	{
		return alignment;
	}

	public void setAlignment(Alignment alignment)
	{
		this.alignment = alignment;
	}

	public String getDeity()
	{
		return deity;
	}

	public void setDeity(String deity)
	{
		this.deity = deity;
	}
}
