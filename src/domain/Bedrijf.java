package domain;

import java.io.*;
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
     *
     * @return null indien de gegeven kost niet gevonden werd
     */
    public Kost vind(Kost kost) {
        for (Kost k : getKosten()
        ) {
            if (k.equals(kost))
                return k;
        }
        return null;
    }

    /**
     * Voegt de gegeven kost toe aan de lijst met kosten
     */
    public void voegKostToe(Kost kost) {
        if (vind(kost) != null)
            throw new IllegalArgumentException("De gegeven kost is reeds opgenomen in de lijst");
        kosten.add(kost);
    }

    /**
     * Berekent het te betalen bedrag in gegeven maand en jaar
     */
    public double geefTeBetalenBedrag(int maand, int jaar) {
        double result = 0;
        for (Kost kost : getKosten()) {
            if (kost.moetNuBetaaldWorden(maand, jaar))
                result += kost.geefKostprijs();
        }
        return result;
    }

    public List<Kost> getKosten() {
        return kosten;
    }

    public String getNaam() {
        return naam;
    }

    private static boolean isGeldigeNaam(String naam) {
        return naam != null && !naam.trim().isEmpty();
    }

    /**
     * Serialiseert de lijst met kosten naar bestand met gegeven naam
     *
     * @throws IOException
     */
    public void schrijfKostenWeg(String filename) throws IOException {
        FileOutputStream fs = new FileOutputStream(filename);
        ObjectOutputStream os = new ObjectOutputStream(fs);
        os.writeObject(getKosten());
        os.close();
    }

    /**
     * Deserialiseert de lijst met kosten uit een bestand met gegeven naam
     *
     * @return een lijst met kosten
     */
    public List<Kost> leesKosten(String filename) throws IOException, ClassNotFoundException {
        FileInputStream fs = new FileInputStream(filename);
        ObjectInputStream os = new ObjectInputStream(fs);
        List<Kost> kosten = (ArrayList<Kost>) os.readObject();
        os.close();
        return kosten;
    }

    public String toString() {
        String result = "Bedrijf: " + this.getNaam() + "\n";
        for (Kost kost : getKosten()
        ) {
            result += kost.toString() + "\n";
        }

        return result;
    }
}
