/*
 * Copyright (C) 2019 Alessandro 'kLeZ' Accardo
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
package it.d4nguard.rgrpg.util.dynacast.adapters;

import it.d4nguard.rgrpg.util.dynacast.Adapter;
import it.d4nguard.rgrpg.util.dynacast.Provider;
import it.d4nguard.rgrpg.util.dynacast.factories.AdapterFactory;

import java.lang.reflect.Type;
import java.util.HashMap;
import java.util.Map;

/**
 * Implements a {@link Provider} of {@link AdapterFactory} that provides
 * {@link Adapter}s for all the primitive types, and their wrapper objects.
 *
 * @author kLeZ-hAcK
 */
public class PrimitivesAdapterProvider implements Provider<AdapterFactory<?>> {
	/**
	 * {@inheritDoc}
	 */
	@Override
	@SuppressWarnings("unchecked")
	public Map<Class<?>, AdapterFactory<?>> get() {
		Map<Class<?>, AdapterFactory<?>> ret = new HashMap<>();
		ret.put(Boolean.class, new AdapterFactory<Boolean>() {
			/**
			 * {@inheritDoc}
			 */
			@Override
			public Adapter<Boolean> create(final Type type) {
				return new Adapter<Boolean>() {
					/**
					 * {@inheritDoc}
					 */
					@Override
					public Boolean adapt(String value) {
						return Boolean.valueOf(value);
					}

					/**
					 * {@inheritDoc}
					 */
					@Override
					public Class<Boolean> getType() {
						return Boolean.class;
					}
				};
			}
		});
		ret.put(Byte.class, new AdapterFactory<Byte>() {
			/**
			 * {@inheritDoc}
			 */
			@Override
			public Adapter<Byte> create(Type type) {
				return new Adapter<Byte>() {
					/**
					 * {@inheritDoc}
					 */
					@Override
					public Byte adapt(String value) {
						char sign = value.charAt(0);
						int i = sign == '+' || sign == '-' ? 0 : -1;
						if (value.startsWith("0b", ++i))
							return Byte.valueOf(value.substring(i + 2), 2);
						else
							return Byte.decode(value);
					}

					/**
					 * {@inheritDoc}
					 */
					@Override
					public Class<Byte> getType() {
						return Byte.class;
					}
				};
			}
		});
		ret.put(Character.class, new AdapterFactory<Character>() {
			/**
			 * {@inheritDoc}
			 */
			@Override
			public Adapter<Character> create(Type type) {
				return new Adapter<Character>() {
					/**
					 * {@inheritDoc}
					 */
					@Override
					public Character adapt(String value) {
						return value.isEmpty() ? '\0' : value.charAt(0);
					}

					/**
					 * {@inheritDoc}
					 */
					@Override
					public Class<Character> getType() {
						return Character.class;
					}
				};
			}
		});
		ret.put(Double.class, new AdapterFactory<Double>() {
			/**
			 * {@inheritDoc}
			 */
			@Override
			public Adapter<Double> create(Type type) {
				return new Adapter<Double>() {
					/**
					 * {@inheritDoc}
					 */
					@Override
					public Double adapt(String value) {
						return Double.valueOf(value);
					}

					/**
					 * {@inheritDoc}
					 */
					@Override
					public Class<Double> getType() {
						return Double.class;
					}
				};
			}
		});
		ret.put(Float.class, new AdapterFactory<Float>() {
			/**
			 * {@inheritDoc}
			 */
			@Override
			public Adapter<Float> create(Type type) {
				return new Adapter<Float>() {
					/**
					 * {@inheritDoc}
					 */
					@Override
					public Float adapt(String value) {
						return Float.valueOf(value);
					}

					/**
					 * {@inheritDoc}
					 */
					@Override
					public Class<Float> getType() {
						return Float.class;
					}
				};
			}
		});
		ret.put(Integer.class, new AdapterFactory<Integer>() {
			/**
			 * {@inheritDoc}
			 */
			@Override
			public Adapter<Integer> create(Type type) {
				return new Adapter<Integer>() {
					/**
					 * {@inheritDoc}
					 */
					@Override
					public Integer adapt(String value) {
						return Integer.valueOf(value);
					}

					/**
					 * {@inheritDoc}
					 */
					@Override
					public Class<Integer> getType() {
						return Integer.class;
					}
				};
			}
		});
		ret.put(Long.class, new AdapterFactory<Long>() {
			/**
			 * {@inheritDoc}
			 */
			@Override
			public Adapter<Long> create(Type type) {
				return new Adapter<Long>() {
					/**
					 * {@inheritDoc}
					 */
					@Override
					public Long adapt(String value) {
						return Long.valueOf(value);
					}

					/**
					 * {@inheritDoc}
					 */
					@Override
					public Class<Long> getType() {
						return Long.class;
					}
				};
			}
		});
		ret.put(Short.class, new AdapterFactory<Short>() {
			/**
			 * {@inheritDoc}
			 */
			@Override
			public Adapter<Short> create(Type type) {
				return new Adapter<Short>() {
					/**
					 * {@inheritDoc}
					 */
					@Override
					public Short adapt(String value) {
						return Short.valueOf(value);
					}

					/**
					 * {@inheritDoc}
					 */
					@Override
					public Class<Short> getType() {
						return Short.class;
					}
				};
			}
		});
		ret.put(String.class, new AdapterFactory<String>() {
			/**
			 * {@inheritDoc}
			 */
			@Override
			public Adapter<String> create(Type type) {
				return new Adapter<String>() {
					/**
					 * {@inheritDoc}
					 */
					@Override
					public String adapt(String value) {
						return value;
					}

					/**
					 * {@inheritDoc}
					 */
					@Override
					public Class<String> getType() {
						return String.class;
					}
				};
			}
		});
		return ret;
	}
}
