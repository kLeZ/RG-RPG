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

import it.d4nguard.rgrpg.d20.feats.Feat;
import it.d4nguard.rgrpg.d20.races.Race;
import it.d4nguard.rgrpg.d20.types.AlignmentType;
import it.d4nguard.rgrpg.d20.types.ArmorClassType;
import it.d4nguard.rgrpg.d20.types.SavingThrowType;
import it.d4nguard.rgrpg.profile.GeneralInfo;
import it.d4nguard.rgrpg.profile.Player;
import it.d4nguard.rgrpg.profile.RPGCharacter;
import it.d4nguard.rgrpg.profile.types.AttackType;
import it.d4nguard.rgrpg.util.NumericUtils;
import it.d4nguard.rgrpg.util.StringUtils;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class D20Character extends RPGCharacter
{
	private static final long serialVersionUID = -8727304403745570110L;

	private Race race;
	private AlignmentType alignment;

	private final AbilityScores abilityScores;
	private final Equipment equipment;
	private final DamageReduction damageReduction;

	private final Set<Class> classes;
	private final Set<ResistanceToEnergy> resistanceToEnergies;
	private final Set<Language> spokenLanguages;
	private final Set<Skill> skills;
	private final Set<Feat> feats;
	private final List<Integer> hpModifiers;
	private final List<Integer> babModifiers;
	private final List<Integer> acModifiers;
	private final List<Integer> stModifiers;
	private final List<Integer> dodgeBonuses;

	private float experience = 0;
	private int damage = 0;
	private int deflection = 0;

	public D20Character(Player owner, GeneralInfo info)
	{
		super(owner, info);
		this.abilityScores = new AbilityScores();
		this.equipment = new Equipment();
		this.damageReduction = new DamageReduction();

		this.classes = new HashSet<Class>();
		this.skills = new HashSet<Skill>();
		this.feats = new HashSet<Feat>();
		this.spokenLanguages = new HashSet<Language>();
		this.resistanceToEnergies = new HashSet<ResistanceToEnergy>();

		this.dodgeBonuses = new ArrayList<Integer>();
		this.hpModifiers = new ArrayList<Integer>();
		this.babModifiers = new ArrayList<Integer>();
		this.acModifiers = new ArrayList<Integer>();
		this.stModifiers = new ArrayList<Integer>();

		this.spokenLanguages.add(Language.COMMON);
	}

	public float getExperience()
	{
		return experience;
	}

	public void setExperience(float experience)
	{
		this.experience = experience;
	}

	public int getDamage()
	{
		return damage;
	}

	public void setDamage(int damage)
	{
		this.damage = damage;
	}

	public int getDeflection()
	{
		return deflection;
	}

	public void setDeflection(int deflection)
	{
		this.deflection = deflection;
	}

	public Race getRace()
	{
		return race;
	}

	public void setRace(Race race)
	{
		this.race = race;
	}

	public AlignmentType getAlignment()
	{
		return alignment;
	}

	public void setAlignment(AlignmentType alignment)
	{
		this.alignment = alignment;
	}

	public AbilityScores getAbilityScores()
	{
		return abilityScores;
	}

	public Equipment getEquipment()
	{
		return equipment;
	}

	public DamageReduction getDamageReduction()
	{
		return damageReduction;
	}

	public Set<Class> getClasses()
	{
		return classes;
	}

	public Set<ResistanceToEnergy> getResistanceToEnergies()
	{
		return resistanceToEnergies;
	}

	public Set<Skill> getSkills()
	{
		return skills;
	}

	public Set<Feat> getFeats()
	{
		return feats;
	}

	public List<Integer> getHpModifiers()
	{
		return hpModifiers;
	}

	public List<Integer> getBabModifiers()
	{
		return babModifiers;
	}

	public List<Integer> getAcModifiers()
	{
		return acModifiers;
	}

	public List<Integer> getStModifiers()
	{
		return stModifiers;
	}

	public List<Integer> getDodgeBonuses()
	{
		return dodgeBonuses;
	}

	public int getHealthPoints(boolean current)
	{
		int hp = 0;
		for (Class c : classes)
			hp += NumericUtils.sum(
							abilityScores.getConstitution().getModifier(),
							c.getHitDiceResultPool());
		hp = NumericUtils.sum(hp, hpModifiers);
		return (current ? NumericUtils.sum(hp, -damage) : hp);
	}

	public Set<Language> getSpokenLanguages()
	{
		Set<Language> langs = new HashSet<Language>();
		langs.addAll(spokenLanguages);
		if (race != null) langs.addAll(race.getSpokenLanguages());
		for (Class c : classes)
			langs.addAll(c.getSpokenLanguages());
		return langs;
	}

	public int getLevel()
	{
		int level = 0;
		level += race.getEffectiveCharacterLevel();
		for (Class c : classes)
			level += c.getLevel();
		return level;
	}

	public int getBab(AttackType type, int attack)
	{
		int bab = 0;
		for (Class c : classes)
			bab += c.getBab(attack);
		switch (type)
		{
			case Melee:
				bab += race.getSize().getModifier();
				bab += abilityScores.getStrength().getModifier();
				break;
			case Ranged:
				bab += race.getSize().getModifier();
				bab += abilityScores.getDexterity().getModifier();
				break;
			case Grapple:
				bab += race.getSize().getGrapple();
				bab += abilityScores.getStrength().getModifier();
				break;
		}
		return NumericUtils.sum(bab, babModifiers);
	}

	public int getMaxDexterity()
	{
		return NumericUtils.min(abilityScores.getDexterity().getModifier(),
						equipment.getArmor().getMaxDexterity());
	}

	public int getArmorClass(ArmorClassType type)
	{
		int ac = 10;
		switch (type)
		{
			case Normal:
				ac += equipment.getArmor().getArmorClass();
				ac += equipment.getShield().getArmorClass();
				ac += getMaxDexterity();
				ac += race.getArmorClass();
				ac = NumericUtils.sum(ac, dodgeBonuses);
				break;
			case FlatFooted: // Denies DEX
				ac += equipment.getArmor().getArmorClass();
				ac += equipment.getShield().getArmorClass();
				ac += race.getArmorClass();
				break;
			case Touch: // Denies ARM, SHI, NAT
				ac += getMaxDexterity();
				ac = NumericUtils.sum(ac, dodgeBonuses);
				break;
		}
		ac += race.getSize().getModifier();
		ac += deflection;
		return NumericUtils.sum(ac, acModifiers);
	}

	public int getSavingThrow(SavingThrowType type)
	{
		int save = 0;
		for (Class c : classes)
			save += c.getSavingThrow(type);
		switch (type)
		{
			case Fortitude:
				save += abilityScores.getConstitution().getModifier();
				break;
			case Reflexes:
				save += abilityScores.getDexterity().getModifier();
				break;
			case WillPower:
				save += abilityScores.getWisdom().getModifier();
				break;
		}
		return NumericUtils.sum(save, stModifiers);
	}

	@Override
	public String toString()
	{
		return StringUtils.genericToString(getClass(), this,
						"serialVersionUID", "owner", "\\$SWITCH_TABLE\\$.*");
	}
}
