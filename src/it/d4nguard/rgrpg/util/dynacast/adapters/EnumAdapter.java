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

/**
 * Implements a {@link SimpleAdapter} of E, where E extends {@link Enum}.
 *
 * @param <E>
 *
 * @author kLeZ-hAcK
 */
public class EnumAdapter<E extends Enum<?>> extends SimpleAdapter<E> {
	/**
	 * {@inheritDoc}
	 */
	@Override
	@SuppressWarnings({ "unchecked", "rawtypes" })
	public E adapt(String value) {
		Class<? extends Enum> ce = (Class<? extends Enum>) getType();
		return (E) Enum.valueOf(ce, value);
	}
}
