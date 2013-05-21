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

import it.d4nguard.rgrpg.util.NumericUtils;
import it.d4nguard.rgrpg.util.RetrievableValue;

import java.util.ArrayList;
import java.util.List;

public class Skill
{
	private int ranks;
	private RetrievableValue abilityMod;
	private final List<Integer> miscMod;

	public Skill()
	{
		this.miscMod = new ArrayList<Integer>();
	}

	public int getRanks()
	{
		return ranks;
	}

	public void setRanks(int ranks)
	{
		this.ranks = ranks;
	}

	public RetrievableValue getAbilityModifier()
	{
		return abilityMod;
	}

	public void setAbilityModifier(RetrievableValue abilityModifier)
	{
		this.abilityMod = abilityModifier;
	}

	public List<Integer> getMiscMod()
	{
		return miscMod;
	}

	public int getSkillModifier()
	{
		return NumericUtils.sum(ranks + abilityMod.getInt(), miscMod);
	}
}
