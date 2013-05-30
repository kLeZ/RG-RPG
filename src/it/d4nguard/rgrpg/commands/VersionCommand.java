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

import it.d4nguard.rgrpg.Context;

public class VersionCommand implements Command
{
	public static final int MAJOR = 0;
	public static final int MINOR = 0;
	public static final int REVISION = 1;

	@Override
	public void execute(String... args)
	{
		System.out.println(String.format(Context.getString("version.msg"),
						MAJOR, MINOR, REVISION));
	}

	@Override
	public String getHelp()
	{
		return Context.getString("version.help");
	}

	@Override
	public String getDescription()
	{
		return Context.getString("version.description");
	}
}
