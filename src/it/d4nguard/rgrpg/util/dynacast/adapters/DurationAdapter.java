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
package it.d4nguard.rgrpg.util.dynacast.adapters;

import java.time.Duration;

/**
 * Implements a {@link SimpleAdapter} of {@link Duration}, an interface
 * of the joda.time project.<br>
 * It adapts a string representation of a duration according to
 * {@link Duration#parse(String)}.<br>
 * <br>
 * The value will be formatted before it can be passed to the
 * {@link Duration#parse(String)} method. The format is esplicitly done by
 * calling {@link String#format(String, Object...)} with "PT%sS" as the format
 * string.
 *
 * @author kLeZ-hAcK
 */
public class DurationAdapter extends SimpleAdapter<Duration> {
	/**
	 * {@inheritDoc}
	 */
	@Override
	public Duration adapt(String value) {
		return Duration.parse(String.format("PT%sS", value));
	}
}
