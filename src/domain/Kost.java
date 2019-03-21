package domain;

import java.io.Serializable;

public interface Kost extends Cloneable, Serializable {
    /**
     * berekent de kostprijs van voorliggende kost
     */
    double geefKostprijs();

    /**
     * Geeft true als deze kost in gegeven maand en jaar betaald moet worden
     */
    boolean moetNuBetaaldWorden(int maand, int jaar);

    String geefId();

    String toString();

    /**
     * Returnt true indien gegeven object van hetzelfde type is en zijn id gelijk is aan this
     */
    @Override
    boolean equals (Object o);

    Kost clone() throws CloneNotSupportedException;
}
