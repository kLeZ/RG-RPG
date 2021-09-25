/*
 * Copyright (C) 2021 Alessandro 'kLeZ' Accardo
 *
 * This file is part of RG-RPG.
 *
 * RG-RPG is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * RG-RPG is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with RG-RPG.  If not, see <http://www.gnu.org/licenses/>.
 *
 */
package it.d4nguard.rgrpg.d20.items.weapons;

import it.d4nguard.rgrpg.d20.CriticalHit;
import it.d4nguard.rgrpg.d20.types.SizeType;
import it.d4nguard.rgrpg.d20.types.WeaponCategoryType;
import it.d4nguard.rgrpg.d20.types.WeaponEncumbranceType;
import it.d4nguard.rgrpg.d20.types.WeaponType;
import it.d4nguard.rgrpg.profile.Coin;
import it.d4nguard.rgrpg.util.Dice;
import org.jscience.physics.amount.Amount;

import javax.measure.quantity.Length;
import javax.measure.quantity.Mass;
import java.util.EnumSet;

public class ReachWeapon extends MeleeWeapon {
	private static final long serialVersionUID = 4791193210807081294L;

	private final Amount<Length> rangeIncrement;

	public ReachWeapon(String name, String description, Coin cost, Amount<Mass> weight, WeaponCategoryType weaponCategory, WeaponEncumbranceType weaponEncumbrance, SizeType size, Dice damage, CriticalHit criticalHit, EnumSet<WeaponType> weaponType, Amount<Length> rangeIncrement) {
		super(name, description, cost, weight, weaponCategory, weaponEncumbrance, size, damage, criticalHit, weaponType);
		this.rangeIncrement = rangeIncrement;
	}

	public Amount<Length> getRangeIncrement() {
		return rangeIncrement;
	}
}
