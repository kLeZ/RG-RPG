/*
 * Copyright (C) 2019 Alessandro 'kLeZ' Accardo
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
package it.d4nguard.rgrpg.util;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.util.*;
import java.util.Map.Entry;

public class BundleSet extends AbstractSet<ResourceBundle> implements Cloneable, Serializable, Set<ResourceBundle> {
	private static final long serialVersionUID = -777579563686715493L;

	private transient HashMap<String, ResourceBundle> map;
	private XMLResourceBundleControl control = new XMLResourceBundleControl();

	public BundleSet() {
		map = new HashMap<>();
	}

	@Override
	public Iterator<ResourceBundle> iterator() {
		return map.values().iterator();
	}

	@Override
	public int size() {
		return map.size();
	}

	public boolean isEmpty() {
		return map.isEmpty();
	}

	public ResourceBundle get(String s) {
		return map.get(s);
	}

	public boolean contains(String s) {
		return map.containsKey(s);
	}

	public boolean add(String s) {
		return map.put(s, ResourceBundle.getBundle(s, control)) == null;
	}

	public boolean remove(String s) {
		ResourceBundle bundle = map.get(s);
		return map.remove(s) == bundle;
	}

	public void clear() {
		map.clear();
	}

	@SuppressWarnings("unchecked")
	public Object clone() {
		try {
			BundleSet newSet = (BundleSet) super.clone();
			newSet.map = (HashMap<String, ResourceBundle>) map.clone();
			return newSet;
		} catch (CloneNotSupportedException e) {
			throw new InternalError();
		}
	}

	private void writeObject(ObjectOutputStream s) throws IOException {
		// Write out any hidden serialization magic
		s.defaultWriteObject();

		// Write out size
		s.writeInt(map.size());

		// Write out all elements in the proper order.
		for (Entry<String, ResourceBundle> e : map.entrySet())
			s.writeObject(e);
	}

	@SuppressWarnings("unchecked")
	private void readObject(ObjectInputStream s) throws IOException, ClassNotFoundException {
		// Read in any hidden serialization magic
		s.defaultReadObject();

		map = new HashMap<>();

		// Read in size
		int size = s.readInt();

		// Read in all elements in the proper order.
		for (int i = 0; i < size; i++) {
			Entry<String, ResourceBundle> e = (Entry<String, ResourceBundle>) s.readObject();
			map.put(e.getKey(), e.getValue());
		}
	}
}
