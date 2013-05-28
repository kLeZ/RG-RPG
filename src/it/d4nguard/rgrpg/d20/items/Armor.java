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
package it.d4nguard.rgrpg.d20.items;

import it.d4nguard.rgrpg.profile.Coin;

import javax.measure.Measurable;
import javax.measure.quantity.Mass;
import javax.measure.quantity.Velocity;

public class Armor extends Item
{
	private final int armorClass;
	private final int maxDexterity;
	private final ArmorCategoryType armorCategory;
	private final int armorCheckPenalty;
	private final int arcaneSpellFailure;
	private final Measurable<Velocity> speed;

	public Armor(String name, String description, Coin cost,
					Measurable<Mass> weight, int armorClass, int maxDexterity,
					ArmorCategoryType armorCategory, int armorCheckPenalty,
					int arcaneSpellFailure, Measurable<Velocity> speed)
	{
		super(name, description, cost, weight);
		this.armorClass = armorClass;
		this.maxDexterity = maxDexterity;
		this.armorCategory = armorCategory;
		this.armorCheckPenalty = armorCheckPenalty;
		this.arcaneSpellFailure = arcaneSpellFailure;
		this.speed = speed;
	}

	public int getArmorClass()
	{
		return armorClass;
	}

	public int getMaxDexterity()
	{
		return maxDexterity;
	}

	public ArmorCategoryType getArmorCategory()
	{
		return armorCategory;
	}

	public int getArmorCheckPenalty()
	{
		return armorCheckPenalty;
	}

	public int getArcaneSpellFailure()
	{
		return arcaneSpellFailure;
	}

	public Measurable<Velocity> getSpeed()
	{
		return speed;
	}
}
