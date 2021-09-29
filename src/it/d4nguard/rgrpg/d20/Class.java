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
package it.d4nguard.rgrpg.d20;

import it.d4nguard.rgrpg.d20.types.SavingThrowType;
import it.d4nguard.rgrpg.util.Dice;

import java.io.Serial;
import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

public abstract class Class implements Serializable {
	@Serial
	private static final long serialVersionUID = -8001346462155284274L;

	private final String name;
	private final Set<Integer> hitDiceResultPool = new HashSet<>();

	public Class(String name) {
		this.name = name;
	}

	public String getName() {
		return name;
	}

	public Set<Integer> getHitDiceResultPool() {
		return hitDiceResultPool;
	}

	public int getSavingThrow(SavingThrowType type) {
		return switch (type) {
			case Fortitude -> getFortitude();
			case Reflexes -> getReflexes();
			case WillPower -> getWillPower();
		};
	}

	/**
	 * Gets the Base Attack Bonus (BAB) of the attack you want.<br />
	 * For example, if you need the 3rd attack for that class, call getBab(3);
	 *
	 * @param attack
	 * 		is the index, counting from 1, of the attack in the list of
	 * 		attacks the class provides to the character.
	 *
	 * @return the Base Attack Bonus (BAB) of the attack you want.
	 */
	public abstract int getBab(int attack);

	public abstract int getLevel();

	public abstract int getFortitude();

	public abstract int getReflexes();

	public abstract int getWillPower();

	public abstract Dice getHitDice();

	public abstract Set<Language> getSpokenLanguages();
}
