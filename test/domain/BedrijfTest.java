package domain;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

/**
 * Een klasse met testen voor de klasse Bedrijf
 */
public class BedrijfTest {
    double delta = 1.e-4;
    Kost p1 = new SalarisPersoneelsLid("001", "Jansen", 4, 2014, 2000.00);
    Kost p2 = new SalarisPersoneelsLid("002", "Fransen", 2, 2015, 2087.00);
    Kost p3 = new SalarisPersoneelsLid("003", "Jansen", 4, 2016, 1977.50);
    Kost f1 = new Factuur("201601", 2, 2016,
            new FactuurLijn("a1", 10, 102.34));
    Kost f2 = new Factuur("201602", 2, 2016,
            new FactuurLijn("a1", 102, 102.34),
            new FactuurLijn("a12", 21, 200.00));
    Kost f3 = new Factuur("201603", 4, 2016,
            new FactuurLijn("a7", 85, 1046.50),
            new FactuurLijn("a23", 8, 100.34));
    Kost nietToegevoegdPersoneelslid =
            new SalarisPersoneelsLid("000", "Jansen", 4, 2014, 2000.00);


    Bedrijf bedrijf;

    @Before
    public void setUp() {
        bedrijf = new Bedrijf("MijnBedrijf");
        bedrijf.voegKostToe(p1);
        bedrijf.voegKostToe(p2);
        bedrijf.voegKostToe(p3);
        bedrijf.voegKostToe(f1);
        bedrijf.voegKostToe(f2);
        bedrijf.voegKostToe(f3);
    }


    @Test
    public void test_voeg_toe() {
        assertEquals(6, bedrijf.getKosten().size());
        assertNotNull(bedrijf.vind(p1));
        assertNull(bedrijf.vind(nietToegevoegdPersoneelslid));
    }

    @Test(expected = IllegalArgumentException.class)
    public void test_voeg_toe_kost_kan_maar_een_maal_toegevoegd_worden() {
        bedrijf.voegKostToe(f3);
    }

    @Test
    public void test_te_betalen_bedrag() {
        assertEquals(19749.08,bedrijf.geefTeBetalenBedrag(2,2016), delta);
    }

}