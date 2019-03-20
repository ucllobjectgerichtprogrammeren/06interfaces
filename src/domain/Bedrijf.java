package domain;

import java.util.ArrayList;
import java.util.List;

public class Bedrijf {
    private final List<Kost> kosten = new ArrayList<>();
    private final String naam;


    public Bedrijf(String naam) {
        if (!isGeldigeNaam(naam))
            throw new IllegalArgumentException("Geen geldige naam voor het bedrijf");
        this.naam = naam;
    }

    /**
     * Zoekt gegeven kost in de lijst met kosten
     * @return de gevonden kost
     * @return null indien de gegeven kost niet gevonden werd
     */
    public Kost vind(Kost kost) {
        for (Kost k:getKosten()
             ) {
            if (k.equals(kost))
                return k;
        }
        return null;
    }

    public void voegKostToe(Kost kost) {
        if (vind(kost) != null)
            throw new IllegalArgumentException("De gegeven kost is reeds opgenomen in de lijst");
        kosten.add(kost);
    }

    public double geefTeBetalenBedrag(int maand, int jaar) {
        double result = 0;
        for (Kost kost:getKosten()) {
            if (kost.moetNuBetaaldWorden(maand,jaar))
                result += kost.geefKostprijs();
        }
        return result;
    }

    public List<Kost> getKosten() {
        return kosten;
    }

    private static boolean isGeldigeNaam(String naam) {
        return naam != null && !naam.trim().isEmpty();
    }
}
