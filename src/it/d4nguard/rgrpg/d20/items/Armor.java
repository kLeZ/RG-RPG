/*
 * Copyright (C) 2019 Alessandro 'kLeZ' Accardo
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
package it.d4nguard.rgrpg.d20.items;

import it.d4nguard.rgrpg.d20.types.ArmorCategoryType;
import it.d4nguard.rgrpg.profile.Coin;
import org.jscience.physics.amount.Amount;

import javax.measure.quantity.Mass;
import javax.measure.quantity.Velocity;

public class Armor extends Item {
	private static final long serialVersionUID = -549132777479378436L;

	private final int armorClass;
	private final int maxDexterity;
	private final ArmorCategoryType armorCategory;
	private final int armorCheckPenalty;
	private final int arcaneSpellFailure;
	private final Amount<Velocity> speed;

	public Armor(String name, String description, Coin cost, Amount<Mass> weight, int armorClass, int maxDexterity, ArmorCategoryType armorCategory, int armorCheckPenalty, int arcaneSpellFailure, Amount<Velocity> speed) {
		super(name, description, cost, weight);
		this.armorClass = armorClass;
		this.maxDexterity = maxDexterity;
		this.armorCategory = armorCategory;
		this.armorCheckPenalty = armorCheckPenalty;
		this.arcaneSpellFailure = arcaneSpellFailure;
		this.speed = speed;
	}

	public int getArmorClass() {
		return armorClass;
	}

	public int getMaxDexterity() {
		return maxDexterity;
	}

	public ArmorCategoryType getArmorCategory() {
		return armorCategory;
	}

	public int getArmorCheckPenalty() {
		return armorCheckPenalty;
	}

	public int getArcaneSpellFailure() {
		return arcaneSpellFailure;
	}

	public Amount<Velocity> getSpeed() {
		return speed;
	}
}
