import java.util.Scanner;

public class DivisionTillValidValueReceived {
	
	public static int div(int a, int b) {
		int c = 0;
		try {
			c = a/b;
			return c;
		} catch (ArithmeticException e) {
			Scanner scanner = new Scanner(System.in);
			int d;
			do {
				System.out.print("Enter a valid B value again: ");
				d = scanner.nextInt();				
			} while (d==0);
			
			c = a/d;
			return c;
			//e.printStackTrace();
		}
	}

	public static void main(String[] args) {
		
		Scanner scanner = new Scanner(System.in);
		
		System.out.print("Enter the first number: ");
		int a = scanner.nextInt();
		System.out.print("Enter the second number: ");
		int b = scanner.nextInt();
		
		int result = div(a,b);
		System.out.println(result);
	}
}