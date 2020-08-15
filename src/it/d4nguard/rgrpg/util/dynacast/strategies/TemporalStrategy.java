/*
 * Copyright (C) 2020 Alessandro 'kLeZ' Accardo
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

import java.lang.reflect.Type;
import java.time.temporal.Temporal;

/**
 * This class implements a {@link SimpleStrategy} for {@link Temporal}s.<br>
 * The {@link #getMine(Type)} method returns {@link Temporal}.class
 *
 * @author kLeZ-hAcK
 */
public class TemporalStrategy extends SimpleStrategy {
	/**
	 * {@inheritDoc}
	 */
	@Override
	public Class<?> getMine(Type type) {
		return Temporal.class;
	}
}
