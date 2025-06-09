package org.example.finallyprojecttekwill;

import java.util.List;

public class Main {
    public static void main(String[] args) {

        // Folosim clasa specializata pentru intrebari
        List<Intrebare> intrebari = GestorIntrebari.incarcaIntrebari();

        JocMilionar joc = new JocMilionar(intrebari);
        joc.pornesteJoc();
    }
}
