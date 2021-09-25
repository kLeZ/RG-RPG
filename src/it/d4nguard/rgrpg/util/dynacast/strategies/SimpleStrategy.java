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

import it.d4nguard.rgrpg.util.GenericsUtils;
import it.d4nguard.rgrpg.util.dynacast.Strategy;

import java.lang.reflect.Type;

/**
 * This abstract class implements an {@link AbstractStrategy}. The only
 * different thing is the implementation of {@link Strategy#isMine(Type)}
 * method.<br>
 * <br>
 * The logic behind this method is:<br>
 * <code>
 * someTypecastingToClass(getMine(type)).isAssignableFrom(someTypecastingToClass(type));
 * </code>
 *
 * @author kLeZ-hAcK
 */
public abstract class SimpleStrategy extends AbstractStrategy {
	/**
	 * {@inheritDoc}
	 */
	@Override
	public boolean isMine(Type type) {
		return getMine(type).isAssignableFrom(GenericsUtils.getClassFromType(type));
	}
}
