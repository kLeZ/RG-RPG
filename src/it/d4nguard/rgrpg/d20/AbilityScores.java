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

import it.d4nguard.rgrpg.profile.AbilityScore;

import java.io.Serializable;

public class AbilityScores extends it.d4nguard.rgrpg.profile.AbilityScores implements Serializable {
	public static final String STRENGTH = "d20-strength";
	public static final String DEXTERITY = "d20-dexterity";
	public static final String CONSTITUTION = "d20-constitution";
	public static final String INTELLIGENCE = "d20-intelligence";
	public static final String WISDOM = "d20-wisdom";
	public static final String CHARISMA = "d20-charisma";
	private static final long serialVersionUID = 1363222839096421722L;

	@Override
	protected AbilityScore newAbilityScore(String abilityScore) {
		return new D20AbilityScore(abilityScore);
	}

	@Override
	public String getPropertyPrefix() {
		return "d20-";
	}

	public AbilityScore getStrength() {
		return getAbilityScore(STRENGTH);
	}

	public void setStrength(int strength) {
		scores.get(STRENGTH).setValue(strength);
	}

	public AbilityScore getDexterity() {
		return getAbilityScore(DEXTERITY);
	}

	public void setDexterity(int dexterity) {
		scores.get(DEXTERITY).setValue(dexterity);
	}

	public AbilityScore getConstitution() {
		return getAbilityScore(CONSTITUTION);
	}

	public void setConstitution(int constitution) {
		scores.get(CONSTITUTION).setValue(constitution);
	}

	public AbilityScore getIntelligence() {
		return getAbilityScore(INTELLIGENCE);
	}

	public void setIntelligence(int intelligence) {
		scores.get(INTELLIGENCE).setValue(intelligence);
	}

	public AbilityScore getWisdom() {
		return getAbilityScore(WISDOM);
	}

	public void setWisdom(int wisdom) {
		scores.get(WISDOM).setValue(wisdom);
	}

	public AbilityScore getCharisma() {
		return getAbilityScore(CHARISMA);
	}

	public void setCharisma(int charisma) {
		scores.get(CHARISMA).setValue(charisma);
	}

	public class D20AbilityScore extends AbilityScore {
		public static final int MID_RANGE = 10;
		private static final long serialVersionUID = 113611044329823552L;

		public D20AbilityScore(String name) {
			this(name, MID_RANGE);
		}

		public D20AbilityScore(String name, int value) {
			super(name, value);
		}

		@Override
		public int calculateModifier(int value) {
			return (int) Math.round(Math.floor(((float) (value - MID_RANGE)) / 2.0F));
		}
	}
}
