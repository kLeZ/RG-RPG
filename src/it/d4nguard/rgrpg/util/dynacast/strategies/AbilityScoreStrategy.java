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

import it.d4nguard.rgrpg.profile.AbilityScore;
import it.d4nguard.rgrpg.util.GenericsUtils;

import java.lang.reflect.Type;

/**
 * This class implements an {@link AbstractStrategy} that applies to
 * {@link AbilityScore} type.
 *
 * @author kLeZ
 */
public class AbilityScoreStrategy extends AbstractStrategy {
	/**
	 * {@inheritDoc}
	 */
	@Override
	public Class<?> getMine(Type type) {
		return Integer.class;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public boolean isMine(Type type) {
		return AbilityScore.class.isAssignableFrom(GenericsUtils.getClassFromType(type));
	}
}
