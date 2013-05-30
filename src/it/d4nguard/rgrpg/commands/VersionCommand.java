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
package it.d4nguard.rgrpg.commands;

import it.d4nguard.rgrpg.util.StringCompiler;

public class VersionCommand implements Command
{
	public static final int MAJOR = 0;
	public static final int MINOR = 0;
	public static final int REVISION = 1;

	@Override
	public void execute(String... args)
	{
		StringCompiler sb = new StringCompiler();
		sb.appendln("RG-RPG is a Java-based text, roleplaying-gal game, in which you");
		sb.appendln("have to carry many girls. The RG-RPG acronym is a recursive one and");
		sb.appendln("it means \"RG-RPG is a Gal Role playing game Pointing on Girls.\"");
		sb.appendln("Copyright (C) 2013 by Alessandro Accardo <julius8774@gmail.com>");
		sb.appendln("RG-RPG version %d.%02d.%03d", MAJOR, MINOR, REVISION);
		System.out.println(sb.toString());
	}

	@Override
	public String getHelp()
	{
		return "Prints some info about the program and its version.";
	}
}
