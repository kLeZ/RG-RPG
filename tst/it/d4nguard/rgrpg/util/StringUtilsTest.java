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
package it.d4nguard.rgrpg.util;

import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class StringUtilsTest {
	@Test
	public final void testPrettyPrintStringClassOfT() {
		//		System.out.print(StringUtils.prettyPrint("it.d4nguard.rgrpg", Item.class));
		//		Assert.assertTrue(true);
	}

	@Test
	public final void testPrettyPrintClassOfT() {
		//		System.out.print(StringUtils.prettyPrint(Character.class));
		//		Assert.assertTrue(true);
	}

	@Test
	public final void testGetBetween() {
		char before = '[', after = ']';
		String s, fmt = "%s %s%s%s %s";
		String left = "this is the left text";
		String center = "this is the center text";
		String right = "this is the right text";

		Triplet<String, String, String> t;

		//	000
		s = String.format(fmt, "", before, "", after, "");
		t = StringUtils.getBetween(s, before, after);

		//		System.out.println(s);
		//		System.out.println(t);

		assertEquals("", t.getLeft()
				.trim());
		assertEquals("", t.getCenter()
				.trim());
		assertEquals("", t.getRight()
				.trim());

		//	001
		s = String.format(fmt, "", before, "", after, right);
		t = StringUtils.getBetween(s, before, after);

		//		System.out.println(s);
		//		System.out.println(t);

		assertEquals("", t.getLeft()
				.trim());
		assertEquals("", t.getCenter()
				.trim());
		assertEquals(right, t.getRight()
				.trim());

		//	011
		s = String.format(fmt, "", before, center, after, right);
		t = StringUtils.getBetween(s, before, after);

		//		System.out.println(s);
		//		System.out.println(t);

		assertEquals("", t.getLeft()
				.trim());
		assertEquals(center, t.getCenter()
				.trim());
		assertEquals(right, t.getRight()
				.trim());

		//	010
		s = String.format(fmt, "", before, center, after, "");
		t = StringUtils.getBetween(s, before, after);

		//		System.out.println(s);
		//		System.out.println(t);

		assertEquals("", t.getLeft()
				.trim());
		assertEquals(center, t.getCenter()
				.trim());
		assertEquals("", t.getRight()
				.trim());

		//	110
		s = String.format(fmt, left, before, center, after, "");
		t = StringUtils.getBetween(s, before, after);

		//		System.out.println(s);
		//		System.out.println(t);

		assertEquals(left, t.getLeft()
				.trim());
		assertEquals(center, t.getCenter()
				.trim());
		assertEquals("", t.getRight()
				.trim());

		//	100
		s = String.format(fmt, left, before, "", after, "");
		t = StringUtils.getBetween(s, before, after);

		//		System.out.println(s);
		//		System.out.println(t);

		assertEquals(left, t.getLeft()
				.trim());
		assertEquals("", t.getCenter()
				.trim());
		assertEquals("", t.getRight()
				.trim());

		//	101
		s = String.format(fmt, left, before, "", after, right);
		t = StringUtils.getBetween(s, before, after);

		//		System.out.println(s);
		//		System.out.println(t);

		assertEquals(left, t.getLeft()
				.trim());
		assertEquals("", t.getCenter()
				.trim());
		assertEquals(right, t.getRight()
				.trim());

		//	111
		s = String.format(fmt, left, before, center, after, right);
		t = StringUtils.getBetween(s, before, after);

		//		System.out.println(s);
		//		System.out.println(t);

		assertEquals(left, t.getLeft()
				.trim());
		assertEquals(center, t.getCenter()
				.trim());
		assertEquals(right, t.getRight()
				.trim());

		//	Special cases
		//	Total blank
		s = "";
		t = StringUtils.getBetween(s, before, after);

		//		System.out.println(s);
		//		System.out.println(t);

		assertEquals("", t.getLeft()
				.trim());
		assertEquals("", t.getCenter()
				.trim());
		assertEquals("", t.getRight()
				.trim());
	}
}
