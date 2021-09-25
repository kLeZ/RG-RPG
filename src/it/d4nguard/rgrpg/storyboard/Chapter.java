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

import it.d4nguard.rgrpg.util.StringUtils;

public class Chapter {
	private final Chapter prev;
	private final Zone zone;
	private Chapter next;

	public Chapter(Chapter prev, Zone zone) {
		this.prev = prev;
		this.zone = zone;
	}

	public Chapter(Chapter prev, Zone zone, Chapter next) {
		this(prev, zone);
		this.setNext(next);
	}

	public Zone getZone() {
		return zone;
	}

	public Chapter getPrev() {
		return prev;
	}

	public Chapter getNext() {
		return next;
	}

	public void setNext(Chapter next) {
		this.next = next;
	}

	@Override
	public String toString() {
		return StringUtils.genericToString(getClass(), this);
	}
}
