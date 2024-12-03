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
			// SOLUTION HERE
		};

//===========================================
//        BASIC LOOP SELECT QUESTION
//===========================================
		while (true) {
			number = my.read("Question Number: ").Int();
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
