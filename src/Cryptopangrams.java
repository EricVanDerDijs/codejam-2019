import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.Scanner;
import java.math.BigInteger;
//import java.lang.StringBuilder;
import java.util.ArrayList;
//import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Collections;

public class Cryptopangrams {
	
	private static int[] getPrimes(int upperBound) {
		boolean[] numbers = new boolean[upperBound+1];
		numbers[0] = true; numbers[1] = true;
		
		for (int i = 2; i <= (int) Math.sqrt(upperBound); i++) {
			if(!numbers[i]) {
				for (int j = i; j <= upperBound/i; j++) {
					numbers[i*j] = true;
				}
			}
		}
		int primesSize = 0;
		for (int i = 0; i < numbers.length ; i++) {
			if (!numbers[i]) {
				primesSize++;
			}
		}
		int[] primes = new int[primesSize];
		int primeIndex = 0;
		for(int i = 0; i < numbers.length; i++) {
			if (!numbers[i]) {
				primes[primeIndex] = i;
				primeIndex++;
			}
		}
		return primes;
	}
	
	private static String solve(BigInteger[] cipherText,
			BigInteger minValue, int minValueIndex,
			BigInteger primesUpperBound) {
		// solve start
		// get primes low end bound
		BigInteger primesBaseBound = 
				minValue.divide(BigInteger.TWO).add(BigInteger.ONE);
		primesBaseBound = primesBaseBound.compareTo(primesUpperBound) == -1 ? 
				primesBaseBound : primesUpperBound;
		// if it is a reasonable number, lets solve, else -> GET OUT!
		if (primesBaseBound.compareTo(new BigInteger("2000000000")) == -1) {
			// needed variables
			// alphabet and alphabetPrimes needed to create translate alphabet
			List<BigInteger> alphabetPrimes = new ArrayList<BigInteger>(26);
			Map<BigInteger, Character> alphabet = new HashMap<BigInteger, Character>(26);
			// currentPrimes used to traverse cipherText and discover the other primes fast
			BigInteger[] currentPrimes = new BigInteger[2];
			// protoText will hold primes in a way in which the can be ordered
			// [currentPrime[0],currentPrime[1], oneOfNextPrimes]
			// this can be translated with help of alphabet
			BigInteger[][] protoText = new BigInteger[cipherText.length][3];
			
			int[] primesForMinValue = getPrimes(primesBaseBound.intValue());
			// with this I found the primes of a cipherText position (minValue)
			for (int i = 0; i < primesForMinValue.length; i++) {
				// Convert int prime to BigInteger
				BigInteger prime = new BigInteger(Integer.toString(primesForMinValue[i])); 
				if (minValue.remainder(prime).equals(BigInteger.ZERO)) {
					alphabetPrimes.add(prime);
					currentPrimes[0] = prime;
					alphabetPrimes.add(minValue.divide(prime));
					currentPrimes[1] = minValue.divide(prime);
					// cut loop
					i = primesForMinValue.length;
				}
			}
			// traverse the whole cipherText from known primes (currentPrimes)
			// to fill alphabetPrimes and protoText
			if (minValueIndex < cipherText.length -1) {
				for (int i = minValueIndex+1; i < cipherText.length; i++) {
					for(int j = 0; j < currentPrimes.length; j++) {
						if (cipherText[i].remainder(currentPrimes[j]).equals(BigInteger.ZERO)) {
							// update alphabetPrimes and currentPrimes
							currentPrimes[0] = currentPrimes[j];
							BigInteger prime = cipherText[i].divide(currentPrimes[j]);
							alphabetPrimes.add(prime);
							currentPrimes[1] = prime;
							// update protoText
							protoText[i][0] = currentPrimes[0];
							protoText[i][1] = currentPrimes[1];
							// cut j loop to prevent overwriting
							j = currentPrimes.length;
							
						}
					}
				}	
			}
			if (minValueIndex > 0) {
				for (int i = minValueIndex-1; i >= 0 ; i--) {
					for(int j = 0; j < currentPrimes.length; j++) {
						if (cipherText[i].remainder(currentPrimes[j]).equals(BigInteger.ZERO)) {
							// update alphabetPrimes and currentPrimes
							currentPrimes[0] = currentPrimes[j];
							BigInteger prime = cipherText[i].divide(currentPrimes[j]);
							alphabetPrimes.add(prime);
							currentPrimes[1] = prime;
							// update protoText
							protoText[i][0] = currentPrimes[0];
							protoText[i][1] = currentPrimes[1];
							// cut j loop to prevent overwriting
							j = currentPrimes.length;
							
						}
					}
				}
			}
			// fill alphabet
			Collections.sort(alphabetPrimes);
			String aToZ = "ABCDEFGHIJKLMNOPKRSTUVWXYZ";
			for(int i = 0; i < alphabetPrimes.size(); i++) {
				alphabet.put(alphabetPrimes.get(i), aToZ.charAt(i));
			}
			// get ciphertext concatenation order
			
			return "hey";
		} else {
			return "HELL NO!, THAT'S NOT GONNA HAPPEN ANY TIME SOON!";
		}
	}

	public static void main(String[] args) {
		// Initialize scanner
		InputStream is =  System.in;
		Scanner scanner = new Scanner(new BufferedReader(new InputStreamReader(is)));
		// Initialize testCount
		int testCount = scanner.nextInt();
		// Start Tests
		for (int testNumber = 1; testNumber <= testCount; testNumber++) {
			BigInteger primesUpperBound = new BigInteger(scanner.next());
			int cipherTextLength = scanner.nextInt();
			BigInteger[] cipherText = new BigInteger[cipherTextLength];

			BigInteger minValue = new BigInteger("0");
			int minValueIndex = 0;
			for(int i = 0; i < cipherText.length; i++) {
				cipherText[i] = new BigInteger(scanner.next());

				if(minValue.equals(BigInteger.ZERO) 
				|| cipherText[i].compareTo(minValue) == -1) {
					minValue = new BigInteger(cipherText[i].toString());
					minValueIndex = i;
				}
			}
			String oponentsPath = solve(cipherText, minValue, minValueIndex, primesUpperBound);
			System.out.println("Case #" + testNumber + ": "+oponentsPath);

//			if(!valueB_test.toString().contains("4")
//			&& !valueA_test.toString().contains("4")
//			&& valueA_test.add(valueB_test).toString().equals(desiredValue)) {
//				System.out.println("pass");
//			}
		}
		scanner.close();
	}

}
