import java.io.*;
import java.text.DecimalFormat;
import java.util.Scanner;

public class Kalkulators {

    static String[] studenti;
    static double[] semVertejums;
    static int[][] kritVertejums;

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

        studenti = new String[studSk];

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
        kritVertejums = new int[studSk][kritSk];
        semVertejums = new double[studSk];

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

        for (int i = 0; i < kritVertejums.length; i++) {
            for (int j = 0; j < kritVertejums[i].length; j++) {
                do {
                    System.out.println("Ievadi " + studenti[i] + " vērtējumu par kritēriju " + kriteriji[j]);
                    while (!scan.hasNextInt()) {
                        System.out.println("Ievadi " + studenti[i] + " vērtējumu par kritēriju " + kriteriji[j]);
                        scan.next();
                    }
                    kritVertejums[i][j] = scan.nextInt();
                } while (kritVertejums[i][j] < 0 || kritVertejums[i][j] > 10);
            }
        }

        for (int i = 0; i < studenti.length; i++) {
            double rezult = 0;
            for (int j = 0; j < kriteriji.length; j++) {
                rezult += ((double) kriterijuSvars[j] / 100) * kritVertejums[i][j];
            }
            semVertejums[i] = rezult;
        }

        for (int i = 0; i < studenti.length; i++) {
            for (int j = 0; j < kriteriji.length; j++) {
                System.out.println("Studenta " + studenti[i] + " vērtējums par kritēriju " + kriteriji[j] + " ir " + kritVertejums[i][j] +
                        ", kura svars ir " + kriterijuSvars[j]);
            }
            System.out.println("Semestra vērtējums ir " + df.format(semVertejums[i]) + " balles\n");
        }
    }

    static void saglabatFaila() {
        if (studenti == null || semVertejums == null) {
            System.out.println("Nav datu, ko saglabāt. Lūdzu vispirms aprēķini.");
            return;
        }

        try (BufferedWriter writer = new BufferedWriter(new FileWriter("rezultati.txt"))) {
            for (int i = 0; i < studenti.length; i++) {
                writer.write(studenti[i] + " - " + semVertejums[i]);
                writer.newLine();
            }
            System.out.println("Dati saglabāti failā rezultati.txt");
        } catch (IOException e) {
            System.out.println("Notika kļūda, saglabājot failu.");
        }
    }

    static void nolasitFailu() {
        try (BufferedReader reader = new BufferedReader(new FileReader("rezultati.txt"))) {
            String line;
            while ((line = reader.readLine()) != null) {
                System.out.println(line);
            }
        } catch (IOException e) {
            System.out.println("Notika kļūda, nolasot failu.");
        }
    }

    static void kartosana(int kartot) {
        if (studenti == null || semVertejums == null || kritVertejums == null) {
            System.out.println("Nav datu, ko kārtot. Lūdzu vispirms aprēķini.");
            return;
        }

        for (int i = 0; i < studenti.length - 1; i++) {
            for (int j = 0; j < studenti.length - i - 1; j++) {
                boolean salidzinat = (kartot == 0) ?
                        semVertejums[j] > semVertejums[j + 1] :
                        semVertejums[j] < semVertejums[j + 1];

                if (salidzinat) {
                    double tempVert = semVertejums[j];
                    semVertejums[j] = semVertejums[j + 1];
                    semVertejums[j + 1] = tempVert;

                    String tempStud = studenti[j];
                    studenti[j] = studenti[j + 1];
                    studenti[j + 1] = tempStud;

                    int[] tempKrit = kritVertejums[j];
                    kritVertejums[j] = kritVertejums[j + 1];
                    kritVertejums[j + 1] = tempKrit;
                }
            }
        }

        System.out.println("Sakārtoti dati " + (kartot == 0 ? "augošā" : "dilstošā") + " secībā:");
        for (int i = 0; i < studenti.length; i++) {
            System.out.println(studenti[i] + " - " + semVertejums[i]);
        }
    }

    public static void main(String[] args) {
        int izvele;
        Scanner scan = new Scanner(System.in);

        do {
            System.out.println("Izvēlies darbību:\n1 - Aprēķināt atzīmes\n2 - Kārtot augošā secībā\n3 - Kārtot dilstošā secībā\n4 - Saglabāt failā\n5 - Nolasīt no faila\n6 - Apturēt");
            while (!scan.hasNextInt()) {
                System.out.println("Lūdzu ievadi skaitli no 1 līdz 6:");
                scan.next();
            }
            izvele = scan.nextInt();

            switch (izvele) {
                case 1:
                    aprekinat();
                    break;
                case 2:
                    kartosana(0);
                    break;
                case 3:
                    kartosana(1);
                    break;
                case 4:
                    saglabatFaila();
                    break;
                case 5:
                    nolasitFailu();
                    break;
                case 6:
                    System.out.println("Programma apturēta.");
                    break;
                default:
                    System.out.println("Nepareiza izvēle.");
            }

        } while (izvele != 6);

        scan.close();
    }
}
