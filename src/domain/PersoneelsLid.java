package domain;


import utils.Tijd;

public abstract class PersoneelsLid implements Cloneable {
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

    public String getPersoneelsnummer() {
        return personeelsnummer;
    }

    /**
     * Returnt true indien gegeven object van hetzelfde type is en zijn id gelijk is aan this
     */
    @Override
    public boolean equals(Object o) {
        if (o == null || o.getClass() != this.getClass())
            return false;
        return ((PersoneelsLid) o).getPersoneelsnummer().equals(this.getPersoneelsnummer());

    }

    @Override
    public String toString() {
        return "Personeelslid met id " + this.getPersoneelsnummer() + ", naam " + this.getNaam()
                + "\nAangeworven in " + this.maandAanwerving + "/" + this.jaarAanwerving + "\n";
    }

    @Override
    public PersoneelsLid clone() throws CloneNotSupportedException {
        return (PersoneelsLid) super.clone();
    }

}
