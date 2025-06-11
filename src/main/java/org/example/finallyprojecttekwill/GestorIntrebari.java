package org.example.finallyprojecttekwill;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class GestorIntrebari {
    //Metoda statica ce returneaza o lista de obiecte Intrebare
    public static List<Intrebare> incarcaIntrebari() {
        List<Intrebare> intrebari = new ArrayList<>();
        Map<Varianta, String> optiuni1 = new HashMap<>();
        optiuni1.put(Varianta.A, "Paris");
        optiuni1.put(Varianta.B, "Londra");
        optiuni1.put(Varianta.C, "Berlin");
        optiuni1.put(Varianta.D, "Madrid");

        intrebari.add(new Intrebare("Care este capitala Frantei?",
                optiuni1, Varianta.A)); // enuntul intrebarii / variantele de raspuns / raspunsul corect

        // Intrebarea 2
        Map<Varianta, String> optiuni2 = new HashMap<>();
        optiuni2.put(Varianta.A, "Venus");
        optiuni2.put(Varianta.B, "Marte");
        optiuni2.put(Varianta.C, "Jupiter");
        optiuni2.put(Varianta.D, "Saturn");

        intrebari.add(new Intrebare("Ce planeta este cunoscuta ca Planeta Rosie?",
                optiuni2, Varianta.B));

        // intrebare 3
        Map<Varianta, String> optiuni3 = new HashMap<>();
        optiuni3.put(Varianta.A, "Plumb");
        optiuni3.put(Varianta.B, "Osmium");
        optiuni3.put(Varianta.C, "Uranium");
        optiuni3.put(Varianta.D, "Mercur");

        intrebari.add(new Intrebare("Care este cel mai greu element chimic natural?",
                optiuni3,Varianta.C));

        // Returneaza lista completa cu intrebbarile incarcate
        return intrebari;
    }
}
