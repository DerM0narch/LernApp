package lernApp;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

import java.util.ArrayList;

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
        assertEquals(db.selectRichtig("SELECT * FROM fragen WHERE ID = 1"), "WPA2 (Wi-Fi Protected Access 2)");
        assertEquals(db.selectErsteFalsch("SELECT * FROM fragen WHERE ID = 1"), "WEP (Wired Equivalent Privacy)");
        assertEquals(db.selectZweiteFalsch("SELECT * FROM fragen WHERE ID = 1"), "WPA (Wi-Fi Protected Access)");
        assertFalse(db.selectMarkiert("SELECT * FROM fragen WHERE ID = 1"));
    }
    @Test
    void testAllIDs() {
        Datenbank db = new Datenbank();
        ArrayList<Integer> allIDs = db.selectAllFragenIDs();
        for (Integer entry : allIDs) {
            System.out.println(entry);

        }
        assertNotEquals(allIDs.size(), 0);
    }
}