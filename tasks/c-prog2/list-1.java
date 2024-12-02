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
				score = my.read("  Score (0-10): ").Double();
			};
		};


//===========================================
//        BASIC LOOP SELECT QUESTION
//===========================================
		while (true) {
			number = my.read("\nQuestion: ").Int();
			if (number == 0) { break; } else { Question[number].run(); };
		}
	}
}

//===========================================
//               MY FUNCTIONS
//===========================================
class my {
	static String OUT;
	static void print(String msg){ System.out.print(msg); }

	static String Str() { return OUT; }

	static int Int() { return Integer.parseInt(OUT); }
	static double Double() { return Double.parseDouble(OUT); }
 
	static my out(String in){ OUT=in; return new my(); }
	static my read(String msg) { print(msg); return out(new Scanner(System.in).nextLine()); }
}

