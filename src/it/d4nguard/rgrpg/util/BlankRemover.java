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
package it.d4nguard.rgrpg.util;

public class BlankRemover
{
	/* replace multiple whitespaces between words with single blank */
	public static CharSequence itrim(final CharSequence source)
	{
		return source.toString().replaceAll("\\b(\\s{2,}|\\u00a0{2,})\\b", " ");
	}

	public static CharSequence lrtrim(final CharSequence source)
	{
		return ltrim(rtrim(source));
	}

	/* remove leading whitespace */
	public static CharSequence ltrim(final CharSequence source)
	{
		return source.toString().replaceAll("^(\\s|\\u00a0)+", "");
	}

	/* remove trailing whitespace */
	public static CharSequence rtrim(final CharSequence source)
	{
		return source.toString().replaceAll("(\\s|\\u00a0)+$", "");
	}

	/* remove all superfluous whitespaces in source string */
	public static CharSequence trim(final CharSequence source)
	{
		return itrim(ltrim(rtrim(source)));
	}

	private BlankRemover()
	{
	}
}
