import java.io.BufferedReader;
//import java.io.FileInputStream;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.Scanner;
//import java.util.Arrays;

public class SaveTheUniverse {
	
	private static int calcDmg(String[] program) {
        int dmg = 1;
        int dmgDone = 0;
        for (String instruction : program) {
            if (instruction.equals("C")) {
              dmg *= 2;
            }
            if (instruction.equals("S")) {
              dmgDone += dmg;
            }
            
        }
        return dmgDone;
    }
    
    public static int getChargePosition(String[] program) {
        int chargePosition = -1;
        int i = program.length - 2;
        while (chargePosition < 0 && i >= 0) {
            if (program[i].equals("C") && !program[i+1].equals("C")) {
              chargePosition = i;
            }
            i--;
        }
        return chargePosition;
    }

    private static int solve(int maxAllowedDamage, String[] program) {
        if (calcDmg(program) <= maxAllowedDamage) {
            return 0;
        } else {
            if (getChargePosition(program) < 0
            && calcDmg(program) > maxAllowedDamage) {
                return -1;
            } else {
                boolean solved = false;
                int swapsNeeded = 0;
                while (!solved) {
                    if(calcDmg(program) > maxAllowedDamage) {
                        int chargePosition = getChargePosition(program);
                        if(chargePosition >= 0) {
                            program[chargePosition] = "S";
                            program[chargePosition + 1] = "C";
                            swapsNeeded++;
                        } else {
                            swapsNeeded = - 1;
                            solved = true;
                        }
                    } else {
                        solved = true;
                    }
                }
                return swapsNeeded;
            }
        }
    }

	public static void main(String[] args) {
		InputStream is =  System.in;
        Scanner scanner = new Scanner(new BufferedReader(new InputStreamReader(is)));
        int testCount = scanner.nextInt();
        for (int testNumber = 1; testNumber <= testCount; testNumber++) {
            int maxAllowedDamage = scanner.nextInt();
            String[] program = scanner.next().split("");
            int swapCount = solve(maxAllowedDamage, program);
            System.out.println("Case #" + testNumber + ": " + (swapCount >= 0 ? swapCount : "IMPOSSIBLE"));
        }
       scanner.close();
	}

}