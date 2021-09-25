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

import it.d4nguard.rgrpg.d20.types.DamageReductionType;
import it.d4nguard.rgrpg.util.StringUtils;

import java.io.Serializable;

public class DamageReduction implements Serializable {
	private static final long serialVersionUID = -2013207030411719125L;

	private DamageReductionType type = DamageReductionType.None;
	private int points = 0;

	public DamageReductionType getType() {
		return type;
	}

	public void setType(DamageReductionType type) {
		this.type = type;
	}

	public int getPoints() {
		return points;
	}

	public void setPoints(int points) {
		this.points = points;
	}

	@Override
	public String toString() {
		return StringUtils.genericToString(getClass(), this);
	}
}
