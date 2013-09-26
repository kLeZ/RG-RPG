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
package it.d4nguard.rgrpg.profile;

import it.d4nguard.rgrpg.d20.types.TryAgainType;
import it.d4nguard.rgrpg.util.NumericUtils;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public abstract class Skill implements Serializable
{
	private static final long serialVersionUID = -7135040561923824661L;

	protected final String name;
	protected final AbilityScore ability;
	protected final int ranks;
	protected final List<Integer> misc;
	protected final boolean trained;
	protected final TryAgainType tryAgain;

	public Skill(String name, AbilityScore ability)
	{
		this(name, ability, false, TryAgainType.Limited);
	}

	public Skill(String name, AbilityScore ability, boolean trained,
					TryAgainType tryAgain)
	{
		this.name = name;
		this.ability = ability;
		this.trained = trained;
		this.tryAgain = tryAgain;
		this.ranks = 0;
		this.misc = new ArrayList<Integer>();
	}

	public int getModifier()
	{
		return NumericUtils.sum(ranks + ability.getModifier(), misc);
	}

	public String getName()
	{
		return name;
	}

	public AbilityScore getAbility()
	{
		return ability;
	}

	public int getRanks()
	{
		return ranks;
	}

	public List<Integer> getMisc()
	{
		return misc;
	}

	public boolean isTrained()
	{
		return trained;
	}

	public TryAgainType getTryAgain()
	{
		return tryAgain;
	}
}
