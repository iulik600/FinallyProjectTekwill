package org.example.finallyprojecttekwill;

import java.util.*;

public class AjutorPublic implements AjutorJoc {

    @Override
    public void oferaAjutor(Intrebare intrebare) {
        // Generează un set de procentaje pentru fiecare variantă de răspuns
        Map<Varianta, Integer> procentaje = genereazaProcentaje(intrebare);

        // Afișeaza pe consola rezultatele sondajului publicului
        System.out.println("Publicul a votat astfel:");
        for (Map.Entry<Varianta, Integer> entry : procentaje.entrySet()) {
            System.out.println(entry.getKey() + ": " + entry.getValue() + "%");
        }
    }

    // Metoda care genereaza procentajele si le returneaza pentru interfața grafica
    public Map<Varianta, Integer> genereazaProcentaje(Intrebare intrebare) {
        Random random = new Random();
        Varianta corecta = intrebare.getVariantaCorecta();

        // Creeaza o mapa pentru a stoca procentajele pentru fiecare varianta (A, B, C, D)
        Map<Varianta, Integer> procentaje = new EnumMap<>(Varianta.class);

        // Publicul tinde să voteze mai mult pentru varianta corectă: între 50% și 80%
        int procentCorect = 50 + random.nextInt(31); // între 50% și 80%
        int ramase = 100 - procentCorect;

        // Creeaza o lista cu variantele greșite (excluzand-o pe cea corecta)
        List<Varianta> gresite = new ArrayList<>();
        for (Varianta v : Varianta.values()) {
            if (!v.equals(corecta)) {
                gresite.add(v);
            }
        }

        // Amesteca variantele greșite pentru diversitate in rezultate
        Collections.shuffle(gresite);

        // imparte procentele ramase in mod aleator intre cele 3 variante greșite
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
