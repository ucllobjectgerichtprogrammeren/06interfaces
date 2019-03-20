package domain;

public class SalarisPersoneelsLid extends PersoneelsLid {
    private double maandelijksSalaris;

    protected SalarisPersoneelsLid(String personeelsnummer, String naam, int maandAanwerving, int jaarAanwerving, double maandelijksSalaris) {
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
        return super.toString() + "\nverdient per maand €" + this.getMaandelijksSalaris();
    }


}
