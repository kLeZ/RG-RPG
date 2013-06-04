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
package it.d4nguard.rgrpg.d20;

import java.io.Serializable;

public class Range implements Serializable
{
	private static final long serialVersionUID = -7074663435117171410L;

	public static final int STEP_MAGIC = 1;

	private int step;
	private int min;
	private final int max;

	public Range()
	{
		this(20, 20);
	}

	public Range(int min)
	{
		this(min, 20);
	}

	public Range(int min, int max)
	{
		this.min = min;
		this.max = max;
		this.step = max - min + STEP_MAGIC;
	}

	public int getMin()
	{
		return min;
	}

	private void setMin(int min)
	{
		this.min = min;
	}

	public int getMax()
	{
		return max;
	}

	public boolean inRange(final int i)
	{
		return i >= min && i <= max;
	}

	public void increase()
	{
		setMin(max - (++step - STEP_MAGIC));
	}
}
