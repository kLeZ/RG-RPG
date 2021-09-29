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
package it.d4nguard.rgrpg.d20.feats;

import it.d4nguard.rgrpg.Context;
import it.d4nguard.rgrpg.d20.items.Armor;
import it.d4nguard.rgrpg.d20.types.FeatCategoryType;

import java.io.Serial;
import java.util.HashSet;
import java.util.Set;

public class ArmorProficiency extends Feat {
	@Serial
	private static final long serialVersionUID = 1908088878347106595L;

	private final Set<Armor> items;
	private boolean allLight;
	private boolean allMedium;
	private boolean allHeavy;

	public ArmorProficiency() {
		super(Context.getFeat("armor.proficiency.title"), FeatCategoryType.General);
		this.items = new HashSet<>();
	}

	public boolean isAllLight() {
		return allLight;
	}

	public void setAllLight(boolean allLight) {
		this.allLight = allLight;
	}

	public boolean isAllMedium() {
		return allMedium;
	}

	public void setAllMedium(boolean allMedium) {
		this.allMedium = allMedium;
	}

	public boolean isAllHeavy() {
		return allHeavy;
	}

	public void setAllHeavy(boolean allHeavy) {
		this.allHeavy = allHeavy;
	}

	public Set<Armor> getItems() {
		return items;
	}
}
