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
package it.d4nguard.rgrpg.d20;

import it.d4nguard.rgrpg.d20.types.ArmorCheckPenaltyType;
import it.d4nguard.rgrpg.d20.types.TryAgainType;
import it.d4nguard.rgrpg.profile.AbilityScore;
import it.d4nguard.rgrpg.util.NumericUtils;

import java.util.List;

public class Skill extends it.d4nguard.rgrpg.profile.Skill {
	private static final long serialVersionUID = 6997008011152393042L;

	private final ArmorCheckPenaltyType armorCheckPenalty;

	public Skill(String name, AbilityScore ability) {
		this(name, ability, false, TryAgainType.Limited, ArmorCheckPenaltyType.None);
	}

	public Skill(String name, AbilityScore ability, boolean trained, TryAgainType tryAgain, ArmorCheckPenaltyType armorCheckPenalty) {
		super(name, ability, trained, tryAgain);
		this.armorCheckPenalty = armorCheckPenalty;
	}

	public int getModifier() {
		return NumericUtils.sum(ranks + ability.getModifier(), misc);
	}

	public String getName() {
		return name;
	}

	public AbilityScore getAbility() {
		return ability;
	}

	public int getRanks() {
		return ranks;
	}

	public List<Integer> getMisc() {
		return misc;
	}

	public boolean isTrained() {
		return trained;
	}

	public ArmorCheckPenaltyType getArmorCheckPenalty() {
		return armorCheckPenalty;
	}

	public TryAgainType getTryAgain() {
		return tryAgain;
	}
}
