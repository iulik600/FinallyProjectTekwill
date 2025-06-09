import org.example.finallyprojecttekwill.AjutorSunaPrieten;
import org.example.finallyprojecttekwill.Intrebare;
import org.example.finallyprojecttekwill.Varianta;
import org.junit.jupiter.api.Test;

import java.util.HashMap;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.assertTrue;

public class AjutorSunaPrietenTest {
    @Test
    public void testAjutorSunaPrietenCorectitudineProbabilistica() {
        // Pregătim întrebarea
        Map<Varianta, String> optiuni = new HashMap<>();
        optiuni.put(Varianta.A, "Paris");
        optiuni.put(Varianta.B, "Londra");
        optiuni.put(Varianta.C, "Berlin");
        optiuni.put(Varianta.D, "Madrid");

        Intrebare intrebare = new Intrebare("Care este capitala Franței?", optiuni, Varianta.A);
        AjutorSunaPrieten ajutor = new AjutorSunaPrieten();

        // Repetăm ajutorul de 1000 de ori ca să vedem frecvența răspunsului corect
        int raspunsuriCorecte = 0;
        int totalIncercari = 1000;

        for (int i = 0; i < totalIncercari; i++) {
            Varianta sugestie = ajutor.oferaAjutorReturnand(intrebare);
            if (sugestie == Varianta.A) {
                raspunsuriCorecte++;
            }

            // Verificăm că sugestia e întotdeauna una din cele 4 variante
            assertTrue(optiuni.containsKey(sugestie));
        }

        double procentCorect = (raspunsuriCorecte * 100.0) / totalIncercari;
        System.out.println("Procent răspunsuri corecte: " + procentCorect);

        // Trebuie să fie aproximativ 70%, dar nu exact — testăm că e între 60% și 80%
        assertTrue(procentCorect >= 60 && procentCorect <= 80);
    }
}
