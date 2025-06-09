import org.example.finallyprojecttekwill.JocMilionar;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class JocMilionarTest {
    @Test
    public void testScorInitialEsteZero() {
        JocMilionar joc = new JocMilionar(List.of());
        assertEquals(0, joc.getScor());
    }

    @Test
    public void testAdaugaScor() {
        JocMilionar joc = new JocMilionar(List.of());
        joc.adaugaScor(10);
        assertEquals(10, joc.getScor());

        joc.adaugaScor(5);
        assertEquals(15, joc.getScor());
    }
}
