package org.example.finallyprojecttekwill;

import java.util.List;
import java.util.Map;

public class Intrebare {
    private String enunt;
    private Map<Varianta, String> optiuni;
    private Varianta variantaCorecta;

    // Constructor
    public Intrebare(String enunt, Map<Varianta, String> optiuni, Varianta variantaCorecta) {
        this.enunt = enunt;
        this.optiuni = optiuni;
        this.variantaCorecta = variantaCorecta;
    }

    // Getteri
    public String getEnunt() {
        return enunt;
    }

    public Map<Varianta, String> getOptiuni() {
        return optiuni;
    }

    public Varianta getVariantaCorecta() {
        return variantaCorecta;
    }

    public boolean afiseazaIntrebare() {
        System.out.println("\n" + enunt);
        for (Varianta v : Varianta.values()) {
            System.out.println(v + ": " + optiuni.get(v));
        }
        return false;
    }
}
