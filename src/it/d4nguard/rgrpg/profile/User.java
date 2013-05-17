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

import java.util.Set;
import java.util.HashSet;

public class User
{
    public static final String LF = System.getProperty("line.separator");

    private String name;
    private float score;
    private final Set<Character> characters;

    public User()
    {
	this.characters = new HashSet<Character>();
    }

    public String getName()
    {
	return this.name;
    }

    public void setName(String name)
    {
	this.name = name;
    }

    public float getScore()
    {
	return this.score;
    }

    public Set<Character> getCharacters()
    {
	return this.characters;
    }

    @Override
    public boolean equals(Object o)
    {
	if (o == null) return false;
	if (!(o instanceof User)) return false;
	return ((User)o).getName().equals(this.name);
    }

    @Override
    public String toString()
    {
	StringBuilder sb = new StringBuilder();
	sb.append("USER").append(LF);
	sb.append("====").append(LF);
	sb.append("Name: ").append(this.name).append(LF);
	sb.append("Score: ").append(this.score).append(LF);
	sb.append("Characters").append(LF);
	sb.append("----------").append(LF);
	for (Character c : this.characters)
	{
	    sb.append(c).append(LF);
	}
	sb.append(LF);
	return sb.toString();
    }
}
