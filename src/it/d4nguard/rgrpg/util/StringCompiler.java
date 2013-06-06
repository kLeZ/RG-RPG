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

import java.util.Arrays;

import org.apache.commons.lang3.text.StrBuilder;

public class StringCompiler extends StrBuilder
{
	public static final char NUL = '\0';

	private final int length;
	private final char fill;

	public StringCompiler()
	{
		length = 0;
		fill = NUL;
	}

	public StringCompiler(int length, char fill)
	{
		this.length = length;
		this.fill = fill;
	}

	private StringCompiler to(StrBuilder sb)
	{
		return (StringCompiler) sb;
	}

	private boolean canFill()
	{
		return length > 0 && fill != NUL;
	}

	public StringCompiler append(String fmt, Object... args)
	{
		String app = String.format(fmt, args);
		return to(canFill() ? fill().append(super.append(app)) : super.append(app));
	}

	public StringCompiler appendln(String fmt, Object... args)
	{
		String app = String.format(fmt, args);
		return to(canFill() ? fill().append(super.appendln(app)) : super.appendln(app));
	}

	public StringCompiler fill()
	{
		return fill(length, fill);
	}

	public StringCompiler fill(int length, char fill)
	{
		char[] filler = new char[length];
		Arrays.fill(filler, fill);
		return (StringCompiler) super.append(filler);
	}
}
