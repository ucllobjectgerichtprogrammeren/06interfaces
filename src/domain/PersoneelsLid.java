package domain;


import utils.Tijd;

public abstract class PersoneelsLid implements Kost {
    private String naam;
    private final String personeelsnummer;
    private final int maandAanwerving;
    private final int jaarAanwerving;

    protected PersoneelsLid(String personeelsnummer, String naam, int maandAanwerving, int jaarAanwerving) {
        if (!isValidNaamOrPersoneelsnummer(naam))
            throw new IllegalArgumentException("Geen geldige naam");
        this.naam = naam;
        if (!isValidNaamOrPersoneelsnummer(personeelsnummer))
            throw new IllegalArgumentException("Geen geldig personeelsnummer");
        this.personeelsnummer = personeelsnummer;
        if (!Tijd.isGeldigeMaand(maandAanwerving))
            throw new IllegalArgumentException("Geen geldige maand van aanwerving");
        this.maandAanwerving = maandAanwerving;
        if (!Tijd.isGeldigJaar(jaarAanwerving))
            throw new IllegalArgumentException("Geen geldig jaar van aanwerving");
        this.jaarAanwerving = jaarAanwerving;
    }

    public static boolean isValidNaamOrPersoneelsnummer(String naam) {
        return naam != null && !naam.isEmpty();
    }

    public String getNaam() {
        return naam;
    }

    public int getMaandAanwerving() {
        return maandAanwerving;
    }

    public int getJaarAanwerving() {
        return jaarAanwerving;
    }

    /**
     * berekent de kostprijs van voorliggende kost
     */
    @Override
    public abstract double geefKostprijs();

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
        return personeelsnummer;
    }

    @Override
    public boolean equals(Object o) {
        if (o == null || o.getClass() != this.getClass())
            return false;
        return ((PersoneelsLid) o).geefId().equals(this.geefId());

    }

    @Override
    public String toString() {
        return "Personeelslid met id "+this.geefId()+", naam "+this.getNaam()
                +"\nAangeworven in "+this.maandAanwerving+"/"+this.jaarAanwerving;
    }

    @Override
    public PersoneelsLid clone() throws CloneNotSupportedException {
        return (PersoneelsLid) super.clone();
    }

}
