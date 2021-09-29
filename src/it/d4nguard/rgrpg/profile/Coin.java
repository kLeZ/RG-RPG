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
package it.d4nguard.rgrpg.profile;

import it.d4nguard.rgrpg.profile.types.CoinType;
import it.d4nguard.rgrpg.util.StringUtils;

import java.io.Serial;
import java.io.Serializable;

public class Coin implements Serializable {
	@Serial
	private static final long serialVersionUID = -8403667979960797301L;

	private final CoinType type;
	private double amount;

	public Coin(CoinType type) {
		this(type, 0);
	}

	public Coin(CoinType type, double amount) {
		this.type = type;
		this.amount = amount;
	}

	public static double convert(Coin coin, CoinType type) {
		return coin.getAmount() * coin.getType()
				.getUnity(type);
	}

	public double getAmount() {
		return amount;
	}

	public void setAmount(double amount) {
		this.amount = amount;
	}

	public CoinType getType() {
		return type;
	}

	@Override
	public String toString() {
		return StringUtils.genericToString(getClass(), this);
	}
}
