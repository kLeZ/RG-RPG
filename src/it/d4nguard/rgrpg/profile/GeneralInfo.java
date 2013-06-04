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

import static javax.measure.unit.SI.KILOGRAM;
import static javax.measure.unit.SI.METER;

import javax.measure.quantity.Length;
import javax.measure.quantity.Mass;
import javax.measure.unit.UnitFormat;

import org.joda.time.DateMidnight;
import org.jscience.physics.amount.Amount;

public class GeneralInfo
{
	private String name;
	private String description;
	private String skinColor;
	private String hairColor;
	private String eyesColor;
	private Amount<Length> height;
	private Amount<Mass> weight;
	private DateMidnight dateOfBirth;
	private GenderType gender;
	private String philosophyDeityReligion;

	public GeneralInfo()
	{
		height = Amount.valueOf(1.0D, METER);
		weight = Amount.valueOf(1.0D, KILOGRAM);
	}

	public String getName()
	{
		return name;
	}

	public void setName(String name)
	{
		this.name = name;
	}

	public String getDescription()
	{
		return description;
	}

	public void setDescription(String description)
	{
		this.description = description;
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

	public Amount<Length> getHeight()
	{
		return height;
	}

	public void setHeight(Amount<Length> height)
	{
		this.height = height;
	}

	public Amount<Mass> getWeight()
	{
		return weight;
	}

	public void setWeight(Amount<Mass> weight)
	{
		this.weight = weight;
	}

	public DateMidnight getDateOfBirth()
	{
		return dateOfBirth;
	}

	public void setDateOfBirth(DateMidnight dateOfBirth)
	{
		this.dateOfBirth = dateOfBirth;
	}

	public GenderType getGender()
	{
		return gender;
	}

	public void setGender(GenderType gender)
	{
		this.gender = gender;
	}

	public String getPhilosophyDeityReligion()
	{
		return philosophyDeityReligion;
	}

	public void setPhilosophyDeityReligion(String philosophyDeityReligion)
	{
		this.philosophyDeityReligion = philosophyDeityReligion;
	}

	@Override
	public String toString()
	{
		UnitFormat uf = UnitFormat.getInstance();
		StringBuilder builder = new StringBuilder();
		builder.append("GeneralInfo [name=");
		builder.append(name);
		builder.append(", description=");
		builder.append(description);
		builder.append(", skinColor=");
		builder.append(skinColor);
		builder.append(", hairColor=");
		builder.append(hairColor);
		builder.append(", eyesColor=");
		builder.append(eyesColor);
		builder.append(", height=");
		builder.append(uf.format(height.getUnit()));
		builder.append(", weight=");
		builder.append(uf.format(weight.getUnit()));
		builder.append(", dateOfBirth=");
		builder.append(dateOfBirth);
		builder.append(", gender=");
		builder.append(gender);
		builder.append(", philosophyDeityReligion=");
		builder.append(philosophyDeityReligion);
		builder.append("]");
		return builder.toString();
	}
}
