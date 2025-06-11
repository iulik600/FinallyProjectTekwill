package org.example.finallyprojecttekwill;

import java.util.*;

public class AjutorSunaPrieten implements AjutorJoc {

    @Override
    public void oferaAjutor(Intrebare intrebare) {
        // pentru compatibilitate cu interfața AjutorJoc
        oferaAjutorReturnand(intrebare);
    }

    // Metodă care întoarce varianta sugerată de "prieten"
    public Varianta oferaAjutorReturnand(Intrebare intrebare) {
        Random random = new Random();
        Varianta variantaSugerata;

        // Cu o probabilitate de 70%, prietenul da varianta corecta
        if (random.nextInt(100) < 70) {
            variantaSugerata = intrebare.getVariantaCorecta();
        } else {
            // Sau, alege una din variantele greșite în mod aleator
            List<Varianta> gresite = new ArrayList<>();
            for (Varianta v : Varianta.values()) {
                if (!v.equals(intrebare.getVariantaCorecta())) {
                    gresite.add(v);
                }
            }
            // Amestecă variantele greșite și alege una
            Collections.shuffle(gresite);
            variantaSugerata = gresite.get(0);
        }

        // returneaza varianta sugerata
        return variantaSugerata;
    }
}

