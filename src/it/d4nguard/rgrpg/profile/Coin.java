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

import java.io.Serializable;

public class Coin implements Serializable
{
	private static final long serialVersionUID = -8403667979960797301L;

	private final CoinType type;
	private double amount;

	public Coin(CoinType type)
	{
		this(type, 0);
	}

	public Coin(CoinType type, double amount)
	{
		this.type = type;
		this.amount = amount;
	}

	public double getAmount()
	{
		return amount;
	}

	public void setAmount(double amount)
	{
		this.amount = amount;
	}

	public CoinType getType()
	{
		return type;
	}

	public static double convert(Coin coin, CoinType type)
	{
		return coin.getAmount() * coin.getType().getUnity(type);
	}
}
