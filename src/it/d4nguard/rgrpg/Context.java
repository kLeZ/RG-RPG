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

import it.d4nguard.rgrpg.profile.CharacterInfo;
import it.d4nguard.rgrpg.profile.Player;
import it.d4nguard.rgrpg.profile.RPGCharacter;
import it.d4nguard.rgrpg.util.BundleSet;
import it.d4nguard.rgrpg.util.StringUtils;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Map.Entry;
import java.util.ResourceBundle;
import java.util.Set;

public class Context
{
	private static final String I18N_PACKAGE = "it.d4nguard.rgrpg.i18n";
	private static final String STRINGS = I18N_PACKAGE.concat(".strings.Strings");
	private static final String FEATS = I18N_PACKAGE.concat(".d20.feats.Feats");
	private static final String LANGUAGES = I18N_PACKAGE.concat(".d20.languages.Languages");
	private static final String ABILITY_SCORES = I18N_PACKAGE.concat(".d20.abilityscores.AbilityScores");
	private static final String DB_PATH = "~/.rgrpg/session.dat";

	private static class Singleton implements Serializable
	{
		private static final long serialVersionUID = -5518515093530450430L;

		private static Singleton Current = new Singleton();
		private static transient BundleSet bundles;

		private boolean debug = false;
		private Player current;

		private final Set<Player> players = new HashSet<Player>();

		private Singleton()
		{
			init();
		}

		private void init()
		{
			if (bundles == null) bundles = new BundleSet();
			bundles.add(STRINGS);
			bundles.add(FEATS);
			bundles.add(LANGUAGES);
			bundles.add(ABILITY_SCORES);
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

		private void load(String path)
		{
			try
			{
				FileInputStream fis = new FileInputStream(getDBPath(path));
				ObjectInputStream ois = new ObjectInputStream(fis);
				Singleton.Current = (Singleton) ois.readObject();
				ois.close();
				fis.close();
			}
			catch (ClassNotFoundException | IOException e)
			{
				e.printStackTrace();
			}
			init();
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
				e.printStackTrace();
			}
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

	public static RPGCharacter getCurrentCharacter()
	{
		RPGCharacter ret = null;
		Iterator<Entry<RPGCharacter, CharacterInfo>> it = Context.getCurrentPlayer().getCharacters().entrySet().iterator();
		while (it.hasNext() && ret == null)
		{
			Entry<RPGCharacter, CharacterInfo> e = it.next();
			if (e.getValue().isCurrent()) ret = e.getKey();
		}
		return ret;
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

	public static void load(String path)
	{
		Singleton.Current.load(path);
	}

	public static void save(String path)
	{
		Singleton.Current.save(path);
	}
}
