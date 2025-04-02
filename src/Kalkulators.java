import java.text.DecimalFormat;
import java.util.Scanner;

public class Kalkulators {

	public static void main(String[] args) {
		int studSk, kritSk;
		Scanner scan = new Scanner(System.in);
		DecimalFormat df = new DecimalFormat(" 0.#");
		
		do {
			System.out.println("Cik studentiem aprēķināt gala vērtējumu?");
			while(!scan.hasNextInt()) {
				System.out.println("Cik studentiem aprēķināt gala vērtējumu?");
				scan.next();
			}
			studSk = scan.nextInt();
		}while(studSk<1);
		String[] studenti = new String[studSk];

	}
}