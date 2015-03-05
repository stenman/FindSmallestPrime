package com.example;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class FindSmallestPrime {

	static int lowest = 1337;
	static int highest = 2014;

	// This algorithm has not been optimized and is NOT to be trusted with numbers larger than a certain size
	public static void main(String[] args) {

		List<Long> primes = new ArrayList<Long>();

		long startTime = System.nanoTime();
		primes = provideNewVariation(9, lowest, highest);
		primes.addAll(provideNewVariation(9, highest, lowest));
		long endTime = System.nanoTime();
		long duration = endTime - startTime;

		primes.removeAll(Arrays.asList(Long.valueOf(0)));
		Long smallestPrime = Collections.min(primes);
		System.out.println("Smallest prime found: " + smallestPrime + "\nTotal numbers of primes found: " + primes.size() + "\nTotal time elapsed: "
				+ duration / 1000000 + "ms");

	}

	private static List<Long> provideNewVariation(int a, int num1, int num2) {

		List<Long> primes = new ArrayList<Long>();

		primes.add(isPrime(Long.parseLong(Integer.toString(num1) + Integer.toString(num2))));
		primes.add(isPrime(Long.parseLong(Integer.toString(num2) + Integer.toString(num1))));

		for (int i = 0; i < a + 1; i++) {
			primes.add(isPrime(Long.parseLong(i + Integer.toString(num2) + Integer.toString(num1))));
			primes.add(isPrime(Long.parseLong(Integer.toString(num2) + i + Integer.toString(num1))));
			primes.add(isPrime(Long.parseLong(Integer.toString(num2) + Integer.toString(num1) + i)));

			for (int j = 0; j < a; j++) {
				primes.add(isPrime(Long.parseLong(i + Integer.toString(num2) + j + Integer.toString(num1))));
				primes.add(isPrime(Long.parseLong(Integer.toString(num2) + i + Integer.toString(num1) + j)));
				primes.add(isPrime(Long.parseLong(i + Integer.toString(num2) + Integer.toString(num1) + j)));

				for (int k = 0; k < j; k++) {
					primes.add(isPrime(Long.parseLong(i + Integer.toString(num2) + j + Integer.toString(num1) + k)));
				}
			}
		}

		return primes;
	}

	private static long isPrime(long n) {
		if (n < 2)
			return 0;
		if (n == 2 || n == 3)
			return n;
		if (n % 2 == 0 || n % 3 == 0)
			return 0;
		long sqrtN = (long) Math.sqrt(n) + 1;
		for (long i = 6L; i <= sqrtN; i += 6) {
			if (n % (i - 1) == 0 || n % (i + 1) == 0)
				return 0;
		}
		return n;
	}
}
