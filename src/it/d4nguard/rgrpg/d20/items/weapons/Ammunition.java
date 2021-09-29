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
package it.d4nguard.rgrpg.d20.items.weapons;

import it.d4nguard.rgrpg.d20.items.Item;
import it.d4nguard.rgrpg.profile.Coin;
import org.jscience.physics.amount.Amount;

import javax.measure.quantity.Mass;
import java.io.Serial;

public class Ammunition extends Item {
	@Serial
	private static final long serialVersionUID = -7244714329584333095L;

	public Ammunition(String name, String description, Coin cost, Amount<Mass> weight) {
		super(name, description, cost, weight);
	}
}
