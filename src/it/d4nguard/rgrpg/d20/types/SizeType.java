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
package it.d4nguard.rgrpg.d20.types;

public enum SizeType {
	Fine(8, -16, 16), Diminutive(4, -12, 12), Tiny(2, -8, 8), Small(1, -4, 4), Medium(0, 0, 0), Large(-1, 4, -4), Huge(-2, 8, -8), Gargantuan(-4, 12, -12), Colossal(-8, 16, -16);

	private int modifier;
	private int grapple;
	private int hide;

	SizeType(int modifier, int grapple, int hide) {
		this.modifier = modifier;
		this.grapple = grapple;
		this.hide = hide;
	}

	public int getModifier() {
		return this.modifier;
	}

	public int getGrapple() {
		return this.grapple;
	}

	public int getHide() {
		return hide;
	}
}
