package domain;

import utils.Tijd;


public class Factuur implements Kost {
    private final String factuurnummer;
    private int maand;
    private int jaar;
    private FactuurLijn[] factuurlijnen;

    public Factuur(String factuurnummer, int maand, int jaar, FactuurLijn... factuurlijnen) {
        this.factuurnummer = factuurnummer;
        setMaand(maand);
        setJaar(jaar);
        setFactuurlijnen(factuurlijnen);
    }

    private void setFactuurlijnen(FactuurLijn[] factuurlijnen) {
        if (factuurlijnen == null || factuurlijnen.length < 1)
            throw new IllegalArgumentException("Er moet minstens één factuurlijn zijn");
        this.factuurlijnen = factuurlijnen;
    }

    public FactuurLijn[] getFactuurlijnen() {
        return factuurlijnen;
    }

    private void setJaar(int jaar) {
        if (!Tijd.isGeldigJaar(jaar))
            throw new IllegalArgumentException("Geen geldig jaar");
        this.jaar = jaar;
    }

    private void setMaand(int maand) {
        if (!Tijd.isGeldigeMaand(maand))
            throw new IllegalArgumentException("Geen geldige maand");
        this.maand = maand;
    }

    public int getMaand() {
        return maand;
    }

    public int getJaar() {
        return jaar;
    }

    /**
     * berekent de kostprijs van voorliggende kost
     */
    @Override
    public double geefKostprijs() {
        double som = 0;
        for (FactuurLijn factuurlijn : factuurlijnen
        ) {
            som += factuurlijn.getKostprijsFactuurLijn();
        }
        return som;
    }

    /**
     * Geeft true als deze kost in gegeven maand en jaar betaald moet worden
     *
     * @param maand
     * @param jaar
     */
    @Override
    public boolean moetNuBetaaldWorden(int maand, int jaar) {
        if (!Tijd.isGeldigeMaand(maand) || !Tijd.isGeldigJaar(jaar))
            throw new IllegalArgumentException("Geen geldig tijdstip om te oordelen");
        return maand == this.maand && jaar == this.jaar;
    }

    @Override
    public String geefId() {
        return factuurnummer;
    }

    @Override
    public String toString() {
        String result = "Factuur met nummer " + this.factuurnummer + ":\n";
        result += "Maand: " + maand + " Jaar: " + jaar + "\n";
        for (FactuurLijn factuurlijn : factuurlijnen
        ) {
            result += factuurlijn.toString() + "\n";
        }
        result += "totale kost van deze factuur: €" + this.geefKostprijs() + "\n";
        return result;
    }

    @Override
    public boolean equals(Object o){
        if (o == null || o.getClass() != this.getClass())
            return false;
        return ((Factuur) o).geefId().equals(this.geefId());
    }

    private int geefAantalFactuurLijnen() {
        return getFactuurlijnen().length;
    }

    private FactuurLijn[] cloneFactuurlijnen() throws CloneNotSupportedException {
        FactuurLijn[] factuurlijnen = new FactuurLijn[geefAantalFactuurLijnen()];
        for (int i = 0; i< geefAantalFactuurLijnen(); i++
             ) {
            factuurlijnen[i]= getFactuurlijnen()[i].clone();
        }
        return factuurlijnen;

    }

    @Override
    public Factuur clone() throws CloneNotSupportedException {
        Factuur factuur = (Factuur) super.clone();
        factuur.setFactuurlijnen(cloneFactuurlijnen());
        return factuur;
    }
}
