import org.example.finallyprojecttekwill.Ajutor5050;
import org.example.finallyprojecttekwill.Intrebare;
import org.example.finallyprojecttekwill.Varianta;
import org.junit.jupiter.api.Test;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class Ajutor5050Test {

    @Test
    void testOferaAjutorReturnand() {
        // Arrange
        Map<Varianta, String> optiuni = new HashMap<>();
        optiuni.put(Varianta.A, "Raspuns A");
        optiuni.put(Varianta.B, "Raspuns B");
        optiuni.put(Varianta.C, "Raspuns C");
        optiuni.put(Varianta.D, "Raspuns D");

        Intrebare intrebare = new Intrebare("Care este capitala Franței?", optiuni, Varianta.B);
        Ajutor5050 ajutor5050 = new Ajutor5050();

        // Act
        List<Varianta> varianteRamase = ajutor5050.oferaAjutorReturnand(intrebare);

        // Assert
        assertEquals(2, varianteRamase.size(), "Trebuie să rămână exact 2 variante.");
        assertTrue(varianteRamase.contains(Varianta.B), "Răspunsul corect trebuie să fie păstrat.");
        assertTrue(optiuni.keySet().containsAll(varianteRamase), "Toate variantele trebuie să fie opțiuni valide.");
    }
}
