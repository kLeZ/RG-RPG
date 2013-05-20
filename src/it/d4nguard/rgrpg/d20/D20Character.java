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

import it.d4nguard.rgrpg.profile.Character;
import it.d4nguard.rgrpg.profile.Player;
import it.d4nguard.rgrpg.util.NumericUtils;

import java.util.Collection;
import java.util.HashSet;
import java.util.Set;

public class D20Character extends Character
{
	private Player owner;
	private String name;
	private Race race;
	private float experience;
	private AlignmentType alignment;
	private SizeType size;
	private final Set<? extends Class> classes;
	private Attributes attributes;
	private Health health;
	private int deflection;
	private Collection<Integer> dodgeBonuses;
	private Armor armor;
	private Shield shield;
	private Weapon weapon;
	private Set<Language> spokenLanguages;
	private AlteredStatuses alteredStatuses;

	public D20Character()
	{
		this.classes = new HashSet<Class>();
		this.spokenLanguages = new HashSet<Language>();
		this.spokenLanguages.add(Language.COMMON);
	}

	public int getLevel()
	{
		int level = 0;
		level += race.getLevelAdjustment();
		for (Class c : classes)
			level += c.getLevel();
		return level;
	}

	public int getBab(BabType type, int attack)
	{
		int bab = 0;
		for (Class c : classes)
			bab += c.getBab(attack);
		switch (type)
		{
			case Melee:
				bab += size.getModifier();
				bab += attributes.getStrength().getModifier();
				break;
			case Ranged:
				bab += size.getModifier();
				bab += attributes.getDexterity().getModifier();
				break;
			case Grapple:
				bab += size.getGrapple();
				bab += attributes.getStrength().getModifier();
				break;
		}
		return bab;
	}

	public int getMaxDexterity()
	{
		return NumericUtils.min(attributes.getDexterity().getModifier(), armor.getMaxDexterity());
	}

	public int getArmorClass(ArmorClassType type)
	{
		int ac = 10;
		switch (type)
		{
			case Normal:
				ac += armor.getArmorClass();
				ac += shield.getArmorClass();
				ac += getMaxDexterity();
				ac += race.getArmorClass();
				ac = NumericUtils.sum(ac, dodgeBonuses);
				break;
			case FlatFooted: // Denies DEX
				ac += armor.getArmorClass();
				ac += shield.getArmorClass();
				ac += race.getArmorClass();
				break;
			case Touch: // Denies ARM, SHI, NAT
				ac += getMaxDexterity();
				ac = NumericUtils.sum(ac, dodgeBonuses);
				break;
		}
		ac += size.getModifier();
		ac += deflection;
		return ac;
	}

	public int getSavingThrow(SavingThrowType type)
	{
		int save = 0;
		for (Class c : classes)
			save += c.getSavingThrow(type);
		switch (type)
		{
			case Fortitude:
				save += attributes.getStamina().getModifier();
				break;
			case Reflexes:
				save += attributes.getDexterity().getModifier();
				break;
			case WillPower:
				save += attributes.getWisdom().getModifier();
				break;
		}
		return save;
	}
}
