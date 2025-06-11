import org.example.finallyprojecttekwill.AjutorPublic;
import org.example.finallyprojecttekwill.Intrebare;
import org.example.finallyprojecttekwill.Varianta;
import org.junit.jupiter.api.Test;

import java.util.HashMap;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class AjutorPublicTest {
    @Test
    public void testGenereazaProcentaje() {
        // 1. Cream o intrebare test
        Map<Varianta, String> optiuni = new HashMap<>();
        optiuni.put(Varianta.A, "Pisica");
        optiuni.put(Varianta.B, "Câine");
        optiuni.put(Varianta.C, "Urs");
        optiuni.put(Varianta.D, "Vulpe");

        Intrebare intrebare = new Intrebare("Care animal miaună?", optiuni, Varianta.A);

        AjutorPublic ajutor = new AjutorPublic();

        // 2. Generam procentajele
        Map<Varianta, Integer> procentaje = ajutor.genereazaProcentaje(intrebare);

        // 3. Verificam ca toate cele 4 variante sunt prezente
        assertEquals(4, procentaje.size());
        for (Varianta v : Varianta.values()) {
            assertTrue(procentaje.containsKey(v), "Lipsește varianta: " + v);
        }

        // 4. Verificăm ca suma e 100%
        int suma = procentaje.values().stream().mapToInt(Integer::intValue).sum();
        assertEquals(100, suma, "Suma procentajelor trebuie să fie 100%");

        // 5. Verificam intervalul variantei corecte
        int procentCorect = procentaje.get(intrebare.getVariantaCorecta());
        assertTrue(procentCorect >= 50 && procentCorect <= 80, "Procentul pentru varianta corectă e în afara intervalului");

        // 6. Verificam că toate valorile sunt intre 0 și 100
        for (int procent : procentaje.values()) {
            assertTrue(procent >= 0 && procent <= 100, "Procent invalid: " + procent);
        }
    }
}
