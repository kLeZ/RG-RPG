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
import it.d4nguard.rgrpg.d20.items.Shield;
import it.d4nguard.rgrpg.d20.types.FeatCategoryType;

import java.util.HashSet;
import java.util.Set;

public class ShieldProficiency extends Feat {
	private static final long serialVersionUID = 4088624038996266287L;

	private final Set<Shield> items;
	private boolean all;
	private boolean tower;

	public ShieldProficiency() {
		super(Context.getFeat("shield.proficiency.title"), FeatCategoryType.General);
		this.items = new HashSet<>();
	}

	public boolean isAll() {
		return all;
	}

	public void setAll(boolean all) {
		this.all = all;
	}

	public boolean isTower() {
		return tower;
	}

	public void setTower(boolean tower) {
		this.tower = tower;
	}

	public Set<Shield> getItems() {
		return items;
	}
}
