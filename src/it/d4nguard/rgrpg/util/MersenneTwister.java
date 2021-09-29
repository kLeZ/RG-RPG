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

import java.io.Serial;
import java.security.SecureRandom;
import java.util.Calendar;
import java.util.Random;

public class MersenneTwister extends Random {
	@Serial
	private static final long serialVersionUID = -8879292960486620328L;

	private static final int N = 624;
	private static final int M = 397;
	private static final int[] MAG01 = {
			0x0, 0x9908b0df
	};

	private final int[] mt;
	private int mti;

	/**
	 * Creates a new random number generator.
	 * <p>
	 * The instance is initialized using the current time as the seed.
	 * </p>
	 */
	public MersenneTwister() {
		mt = new int[N];
		SecureRandom random = new SecureRandom();
		byte[] seed = random.generateSeed(Calendar.getInstance()
				.get(Calendar.SECOND));
		setSeed(seed[random.nextInt(seed.length - 1)]);
	}

	/**
	 * Creates a new random number generator using a single int seed.
	 *
	 * @param seed
	 * 		the initial seed (32 bits integer)
	 */
	public MersenneTwister(int seed) {
		mt = new int[N];
		setSeed(seed);
	}

	/**
	 * Creates a new random number generator using an int array seed.
	 *
	 * @param seed
	 * 		the initial seed (32 bits integers array), if null the seed of
	 * 		the generator will be related to the current time
	 */
	public MersenneTwister(int[] seed) {
		mt = new int[N];
		setSeed(seed);
	}

	/**
	 * Creates a new random number generator using a single long seed.
	 *
	 * @param seed
	 * 		the initial seed (64 bits integer)
	 */
	public MersenneTwister(long seed) {
		mt = new int[N];
		setSeed(seed);
	}

	/**
	 * Reinitialize the generator as if just built with the given int seed.
	 * <p>
	 * The state of the generator is exactly the same as a new generator built
	 * with the same seed.
	 * </p>
	 *
	 * @param seed
	 * 		the initial seed (32 bits integer)
	 */
	public void setSeed(int seed) {
		// we use a long masked by 0xffffffffL as a poor man unsigned int
		long longMT = seed;
		mt[0] = (int) longMT;
		for (mti = 1; mti < N; ++mti) {
			// See Knuth TAOCP Vol2. 3rd Ed. P.106 for multiplier.
			// initializer from the 2002-01-09 C version by Makoto Matsumoto
			longMT = ((1812433253L * (longMT ^ (longMT >> 30))) + mti) & 0xffffffffL;
			mt[mti] = (int) longMT;
		}
	}

	/**
	 * Reinitialize the generator as if just built with the given int array
	 * seed.
	 * <p>
	 * The state of the generator is exactly the same as a new generator built
	 * with the same seed.
	 * </p>
	 *
	 * @param seed
	 * 		the initial seed (32 bits integers array), if null the seed of
	 * 		the generator will be related to the current time
	 */
	public void setSeed(int[] seed) {

		if (seed == null) {
			setSeed(System.currentTimeMillis());
			return;
		}

		setSeed(19650218);
		int i = 1;
		int j = 0;

		for (int k = Math.max(N, seed.length); k != 0; k--) {
			long l0 = (mt[i] & 0x7fffffffL) | ((mt[i] < 0) ? 0x80000000L : 0x0L);
			long l1 = (mt[i - 1] & 0x7fffffffL) | ((mt[i - 1] < 0) ? 0x80000000L : 0x0L);
			long l = (l0 ^ ((l1 ^ (l1 >> 30)) * 1664525L)) + seed[j] + j; // non
			// linear
			mt[i] = (int) (l & 0xffffffffL);
			i++;
			j++;
			if (i >= N) {
				mt[0] = mt[N - 1];
				i = 1;
			}
			if (j >= seed.length) {
				j = 0;
			}
		}

		for (int k = N - 1; k != 0; k--) {
			long l0 = (mt[i] & 0x7fffffffL) | ((mt[i] < 0) ? 0x80000000L : 0x0L);
			long l1 = (mt[i - 1] & 0x7fffffffL) | ((mt[i - 1] < 0) ? 0x80000000L : 0x0L);
			long l = (l0 ^ ((l1 ^ (l1 >> 30)) * 1566083941L)) - i; // non linear
			mt[i] = (int) (l & 0xffffffffL);
			i++;
			if (i >= N) {
				mt[0] = mt[N - 1];
				i = 1;
			}
		}

		mt[0] = (int) 0x80000000L; // MSB is 1; assuring non-zero initial array
	}

	/**
	 * Reinitialize the generator as if just built with the given long seed.
	 * <p>
	 * The state of the generator is exactly the same as a new generator built
	 * with the same seed.
	 * </p>
	 *
	 * @param seed
	 * 		the initial seed (64 bits integer)
	 */
	@Override
	public void setSeed(long seed) {
		if (mt == null) {
			// this is probably a spurious call from base class constructor,
			// we do nothing and wait for the setSeed in our own
			// constructors after array allocation
			return;
		}
		setSeed(new int[] {
				(int) (seed >>> 32), (int) (seed & 0xffffffffL)
		});
	}

	/**
	 * Generate next pseudorandom number.
	 * <p>
	 * This method is the core generation algorithm. As per
	 * {@link java.util.Random Random } contract, it is used by all the public
	 * generation methods for the various primitive types
	 * {@link java.util.Random#nextBoolean nextBoolean},
	 * {@link java.util.Random#nextBytes nextBytes},
	 * {@link java.util.Random#nextDouble nextDouble},
	 * {@link java.util.Random#nextFloat nextFloat},
	 * {@link java.util.Random#nextGaussian nextGaussian},
	 * {@link java.util.Random#nextInt() nextInt} and
	 * {@link java.util.Random#nextLong nextLong}.
	 * </p>
	 *
	 * @param bits
	 * 		number of random bits to produce
	 */
	@Override
	protected int next(int bits) {

		int y;

		if (mti >= N) { // generate N words at one time
			int mtNext = mt[0];
			for (int k = 0; k < (N - M); ++k) {
				int mtCurr = mtNext;
				mtNext = mt[k + 1];
				y = (int) ((mtCurr & 0x80000000L) | (mtNext & 0x7fffffff));
				mt[k] = mt[k + M] ^ (y >>> 1) ^ MAG01[y & 0x1];
			}
			for (int k = N - M; k < (N - 1); ++k) {
				int mtCurr = mtNext;
				mtNext = mt[k + 1];
				y = (int) ((mtCurr & 0x80000000L) | (mtNext & 0x7fffffff));
				mt[k] = mt[k + (M - N)] ^ (y >>> 1) ^ MAG01[y & 0x1];
			}
			y = (int) ((mtNext & 0x80000000L) | (mt[0] & 0x7fffffff));
			mt[N - 1] = mt[M - 1] ^ (y >>> 1) ^ MAG01[y & 0x1];

			mti = 0;
		}

		y = mt[mti++];

		// tempering
		y ^= (y >>> 11);
		y ^= (y << 7) & 0x9d2c5680;
		y ^= (y << 15) & 0xefc60000;
		y ^= (y >>> 18);

		return y >>> (32 - bits);
	}

	/**
	 * Generates a random integer between minValue and maxValue.
	 *
	 * @param maxValue
	 * 		The lower bound.
	 * @param minValue
	 * 		The upper bound.
	 */
	public int next(int minValue, int maxValue) {
		return (int) (Math.floor((((maxValue - minValue) + 1) * nextDouble()) + minValue));
	}
}
