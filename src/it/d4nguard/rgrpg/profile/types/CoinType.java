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
package it.d4nguard.rgrpg.profile.types;

public enum CoinType {
	PlatinumPiece(1000, 100, 10, 1),
	GoldPiece(100, 10, 1, .1),
	SilverPiece(10, 1, .1, .01),
	CopperPiece(1, .1, .01, .001);

	private final double cp;
	private final double sp;
	private final double gp;
	private final double pp;

	CoinType(final double cp, final double sp, final double gp, final double pp) {
		this.cp = cp;
		this.sp = sp;
		this.gp = gp;
		this.pp = pp;
	}

	public double getUnity(CoinType type) {
		double ret = 0;
		switch (type) {
			case PlatinumPiece:
				ret = pp;
				break;
			case GoldPiece:
				ret = gp;
				break;
			case SilverPiece:
				ret = sp;
				break;
			case CopperPiece:
				ret = cp;
				break;
		}
		return ret;
	}
}
