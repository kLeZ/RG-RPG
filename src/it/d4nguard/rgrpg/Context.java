// RG-RPG is a Java-based text, roleplaying-gal game, in which you
// have to carry many girls. The RG-RPG acronym is a recursive one and
// it means "RG-RPG is a Gal Role playing game Pointing on Girls."
// Copyright (C) 2013 by Alessandro Accardo <julius8774@gmail.com>
// 
// This program is free software: you can redistribute it and/or modify
// it under the terms of the GNU General Public License as published by
// the Free Software Foundation, either version 2 of the License, or (at
// your option) any later version.
// 
// This program is distributed in the hope that it will be useful, but
// WITHOUT ANY WARRANTY; without even the implied warranty of
// MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the GNU
// General Public License for more details.
// 
// You should have received a copy of the GNU General Public License
// along with this program.  If not, see <http://www.gnu.org/licenses/>.
// 
package it.d4nguard.rgrpg;

import static org.reflections.util.ClasspathHelper.forPackage;
import it.d4nguard.rgrpg.profile.Character;
import it.d4nguard.rgrpg.profile.CharacterInfo;
import it.d4nguard.rgrpg.profile.Player;
import it.d4nguard.rgrpg.util.BundleSet;
import it.d4nguard.rgrpg.util.StringUtils;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.PrintWriter;
import java.io.Serializable;
import java.io.StringWriter;
import java.util.Enumeration;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Map.Entry;
import java.util.ResourceBundle;
import java.util.Set;

import jline.Terminal;
import jline.TerminalFactory;
import jline.console.ConsoleReader;

import org.reflections.Reflections;
import org.reflections.scanners.SubTypesScanner;

public class Context
{
	private static final String PACKAGE = Context.class.getPackage().getName();
	private static final String I18N_PACKAGE = PACKAGE.concat(".i18n");
	private static final String STRINGS = I18N_PACKAGE.concat(".strings.Strings");
	private static final String FEATS = I18N_PACKAGE.concat(".d20.feats.Feats");
	private static final String LANGUAGES = I18N_PACKAGE.concat(".d20.languages.Languages");
	private static final String ABILITY_SCORES = I18N_PACKAGE.concat(".d20.abilityscores.AbilityScores");
	private static final String DB_PATH = "~/.rgrpg/session.dat";

	private static class Singleton implements Serializable
	{
		private static final long serialVersionUID = -5518515093530450430L;

		private static final SubTypesScanner STS = new SubTypesScanner(false);

		private static Singleton Current = new Singleton();

		private transient BundleSet bundles;
		private transient ConsoleReader reader;

		private boolean debug = false;
		private Player current;

		private final Set<Player> players = new HashSet<Player>();

		private Singleton()
		{
			init();
		}

		private void init()
		{
			TerminalFactory.configure("unix");
			Terminal term = TerminalFactory.create();

			try
			{
				setReader(new ConsoleReader("RG-RPG", System.in, System.out,
								term, "utf-8"));

				if (bundles == null) bundles = new BundleSet();
				bundles.add(STRINGS);
				bundles.add(FEATS);
				bundles.add(LANGUAGES);
				bundles.add(ABILITY_SCORES);
			}
			catch (IOException e)
			{
				e.printStackTrace();
				System.exit(1);
			}
		}

		private boolean isDebug()
		{
			return debug;
		}

		private void setDebug(boolean debug)
		{
			this.debug = debug;
		}

		private ResourceBundle getBundle(String name)
		{
			init();
			return bundles.get(name);
		}

		private Set<Player> getPlayers()
		{
			return players;
		}

		private Player getCurrent()
		{
			return current;
		}

		private void setCurrent(Player current)
		{
			this.current = current;
		}

		private void wipe()
		{
			players.clear();
			current = null;
			debug = false;
		}

		private String getDBPath(String path) throws IOException
		{
			String db = DB_PATH;
			if (!StringUtils.isNullOrWhitespace(path)) db = path;
			db = db.replace("~", System.getProperty("user.home"));
			File f = new File(db);
			if (!f.exists())
			{
				f.getParentFile().mkdirs();
				f.createNewFile();
			}
			return db;
		}

		public void delete(String path)
		{
			try
			{
				File f = new File(getDBPath(path));
				f.delete();
			}
			catch (IOException e)
			{
				Context.printThrowable(e);
			}
		}

		private void load(String path)
		{
			try
			{
				if (new File(getDBPath(path)).exists())
				{
					FileInputStream fis = new FileInputStream(getDBPath(path));
					ObjectInputStream ois = new ObjectInputStream(fis);
					Singleton.Current = (Singleton) ois.readObject();
					ois.close();
					fis.close();
					init();
				}
				else
				{
					getReader().println(
									"Could not load the save. File doesn't exists.");
				}
			}
			catch (ClassNotFoundException | IOException e)
			{
				Context.printThrowable(e);
			}
		}

