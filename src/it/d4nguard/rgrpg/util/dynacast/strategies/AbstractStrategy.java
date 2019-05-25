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
package it.d4nguard.rgrpg.util.dynacast.strategies;

import it.d4nguard.rgrpg.util.dynacast.Strategy;

import java.lang.reflect.Type;

/**
 * This abstract class implements a {@link Strategy} in which the only
 * implemented method is {@link Strategy#apply(Type)}.<br>
 * The underlying logic is:<br>
 * <code>
 * if (isMine(type)) return getMine(type);<br>
 * else return type;
 * </code>
 *
 * @author kLeZ-hAcK
 */
public abstract class AbstractStrategy implements Strategy {
	/**
	 * {@inheritDoc}
	 */
	@Override
	public Type apply(Type type) {
		if (isMine(type))
			return getMine(type);
		else
			return type;
	}
}
