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
package it.d4nguard.rgrpg.util;

import it.d4nguard.rgrpg.Context;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class PromptFeeder
{
	public static final String PROMPT_ENV = "RGRPG_PROMPT";
	public static final String PROMPT_DEFAULT = "\\s@\\p> ";

	private final String prompt;

	public PromptFeeder()
	{
		String prompt = System.getenv(PROMPT_ENV);
		if (prompt == null || prompt.isEmpty()) this.prompt = PROMPT_DEFAULT;
		else this.prompt = prompt;
	}

	public String getHelp()
	{
		return Context.getString("promptfeeder.help");
	}

	public String get()
	{
		StringCompiler ret = new StringCompiler();
		char[] tokens = this.prompt.toCharArray();
		boolean isCmd = false;

		for (int i = 0; i < tokens.length; i++)
		{
			char token = tokens[i];
			if (isCmd)
			{
				isCmd = false;
				switch (token)
				// man 1 bash:1576
				{
					case '\\':
						ret.append(token);
						break;
					case 'u':
						ret.append(System.getProperty("user.name"));
						break;
					case 'H':
					case 'h':
						try
						{
							Process p = Runtime.getRuntime().exec("hostname");
							p.waitFor();
							BufferedReader br = new BufferedReader(
											new InputStreamReader(
															p.getInputStream()));
							String hostname = br.readLine();
							int dot = hostname.indexOf('.');
							if (token == 'h' && dot > 0) hostname = hostname.substring(
											0, dot);
							ret.append(hostname);
							br.close();
							br = null;
							p = null;
							System.gc();
						}
						catch (InterruptedException e)
						{
							e.printStackTrace();
						}
						catch (IOException e)
						{
							e.printStackTrace();
						}
						break;
					case 'w':
						String currentDir = System.getProperty("user.dir");
						currentDir = currentDir.replace(
										System.getProperty("user.home"), "~");
						ret.append(currentDir);
						break;
					case 'r':
						ret.append('\r');
						break;
					case 'n':
						ret.append('\n');
						break;
					case 'p':
						String player = "No-player-selected";
						if (Context.hasCurrentPlayer())
						{
							player = Context.getCurrentPlayer().getName();
						}
						ret.append('[').append(player).append(']');
						break;
					case 's':
						String character = "No-character-selected";
						if (Context.hasCurrentCharacter())
						{
							character = Context.getCurrentCharacter().getInfo().getName();
						}
						ret.append('[').append(character).append(']');
						break;
					default:
						ret.append(token);
						break;
				}
			}
			else if (token == '\\') isCmd = true;
			else ret.append(token);
		}
		return ret.toString();
	}
}
