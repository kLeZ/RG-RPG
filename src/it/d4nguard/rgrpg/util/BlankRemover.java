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
package it.d4nguard.rgrpg.util;

public class BlankRemover {
	private BlankRemover() {
	}

	/* replace multiple whitespaces between words with single blank */
	public static CharSequence itrim(final CharSequence source) {
		return source.toString().replaceAll("\\b(\\s{2,}|\\u00a0{2,})\\b", " ");
	}

	public static CharSequence lrtrim(final CharSequence source) {
		return ltrim(rtrim(source));
	}

	/* remove leading whitespace */
	public static CharSequence ltrim(final CharSequence source) {
		return source.toString().replaceAll("^(\\s|\\u00a0)+", "");
	}

	/* remove trailing whitespace */
	public static CharSequence rtrim(final CharSequence source) {
		return source.toString().replaceAll("(\\s|\\u00a0)+$", "");
	}

	/* remove all superfluous whitespaces in source string */
	public static CharSequence trim(final CharSequence source) {
		return itrim(ltrim(rtrim(source)));
	}
}
