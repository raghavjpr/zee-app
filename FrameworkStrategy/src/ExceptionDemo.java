import java.util.Scanner;

public class ExceptionDemo {

	public static int div(int a, int b) {
		int c = 0;
		try {
			c = a / b;

			String s = null;// it is not referring any object its null
			// s.length() ==> Null Pointer Exception
			// Line no 8 and 10 both have exception but try will only give the first one.
			// Even if there are hundreds of exception it will only handel the first one.
			System.out.println(s.length());// NPE Object
			return c;
			// inside the try or catch block then
			// Before returning the value it will execute the finally block
			// and then it will return the value.
		} catch (ArithmeticException e) {
			// write a code for only non zero values or non zero acceptance
			System.out.println("Value of B invalid.");
			System.out.print("Enter the value for B again: ");
			Scanner scanner = new Scanner(System.in);
			int d = scanner.nextInt();

			c = a / d;
			return c;
			// TODO Auto-generated catch block
			// e.printStackTrace();
		} catch (NullPointerException e) {
			System.out.println("NPE Caught.");
		} catch (Exception e) {
			if (e instanceof NullPointerException) {

			}
			if (e instanceof ArithmeticException) {

			}
			System.out.println("Any Exception can be handled by this handler.");
		} finally {
			System.out.println("Hello from div.");
		}
		return c;
		// System.out.println("Hello from div.");
		// return c;
	}

	public static void main(String[] args) {

		Scanner scanner = new Scanner(System.in);

		try {
			System.out.print("Enter the first number: ");
			int a = scanner.nextInt();
			System.out.print("Enter the second number: ");
			int b = scanner.nextInt();

//			System.out.println(a);
//			System.out.println(b);
//			System.out.println(a/b);
			int result = div(a, b);
			System.out.println(result);
		} catch (ArithmeticException e) {
			// TODO Auto-generated catch block
			System.out.println("Inside the catch block.");
			e.printStackTrace();
		}

		System.out.println("Hello from Raghav.");

	}

}
