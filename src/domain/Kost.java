package domain;

public interface Kost extends Cloneable{
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

    @Override
    boolean equals (Object o);

    Kost clone();

}
