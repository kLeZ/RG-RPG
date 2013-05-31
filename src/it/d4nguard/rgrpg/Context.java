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

import it.d4nguard.rgrpg.profile.Player;
import it.d4nguard.rgrpg.util.BundleSet;

import java.util.HashSet;
import java.util.ResourceBundle;
import java.util.Set;

public class Context
{
	private static final String I18N_PACKAGE = "it.d4nguard.rgrpg.i18n";
	private static final String STRINGS = I18N_PACKAGE.concat(".strings.Strings");
	private static final String FEATS = I18N_PACKAGE.concat(".d20.feats.Feats");
	private static final String LANGUAGES = I18N_PACKAGE.concat(".d20.languages.Languages");
	private static final String ABILITY_SCORES = I18N_PACKAGE.concat(".d20.abilityscores.AbilityScores");

	private static enum Singleton
	{
		Current;

		private boolean debug = false;
		private final Set<Player> players;
		private final BundleSet bundles;
		private Player current;

		private Singleton()
		{
			players = new HashSet<Player>();
			bundles = new BundleSet();
			bundles.add(STRINGS);
			bundles.add(FEATS);
			bundles.add(LANGUAGES);
		}

		public boolean isDebug()
		{
			return debug;
		}

		public void setDebug(boolean debug)
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
}
