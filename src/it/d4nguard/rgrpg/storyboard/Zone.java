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
import org.apache.commons.lang3.ObjectUtils;

import java.util.HashSet;
import java.util.Set;
import java.util.TreeSet;

public class Zone implements Comparable<Zone> {
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

	/**
	 * Compares this object with the specified object for order.  Returns a
	 * negative integer, zero, or a positive integer as this object is less
	 * than, equal to, or greater than the specified object.
	 *
	 * <p>The implementor must ensure {@link Integer#signum
	 * signum}{@code (x.compareTo(y)) == -signum(y.compareTo(x))} for
	 * all {@code x} and {@code y}.  (This implies that {@code
	 * x.compareTo(y)} must throw an exception if and only if {@code
	 * y.compareTo(x)} throws an exception.)
	 *
	 * <p>The implementor must also ensure that the relation is transitive:
	 * {@code (x.compareTo(y) > 0 && y.compareTo(z) > 0)} implies
	 * {@code x.compareTo(z) > 0}.
	 *
	 * <p>Finally, the implementor must ensure that {@code
	 * x.compareTo(y)==0} implies that {@code signum(x.compareTo(z))
	 * == signum(y.compareTo(z))}, for all {@code z}.
	 *
	 * @param o
	 * 		the object to be compared.
	 *
	 * @return a negative integer, zero, or a positive integer as this object
	 * is less than, equal to, or greater than the specified object.
	 *
	 * @throws NullPointerException
	 * 		if the specified object is null
	 * @throws ClassCastException
	 * 		if the specified object's type prevents it
	 * 		from being compared to this object.
	 * @apiNote It is strongly recommended, but <i>not</i> strictly required that
	 * {@code (x.compareTo(y)==0) == (x.equals(y))}.  Generally speaking, any
	 * class that implements the {@code Comparable} interface and violates
	 * this condition should clearly indicate this fact.  The recommended
	 * language is "Note: this class has a natural ordering that is
	 * inconsistent with equals."
	 */
	@Override
	public int compareTo(final Zone o) {
		return ObjectUtils.compare(this, o);
	}
}
