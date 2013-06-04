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
package it.d4nguard.rgrpg.profile;

import it.d4nguard.rgrpg.util.StringCompiler;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;

public class Player implements Serializable
{
	private static final long serialVersionUID = 2005023218428164699L;

	private final String name;
	private final Map<RPGCharacter, CharacterInfo> characters;

	public Player(String name)
	{
		this(name, new HashMap<RPGCharacter, CharacterInfo>());
	}

	private Player(String name, Map<RPGCharacter, CharacterInfo> characters)
	{
		this.name = name;
		this.characters = characters;
	}

	public String getName()
	{
		return name;
	}

	public Map<RPGCharacter, CharacterInfo> getCharacters()
	{
		return characters;
	}

	public Player copyRename(String name)
	{
		return new Player(name, this.characters);
	}

	@Override
	public int hashCode()
	{
		final int prime = 31;
		int result = 1;
		result = prime * result + ((characters == null) ? 0 : characters.hashCode());
		result = prime * result + ((name == null) ? 0 : name.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object o)
	{
		if (o == null) return false;
		if (!(o instanceof Player)) return false;
		return ((Player) o).getName().equals(this.name);
	}

	@Override
	public String toString()
	{
		StringCompiler sc = new StringCompiler();
		sc.appendln("PLAYER");
		sc.appendln("======");
		sc.append("Name: ").appendln(this.name).appendNewLine();
		sc.appendln("= CHARACTERS");
		sc.appendln("------------");
		for (RPGCharacter c : this.characters.keySet())
		{
			sc.appendln(c);
		}
		sc.appendln("------------");
		return sc.toString();
	}
}
