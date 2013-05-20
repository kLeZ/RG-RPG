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
package it.d4nguard.rgrpg.d20.races;

import it.d4nguard.rgrpg.d20.Language;
import it.d4nguard.rgrpg.d20.SizeType;
import it.d4nguard.rgrpg.d20.Trait;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Set;

import com.db4o.foundation.ArgumentNullException;

public abstract class Race
{
	private static final HashMap<RaceType, HashSet<Trait>> RACE_TRAITS = new HashMap<RaceType, HashSet<Trait>>();
	private static final HashMap<RaceSubType, HashSet<Trait>> SUB_RACE_TRAITS = new HashMap<RaceSubType, HashSet<Trait>>();
	private static final HashMap<HumanoidRaceSubType, HashSet<Trait>> HUMANOID_RACE_TRAITS = new HashMap<HumanoidRaceSubType, HashSet<Trait>>();
	static
	{
		//TODO: Set Traits
	}

	protected final Set<Language> spokenLanguages;
	protected SizeType size = SizeType.Medium;
	protected int effectiveCharacterLevel = 0;
	protected int armorClass = 0;
	protected final RaceType type;
	protected final RaceSubType subType;
	protected final HumanoidRaceSubType humanoidSubType;

	private final Set<Trait> traits;

	public Race(RaceType type, RaceSubType subType, HumanoidRaceSubType humanoidSubType)
	{
		this.type = type;
		this.subType = subType;
		this.humanoidSubType = humanoidSubType;
		this.spokenLanguages = new HashSet<Language>();
		this.traits = new HashSet<Trait>();
		if (type == null) throw new ArgumentNullException("type");
		this.traits.addAll(RACE_TRAITS.get(this.type));
		if (subType != null) this.traits.addAll(SUB_RACE_TRAITS.get(this.subType));
		if (humanoidSubType != null) this.traits.addAll(HUMANOID_RACE_TRAITS.get(this.humanoidSubType));
	}

	public SizeType getSize()
	{
		return size;
	}

	public void setSize(SizeType size)
	{
		this.size = size;
	}

	public int getEffectiveCharacterLevel()
	{
		return effectiveCharacterLevel;
	}

	public void setEffectiveCharacterLevel(int effectiveCharacterLevel)
	{
		this.effectiveCharacterLevel = effectiveCharacterLevel;
	}

	public int getArmorClass()
	{
		return armorClass;
	}

	public void setArmorClass(int armorClass)
	{
		this.armorClass = armorClass;
	}

	public RaceType getType()
	{
		return type;
	}

	public RaceSubType getSubType()
	{
		return subType;
	}

	public HumanoidRaceSubType getHumanoidSubType()
	{
		return humanoidSubType;
	}

	public Set<Language> getSpokenLanguages()
	{
		return spokenLanguages;
	}
}
