package cz.czechitas.lekce10;

import com.fasterxml.jackson.databind.ObjectMapper;
import cz.czechitas.lekce10.operace.*;

import java.io.IOException;
import java.io.InputStream;
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
        Konstanty konstanty = nacistKonstanty();
        mapaOperaci = Map.of(
                "+", new Scitani(),
                "-", new Odcitani(),
                "*", new Nasobeni(),
                "/", new Deleni(),
                "odmocnit", new DruhaOdmocnina(),
                "pi", new CisloPi(konstanty.getPi()),
                "e", new CisloE(konstanty.getE())
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

    private static Konstanty nacistKonstanty() {
        ObjectMapper objectMapper = new ObjectMapper();
        try (InputStream inputStream = Kalkulacka.class.getResourceAsStream("konstanty.json")) {
            return objectMapper.readValue(inputStream, Konstanty.class);
        } catch (IOException e) {
            throw new RuntimeException("Nepodařilo se načíst konstanty.", e);
        }
    }

    private static Konstanty nacistKonstantyStarsi() {
        ObjectMapper objectMapper = new ObjectMapper();
        // Alternativní zápis pomocí try-finally
        try {
            InputStream inputStream = Kalkulacka.class.getResourceAsStream("konstanty.json");
            try {
                return objectMapper.readValue(inputStream, Konstanty.class);
            } finally {
                inputStream.close();
            }
        } catch (IOException e) {
            throw new RuntimeException("Nepodařilo se načíst konstanty.", e);
        }
    }

}
