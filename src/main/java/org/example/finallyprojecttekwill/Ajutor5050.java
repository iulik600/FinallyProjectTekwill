package org.example.finallyprojecttekwill;

import java.util.*;

public class Ajutor5050 implements AjutorJoc {

    @Override
    public void oferaAjutor(Intrebare intrebare) {
        // versiunea veche pentru consolă
        List<Varianta> ramase = oferaAjutorReturnand(intrebare);
        Map<Varianta, String> optiuni = intrebare.getOptiuni();
        System.out.println("Raman doua variante:");
        for (Varianta v : ramase) {
            System.out.println(v + ": " + optiuni.get(v));
        }
    }

    // Metoda care returnează cele 2 variante rămase
    public List<Varianta> oferaAjutorReturnand(Intrebare intrebare) {
        Varianta corecta = intrebare.getVariantaCorecta();
        List<Varianta> gresite = new ArrayList<>();

        for (Varianta v : Varianta.values()) {
            if (!v.equals(corecta)) {
                gresite.add(v);
            }
        }
        // Amestecă aleator opțiunile greșite și alege una.
        Collections.shuffle(gresite);
        Varianta unaGresita = gresite.get(0);

        // creaza o lista cu variantele ramase
        List<Varianta> ramase = new ArrayList<>();
        ramase.add(corecta);
        ramase.add(unaGresita);

        //returneaza variantele ramase
        return ramase;
    }
}
