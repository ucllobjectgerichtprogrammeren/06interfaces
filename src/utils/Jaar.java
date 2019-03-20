package utils;

public class Jaar implements Comparable<Jaar> {
    private int jaarNummer;

    public static boolean isGeldigJaarNummer(int jaarNummer) {
        return jaarNummer >= 2000 && jaarNummer <= 2030;
    }

    public Jaar(int jaarNummer) {
        setJaarNummer(jaarNummer);
    }

    private void setJaarNummer(int jaarNummer) {
        if (!isGeldigJaarNummer(jaarNummer))
            throw new IllegalArgumentException("Geen geldig jaarnummer");
        this.jaarNummer = jaarNummer;
    }

    public int getJaarNummer() {
        return this.jaarNummer;
    }

    @Override
    public boolean equals(Object o) {
        try {
            return ((Jaar) o).getJaarNummer() == this.getJaarNummer();
        } catch (ClassCastException e) {
            return false;
        }
    }

    @Override
    public String toString() {
        return this.getJaarNummer() + "";
    }


    @Override
    public int compareTo(Jaar o) {
        return this.getJaarNummer() - o.getJaarNummer();
    }
}
