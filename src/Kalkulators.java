import java.text.DecimalFormat;
import java.util.Scanner;

public class Kalkulators {
 static void aprekinat() {
	 int studSk, kritSk;
	 Scanner scan = new Scanner(System.in);
     DecimalFormat df = new DecimalFormat(" 0.#");
	 do {
         System.out.println("Cik studentiem aprēķināt gala vērtējumu?");
         while (!scan.hasNextInt()) {
             System.out.println("Cik studentiem aprēķināt gala vērtējumu?");
             scan.next();
         }
         studSk = scan.nextInt();
     } while (studSk < 1);
     
     String[] studenti = new String[studSk];

    
     do {
         System.out.println("Kāds būs kritēriju skaits?");
         while (!scan.hasNextInt()) {
             System.out.println("Kāds būs kritēriju skaits?");
             scan.next();
         }
         kritSk = scan.nextInt();
     } while (kritSk < 1);
     
     String[] kriteriji = new String[kritSk];
     int[] kriterijuSvars = new int[kritSk];
     int[][] kritVertejums = new int[studSk][kritSk];
     double[] semVertejums = new double[studSk];

     scan.nextLine(); 

     
     for (int i = 0; i < studenti.length; i++) {
         do {
             System.out.println("Ievadi " + (i + 1) + ". studenta vārdu");
             studenti[i] = scan.nextLine().trim();
         } while (!studenti[i].matches("^[\\p{L} ]+$"));
     }

     
     int kopsvars = 0;
     boolean validSvars = false;

     while (!validSvars) {
         kopsvars = 0; 

    
         for (int i = 0; i < kriteriji.length; i++) {
             do {
                 System.out.println("Ievadi " + (i + 1) + ". kritēriju");
                 kriteriji[i] = scan.nextLine().trim();
             } while (!kriteriji[i].matches("^[\\p{L} ]+$"));

             int svars = -1;
            
             while (svars < 0 || svars > 100) {
                 System.out.println("Ievadi " + (i + 1) + ". kritērija svaru (0 - 100):");
                 while (!scan.hasNextInt()) {
                     System.out.println("Lūdzu ievadi veselīgu skaitli.");
                     scan.next();
                 }
                 svars = scan.nextInt();
                 scan.nextLine(); 
             }

             
             if (kopsvars + svars > 100) {
                 System.out.println("Kopējais svaru apjoms pārsniedz 100%. Lūdzu ievadi citus svarus.");
                 i--; 
             } else {
                 kriterijuSvars[i] = svars;
                 kopsvars += svars;
             }
         }

        
         if (kopsvars == 100) {
             System.out.println("Kopējais svaru apjoms ir 100%, turpinām darbu.");
             validSvars = true; 
         } else {
             System.out.println("Kopējais svaru apjoms nav 100%. Lūdzu ievadi atkal.");
         }
     }
     for(int i=0; i<kritVertejums.length; i++) {
     	for(int j=0; j<kritVertejums[i].length; j++) {
     		do {
     			System.out.println("Ievadi "+studenti[i]+" vērtējumu par kritēriju "+kriteriji[j]);
     			while(!scan.hasNextInt()) {
     				System.out.println("Ievadi "+studenti[i]+" vērtējumu par kritēriju "+kriteriji[j]);
     				scan.next();
     			}
     			kritVertejums[i][j] = scan.nextInt();
     		}while(kritVertejums[i][j] <0 || kritVertejums[i][j] > 10);
     	}
     }
     double rezult;
     for(int i=0; i<studenti.length; i++) {
     	rezult=0;
     	for(int j=0; j<kriteriji.length; j++) {
     		rezult += ((double)kriterijuSvars[j]/100) * kritVertejums[i][j];
     	}
     	semVertejums[i] = rezult;
     }
     for(int i=0; i<studenti.length; i++) {
     	for(int j=0; j<kriteriji.length; j++) {
     		System.out.println("Studenta "+studenti[i]+" vērtejums par kritēriju "+kriteriji[j]+" ir "+kritVertejums[i][j]+
     				", kura svars ir "+kriterijuSvars[j]);
     	}
     	System.out.println("Semestra vērtējums ir "+df.format(semVertejums[i])+" balles\n");
     }
 }
    public static void main(String[] args) {
        int izvele;
        Scanner scan = new Scanner(System.in);
        do {
        	
        	System.out.println("1-aprēķināt atzīmes \n2-Kārtot augošā secībā \n3-Kārtot dilstošā secībā \n4-saglabā failā \n5-Nolasīt \n6-Apturēt");
        	izvele = scan.nextInt();
        switch(izvele) {
        case 1:
        aprekinat();
        break;
        case 2:
        	break;
        case 3:
        	break;
        case 4:
        	break;
        case 5:
        	break;
        case 6:
        	System.out.println("Programma apturēta");
        	break;
        }
        }while(izvele != 6);
        scan.close();
        
    }
}
