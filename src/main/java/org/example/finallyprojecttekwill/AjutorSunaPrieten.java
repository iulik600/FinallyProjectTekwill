package org.example.finallyprojecttekwill;

import java.util.*;

public class AjutorSunaPrieten implements AjutorJoc {

    @Override
    public void oferaAjutor(Intrebare intrebare) {
        // pentru compatibilitate cu interfața AjutorJoc
        oferaAjutorReturnand(intrebare);
    }

    public Varianta oferaAjutorReturnand(Intrebare intrebare) {
        Random random = new Random();
        Varianta variantaSugerata;

        if (random.nextInt(100) < 70) {
            variantaSugerata = intrebare.getVariantaCorecta();
        } else {
            List<Varianta> gresite = new ArrayList<>();
            for (Varianta v : Varianta.values()) {
                if (!v.equals(intrebare.getVariantaCorecta())) {
                    gresite.add(v);
                }
            }
            Collections.shuffle(gresite);
            variantaSugerata = gresite.get(0);
        }

        return variantaSugerata;
    }
}

