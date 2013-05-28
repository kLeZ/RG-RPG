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

import it.d4nguard.rgrpg.d20.types.ArmorCheckPenaltyType;
import it.d4nguard.rgrpg.d20.types.TryAgainType;
import it.d4nguard.rgrpg.util.NumericUtils;

import java.util.ArrayList;
import java.util.List;

public class Skill
{
	private final String name;
	private final AbilityScore ability;
	private final int ranks;
	private final List<Integer> misc;
	private final boolean trained;
	private final ArmorCheckPenaltyType armorCheckPenalty;
	private final TryAgainType tryAgain;

	public Skill(String name, AbilityScore ability)
	{
		this(name, ability, false, ArmorCheckPenaltyType.None,
						TryAgainType.Limited);
	}

	public Skill(String name, AbilityScore ability, boolean trained,
					ArmorCheckPenaltyType armorCheckPenalty,
					TryAgainType tryAgain)
	{
		this.name = name;
		this.ability = ability;
		this.trained = trained;
		this.armorCheckPenalty = armorCheckPenalty;
		this.tryAgain = tryAgain;
		this.ranks = 0;
		this.misc = new ArrayList<Integer>();
	}

	public int getSkillModifier()
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

	public ArmorCheckPenaltyType getArmorCheckPenalty()
	{
		return armorCheckPenalty;
	}

	public TryAgainType getTryAgain()
	{
		return tryAgain;
	}
}
