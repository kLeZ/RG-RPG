/*
 * Copyright (C) 2021 Alessandro 'kLeZ' Accardo
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
package it.d4nguard.rgrpg.util;

import java.io.BufferedInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.net.URLConnection;
import java.util.Collections;
import java.util.List;
import java.util.Locale;
import java.util.ResourceBundle;

public class XMLResourceBundleControl extends ResourceBundle.Control {
	private static final String XML = "xml";

	public static void main(String[] args) {
		ResourceBundle bundle = ResourceBundle.getBundle("Strings", new XMLResourceBundleControl());
		String string = bundle.getString("Key");
		System.out.println("Key: " + string);
	}

	public List<String> getFormats(String baseName) {
		return Collections.singletonList(XML);
	}

	public ResourceBundle newBundle(String baseName, Locale locale, String format, ClassLoader loader, boolean reload) throws IOException {

		if (baseName == null || locale == null || format == null || loader == null) {
			throw new NullPointerException();
		}
		ResourceBundle bundle;
		if (!format.equals(XML)) {
			return null;
		}

		String bundleName = toBundleName(baseName, locale);
		String resourceName = toResourceName(bundleName, format);
		URL url = loader.getResource(resourceName);
		if (url == null) {
			return null;
		}
		URLConnection connection = url.openConnection();
		if (connection == null) {
			return null;
		}
		if (reload) {
			connection.setUseCaches(false);
		}
		InputStream stream = connection.getInputStream();
		if (stream == null) {
			return null;
		}
		BufferedInputStream bis = new BufferedInputStream(stream);
		bundle = new XMLResourceBundle(bis);
		bis.close();

		return bundle;
	}
}
