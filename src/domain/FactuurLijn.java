package domain;

public class FactuurLijn implements Cloneable {
    private String artikelnaam;
    private int aantal;
    private double eenheidsprijs;

    public FactuurLijn(String artikelnaam, int aantal, double eenheidsprijs) {
        setArtikelnaam(artikelnaam);
        setAantal(aantal);
        setEenheidsprijs(eenheidsprijs);
    }

    public static boolean isGeldigeArtikelnaam(String naam) {
        return naam != null && !naam.isEmpty();
    }

    public String getArtikelnaam() {
        return artikelnaam;
    }

    public void setArtikelnaam(String artikelnaam) {
        if (!isGeldigeArtikelnaam(artikelnaam))
            throw new IllegalArgumentException("Geen geldige artikelnaam");
        this.artikelnaam = artikelnaam;
    }

    public static boolean isGeldigAantal(int aantal) {
        return aantal > 0;
    }

    public int getAantal() {
        return aantal;
    }

    public void setAantal(int aantal) {
        if (!isGeldigAantal(aantal))
            throw new IllegalArgumentException("Geen geldig aantal");
        this.aantal = aantal;
    }

    private static boolean isGeldigeEenheidsprijs(double eenheidsprijs) {
        return eenheidsprijs > 0;
    }

    public double getEenheidsprijs() {
        return eenheidsprijs;
    }

    public void setEenheidsprijs(double eenheidsprijs) {
        if (!isGeldigeEenheidsprijs(eenheidsprijs))
            throw new IllegalArgumentException("Geen geldige eenheidsprijs");
        this.eenheidsprijs = eenheidsprijs;
    }

    public double getKostprijsFactuurLijn() {
        return this.getAantal() * this.getEenheidsprijs();
    }

    @Override
    public String toString() {
        return getArtikelnaam() + ", " + getAantal() + " stuks, €" + getEenheidsprijs() + " per stuk";
    }

    @Override
    public FactuurLijn clone() throws CloneNotSupportedException {
       return (FactuurLijn) super.clone();
    }
}

