package it.d4nguard.rgrpg.util;

import it.d4nguard.rgrpg.d20.D20Character;
import it.d4nguard.rgrpg.profile.RPGCharacter;

import org.junit.Assert;
import org.junit.Test;

public class StringUtilsTest
{
	@Test
	public final void testPrettyPrintStringClassOfT()
	{
		System.out.print(StringUtils.prettyPrint("it.d4nguard.rgrpg",
						RPGCharacter.class));
		Assert.assertTrue(true);
	}

	@Test
	public final void testPrettyPrintClassOfT()
	{
		System.out.print(StringUtils.prettyPrint(D20Character.class));
		Assert.assertTrue(true);
	}
}
