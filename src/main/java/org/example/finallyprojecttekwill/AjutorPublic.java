package org.example.finallyprojecttekwill;

import java.util.*;

public class AjutorPublic implements AjutorJoc {

    @Override
    public void oferaAjutor(Intrebare intrebare) {
        Map<Varianta, Integer> procentaje = genereazaProcentaje(intrebare);

        System.out.println("Publicul a votat astfel:");
        for (Map.Entry<Varianta, Integer> entry : procentaje.entrySet()) {
            System.out.println(entry.getKey() + ": " + entry.getValue() + "%");
        }
    }

    // Metodă care generează procentajele și le returnează pentru interfață grafică
    public Map<Varianta, Integer> genereazaProcentaje(Intrebare intrebare) {
        Random random = new Random();
        Varianta corecta = intrebare.getVariantaCorecta();

        Map<Varianta, Integer> procentaje = new EnumMap<>(Varianta.class);

        int procentCorect = 50 + random.nextInt(31); // între 50% și 80%
        int ramase = 100 - procentCorect;

        List<Varianta> gresite = new ArrayList<>();
        for (Varianta v : Varianta.values()) {
            if (!v.equals(corecta)) {
                gresite.add(v);
            }
        }

        Collections.shuffle(gresite);

        int p1 = random.nextInt(ramase + 1);
        int p2 = random.nextInt(ramase - p1 + 1);
        int p3 = ramase - p1 - p2;

        procentaje.put(corecta, procentCorect);
        procentaje.put(gresite.get(0), p1);
        procentaje.put(gresite.get(1), p2);
        procentaje.put(gresite.get(2), p3);

        return procentaje;
    }
}
