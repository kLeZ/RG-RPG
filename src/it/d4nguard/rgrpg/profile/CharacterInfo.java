/*
 * Copyright (C) 2020 Alessandro 'kLeZ' Accardo
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
package it.d4nguard.rgrpg.profile;

import it.d4nguard.rgrpg.util.StringUtils;

import java.io.Serializable;
import java.time.Duration;
import java.time.ZonedDateTime;
import java.time.temporal.ChronoUnit;

public class CharacterInfo implements Serializable {
	private static final long serialVersionUID = 5583221130662794997L;

	private ZonedDateTime creation;
	private Duration played;
	private ZonedDateTime last;
	private Duration lastDuration;
	private float lastExp;
	private boolean current;

	public CharacterInfo() {
		this.creation = ZonedDateTime.now();
		this.played = Duration.of(0L, ChronoUnit.SECONDS);
		this.last = ZonedDateTime.from(this.creation);
		this.lastDuration = Duration.of(0L, ChronoUnit.SECONDS);
		this.lastExp = 0;
		this.current = false;
	}

	public ZonedDateTime getCreation() {
		return creation;
	}

	public void setCreation(ZonedDateTime creation) {
		this.creation = creation;
	}

	public Duration getPlayed() {
		return played;
	}

	public void setPlayed(Duration played) {
		this.played = played;
	}

	public ZonedDateTime getLast() {
		return last;
	}

	public void setLast(ZonedDateTime last) {
		this.last = last;
	}

	public Duration getLastDuration() {
		return lastDuration;
	}

	public void setLastDuration(Duration lastDuration) {
		this.lastDuration = lastDuration;
	}

	public float getLastExp() {
		return lastExp;
	}

	public void setLastExp(float lastExp) {
		this.lastExp = lastExp;
	}

	public boolean isCurrent() {
		return current;
	}

	public void setCurrent(boolean current) {
		this.current = current;
	}

	@Override
	public String toString() {
		return StringUtils.genericToString(getClass(), this);
	}
}
