// RG-RPG is a Java-based text, roleplaying-gal game, in which you
// have to carry many girls. The RG-RPG acronym is a recursive one and
// it means "RG-RPG is a Gal Role playing game Pointing on Girls."
// Copyright (C) 2013 by Alessandro Accardo <julius8774@gmail.com>
// 
// This program is free software: you can redistribute it and/or modify
// it under the terms of the GNU General Public License as published by
// the Free Software Foundation, either version 2 of the License, or (at
// your option) any later version.
// 
// This program is distributed in the hope that it will be useful, but
// WITHOUT ANY WARRANTY; without even the implied warranty of
// MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the GNU
// General Public License for more details.
// 
// You should have received a copy of the GNU General Public License
// along with this program.  If not, see <http://www.gnu.org/licenses/>.
// 
package it.d4nguard.rgrpg.util.dynacast.adapters;

import it.d4nguard.rgrpg.util.StringUtils;
import it.d4nguard.rgrpg.util.Triplet;

import java.util.Locale;

import org.joda.time.DateMidnight;
import org.joda.time.DateTime;
import org.joda.time.Instant;
import org.joda.time.MutableDateTime;
import org.joda.time.ReadableInstant;
import org.joda.time.format.DateTimeFormat;
import org.joda.time.format.DateTimeFormatter;
import org.joda.time.format.ISODateTimeFormat;

public class DateTimeAdapter extends AbstractAdapter<ReadableInstant>
{
	private Class<ReadableInstant> type;

	@Override
	public ReadableInstant adapt(String value)
	{
		// value is a string formatted as: "07/04/1987[dd/MM/yyyy]"
		String date = "";
		DateTimeFormatter fmt;
		Triplet<String, String, String> tri = StringUtils.getBetween(value,
						'[', ']');
		date = tri.getLeft();
		if (tri.hasCenter()) fmt = DateTimeFormat.forPattern(tri.getCenter());
		else fmt = ISODateTimeFormat.localDateOptionalTimeParser();
		fmt = fmt.withLocale(Locale.getDefault());
		if (type.equals(DateTime.class)) return DateTime.parse(date, fmt);
		else if (type.equals(DateMidnight.class)) return DateMidnight.parse(
						date, fmt);
		else if (type.equals(Instant.class)) return Instant.parse(date, fmt);
		else if (type.equals(MutableDateTime.class)) return MutableDateTime.parse(
						date, fmt);
		else throw new UnsupportedOperationException("type");
	}

	@Override
	public void beforeCreateAdapter(Class<ReadableInstant> type)
	{
		this.type = type;
	}
}
