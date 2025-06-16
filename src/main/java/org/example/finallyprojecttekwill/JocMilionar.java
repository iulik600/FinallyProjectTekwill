package org.example.finallyprojecttekwill;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Scanner;

public class JocMilionar {
    private List<Intrebare> intrebari;
    private Integer scor = 0;
    private Scanner scanner;

    private Map<String, AjutorJoc> ajutoareDisponibile;

    public JocMilionar(List<Intrebare> intrebari) {
        this.intrebari = intrebari;
        this.scor = 0;
        this.scanner = new Scanner(System.in);

        //Initializam ajutoarele disponibile
        ajutoareDisponibile = new HashMap<>();
        ajutoareDisponibile.put("5050", new Ajutor5050());
        ajutoareDisponibile.put("prieten", new AjutorSunaPrieten());
        ajutoareDisponibile.put("public", new AjutorPublic());
    }


    public int getScor() {
        return scor;
    }

    public void adaugaScor(int puncte) {
         scor += puncte;
    }

    public List<Intrebare> getIntrebari() {
        return intrebari;
    }

    private void afiseazaOptiuniAjutor() {
        System.out.println("Ajutoare disponibile: ");
        for (String cheie : ajutoareDisponibile.keySet()) {
            System.out.println(cheie + " ");
        }
        System.out.println();
    }

    // Metodă care activează un ajutor, dacă este disponibil
    public void folosesteAjutor(String cheie, Intrebare intrebare) {
        AjutorJoc ajutor = ajutoareDisponibile.get(cheie);
        if (ajutor != null) {
            ajutor.oferaAjutor(intrebare);
            ajutoareDisponibile.remove(cheie); // poate fi folosit doar odata
        } else {
            System.out.println("Ajutorul a fost deja folosit.");
        }
    }

    public void pornesteJoc() {
        System.out.println("Bun venit la 'Cine vrea sa fie milionar?' ");

        // Parcurgem toate întrebările din listă
        for (int i = 0; i < intrebari.size(); i++) {
            Intrebare intrebare = intrebari.get(i);
            System.out.println("Intrebarea " + (i + 1) + ": " + intrebare.getEnunt());

            // Afisam optiunile
            for (Map.Entry<Varianta, String> entry : intrebare.getOptiuni().entrySet()) {
                System.out.println(entry.getKey() + ": " + entry.getValue());
            }

            afiseazaOptiuniAjutor();

            System.out.println("Raspunsul tau (A/B/C/D sau ajutor): ");
            String raspuns = scanner.nextLine();

            // Dacă utilizatorul a scris un ajutor
            if (ajutoareDisponibile.containsKey(raspuns)) {
                folosesteAjutor(raspuns, intrebare);
                i--; // Ramane la aceaiasi intrebare
                continue;
            }

            // Validare Raspuns
            try {
                Varianta variantaAleasa = Varianta.valueOf(raspuns.toUpperCase());
                if (variantaAleasa == intrebare.getVariantaCorecta()) {
                    scor += 100;
                    System.out.println("Corect! Scorul tau: " + scor);
                } else {
                    System.out.println("Gresit! Raspunsul corect era: " + intrebare.getVariantaCorecta());
                    System.out.println("Scor final: " + scor);
                    break;
                }
            } catch (IllegalArgumentException e) {
                // Dacă utilizatorul a scris un răspuns invalid (nu A/B/C/D)
                System.out.println("Raspuns invalid. Incearca din nou.");
                i--; // ramane la aceiasi intrebare
            } catch (Exception e) {
                // Orice altă eroare neașteptată
                System.out.println("A aparut o eroare: " + e.getMessage());
                break;
            }
        }

        System.out.println("Joc incheiat. Scor final: " + scor);
    }

}
