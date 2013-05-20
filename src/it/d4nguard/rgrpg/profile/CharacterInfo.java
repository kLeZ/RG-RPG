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

import org.joda.time.DateTime;
import org.joda.time.Duration;

public class CharacterInfo
{
	private DateTime creationDate;
	private Duration playedTime;
	private DateTime lastSession;
	private Duration lastSessionDuration;
	private float lastSessionEarnedExperience;

	public DateTime getCreationDate()
	{
		return creationDate;
	}

	public void setCreationDate(DateTime creationDate)
	{
		this.creationDate = creationDate;
	}

	public Duration getPlayedTime()
	{
		return playedTime;
	}

	public void setPlayedTime(Duration playedTime)
	{
		this.playedTime = playedTime;
	}

	public DateTime getLastSession()
	{
		return lastSession;
	}

	public void setLastSession(DateTime lastSession)
	{
		this.lastSession = lastSession;
	}

	public Duration getLastSessionDuration()
	{
		return lastSessionDuration;
	}

	public void setLastSessionDuration(Duration lastSessionDuration)
	{
		this.lastSessionDuration = lastSessionDuration;
	}

	public float getLastSessionEarnedExperience()
	{
		return lastSessionEarnedExperience;
	}

	public void setLastSessionEarnedExperience(float lastSessionEarnedExperience)
	{
		this.lastSessionEarnedExperience = lastSessionEarnedExperience;
	}
}
