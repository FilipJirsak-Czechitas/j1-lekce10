package cz.czechitas.lekce10;

import java.util.Scanner;

/**
 * Hlavní třída aplikace Kalkulačka.
 */
public class Kalkulacka implements Runnable {

    private final KalkulackaSimpleEngine kalkulacka = new KalkulackaSimpleEngine();
    private final Scanner console = new Scanner(System.in);

    public static void main(String[] args) {
        new Kalkulacka().run();
    }

    @Override
    public void run() {
        nacistPrvniCislo();
        nacistDruheCisloCislo();
        nacistOperaciAVypocitat();
    }

    private void nacistPrvniCislo() {
        System.out.println("Zadejte první číslo a stiskněte <Enter>:");
        String vstup = console.nextLine();
        int cislo = Integer.parseInt(vstup);
        kalkulacka.setA(cislo);
    }

    private void nacistDruheCisloCislo() {
        System.out.println("Zadejte druhé číslo a stiskněte <Enter>:");
        String vstup = console.nextLine();
        int cislo = Integer.parseInt(vstup);
        kalkulacka.setB(cislo);
    }

    private void nacistOperaciAVypocitat() {
        System.out.println("Zadejte operaci (+, -, *, /, odmocnit) a stiskněte <Enter>:");
        String vstup = console.nextLine();
        System.out.println("Výpočet:");
        switch (vstup) {
            case "+":
                secistCisla();
                break;
            case "-":
                odecistCisla();
                break;
            case "*":
                vynasobitCisla();
                break;
            case "/":
                vydelitCisla();
                break;
            case "odmocnit":
                odmocnitCislo();
                break;
            default:
        }
    }

    private void secistCisla() {
        int vysledek = kalkulacka.secist();
        System.out.printf("%d + %d = %d", kalkulacka.getA(), kalkulacka.getB(), vysledek).println();
    }

    private void odecistCisla() {
        int vysledek = kalkulacka.odecist();
        System.out.printf("%d - %d = %d", kalkulacka.getA(), kalkulacka.getB(), vysledek).println();
    }

    private void vynasobitCisla() {
        int vysledek = kalkulacka.nasobit();
        System.out.printf("%d ⋅ %d = %d", kalkulacka.getA(), kalkulacka.getB(), vysledek).println();
    }

    private void vydelitCisla() {
        int vysledek = kalkulacka.delit();
        System.out.printf("%d ÷ %d = %d", kalkulacka.getA(), kalkulacka.getB(), vysledek).println();
    }

    private void odmocnitCislo() {
        int vysledek = kalkulacka.druhaOdmocnina();
        System.out.printf("√%d = %d", kalkulacka.getA(), vysledek).println();
    }
}
