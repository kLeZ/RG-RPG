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
package it.d4nguard.rgrpg.d20.items;

import it.d4nguard.rgrpg.profile.Coin;
import org.jscience.physics.amount.Amount;

import javax.measure.quantity.Mass;
import java.io.Serializable;

public abstract class Item implements Serializable {
	private static final long serialVersionUID = -1105954765958477231L;

	private final String name;
	private final String description;
	private final Coin cost;
	private final Amount<Mass> weight;

	public Item(String name, String description, Coin cost, Amount<Mass> weight) {
		this.name = name;
		this.description = description;
		this.cost = cost;
		this.weight = weight;
	}

	public String getName() {
		return name;
	}

	public String getDescription() {
		return description;
	}

	public Coin getCost() {
		return cost;
	}

	public Amount<Mass> getWeight() {
		return weight;
	}
}
