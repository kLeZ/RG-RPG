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
package it.d4nguard.rgrpg.util.dynacast.strategies;

import org.jscience.physics.amount.Amount;

import java.lang.reflect.Type;

/**
 * This class implements a {@link SimpleStrategy} in which the returned type
 * from {@link #getMine(Type)} is simply {@link Amount}.class.
 *
 * @author kLeZ-hAcK
 */
public class AmountStrategy extends SimpleStrategy {
	/**
	 * {@inheritDoc}
	 */
	@Override
	public Class<?> getMine(Type type) {
		return Amount.class;
	}
}
