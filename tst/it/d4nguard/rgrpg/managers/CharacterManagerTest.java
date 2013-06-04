package it.d4nguard.rgrpg.managers;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;
import it.d4nguard.rgrpg.Context;
import it.d4nguard.rgrpg.profile.RPGCharacter;

import java.util.ArrayList;
import java.util.Iterator;

import org.junit.Before;
import org.junit.Test;

public class CharacterManagerTest
{
	@Before
	public void setUp()
	{
		Context.wipe();
		Context.setCurrentPlayer(new PlayerManager().create("kLeZ",
						new Object[] {}));
	}

	@Test
	public final void testCreate()
	{
		Context.clearCharacters(null);
		ArrayList<RPGCharacter> list = new ArrayList<RPGCharacter>();
		String[] names = new String[] { "Julius", "Mialee", "Viktor", "Hansel", "Marril", "Pipino" };
		for (String name : names)
			list.add(new CharacterManager().create(name, "d20"));

		Iterator<RPGCharacter> it = Context.getCurrentPlayer().getCharacters().keySet().iterator();
		while (it.hasNext())
		{
			RPGCharacter c = it.next();
			assertTrue(list.contains(c));
		}
		assertEquals(6, Context.getCurrentPlayer().getCharacters().size());
	}

	@Test
	public final void testDelete()
	{
		testCreate();
		assertEquals(6, Context.getCurrentPlayer().getCharacters().size());
		RPGCharacter c = null;
		Iterator<RPGCharacter> it = Context.getCurrentPlayer().getCharacters().keySet().iterator();
		while (it.hasNext() && c == null)
		{
			RPGCharacter cur = it.next();
			if (cur.getInfo().getName().equals("Julius"))
			{
				c = cur;
			}
		}
		assertNotNull(c);
		assertTrue(new CharacterManager().delete("Julius"));
		assertEquals(5, Context.getCurrentPlayer().getCharacters().size());
	}

	@Test
	public final void testRename()
	{
		testCreate();
		RPGCharacter c = null;
		Iterator<RPGCharacter> it = Context.getCurrentPlayer().getCharacters().keySet().iterator();
		while (it.hasNext() && c == null)
		{
			RPGCharacter cur = it.next();
			if (cur.getInfo().getName().equals("Viktor"))
			{
				c = cur;
			}
		}
		assertNotNull(c);
		assertEquals("Victor",
						new CharacterManager().rename("Viktor", "Victor").getInfo().getName());
	}

	@Test
	public final void testUse()
	{
		testCreate();
		RPGCharacter c = null;
		Iterator<RPGCharacter> it = Context.getCurrentPlayer().getCharacters().keySet().iterator();
		while (it.hasNext() && c == null)
		{
			RPGCharacter cur = it.next();
			if (cur.getInfo().getName().equals("Marril"))
			{
				c = cur;
			}
		}
		assertNotNull(c);
		assertEquals(c, new CharacterManager().use("Marril"));
		assertEquals(c, Context.getCurrentCharacter());
	}

	@Test
	public final void testCurrent()
	{
		testCreate();
		new CharacterManager().use("Mialee");
		assertNotNull(Context.getCurrentCharacter());
		assertEquals("Mialee",
						Context.getCurrentCharacter().getInfo().getName());
	}

	@Test
	public final void testGet()
	{
		testCreate();
		RPGCharacter c = new CharacterManager().get("Pipino");
		assertNotNull(c);
		assertEquals("Pipino", c.getInfo().getName());
	}
}
