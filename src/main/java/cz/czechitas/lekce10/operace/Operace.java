package cz.czechitas.lekce10.operace;

/**
 * @author Filip Jirsák
 */
public interface Operace {
    void setA(int a);

    int getA();

    void setB(int b);

    int getB();

    int getPocetOperandu();

    String vypocet();
}
