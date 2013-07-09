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

import it.d4nguard.rgrpg.util.StringUtils;

import java.io.Serializable;

import org.joda.time.DateTime;
import org.joda.time.Duration;

public class CharacterInfo implements Serializable
{
	private static final long serialVersionUID = 5583221130662794997L;

	private DateTime creation;
	private Duration played;
	private DateTime last;
	private Duration lastDuration;
	private float lastExp;
	private boolean current;

	public CharacterInfo()
	{
		this.creation = DateTime.now();
		this.played = new Duration(0);
		this.last = new DateTime(this.creation.getMillis());
		this.lastDuration = new Duration(0);
		this.lastExp = 0;
		this.current = false;
	}

	public DateTime getCreation()
	{
		return creation;
	}

	public void setCreation(DateTime creation)
	{
		this.creation = creation;
	}

	public Duration getPlayed()
	{
		return played;
	}

	public void setPlayed(Duration played)
	{
		this.played = played;
	}

	public DateTime getLast()
	{
		return last;
	}

	public void setLast(DateTime last)
	{
		this.last = last;
	}

	public Duration getLastDuration()
	{
		return lastDuration;
	}

	public void setLastDuration(Duration lastDuration)
	{
		this.lastDuration = lastDuration;
	}

	public float getLastExp()
	{
		return lastExp;
	}

	public void setLastExp(float lastExp)
	{
		this.lastExp = lastExp;
	}

	public boolean isCurrent()
	{
		return current;
	}

	public void setCurrent(boolean current)
	{
		this.current = current;
	}

	@Override
	public String toString()
	{
		return StringUtils.genericToString(getClass(), this);
	}
}
