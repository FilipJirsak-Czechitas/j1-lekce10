package cz.czechitas.lekce10;

import cz.czechitas.lekce10.operace.*;

import java.util.Map;
import java.util.Scanner;

/**
 * Hlavní třída aplikace Kalkulačka.
 */
public class Kalkulacka implements Runnable {

    private final Scanner console = new Scanner(System.in);
    private final Map<String, Operace> mapaOperaci;
    private Operace zvolenaOperace;

    public static void main(String[] args) {
        new Kalkulacka().run();
    }

    public Kalkulacka() {
        mapaOperaci = Map.of(
                "+", new Scitani(),
                "-", new Odcitani(),
                "*", new Nasobeni(),
                "/", new Deleni(),
                "odmocnit", new DruhaOdmocnina(),
                "pi", new CisloPi(Math.PI),
                "e", new CisloE(Math.E)
        );
    }

    @Override
    public void run() {
        nacistOperaci();
        if (zvolenaOperace.getPocetOperandu() >= 1) {
            nacistPrvniCislo();
        }
        if (zvolenaOperace.getPocetOperandu() >= 2) {
            nacistDruheCisloCislo();
        }
        vypsatVysledek();
    }

    private String nacistUdaj(String format, Object... args) {
        return nacistUdaj(String.format(format, args));
    }

    private String nacistUdaj(String prompt) {
        System.out.println(prompt);
        System.out.print("> ");
        return console.nextLine();
    }

    private void nacistOperaci() {
        String seznamOperaci = String.join(", ", mapaOperaci.keySet());
        String vstup = nacistUdaj("Zadejte operaci (%s) a stiskněte <Enter>:", seznamOperaci);
        zvolenaOperace = mapaOperaci.get(vstup);
    }

    private void nacistPrvniCislo() {
        String vstup = nacistUdaj("Zadejte první (celé) číslo a stiskněte <Enter>:");
        int cislo = Integer.parseInt(vstup);
        zvolenaOperace.setA(cislo);
    }

    private void nacistDruheCisloCislo() {
        String vstup = nacistUdaj("Zadejte druhé (celé) číslo a stiskněte <Enter>:");
        int cislo = Integer.parseInt(vstup);
        zvolenaOperace.setB(cislo);
    }

    private void vypsatVysledek() {
        System.out.printf("Výpočet: %s", zvolenaOperace.vypocet()).println();
    }

}
