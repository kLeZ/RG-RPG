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

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.IOException;

public class PromptFeeder
{
    public static final String LF = System.getProperty("line.separator");
    public static final String PROMPT_ENV = "RGRPG_PROMPT";
    public static final String PROMPT_DEFAULT = "\\u@\\h:\\w> ";

    private final String prompt;

    public PromptFeeder()
    {
	String prompt = System.getenv(PROMPT_ENV);
	if (prompt == null || prompt.isEmpty())
	    this.prompt = PROMPT_DEFAULT;
	else this.prompt = prompt;
    }

    public String getHelp()
    {
	StringBuilder sb = new StringBuilder();
	sb.append("STRINGA DI PROMPT").append(LF);
	sb.append("=================").append(LF);
	sb.append(LF);
	sb.append("La shell di base che completa RG-RPG, rendendolo un gioco interattivo, comprende anche un prompt che aiuta nell'utilizzo. ");
	sb.append("Questo è personalizzabile in base alle esigenze, esportando la variabile \"").append(PROMPT_ENV).append("\".").append(LF).append(LF);
	sb.append("Le funzioni utilizzabili sono le seguenti:").append(LF);
	sb.append("\t\t\\u:\tStampa il nome dell'utente corrente.").append(LF);
	sb.append("\t\t\\h:\tStampa il nome dell'host, fino al primo punto.").append(LF);
	sb.append("\t\t\\H:\tStampa il nome dell'host, completo.").append(LF);
	sb.append("\t\t\\w:\tStampa la directory corrente, sostituendo la home dell'utente corrente con una tilde (~).").append(LF);
	sb.append("\t\t\\r:\tStampa un Ritorno di Carrello (Carriage Return) letterale.").append(LF);
	sb.append("\t\t\\n:\tStampa un Aumento di Linea (Line Feed) letterale.").append(LF);
	sb.append("\t\t\\\\:\tStampa un backslash (\\) letterale.").append(LF).append(LF);
	return sb.toString();
    }

    public String get()
    {
	StringBuilder ret = new StringBuilder();
	char[] tokens = this.prompt.toCharArray();
	boolean isCmd = false;

	for (int i = 0; i < tokens.length; i++)
        {
	    char token = tokens[i];
	    if (isCmd)
	    {
		isCmd = false;
		switch(token) // man 1 bash:1576
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
			BufferedReader br = new BufferedReader(new InputStreamReader(p.getInputStream()));
			String hostname = br.readLine();
			int dot = hostname.indexOf('.');
			if (token == 'h' && dot > 0) hostname = hostname.substring(0, dot);
			ret.append(hostname);
			br.close();
			br = null;
			p = null;
			System.gc();
		    }
		    catch(InterruptedException e)
		    {
			e.printStackTrace();
		    }
		    catch(IOException e)
		    {
			e.printStackTrace();
		    }
		    break;
		case 'w':
		    String currentDir = System.getProperty("user.dir");
		    currentDir = currentDir.replace(System.getProperty("user.home"), "~");
		    ret.append(currentDir);
		    break;
		case 'r':
		    ret.append('\r');
		    break;
		case 'n':
		    ret.append('\n');
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
