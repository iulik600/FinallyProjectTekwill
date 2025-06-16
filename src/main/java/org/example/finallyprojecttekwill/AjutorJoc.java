package org.example.finallyprojecttekwill;

@FunctionalInterface

// Indica faptul că aceasta este o interfața funcționala – adică are o singura metoda abstracta
public interface AjutorJoc {
   // Ea primește ca parametru o intrebare si ofera ajutorul corespunzator pe baza acelei întrebari
    void oferaAjutor(Intrebare intrebare);
}
