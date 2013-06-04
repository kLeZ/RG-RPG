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
package it.d4nguard.rgrpg.d20.items.weapons;

import it.d4nguard.rgrpg.d20.CriticalHit;
import it.d4nguard.rgrpg.d20.items.Item;
import it.d4nguard.rgrpg.d20.types.SizeType;
import it.d4nguard.rgrpg.d20.types.WeaponCategoryType;
import it.d4nguard.rgrpg.d20.types.WeaponEncumbranceType;
import it.d4nguard.rgrpg.d20.types.WeaponType;
import it.d4nguard.rgrpg.profile.Coin;
import it.d4nguard.rgrpg.util.Dice;

import java.util.EnumSet;

import javax.measure.Measurable;
import javax.measure.quantity.Mass;

public abstract class Weapon extends Item
{
	private static final long serialVersionUID = -2204037533603352232L;

	private final WeaponCategoryType weaponCategory;
	private final WeaponEncumbranceType weaponEncumbrance;
	private final SizeType size;
	private final Dice damage;
	private final CriticalHit criticalHit;
	private final EnumSet<WeaponType> weaponType;

	public Weapon(String name, String description, Coin cost,
					Measurable<Mass> weight, WeaponCategoryType weaponCategory,
					WeaponEncumbranceType weaponEncumbrance, SizeType size,
					Dice damage, CriticalHit criticalHit,
					EnumSet<WeaponType> weaponType)
	{
		super(name, description, cost, weight);
		this.weaponCategory = weaponCategory;
		this.weaponEncumbrance = weaponEncumbrance;
		this.size = size;
		this.damage = damage;
		this.criticalHit = criticalHit;
		this.weaponType = weaponType;
	}

	public WeaponCategoryType getWeaponCategory()
	{
		return weaponCategory;
	}

	public WeaponEncumbranceType getWeaponEncumbrance()
	{
		return weaponEncumbrance;
	}

	public SizeType getSize()
	{
		return size;
	}

	public Dice getDamage()
	{
		return damage;
	}

	public CriticalHit getCriticalHit()
	{
		return criticalHit;
	}

	public EnumSet<WeaponType> getWeaponType()
	{
		return weaponType;
	}
}
