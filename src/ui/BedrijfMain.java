package ui;

import domain.*;

import java.io.IOException;
import java.util.ArrayList;

/**
 * Een klasse waarin
 * - een bedrijf wordt aangemaakt met verschillende kosten
 * - alle kosten van het bedrijf worden weggeschreven naar een bestand
 * - de kosten opnieuw worden uitgelezen en afgedrukt naar console ter controle
 */
public class BedrijfMain {
    public static void main(String[] args) {
        PersoneelsLid p1 = new SalarisPersoneelsLid("001", "Jansen", 4, 2014, 2000.00);
        PersoneelsLid p2 = new SalarisPersoneelsLid("002", "Fransen", 2, 2015, 2087.00);
        PersoneelsLid p3 = new SalarisPersoneelsLid("003", "Jansen", 4, 2016, 1977.50);
        Factuur f1 = new Factuur("201601", 2, 2016,
                new FactuurLijn("a1", 10, 102.34));
        Factuur f2 = new Factuur("201602", 2, 2016,
                new FactuurLijn("a1", 102, 102.34),
                new FactuurLijn("a12", 21, 200.00));
        Factuur f3 = new Factuur("201603", 4, 2016,
                new FactuurLijn("a7", 85, 1046.50),
                new FactuurLijn("a23", 8, 100.34));
        SalarisPersoneelsLid nietToegevoegdPersoneelslid =
                new SalarisPersoneelsLid("000", "Jansen", 4, 2014, 2000.00);


        Bedrijf bedrijf = new Bedrijf("MijnBedrijf");

        bedrijf.voegKostToe(p1);
        bedrijf.voegKostToe(p2);
        bedrijf.voegKostToe(p3);
        bedrijf.voegKostToe(f1);
        bedrijf.voegKostToe(f2);
        bedrijf.voegKostToe(f3);

        try {
            bedrijf.schrijfKostenWeg("lijstKostenMijnBedrijf");
        } catch (IOException e) {
            System.out.println(e.toString());
        }

        try {
            ArrayList<Kost> kosten = (ArrayList<Kost>) bedrijf.leesKosten("lijstKostenMijnBedrijf");
            for (Kost k: kosten
                 ) {
                System.out.println(k.toString());
                System.out.println("");
            }
        } catch (IOException | ClassNotFoundException e) {
            System.out.println("FOUT: " + e.toString());
        }
    }
}

