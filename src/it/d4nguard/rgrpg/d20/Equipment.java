/*
 * Copyright (C) 2021 Alessandro 'kLeZ' Accardo
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
package it.d4nguard.rgrpg.d20;

import it.d4nguard.rgrpg.d20.items.Armor;
import it.d4nguard.rgrpg.d20.items.Item;
import it.d4nguard.rgrpg.d20.items.Shield;
import it.d4nguard.rgrpg.d20.items.weapons.Weapon;
import it.d4nguard.rgrpg.util.StringUtils;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class Equipment implements Serializable {
	private static final long serialVersionUID = 8592149089544477908L;
	private final List<Item> inventory;
	private final Wallet wallet;
	private Armor armor;
	private Shield shield;
	private Weapon weapon;

	public Equipment() {
		this.inventory = new ArrayList<>();
		this.wallet = new Wallet();
	}

	public Armor getArmor() {
		return armor;
	}

	public void setArmor(Armor armor) {
		this.armor = armor;
	}

	public Shield getShield() {
		return shield;
	}

	public void setShield(Shield shield) {
		this.shield = shield;
	}

	public Weapon getWeapon() {
		return weapon;
	}

	public void setWeapon(Weapon weapon) {
		this.weapon = weapon;
	}

	public List<Item> getInventory() {
		return inventory;
	}

	public Wallet getWallet() {
		return wallet;
	}

	@Override
	public String toString() {
		return StringUtils.genericToString(getClass(), this);
	}
}
