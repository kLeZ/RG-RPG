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

import java.util.Set;
import java.util.HashSet;

import org.joda.time.Duration;

public class Character
{
	private Player owner;
	private Duration playedTime;
	private String name;
	private float experience;
	private GeneralInfo info;
	private final Set<? extends Class> classes;
	private Attributes attributes;
	private Health health;
	private Armor armor;
	private Shield shield;
	private Weapon weapon;

	public Character()
	{
		this.classes = new HashSet<Class>();
	}

	public int getLevel()
	{
		int level = 0;
		level += info.getRace().getLevelAdjustment();
		for (Class c : classes)
			level += c.getLevel();
		return level;
	}

	public int getBab(Bab type, int attack)
	{
		int bab = 0;
		for (Class c : classes)
			bab += c.getBab(attack);
		switch (type)
		{
			case Melee:
				bab += info.getSize().getModifier();
				bab += attributes.getStrength().getModifier();
				break;
			case Ranged:
				bab += info.getSize().getModifier();
				bab += attributes.getDexterity().getModifier();
				break;
			case Grapple:
				bab += info.getSize().getGrapple();
				bab += attributes.getStrength().getModifier();
				break;
		}
		return bab;
	}

	public int getArmorClass(ArmorClass type)
	{
		int ac = 10;
		switch (type)
		{
			case Normal:
				
				break;
			case FlatFooted:
				break;
			case Touch:
				break;
		}
		ac += info.getSize().getModifier();
		return ac;
	}

	public int getSavingThrow(SavingThrow type)
	{
		int sThrow = 0;
		for (Class c : classes)
			sThrow += c.getSavingThrow(type);
		switch (type)
		{
			case Fortitude:
				sThrow += attributes.getStamina().getModifier();
				break;
			case Reflexes:
				sThrow += attributes.getDexterity().getModifier();
				break;
			case WillPower:
				sThrow += attributes.getWisdom().getModifier();
				break;
		}
		return sThrow;
	}
}
