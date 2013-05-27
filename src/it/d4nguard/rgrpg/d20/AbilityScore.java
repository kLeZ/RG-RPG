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

public class AbilityScore
{
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
		setValue(value);
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
		this.value = value;
		this.modifier = getModifier(value);
	}

	public int getModifier()
	{
		return modifier;
	}

	public static int getModifier(int value)
	{
		return (int) Math.round(Math.floor(((float) (value - MID_RANGE)) / 2.0F));
	}

	public static class UnmodifiableAbilityScore extends AbilityScore
	{
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
