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
package it.d4nguard.rgrpg.d20.feats;

import it.d4nguard.rgrpg.Context;
import it.d4nguard.rgrpg.d20.items.weapons.Weapon;
import it.d4nguard.rgrpg.d20.types.FeatCategoryType;

import java.util.HashSet;
import java.util.Set;

public class WeaponProficiency extends Feat {
	private static final long serialVersionUID = 5281622416467554030L;

	private final Set<Weapon> items;
	private boolean allSimple;
	private boolean allMartial;

	public WeaponProficiency() {
		super(Context.getFeat("weapon.proficiency.title"), FeatCategoryType.General);
		this.items = new HashSet<>();
	}

	public boolean isAllSimple() {
		return allSimple;
	}

	public void setAllSimple(boolean allSimple) {
		this.allSimple = allSimple;
	}

	public boolean isAllMartial() {
		return allMartial;
	}

	public void setAllMartial(boolean allMartial) {
		this.allMartial = allMartial;
	}

	public Set<Weapon> getItems() {
		return items;
	}
}
