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
package it.d4nguard.rgrpg.engine;

import it.d4nguard.rgrpg.d20.Character;
import it.d4nguard.rgrpg.d20.Skill;
import it.d4nguard.rgrpg.d20.types.ArmorClassType;
import it.d4nguard.rgrpg.profile.types.AttackType;
import it.d4nguard.rgrpg.util.Dice;
import it.d4nguard.rgrpg.util.NumericUtils;
import it.d4nguard.rgrpg.util.OperatorType;

import java.util.ArrayList;

public class D20Engine {
	public static final int LIMITED_RETRIES = 3;

	public static boolean skillCheck(Character c, Skill skill, int DC, int DCMul) {
		boolean ret = false;
		ArrayList<Integer> rolls = new ArrayList<>();
		int total = skill.getRanks();
		total += skill.getAbility().getModifier();
		total += NumericUtils.sum(skill.getMisc());
		switch (skill.getArmorCheckPenalty()) {
			case None:
				break;
			case NormalPenalty:
				total += c.getEquipment().getArmor().getArmorCheckPenalty();
				total += c.getEquipment().getShield().getArmorCheckPenalty();
				break;
			case DoublePenalty:
				total += (c.getEquipment().getArmor().getArmorCheckPenalty() * 2);
				total += (c.getEquipment().getShield().getArmorCheckPenalty() * 2);
				break;
		}
		int roll = new Dice(1, 20).roll();
		rolls.add(roll);
		switch (skill.getTryAgain()) {
			case Once:
				ret = (roll + total) >= DC;
				break;
			case Limited: {
				for (int i = 0; i < LIMITED_RETRIES || ret; i++) {
					roll = new Dice(1, 20).roll();
					rolls.add(roll);
					ret |= (roll + total) >= DC;
				}
				break;
			}
			case Always: {
				int tries = LIMITED_RETRIES + (LIMITED_RETRIES / 2 + LIMITED_RETRIES * 3);
				for (int i = 0; i < tries || ret; i++) {
					roll = new Dice(1, 20).roll();
					rolls.add(roll);
					ret |= (roll + total) >= DC;
				}
				break;
			}
			case WithPenalty: {
				for (int i = 0; i < LIMITED_RETRIES || ret; i++) {
					roll = new Dice(1, 20).roll();
					rolls.add(roll);
					ret |= (roll + total) >= (DC + (DCMul * i));
				}
				break;
			}
		}
		return ret;
	}

	public static boolean armorClassCheck(Character c, ArmorClassType type, int ar) {
		return ar >= c.getArmorClass(type);
	}

	public static Dice attack(Character c, AttackType type, int babAttackIndex) {
		return new Dice(1, 20, OperatorType.Addition, c.getBab(type, babAttackIndex));
	}
}
