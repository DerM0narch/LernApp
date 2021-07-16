package lernApp;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

/**
 * Datenbank test
 */
class DatenbankTest {

    /**
     * Test der select Befehle.
     */
    @Test
    void testSelect() {
        Datenbank db = new Datenbank();
        assertEquals(db.selectId("SELECT * FROM fragen WHERE ID = 1"), 1);
        assertEquals(db.selectFrage("SELECT * FROM fragen WHERE ID = 1"), "Welche ist die sicherste WLAN-Verschlüsselung?");
        assertEquals(db.selectRichtig("SELECT * FROM fragen WHERE ID = 1"), "WPA2");
        assertEquals(db.selectErsteFalsch("SELECT * FROM fragen WHERE ID = 1"), "WEP");
        assertEquals(db.selectZweiteFalsch("SELECT * FROM fragen WHERE ID = 1"), "WPA");
        assertFalse(db.selectMarkiert("SELECT * FROM fragen WHERE ID = 1"));
    }
}