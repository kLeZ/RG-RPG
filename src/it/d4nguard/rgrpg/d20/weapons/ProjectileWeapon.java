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
package it.d4nguard.rgrpg.d20.weapons;

import it.d4nguard.rgrpg.d20.CriticalHit;
import it.d4nguard.rgrpg.d20.SizeType;
import it.d4nguard.rgrpg.profile.Coin;
import it.d4nguard.rgrpg.util.Dice;

import java.util.ArrayList;
import java.util.EnumSet;

import javax.measure.Measurable;
import javax.measure.quantity.Length;
import javax.measure.quantity.Mass;

public class ProjectileWeapon extends RangedWeapon
{
	private final ArrayList<Ammunition> ammunitions;

	public ProjectileWeapon(String name, String description, Coin cost,
					Measurable<Mass> weight, WeaponCategoryType weaponCategory,
					WeaponEncumbranceType weaponEncumbrance, SizeType size,
					Dice damage, CriticalHit criticalHit,
					EnumSet<WeaponType> weaponType,
					Measurable<Length> rangeIncrement,
					ArrayList<Ammunition> ammunitions)
	{
		super(name, description, cost, weight, weaponCategory,
						weaponEncumbrance, size, damage, criticalHit,
						weaponType, rangeIncrement);
		this.ammunitions = ammunitions;
	}

	public ArrayList<Ammunition> getAmmunitions()
	{
		return ammunitions;
	}
}