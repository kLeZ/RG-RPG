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
package it.d4nguard.rgrpg.util.dynacast.adapters;

import it.d4nguard.rgrpg.util.dynacast.Adapter;

import java.lang.reflect.Type;

/**
 * This abstract class implements an {@link AbstractAdapter} providing an empty
 * {@link AbstractAdapter#beforeCreateAdapter(Type)} method for those classes
 * who don't need it.
 * This class has reason to be only because it helps to keep the code simpler.
 *
 * @param <T>
 * 		The type of the implementing {@link Adapter}
 *
 * @author kLeZ-hAcK
 */
public abstract class SimpleAdapter<T> extends AbstractAdapter<T> {
	/**
	 * {@inheritDoc}
	 */
	@Override
	public void beforeCreateAdapter(Type type) {
	}
}
