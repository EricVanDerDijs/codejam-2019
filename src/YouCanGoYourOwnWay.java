import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.Scanner;
import java.lang.StringBuilder;

public class YouCanGoYourOwnWay {
	
	private static StringBuilder ownPath = new StringBuilder();
	
	private static String solve(String oponentsPath) {
		ownPath.setLength(0);
		for(int i = 0; i < oponentsPath.length(); i++) {
			if(oponentsPath.charAt(i) == 'E') {
				ownPath.append('S');
			} else {
				ownPath.append('E');
			}
		}
		return ownPath.toString();
	}

	public static void main(String[] args) {
		// Initialize scanner
		InputStream is =  System.in;
		Scanner scanner = new Scanner(new BufferedReader(new InputStreamReader(is)));
		// Initialize testCount
		int testCount = scanner.nextInt();
		// Start Tests
		for (int testNumber = 1; testNumber <= testCount; testNumber++) {
			int mazeSize = scanner.nextInt();
			String oponentsPath = scanner.next();
			System.out.println("Case #" + testNumber + ": "+solve(oponentsPath));

//			if(!valueB_test.toString().contains("4")
//			&& !valueA_test.toString().contains("4")
//			&& valueA_test.add(valueB_test).toString().equals(desiredValue)) {
//				System.out.println("pass");
//			}
		}
		scanner.close();
	}

}