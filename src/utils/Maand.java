package utils;

public class Maand implements Comparable<Maand> {
    private int maandNummer;

    public static boolean isGeldigMaandNummer(int maandNummer) {
        return maandNummer > 0 && maandNummer <= 12;
    }

    public Maand(int maandNummer) {
        setMaandNummer(maandNummer);
    }

    private void setMaandNummer(int maandNummer) {
        if (!isGeldigMaandNummer(maandNummer))
            throw new IllegalArgumentException("Geen geldig maandnummer");
        this.maandNummer = maandNummer;
    }

    public int getMaandNummer() {
        return this.maandNummer;
    }

    @Override
    public boolean equals(Object o) {
        try {
            return ((Maand) o).getMaandNummer() == this.getMaandNummer();
        } catch (ClassCastException e) {
            return false;
        }
    }

    @Override
    public String toString() {
        return this.getMaandNummer() + "";
    }

    @Override
    public int compareTo(Maand o) {
        return this.getMaandNummer() - o.getMaandNummer();
    }
}
