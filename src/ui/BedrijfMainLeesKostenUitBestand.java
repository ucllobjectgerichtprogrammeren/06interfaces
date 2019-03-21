package ui;

import domain.Bedrijf;
import domain.Kost;

import java.io.IOException;
import java.util.ArrayList;

/**
 * Een klasse waarin
 * - een bedrijf wordt aangemaakt ZONDER kosten
 * - kosten worden uitgelezen uit een bestand (bijvoorbeeld gemaakt met klasse BedrijfMain)
 * - ter controle alle kosten worden uitgeschreven naar de console
 */
public class BedrijfMainLeesKostenUitBestand {

    public static void main(String[] args) {
        Bedrijf bedrijf = new Bedrijf("Mijn Bedrijf");
        ArrayList<Kost> kosten = null;

        try {
            kosten = (ArrayList<Kost>) bedrijf.leesKosten("lijstKostenMijnBedrijf");
        } catch (IOException | ClassNotFoundException e) {
            System.out.println(e.toString());
        }

        if (kosten != null) {
            for (Kost kost : kosten) {
                bedrijf.voegKostToe(kost);
            }
        }
        System.out.println(bedrijf.toString());
        System.out.println("Het totaal te bedalen bedrag voor februari 2016 is\n€ "+bedrijf.geefTeBetalenBedrag(2,2016));
    }
}
