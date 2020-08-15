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

import it.d4nguard.rgrpg.util.StringUtils;
import it.d4nguard.rgrpg.util.Triplet;

import java.time.*;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeFormatterBuilder;
import java.time.temporal.Temporal;
import java.time.temporal.TemporalAccessor;
import java.util.Locale;

/**
 * Implements a {@link SimpleAdapter} of {@link Temporal}, an interface
 * of the java.time framework.<br>
 * Currently it converts:<br>
 * <ul>
 * <li>{@link ZonedDateTime}</li>
 * <li>{@link Instant}</li>
 * </ul>
 * The value must be a string formatted according to
 * {@link DateTimeFormatter#ISO_DATE} and optionally
 * {@link DateTimeFormatter#ISO_TIME} separated by 'T'.<br>
 * <br>
 * Optionally in the value you can specify a pattern of date and/or time in
 * order to pass a custom formatted value.<br>
 * Passing a pattern for date and/or time can be done by appending to the value
 * the string below:<br>
 * <b>{some custom formatted date}[{the pattern of your choice according to</b>
 * {@link DateTimeFormatter#ofPattern(String)}<b>]</b><br>
 * That is: the value, opened square, the pattern, closed square, no spaces.
 *
 * @author kLeZ-hAcK
 */
public class DateTimeAdapter extends SimpleAdapter<Temporal> {
	/**
	 * {@inheritDoc}
	 */
	@Override
	public Temporal adapt(String value) {
		// value is a string formatted as: "07/04/1987[dd/MM/yyyy]"
		DateTimeFormatter fmt;
		Triplet<String, String, String> tri = StringUtils.getBetween(value, '[', ']');
		String date = tri.getLeft();
		if (tri.hasCenter())
			fmt = DateTimeFormatter.ofPattern(tri.getCenter());
		else {
			DateTimeFormatter time = new DateTimeFormatterBuilder().appendLiteral('T')
					.append(DateTimeFormatter.ISO_TIME)
					.toFormatter();
			fmt = new DateTimeFormatterBuilder().append(DateTimeFormatter.ISO_DATE)
					.appendOptional(time)
					.toFormatter()
					.withZone(ZoneOffset.UTC);
		}
		fmt = fmt.withLocale(Locale.getDefault());
		if (getType().equals(ZonedDateTime.class)) {
			TemporalAccessor temporalAccessor = fmt.parseBest(date, ZonedDateTime::from, LocalDate::from);
			if (temporalAccessor instanceof ZonedDateTime zonedDateTime) {
				return zonedDateTime;
			} else if (temporalAccessor instanceof LocalDate localDate) {
				return localDate.atStartOfDay(ZoneId.systemDefault());
			} else
				throw new UnsupportedOperationException("type");
		} else if (getType().equals(Instant.class))
			return fmt.parse(date, Instant::from);
		else
			throw new UnsupportedOperationException("type");
	}
}
