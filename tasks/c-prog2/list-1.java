//===========================================
//             PROBLEM SOLUTION
//===========================================
import java.util.*;

public class Main {
	public static void main(String[] str){
		int number; 
		Runnable[] Question = new Runnable[10];
//===========================================	
		Question[1] = () -> {
			double score = 11;
			while ((score<0) | (score>10)){
				score = my.read("  Score (0-10): ").nextDouble();
			};
		};

		Question[2] = () -> {
			int qt = 0;
			double A = 55000;
			double B = 160000;

			while (A < B){ qt+=1; A*=1.3; B*=1.18; };
			my.print("  Months: %d\n", qt);
		};

		Question[3] = () -> {
			for (int x=1; x<=20; x++){ my.print("  %d\n", x); };
		};

		Question[4] = () -> {
			int n = my.read("  Number: ").nextInt();
	
			for (int x=1; x<=10; x++){
				my.print("  %d x %d = %d\n", n, x, n*x);
			};
		};

		Question[5] = () -> {
			my.print("  %d\n", ((1+499)/2)*(250) ); // arithmetic progression
		};
		
		Question[6] = () -> {

		};
//===========================================
//        BASIC LOOP SELECT QUESTION
//===========================================
		while (true) {
			number = my.read("\nQuestion: ").nextInt();
			if (number == 0) { break; } else { Question[number].run(); };
		}
	}
}

//===========================================
//               MY FUNCTIONS
//===========================================
class my {
	static void print(String msg, Object... args){ System.out.printf(msg, args); }
	static Scanner read(String msg) { print(msg); return new Scanner(System.in); }
}

