import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.Scanner;
import java.math.BigInteger;;

public class ForegoneSolution {
	
//	private static BigInteger valueA_test;
//	private static BigInteger valueB_test;
	
	private static String solve(String desiredValue) {
		BigInteger valueA = new BigInteger(desiredValue);
		BigInteger valueB = new BigInteger("0");
		BigInteger ten = new BigInteger("10");
		int inputLength = desiredValue.length();
		for(int i = 0; i < inputLength; i++) {
			if (desiredValue.charAt(i) == '4' ) {
				valueB = valueB.add(ten.pow(inputLength - i -1));
			}
		}
//		valueB_test = valueB;
		valueA = valueA.subtract(valueB);
//		valueA_test = valueA;
		return (valueA.toString()+" "+valueB.toString());
	}

	public static void main(String[] args) {
		// Initialize scanner
		InputStream is =  System.in;
		Scanner scanner = new Scanner(new BufferedReader(new InputStreamReader(is)));
		// Initialize testCount
		int testCount = scanner.nextInt();
		// Start Tests
		for (int testNumber = 1; testNumber <= testCount; testNumber++) {
			String desiredValue = scanner.next();
			String solution = solve(desiredValue);
			System.out.println("Case #" + testNumber + ": "+solution);

//			if(!valueB_test.toString().contains("4")
//			&& !valueA_test.toString().contains("4")
//			&& valueA_test.add(valueB_test).toString().equals(desiredValue)) {
//				System.out.println("pass");
//			}
		}
		scanner.close();
	}

}