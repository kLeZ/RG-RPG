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

import java.io.Serializable;

public class Range implements Serializable {
	public static final int STEP_MAGIC = 1;
	private static final long serialVersionUID = -7074663435117171410L;
	private final int max;
	private int step;
	private int min;

	public Range() {
		this(20, 20);
	}

	public Range(int min) {
		this(min, 20);
	}

	public Range(int min, int max) {
		this.min = min;
		this.max = max;
		this.step = max - min + STEP_MAGIC;
	}

	public int getMin() {
		return min;
	}

	private void setMin(int min) {
		this.min = min;
	}

	public int getMax() {
		return max;
	}

	public boolean inRange(final int i) {
		return i >= min && i <= max;
	}

	public void increase() {
		setMin(max - (++step - STEP_MAGIC));
	}
}
