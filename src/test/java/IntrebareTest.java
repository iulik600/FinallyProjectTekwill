import org.example.finallyprojecttekwill.Intrebare;
import org.example.finallyprojecttekwill.Varianta;
import org.junit.jupiter.api.Test;

import java.util.HashMap;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class IntrebareTest {
    @Test
    void testGetVariantaCorecta() {
        Map<Varianta, String> optiuni = new HashMap<>();
        optiuni.put(Varianta.A, "Pisica");
        optiuni.put(Varianta.B, "Caine");
        optiuni.put(Varianta.C, "Iepure");
        optiuni.put(Varianta.D, "Cal");

        Intrebare intrebare = new Intrebare("Ce animal miaună?", optiuni, Varianta.A);
        assertEquals(Varianta.A, intrebare.getVariantaCorecta());
    }
}
