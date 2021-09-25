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

import it.d4nguard.rgrpg.d20.items.Item;
import it.d4nguard.rgrpg.profile.Coin;
import it.d4nguard.rgrpg.profile.types.CoinType;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class Wallet implements Serializable {
	private static final long serialVersionUID = 612105702965224166L;

	private final List<Item> gems;
	private final Coin cp;
	private final Coin sp;
	private final Coin gp;
	private final Coin pp;

	public Wallet() {
		this.gems = new ArrayList<>();
		this.cp = new Coin(CoinType.CopperPiece);
		this.sp = new Coin(CoinType.SilverPiece);
		this.gp = new Coin(CoinType.GoldPiece);
		this.pp = new Coin(CoinType.PlatinumPiece);
	}

	public List<Item> getGems() {
		return gems;
	}

	public Coin getCp() {
		return cp;
	}

	public Coin getSp() {
		return sp;
	}

	public Coin getGp() {
		return gp;
	}

	public Coin getPp() {
		return pp;
	}
}
