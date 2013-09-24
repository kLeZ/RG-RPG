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
package it.d4nguard.rgrpg.engine;

import it.d4nguard.rgrpg.d20.D20Character;
import it.d4nguard.rgrpg.d20.Skill;
import it.d4nguard.rgrpg.d20.types.ArmorClassType;
import it.d4nguard.rgrpg.profile.types.AttackType;
import it.d4nguard.rgrpg.util.Dice;
import it.d4nguard.rgrpg.util.NumericUtils;
import it.d4nguard.rgrpg.util.OperatorType;

import java.util.ArrayList;

public class D20Engine
{
	public static final int LIMITED_RETRIES = 3;

	public static boolean skillCheck(D20Character c, Skill skill, int DC,
					int DCMul)
	{
		boolean ret = false;
		ArrayList<Integer> rolls = new ArrayList<>();
		int total = skill.getRanks();
		total += skill.getAbility().getModifier();
		total += NumericUtils.sum(skill.getMisc());
		switch (skill.getArmorCheckPenalty())
		{
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
		switch (skill.getTryAgain())
		{
			case Once:
				ret = (roll + total) >= DC;
				break;
			case Limited:
			{
				for (int i = 0; i < LIMITED_RETRIES || ret; i++)
				{
					roll = new Dice(1, 20).roll();
					rolls.add(roll);
					ret |= (roll + total) >= DC;
				}
				break;
			}
			case Always:
			{
				int tries = LIMITED_RETRIES + (LIMITED_RETRIES / 2 + LIMITED_RETRIES * 3);
				for (int i = 0; i < tries || ret; i++)
				{
					roll = new Dice(1, 20).roll();
					rolls.add(roll);
					ret |= (roll + total) >= DC;
				}
				break;
			}
			case WithPenalty:
			{
				for (int i = 0; i < LIMITED_RETRIES || ret; i++)
				{
					roll = new Dice(1, 20).roll();
					rolls.add(roll);
					ret |= (roll + total) >= (DC + (DCMul * i));
				}
				break;
			}
		}
		return ret;
	}

	public static boolean armorClassCheck(D20Character c, ArmorClassType type,
					int ar)
	{
		return ar >= c.getArmorClass(type);
	}

	public static Dice attack(D20Character c, AttackType type, int babAttackIndex)
	{
		Dice ret = new Dice(1, 20, OperatorType.Addition, c.getBab(type,
						babAttackIndex));
		return ret;
	}
}
