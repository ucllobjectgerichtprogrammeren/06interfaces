package domain;

import java.io.Serializable;

public class SalarisPersoneelsLid extends PersoneelsLid implements Kost {
    private double maandelijksSalaris;

    public SalarisPersoneelsLid(String personeelsnummer, String naam, int maandAanwerving, int jaarAanwerving, double maandelijksSalaris) {
        super(personeelsnummer, naam, maandAanwerving, jaarAanwerving);
        this.setMaandelijksSalaris(maandelijksSalaris);
    }

    private double getMaandelijksSalaris() {
        return maandelijksSalaris;
    }

    private static boolean isGeldigMaandelijksSalaris(double maandelijksSalaris) {
        return maandelijksSalaris > 0;
    }

    private void setMaandelijksSalaris(double maandelijksSalaris) {
        if (!isGeldigMaandelijksSalaris(maandelijksSalaris))
            throw new IllegalArgumentException("Geen geldig maandelijks salaris");
        this.maandelijksSalaris = maandelijksSalaris;
    }

    /**
     * berekent de kostprijs van voorliggende kost
     */
    @Override
    public double geefKostprijs() {
        return getMaandelijksSalaris();
    }

    @Override
    public String toString() {
        return super.toString() + "verdient per maand €" + this.getMaandelijksSalaris() + "\n";
    }


    /**
     * Geeft true als deze kost in gegeven maand en jaar betaald moet worden
     */
    @Override
    public boolean moetNuBetaaldWorden(int maand, int jaar) {
        if (getJaarAanwerving() < jaar)
            return true;
        if (getJaarAanwerving() == jaar)
            return getMaandAanwerving() < maand;
        return false;
    }

    @Override
    public String geefId() {
        return getPersoneelsnummer();
    }

    @Override
    public SalarisPersoneelsLid clone() throws CloneNotSupportedException {
        return (SalarisPersoneelsLid) super.clone();
    }

}
