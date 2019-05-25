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
package it.d4nguard.rgrpg.d20.races;

import it.d4nguard.rgrpg.d20.Language;
import it.d4nguard.rgrpg.d20.types.HumanoidRaceSubType;
import it.d4nguard.rgrpg.d20.types.RaceSubType;
import it.d4nguard.rgrpg.d20.types.RaceType;
import it.d4nguard.rgrpg.d20.types.SizeType;

import java.io.Serializable;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Set;
import java.util.concurrent.atomic.AtomicReference;

public abstract class Race implements Serializable {
	private static final long serialVersionUID = 7221883394924344504L;

	private static final AtomicReference<HashMap<RaceType, HashSet<Trait>>> RACE_TRAITS = new AtomicReference<>(new HashMap<>());
	private static final AtomicReference<HashMap<RaceSubType, HashSet<Trait>>> SUB_RACE_TRAITS = new AtomicReference<>(new HashMap<>());
	private static final AtomicReference<HashMap<HumanoidRaceSubType, HashSet<Trait>>> HUMANOID_RACE_TRAITS = new AtomicReference<>(new HashMap<>());

	static {
		//TODO: Set Traits
	}

	protected final Set<Language> spokenLanguages;
	protected final RaceType type;
	protected final RaceSubType subType;
	protected final HumanoidRaceSubType humanoidSubType;
	protected final Set<Trait> traits;
	protected SizeType size = SizeType.Medium;
	protected int effectiveCharacterLevel = 0;
	protected int armorClass = 0;

	public Race(RaceType type, RaceSubType subType, HumanoidRaceSubType humanoidSubType) {
		this.type = type;
		this.subType = subType;
		this.humanoidSubType = humanoidSubType;
		this.spokenLanguages = new HashSet<>();
		this.traits = new HashSet<>();
		if (type == null)
			throw new NullPointerException("type");
		this.traits.addAll(RACE_TRAITS.get().get(this.type));
		if (subType != null)
			this.traits.addAll(SUB_RACE_TRAITS.get().get(this.subType));
		if (humanoidSubType != null)
			this.traits.addAll(HUMANOID_RACE_TRAITS.get().get(this.humanoidSubType));
	}

	public SizeType getSize() {
		return size;
	}

	public void setSize(SizeType size) {
		this.size = size;
	}

	public int getEffectiveCharacterLevel() {
		return effectiveCharacterLevel;
	}

	public void setEffectiveCharacterLevel(int effectiveCharacterLevel) {
		this.effectiveCharacterLevel = effectiveCharacterLevel;
	}

	public int getArmorClass() {
		return armorClass;
	}

	public void setArmorClass(int armorClass) {
		this.armorClass = armorClass;
	}

	public RaceType getType() {
		return type;
	}

	public RaceSubType getSubType() {
		return subType;
	}

	public HumanoidRaceSubType getHumanoidSubType() {
		return humanoidSubType;
	}

	public Set<Language> getSpokenLanguages() {
		return spokenLanguages;
	}
}
