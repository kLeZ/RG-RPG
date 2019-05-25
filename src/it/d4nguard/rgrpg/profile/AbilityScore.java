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
package it.d4nguard.rgrpg.profile;

import java.io.Serializable;

public abstract class AbilityScore implements Serializable {
	private static final long serialVersionUID = -7369119723062034841L;

	private final String name;
	private int value;
	private int modifier;

	public AbilityScore(String name, int value) {
		this.name = name;
		safeSetValue(value);
	}

	protected AbilityScore(String name, int value, int modifier) {
		this.name = name;
		this.value = value;
		this.modifier = modifier;
	}

	public String getName() {
		return name;
	}

	public int getValue() {
		return value;
	}

	public void setValue(int value) {
		safeSetValue(value);
	}

	public int getModifier() {
		return modifier;
	}

	private void safeSetValue(int value) {
		this.value = value;
		this.modifier = calculateModifier(value);
	}

	public abstract int calculateModifier(int value);

	@Override
	public String toString() {
		return String.format("%s: %d(%+d)", name, value, modifier);
	}

	public static class UnmodifiableAbilityScore extends AbilityScore {
		private static final long serialVersionUID = 8740668067478743803L;

		public UnmodifiableAbilityScore(AbilityScore as) {
			super(as.getName(), as.getValue(), as.getModifier());
		}

		@Override
		public void setValue(int value) {
			throw new UnsupportedOperationException();
		}

		@Override
		public int calculateModifier(int value) {
			return 0;
		}
	}
}
