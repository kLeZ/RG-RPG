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
package it.d4nguard.rgrpg.d20;

public class Attributes
{
	private Attribute strength;
	private Attribute dexterity;
	private Attribute stamina;
	private Attribute intelligence;
	private Attribute wisdom;
	private Attribute charisma;

	public Attribute getStrength()
	{
		return strength;
	}

	public void setStrength(Attribute strength)
	{
		this.strength = strength;
	}

	public Attribute getDexterity()
	{
		return dexterity;
	}

	public void setDexterity(Attribute dexterity)
	{
		this.dexterity = dexterity;
	}

	public Attribute getStamina()
	{
		return stamina;
	}

	public void setStamina(Attribute stamina)
	{
		this.stamina = stamina;
	}

	public Attribute getIntelligence()
	{
		return intelligence;
	}

	public void setIntelligence(Attribute intelligence)
	{
		this.intelligence = intelligence;
	}

	public Attribute getWisdom()
	{
		return wisdom;
	}

	public void setWisdom(Attribute wisdom)
	{
		this.wisdom = wisdom;
	}

	public Attribute getCharisma()
	{
		return charisma;
	}

	public void setCharisma(Attribute charisma)
	{
		this.charisma = charisma;
	}

	public Attributes()
	{
	}
}
