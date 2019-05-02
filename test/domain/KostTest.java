package domain;

import org.junit.Test;

import static org.junit.Assert.*;


/**
 * Een klasse van testen voor de interface Kost en zijn implementaties
 */
public class KostTest {


    SalarisPersoneelsLid salarisPersoneelslid1 =
            new SalarisPersoneelsLid("12345", "Jan", 1, 2019, 1000);
    SalarisPersoneelsLid salarisPersoneelslid2 =
            new SalarisPersoneelsLid("12347", "Piet", 2, 2019, 1000);

    FactuurLijn factuurLijn1 = new FactuurLijn("boek", 3, 10);
    FactuurLijn factuurLijn2 = new FactuurLijn("schrift", 2, 5);
    FactuurLijn factuurLijn3 = new FactuurLijn("potlood", 10, 1);
    FactuurLijn[] factuurlijnen = {factuurLijn1, factuurLijn2, factuurLijn3};
    Factuur factuur1 = new Factuur("12346", 1, 2019, factuurlijnen);
    Factuur factuur2 = new Factuur("12346", 1, 2020, factuurlijnen);
    // als kost declareren

    // foutenmarge bij assertEquals(double, double)
    double delta = 0.0001;

    @Test
    public void test_kostprijs() {
        assertEquals(salarisPersoneelslid1.geefKostprijs(), 1000, delta);
        assertEquals(factuur1.geefKostprijs(), 50, delta);
    }

    @Test
    public void test_moetNuBetaaldWorden() {
        assertTrue(factuur1.moetNuBetaaldWorden(1, 2019));
        assertFalse(factuur1.moetNuBetaaldWorden(2, 2019));
        assertFalse(factuur1.moetNuBetaaldWorden(1, 2020));
        assertTrue(salarisPersoneelslid1.moetNuBetaaldWorden(2, 2020));
        assertFalse(salarisPersoneelslid2.moetNuBetaaldWorden(1, 2019));
    }

    @Test
    public void test_equals_personeelslid() {
        // personeelsleden zijn gelijk indien ze hetzelfde personeelsnummer hebben
        assertEquals(new SalarisPersoneelsLid("12345", "Jan", 1, 2019, 1000),
                (new SalarisPersoneelsLid("12345", "Piet", 1, 2019, 1000)));
        assertNotEquals(salarisPersoneelslid1, salarisPersoneelslid2);
    }

    @Test
    public void equals_factuur() {
        // facturen zijn gelijk als ze dezelfde factuurnummer hebben
        assertEquals(new Factuur("12346", 1, 2019, factuurlijnen),
                new Factuur("12346", 2, 2020, factuurlijnen));
        assertNotEquals(new Factuur("12346", 1, 2019, factuurlijnen),
                new Factuur("12347", 1, 2019, factuurlijnen));
    }

    @Test
    public void equals_kost_verschillend_type() {
        // kosten van verschillend type zijn altijd verschillend
        assertNotEquals(new Factuur("12346", 1, 2019, factuurlijnen),
                new SalarisPersoneelsLid("12346", "Jan", 1, 2019, 1000));
    }

    @Test
    public void deep_clone_test_salarispersoneel() throws CloneNotSupportedException {
        SalarisPersoneelsLid sClone = (SalarisPersoneelsLid) salarisPersoneelslid1.clone();
        //System.out.println(sClone.toString());
        assertEquals(salarisPersoneelslid1.toString(),sClone.toString() );
        assertNotSame(salarisPersoneelslid1,sClone);
    }

    @Test
    public  void deep_clone_test_factuur() throws CloneNotSupportedException {
        Factuur cloneFactuur1 = factuur1.clone();
        assertEquals(factuur1.toString(),cloneFactuur1.toString());
        assertNotSame(factuur1, cloneFactuur1);
        factuurLijn1.setArtikelnaam("ietsNieuws");
        assertNotEquals(factuur1.toString(),cloneFactuur1.toString());

    }
}