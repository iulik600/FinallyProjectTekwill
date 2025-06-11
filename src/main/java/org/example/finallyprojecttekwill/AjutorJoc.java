package org.example.finallyprojecttekwill;

@FunctionalInterface
// Indică faptul că aceasta este o interfață funcțională – adică are o singură metodă abstracta
public interface AjutorJoc {
   // Ea primește ca parametru o intrebare si ofera ajutorul corespunzator pe baza acelei întrebari
    void oferaAjutor(Intrebare intrebare);
}
