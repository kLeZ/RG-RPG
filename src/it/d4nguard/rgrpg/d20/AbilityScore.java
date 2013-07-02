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

public class AbilityScore implements Serializable
{
	private static final long serialVersionUID = -7369119723062034841L;

	public static final int MID_RANGE = 10;

	private final String name;
	private int value;
	private int modifier;

	public AbilityScore(String name)
	{
		this(name, MID_RANGE);
	}

	public AbilityScore(String name, int value)
	{
		this.name = name;
		safeSetValue(value);
	}

	public String getName()
	{
		return name;
	}

	public int getValue()
	{
		return value;
	}

	public void setValue(int value)
	{
		safeSetValue(value);
	}

	public int getModifier()
	{
		return modifier;
	}

	private void safeSetValue(int value)
	{
		this.value = value;
		this.modifier = getModifier(value);
	}

	public static int getModifier(int value)
	{
		return (int) Math.round(Math.floor(((float) (value - MID_RANGE)) / 2.0F));
	}

	public static class UnmodifiableAbilityScore extends AbilityScore
	{
		private static final long serialVersionUID = 8740668067478743803L;

		public UnmodifiableAbilityScore(AbilityScore as)
		{
			super(as.getName(), as.getValue());
		}

		@Override
		public void setValue(int value)
		{
			throw new UnsupportedOperationException();
		}
	}
}
