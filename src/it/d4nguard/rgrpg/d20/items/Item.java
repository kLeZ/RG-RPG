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
package it.d4nguard.rgrpg.d20.items;

import it.d4nguard.rgrpg.profile.Coin;

import java.io.Serializable;

import javax.measure.Measurable;
import javax.measure.quantity.Mass;

public abstract class Item implements Serializable
{
	private static final long serialVersionUID = -1105954765958477231L;

	private final String name;
	private final String description;
	private final Coin cost;
	private final Measurable<Mass> weight;

	public Item(String name, String description, Coin cost,
					Measurable<Mass> weight)
	{
		this.name = name;
		this.description = description;
		this.cost = cost;
		this.weight = weight;
	}

	public String getName()
	{
		return name;
	}

	public String getDescription()
	{
		return description;
	}

	public Coin getCost()
	{
		return cost;
	}

	public Measurable<Mass> getWeight()
	{
		return weight;
	}
}
