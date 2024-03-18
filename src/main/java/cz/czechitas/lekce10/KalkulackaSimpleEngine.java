package cz.czechitas.lekce10;

/**
 * Jednoduchá implementace kalkulačky.
 *
 * Implementace kalkulačky, která má displej s 8 číslicemi.
 */
public class KalkulackaSimpleEngine {
    /**
     * První kladné číslo, které na displeji nelze zobrazit (má 9 číslic).
     */
    private final int limitVelikostiDispleje = 100_000_000;
    private int a;
    private int b;

    public int getA() {
        return a;
    }

    public void setA(int a) {
        this.a = a;
    }

    public int getB() {
        return b;
    }

    public void setB(int b) {
        this.b = b;
    }

    public int secist() {
        long soucet = a + b;
        // Zkontrolovat, zda součet nemá více než 8 číslic.
        return (int) soucet;
    }

    public int odecist() {
        long rozdil = a - b;
        // Zkontrolovat, zda rozdíl nemá více než 8 číslic.
        return (int) rozdil;
    }

    public int nasobit() {
        long soucin = a * b;
        // Zkontrolovat, zda součin nemá více než 8 číslic.
        return (int) soucin;
    }

    public int delit() {
        // TODO zkontrolovat, že b není nulové
        return a / b;
    }

    public int druhaOdmocnina() {
        // TODO zkontrolovat, že a není záporné
        return (int) Math.sqrt(a);
    }
}
