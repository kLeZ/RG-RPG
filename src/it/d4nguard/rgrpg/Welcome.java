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
package it.d4nguard.rgrpg;

import it.d4nguard.rgrpg.commands.VersionCommand;

import java.util.Scanner;

public class Welcome {
	public static void print() {
		Context.println(Context.getString("welcome"));
		new VersionCommand().execute();
		Scanner scn = new Scanner(ClassLoader.getSystemClassLoader()
				.getResourceAsStream("it/d4nguard/rgrpg/Welcome.md"));
		while (scn.hasNext()) {
			Context.println(scn.nextLine());
		}
		scn.close();
		Context.println();
	}
}
