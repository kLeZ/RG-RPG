package it.d4nguard.rgrpg.util;

import static org.junit.Assert.assertEquals;
import it.d4nguard.rgrpg.Context;
import it.d4nguard.rgrpg.d20.items.Item;
import it.d4nguard.rgrpg.profile.Character;

import org.junit.Assert;
import org.junit.Test;

public class StringUtilsTest
{
	@Test
	public final void testPrettyPrintStringClassOfT()
	{
		System.out.print(StringUtils.prettyPrint("it.d4nguard.rgrpg",
						Item.class));
		Assert.assertTrue(true);
	}

	@Test
	public final void testPrettyPrintClassOfT()
	{
		System.out.print(StringUtils.prettyPrint(Character.class));
		Assert.assertTrue(true);
	}

	@Test
	public final void testGetBetween()
	{
		char before = '[', after = ']';
		String s, fmt = "%s %s%s%s %s";
		String left = "this is the left text";
		String center = "this is the center text";
		String right = "this is the right text";

		Triplet<String, String, String> t;

		//	000
		s = String.format(fmt, "", before, "", after, "");
		t = StringUtils.getBetween(s, before, after);

		System.out.println(s);
		System.out.println(t);

		assertEquals("", t.getLeft().trim());
		assertEquals("", t.getCenter().trim());
		assertEquals("", t.getRight().trim());

		//	001
		s = String.format(fmt, "", before, "", after, right);
		t = StringUtils.getBetween(s, before, after);

		System.out.println(s);
		System.out.println(t);

		assertEquals("", t.getLeft().trim());
		assertEquals("", t.getCenter().trim());
		assertEquals(right, t.getRight().trim());

		//	011
		s = String.format(fmt, "", before, center, after, right);
		t = StringUtils.getBetween(s, before, after);

		System.out.println(s);
		System.out.println(t);

		assertEquals("", t.getLeft().trim());
		assertEquals(center, t.getCenter().trim());
		assertEquals(right, t.getRight().trim());

		//	010
		s = String.format(fmt, "", before, center, after, "");
		t = StringUtils.getBetween(s, before, after);

		System.out.println(s);
		System.out.println(t);

		assertEquals("", t.getLeft().trim());
		assertEquals(center, t.getCenter().trim());
		assertEquals("", t.getRight().trim());

		//	110
		s = String.format(fmt, left, before, center, after, "");
		t = StringUtils.getBetween(s, before, after);

		System.out.println(s);
		System.out.println(t);

		assertEquals(left, t.getLeft().trim());
		assertEquals(center, t.getCenter().trim());
		assertEquals("", t.getRight().trim());

		//	100
		s = String.format(fmt, left, before, "", after, "");
		t = StringUtils.getBetween(s, before, after);

		System.out.println(s);
		System.out.println(t);

		assertEquals(left, t.getLeft().trim());
		assertEquals("", t.getCenter().trim());
		assertEquals("", t.getRight().trim());

		//	101
		s = String.format(fmt, left, before, "", after, right);
		t = StringUtils.getBetween(s, before, after);

		System.out.println(s);
		System.out.println(t);

		assertEquals(left, t.getLeft().trim());
		assertEquals("", t.getCenter().trim());
		assertEquals(right, t.getRight().trim());

		//	111
		s = String.format(fmt, left, before, center, after, right);
		t = StringUtils.getBetween(s, before, after);

		System.out.println(s);
		System.out.println(t);

		assertEquals(left, t.getLeft().trim());
		assertEquals(center, t.getCenter().trim());
		assertEquals(right, t.getRight().trim());

		//	Special cases
		//	Total blank
		s = "";
		t = StringUtils.getBetween(s, before, after);

		System.out.println(s);
		System.out.println(t);

		assertEquals("", t.getLeft().trim());
		assertEquals("", t.getCenter().trim());
		assertEquals("", t.getRight().trim());
	}

	@Test
	public final void testGenericToString()
	{
		try
		{
			Context.loadDefault();
			if (Context.hasCurrentCharacter())
			{
				Character c = Context.getCurrentCharacter();
				System.out.println(StringUtils.genericToString(c.getClass(), c,
								"serialVersionUID", "owner",
								"\\$SWITCH_TABLE\\$.*"));
			}
		}
		catch (Throwable t)
		{
			Assert.fail(t.getMessage());
		}
	}
}
