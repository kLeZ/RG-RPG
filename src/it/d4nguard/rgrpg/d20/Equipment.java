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

import it.d4nguard.rgrpg.d20.items.Armor;
import it.d4nguard.rgrpg.d20.items.Item;
import it.d4nguard.rgrpg.d20.items.Shield;
import it.d4nguard.rgrpg.d20.items.weapons.Weapon;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class Equipment implements Serializable
{
	private static final long serialVersionUID = 8592149089544477908L;

	private Armor armor;
	private Shield shield;
	private Weapon weapon;
	private final List<Item> inventory;
	private final Wallet wallet;

	public Equipment()
	{
		this.inventory = new ArrayList<Item>();
		this.wallet = new Wallet();
	}

	public Armor getArmor()
	{
		return armor;
	}

	public void setArmor(Armor armor)
	{
		this.armor = armor;
	}

	public Shield getShield()
	{
		return shield;
	}

	public void setShield(Shield shield)
	{
		this.shield = shield;
	}

	public Weapon getWeapon()
	{
		return weapon;
	}

	public void setWeapon(Weapon weapon)
	{
		this.weapon = weapon;
	}

	public List<Item> getInventory()
	{
		return inventory;
	}

	public Wallet getWallet()
	{
		return wallet;
	}
}
