package it.d4nguard.rgrpg.util.dynacast;

import static org.junit.Assert.assertArrayEquals;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;

import java.util.Arrays;
import java.util.Calendar;
import java.util.List;

import org.junit.Test;

public class DynaManipulatorTest
{
	enum Gender
	{
		Male,
		Female;
	}

	class Bean
	{
		private int ranks;
		private long millis;
		private short year;
		private char control;
		private byte magic;
		private double pi;
		private float temp;
		private boolean all;
		private Gender gender;
		private String description;
		private byte[] data;
		private List<Integer> modifiers;

		public int getRanks()
		{
			return ranks;
		}

		public void setRanks(int ranks)
		{
			this.ranks = ranks;
		}

		public long getMillis()
		{
			return millis;
		}

		public void setMillis(long millis)
		{
			this.millis = millis;
		}

		public short getYear()
		{
			return year;
		}

		public void setYear(short year)
		{
			this.year = year;
		}

		public char getControl()
		{
			return control;
		}

		public void setControl(char control)
		{
			this.control = control;
		}

		public byte getMagic()
		{
			return magic;
		}

		public void setMagic(byte magic)
		{
			this.magic = magic;
		}

		public float getTemp()
		{
			return temp;
		}

		public void setTemp(float temp)
		{
			this.temp = temp;
		}

		public double getPi()
		{
			return pi;
		}

		public void setPi(double pi)
		{
			this.pi = pi;
		}

		public boolean isAll()
		{
			return all;
		}

		public void setAll(boolean all)
		{
			this.all = all;
		}

		public Gender getGender()
		{
			return gender;
		}

		public void setGender(Gender gender)
		{
			this.gender = gender;
		}

		public String getDescription()
		{
			return description;
		}

		public void setDescription(String description)
		{
			this.description = description;
		}

		public byte[] getData()
		{
			return data;
		}

		public void setData(byte[] data)
		{
			this.data = data;
		}

		public List<Integer> getModifiers()
		{
			return modifiers;
		}

		public void setModifiers(List<Integer> modifiers)
		{
			this.modifiers = modifiers;
		}
	}

	public final Bean b = new Bean();

	@Test
	public final void testSetValue()
	{
		try
		{
			DynaManipulator.setValue("description", b,
							"This would be a simple description");
			assertEquals("This would be a simple description",
							b.getDescription());
			long millis = System.currentTimeMillis();
			DynaManipulator.setValue("all", b, "true");
			assertEquals(true, b.isAll());
			DynaManipulator.setValue("control", b, "c");
			assertEquals('c', b.getControl());
			DynaManipulator.setValue("magic", b, "0b1010011");
			assertEquals(0b1010011, b.getMagic());
			DynaManipulator.setValue("millis", b, String.valueOf(millis));
			assertEquals(millis, b.getMillis());
			DynaManipulator.setValue("pi", b, String.valueOf(Math.PI));
			assertEquals(Math.PI, b.getPi(), Double.NaN);
			DynaManipulator.setValue("ranks", b, "13");
			assertEquals(13, b.getRanks());
			DynaManipulator.setValue("temp", b, "25.572");
			assertEquals((float) 25.572, b.getTemp(), Float.NaN);
			DynaManipulator.setValue(
							"year",
							b,
							String.valueOf(Calendar.getInstance().get(
											Calendar.YEAR)));
			assertEquals((short) Calendar.getInstance().get(Calendar.YEAR),
							b.getYear());
			DynaManipulator.setValue("gender", b, "Male");
			assertEquals(Gender.Male, b.getGender());
			byte[] data = new byte[] { 0x1C, 0x75, 0x08, 0x4B, (byte) 0xd6, 0x6A };
			DynaManipulator.setValue("data", b, TypeAdapter.toString(data));
			assertArrayEquals(data, b.getData());

			List<Integer> modifiers = Arrays.asList(new Integer[] { 1, 5, 4, 8, 6, 4, 23, 5, 87, 5, 2, 4 });
			DynaManipulator.setValue("modifiers", b,
							TypeAdapter.toString(modifiers));
			assertEquals(modifiers, b.getModifiers());
		}
		catch (DynaManipulatorException e)
		{
			e.printStackTrace();
			fail(e.getLocalizedMessage());
		}
	}

	@Test
	public final void testGetValue()
	{
		try
		{
			long millis = System.currentTimeMillis();
			b.setAll(true);
			assertEquals(true, DynaManipulator.getValue("all", b));
			b.setControl('c');
			assertEquals('c', DynaManipulator.getValue("control", b));
			b.setMagic((byte) 0b1010011);
			assertEquals((byte) 0b1010011, DynaManipulator.getValue("magic", b));
			b.setMillis(millis);
			assertEquals(millis, DynaManipulator.getValue("millis", b));
			b.setPi(Math.PI);
			assertEquals(Math.PI, DynaManipulator.getValue("pi", b));
			b.setRanks(13);
			assertEquals(13, DynaManipulator.getValue("ranks", b));
			b.setTemp((float) 25.572);
			assertEquals((float) 25.572, DynaManipulator.getValue("temp", b));
			b.setYear((short) Calendar.getInstance().get(Calendar.YEAR));
			assertEquals((short) Calendar.getInstance().get(Calendar.YEAR),
							DynaManipulator.getValue("year", b));
			b.setGender(Gender.Male);
			assertEquals(Gender.Male, DynaManipulator.getValue("gender", b));
			b.setDescription("This would be a simple description");
			assertEquals("This would be a simple description",
							DynaManipulator.getValue("description", b));
			byte[] data = new byte[] { 0x1C, 0x75, 0x08, 0x4B, (byte) 0xd6, 0x6A };
			b.setData(data);
			assertArrayEquals(data,
							(byte[]) DynaManipulator.getValue("data", b));
			List<Integer> modifiers = Arrays.asList(new Integer[] { 1, 5, 4, 8, 6, 4, 23, 5, 87, 5, 2, 4 });
			b.setModifiers(modifiers);
			assertEquals(modifiers, DynaManipulator.getValue("modifiers", b));
		}
		catch (DynaManipulatorException e)
		{
			e.printStackTrace();
		}
	}
}
