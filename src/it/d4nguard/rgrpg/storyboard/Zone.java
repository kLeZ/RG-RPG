/*
 * Copyright (C) 2021 Alessandro 'kLeZ' Accardo
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
package it.d4nguard.rgrpg.storyboard;

import it.d4nguard.rgrpg.storyboard.events.Event;
import it.d4nguard.rgrpg.util.StringUtils;

import java.util.HashSet;
import java.util.Set;
import java.util.TreeSet;

public class Zone {
	private final TreeSet<Zone> subZones;
	private final Set<Event> events;
	private String description;

	public Zone() {
		subZones = new TreeSet<>();
		events = new HashSet<>();
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public Set<Event> getEvents() {
		return events;
	}

	public TreeSet<Zone> getSubZones() {
		return subZones;
	}

	@Override
	public String toString() {
		return StringUtils.genericToString(getClass(), this);
	}
}
