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
package it.d4nguard.rgrpg.util.dynacast.adapters;

import org.jscience.physics.amount.Amount;

/**
 * Implements a {@link SimpleAdapter} of {@link Amount}, a type of the jscience
 * project.
 *
 * @author kLeZ-hAcK
 */
public class AmountAdapter extends SimpleAdapter<Amount<?>> {
	/**
	 * {@inheritDoc}
	 */
	@Override
	public Amount<?> adapt(String value) {
		return Amount.valueOf(value);
	}
}
