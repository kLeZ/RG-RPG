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
package it.d4nguard.rgrpg.managers;

import it.d4nguard.rgrpg.Context;
import it.d4nguard.rgrpg.profile.Character;
import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.Iterator;

import static org.junit.Assert.*;

public class CharacterManagerTest {
	@Before
	public void setUp() {
		Context.wipe();
		Context.setCurrentPlayer(new PlayerManager().create("kLeZ"));
	}

	@Test
	public final void testCreate() {
		Context.clearCharacters(null);
		ArrayList<Character> list = new ArrayList<>();
		String[] names = new String[] {
				"Julius", "Mialee", "Viktor", "Hansel", "Marril", "Pipino"
		};
		for (String name : names)
			list.add(new CharacterManager().create(name, it.d4nguard.rgrpg.d20.Character.TYPE));

		Iterator<Character> it = Context.getCurrentPlayer()
				.getCharacters()
				.keySet()
				.iterator();
		while (it.hasNext()) {
			Character c = it.next();
			assertTrue(list.contains(c));
		}
		assertEquals(6, Context.getCurrentPlayer()
				.getCharacters()
				.size());
	}

	@Test
	public final void testDelete() {
		testCreate();
		assertEquals(6, Context.getCurrentPlayer()
				.getCharacters()
				.size());
		Character c = null;
		Iterator<Character> it = Context.getCurrentPlayer()
				.getCharacters()
				.keySet()
				.iterator();
		while (it.hasNext() && c == null) {
			Character cur = it.next();
			if (cur.getInfo()
					.getName()
					.equals("Julius")) {
				c = cur;
			}
		}
		assertNotNull(c);
		assertTrue(new CharacterManager().delete("Julius"));
		assertEquals(5, Context.getCurrentPlayer()
				.getCharacters()
				.size());
	}

	@Test
	public final void testRename() {
		testCreate();
		Character c = null;
		Iterator<Character> it = Context.getCurrentPlayer()
				.getCharacters()
				.keySet()
				.iterator();
		while (it.hasNext() && c == null) {
			Character cur = it.next();
			if (cur.getInfo()
					.getName()
					.equals("Viktor")) {
				c = cur;
			}
		}
		assertNotNull(c);
		assertEquals("Victor", new CharacterManager().rename("Viktor", "Victor")
				.getInfo()
				.getName());
	}

	@Test
	public final void testUse() {
		testCreate();
		Character c = null;
		Iterator<Character> it = Context.getCurrentPlayer()
				.getCharacters()
				.keySet()
				.iterator();
		while (it.hasNext() && c == null) {
			Character cur = it.next();
			if (cur.getInfo()
					.getName()
					.equals("Marril")) {
				c = cur;
			}
		}
		assertNotNull(c);
		assertEquals(c, new CharacterManager().use("Marril"));
		assertEquals(c, Context.getCurrentCharacter());
	}

	@Test
	public final void testCurrent() {
		testCreate();
		new CharacterManager().use("Mialee");
		assertNotNull(Context.getCurrentCharacter());
		assertEquals("Mialee", Context.getCurrentCharacter()
				.getInfo()
				.getName());
	}

	@Test
	public final void testGet() {
		testCreate();
		Character c = new CharacterManager().get("Pipino");
		assertNotNull(c);
		assertEquals("Pipino", c.getInfo()
				.getName());
	}
}