		private void save(String path)
		{
			try
			{
				FileOutputStream fos = new FileOutputStream(getDBPath(path));
				ObjectOutputStream oos = new ObjectOutputStream(fos);
				oos.writeObject(Singleton.Current);
				oos.close();
				fos.close();
			}
			catch (IOException e)
			{
				Context.printThrowable(e);
			}
		}

		private Reflections getReflections()
		{
			return new Reflections(forPackage(PACKAGE), STS);
		}

		public ConsoleReader getReader()
		{
			return reader;
		}

		public void setReader(ConsoleReader reader)
		{
			this.reader = reader;
		}
	};

	public static boolean isDebug()
	{
		return Singleton.Current.isDebug();
	}

	public static void setDebug(boolean debug)
	{
		Singleton.Current.setDebug(debug);
	}

	public static Set<Player> getPlayers()
	{
		return Singleton.Current.getPlayers();
	}

	public static Player getCurrentPlayer()
	{
		return Singleton.Current.getCurrent();
	}

	public static void setCurrentPlayer(Player current)
	{
		Singleton.Current.setCurrent(current);
	}

	public static boolean hasCurrentPlayer()
	{
		return Singleton.Current.getCurrent() != null;
	}

	public static String getString(String name)
	{
		return Singleton.Current.getBundle(STRINGS).getString(name);
	}

	public static String getFeat(String name)
	{
		return Singleton.Current.getBundle(FEATS).getString(name);
	}

	public static String getLanguage(String name)
	{
		return Singleton.Current.getBundle(LANGUAGES).getString(name);
	}

	public static String getAbilityScore(String name)
	{
		return Singleton.Current.getBundle(ABILITY_SCORES).getString(name);
	}

	public static Enumeration<String> getAvailableAbilityScores()
	{
		return Singleton.Current.getBundle(ABILITY_SCORES).getKeys();
	}

	public static Character getCurrentCharacter()
	{
		Character ret = null;
		if (hasCurrentPlayer())
		{
			Iterator<Entry<Character, CharacterInfo>> it;
			it = Context.getCurrentPlayer().getCharacters().entrySet().iterator();
			while (it.hasNext() && ret == null)
			{
				Entry<Character, CharacterInfo> e = it.next();
				if (e.getValue().isCurrent()) ret = e.getKey();
			}
		}
		return ret;
	}

	public static boolean hasCurrentCharacter()
	{
		return getCurrentCharacter() != null;
	}

	public static void wipe()
	{
		Singleton.Current.wipe();
	}

	public static void clearCharacters(String player)
	{
		for (Player p : getPlayers())
			if (player == null || p.getName().equals(player)) p.getCharacters().clear();
	}

	public static void deleteDefault()
	{
		delete("");
	}

	public static void delete(String path)
	{
		Singleton.Current.delete(path);
	}

	public static void loadDefault()
	{
		load("");
	}

	public static void load(String path)
	{
		Singleton.Current.load(path);
	}

	public static void save(String path)
	{
		Singleton.Current.save(path);
	}

	public static void saveDefault()
	{
		save("");
	}

	public static Reflections getReflections()
	{
		return Singleton.Current.getReflections();
	}

	public static void setReader(ConsoleReader reader)
	{
		Singleton.Current.setReader(reader);
	}

	public static void print(Object o)
	{
		print(o.toString());
	}

	public static void println(Object o)
	{
		println(o.toString());
	}

	public static void println(boolean b)
	{
		println(String.valueOf(b));
	}

	public static void print(CharSequence s)
	{
		try
		{
			Singleton.Current.getReader().print(s);
			Singleton.Current.getReader().flush();
		}
		catch (IOException e)
		{
			printThrowable(e);
		}
	}

	public static void println(CharSequence s)
	{
		try
		{
			Singleton.Current.getReader().println(s);
			Singleton.Current.getReader().flush();
		}
		catch (IOException e)
		{
			printThrowable(e);
		}
	}

	public static void println()
	{
		try
		{
			Singleton.Current.getReader().println();
			Singleton.Current.getReader().flush();
		}
		catch (IOException e)
		{
			printThrowable(e);
		}
	}

	public static String readLine() throws IOException
	{
		return Singleton.Current.getReader().readLine();
	}

	public static void printThrowable(Throwable t)
	{
		if (isDebug()) println(t);
		else println(t.getMessage() == null ? t.getClass().getSimpleName() : t.getMessage());
	}

	public static void print(Throwable t)
	{
		StringWriter sw = new StringWriter();
		PrintWriter pw = new PrintWriter(sw);
		t.printStackTrace(pw);
		try
		{
			Singleton.Current.getReader().print(sw.toString());
			Singleton.Current.getReader().flush();

		}
		catch (IOException e)
		{
			e.printStackTrace();
		}
	}

	public static void println(Throwable t)
	{
		StringWriter sw = new StringWriter();
		PrintWriter pw = new PrintWriter(sw);
		t.printStackTrace(pw);
		try
		{
			Singleton.Current.getReader().println(sw.toString());
			Singleton.Current.getReader().flush();
		}
		catch (IOException e)
		{
			e.printStackTrace();
		}
	}
}
