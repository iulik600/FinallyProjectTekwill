package org.example.finallyprojecttekwill;

import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.image.ImageView;
import java.util.*;

public class HelloController {

    private boolean ajutorFolositLaIntrebareaCurenta = false;

    @FXML
    private Label intrebareLabel;
    @FXML
    private Label nivelLabel;
    @FXML
    private Label scorLabel;

    @FXML
    private Button btnA;
    @FXML
    private Button btnB;
    @FXML
    private Button btnC;
    @FXML
    private Button btnD;

    @FXML
    private Button btn5050;
    @FXML
    private Button btnPublic;
    @FXML
    private Button btnPrieten;

    @FXML
    private ImageView imagineView;

    private JocMilionar joc;
    private int indexCurent;

    private Map<Varianta, Button> butoaneRaspuns;

    @FXML
    public void initialize() {
        List<Intrebare> intrebari = GestorIntrebari.incarcaIntrebari();
        joc = new JocMilionar(intrebari);
        indexCurent = 0;

        // Asociaza fiecare variantă (A, B, C, D) cu un buton corespunzător
        butoaneRaspuns = Map.of(
                Varianta.A, btnA,
                Varianta.B, btnB,
                Varianta.C, btnC,
                Varianta.D, btnD
        );

        // Setează acțiunea la click pentru fiecare buton de răspuns
        for (Map.Entry<Varianta, Button> entry : butoaneRaspuns.entrySet()) {
            entry.getValue().setOnAction(e -> proceseazaRaspuns(entry.getKey()));
        }

        btn5050.setOnAction(e -> folosesteAjutor(TipAjutor.CINCI_SPREZECE));
        btnPublic.setOnAction(e -> folosesteAjutor(TipAjutor.PUBLIC));
        btnPrieten.setOnAction(e -> folosesteAjutor(TipAjutor.PRIETEN));

        afiseazaIntrebareCurenta();
    }

    private void afiseazaIntrebareCurenta() {
        if (indexCurent >= joc.getIntrebari().size()) {
            intrebareLabel.setText("Felicitări! Ai câștigat jocul!");
            dezactiveazaToateButoanele();
            return;
        }

        Intrebare intrebare = joc.getIntrebari().get(indexCurent);
        intrebareLabel.setText(intrebare.getEnunt()); // afiseaza intrebarea
        nivelLabel.setText("Nivel " + (indexCurent + 1)); // afiseaza nivelul
        scorLabel.setText("Scor: " + joc.getScor()); // afiseaza scorul

        ajutorFolositLaIntrebareaCurenta = false; // Resetăm ajutorul
        activeazaAjutoare(); // Activăm toate ajutoarele

        // Reafisează toate butoanele și le reactivează
        for (Map.Entry<Varianta, String> optiune : intrebare.getOptiuni().entrySet()) {
            Button btn = butoaneRaspuns.get(optiune.getKey());
            btn.setText(optiune.getKey() + ": " + optiune.getValue());
            btn.setDisable(false);
            btn.setVisible(true);
        }

        // Reactivăm butoanele de ajutor (dacă nu au fost folosite deja definitiv)
        if (!btn5050.isDisabled()) btn5050.setDisable(false);
        if (!btnPublic.isDisabled()) btnPublic.setDisable(false);
        if (!btnPrieten.isDisabled()) btnPrieten.setDisable(false);
    }


    private void finalizeazaJocul() {
        // Afișează un dialog de confirmare la finalul jocului
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle("Sfârșitul jocului");
        alert.setHeaderText("Ai răspuns greșit!");
        alert.setContentText("Scorul tău final: " + joc.getScor() + "\nVrei să joci din nou?");

        ButtonType da = new ButtonType("Da");
        ButtonType nu = new ButtonType("Nu");

        alert.getButtonTypes().setAll(da, nu);

        Optional<ButtonType> rezultat = alert.showAndWait();

        // Dacă utilizatorul vrea să joace din nou, se resetează jocul
        if (rezultat.isPresent() && rezultat.get() == da) {
            // Resetare joc
            List<Intrebare> intrebariNoi = GestorIntrebari.incarcaIntrebari();
            joc = new JocMilionar(intrebariNoi);  // creezi un joc nou, cu scor 0
            indexCurent = 0;
            activeazaToateButoanele();
            afiseazaIntrebareCurenta();
        } else {
            dezactiveazaToateButoanele();
        }
    }

    private void proceseazaRaspuns(Varianta variantaAleasa) {
        // Verifică dacă varianta aleasă este corectă
        Intrebare intrebare = joc.getIntrebari().get(indexCurent);
        if (variantaAleasa.equals(intrebare.getVariantaCorecta())) {
            joc.adaugaScor(10);
            indexCurent++; // urmatoarea intrebare trecem
            afiseazaIntrebareCurenta();
        } else {
            finalizeazaJocul(); // raspuns gresit - joc terminat
        }
    }

    private void folosesteAjutor(TipAjutor tipAjutor) {
        // Nu poți folosi mai mult de un ajutor pe întrebare
        if (ajutorFolositLaIntrebareaCurenta) {
            arataAlert("Atenție", "Ai folosit deja un ajutor la această întrebare.");
            return;
        }

        Intrebare intrebare = joc.getIntrebari().get(indexCurent);

        switch (tipAjutor) {
            // Ajutor 50/50 → elimină 2 variante greșite
            case CINCI_SPREZECE -> {
                Ajutor5050 ajutor = new Ajutor5050();
                List<Varianta> varianteRamase = ajutor.oferaAjutorReturnand(intrebare);
                for (Varianta v : Varianta.values()) {
                    if (!varianteRamase.contains(v)) {
                        butoaneRaspuns.get(v).setVisible(false);
                    }
                }
                btn5050.setDisable(true);
            }
// sugestia publicului
            case PUBLIC -> {
                AjutorPublic ajutor = new AjutorPublic();
                Map<Varianta, Integer> voturi = ajutor.genereazaProcentaje(intrebare);
                StringBuilder mesaj = new StringBuilder("Publicul a votat: ");
                voturi.forEach((v, p) -> mesaj.append(v).append(": ").append(p).append("% "));
                arataAlert("Ajutor Public", mesaj.toString());
                btnPublic.setDisable(true);
            }
// sugetia unui prieten
            case PRIETEN -> {
                AjutorSunaPrieten ajutor = new AjutorSunaPrieten();
                Varianta sugestie = ajutor.oferaAjutorReturnand(intrebare);
                arataAlert("Sfatul prietenului", "Cred că răspunsul corect este: " + sugestie);
                btnPrieten.setDisable(true);
            }
        }
        ajutorFolositLaIntrebareaCurenta = true; // marcam ca s-a folosit un ajutor
        dezactiveazaAlteAjutoare(tipAjutor); //dezactivam celelalte ajutoare
    }

    private void arataAlert(String titlu, String mesaj) {
        // afiseaza o fereastra de tip alert
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle(titlu);
        alert.setHeaderText(null);
        alert.setContentText(mesaj);
        alert.showAndWait();
    }

    private void activeazaToateButoanele() {
        btnA.setDisable(false);
        btnB.setDisable(false);
        btnC.setDisable(false);
        btnD.setDisable(false);
        btn5050.setDisable(false);
        btnPublic.setDisable(false);
        btnPrieten.setDisable(false);
    }

    private void dezactiveazaToateButoanele() {
        btnA.setDisable(true);
        btnB.setDisable(true);
        btnC.setDisable(true);
        btnD.setDisable(true);
        btn5050.setDisable(true);
        btnPublic.setDisable(true);
        btnPrieten.setDisable(true);
    }

    private void dezactiveazaAlteAjutoare(TipAjutor ajutorFolosit) {
        if (ajutorFolosit != TipAjutor.CINCI_SPREZECE) btn5050.setDisable(true);
        if (ajutorFolosit != TipAjutor.PUBLIC) btnPublic.setDisable(true);
        if (ajutorFolosit != TipAjutor.PRIETEN) btnPrieten.setDisable(true);
    }

    private void activeazaAjutoare() {
        btn5050.setDisable(false);
        btnPublic.setDisable(false);
        btnPrieten.setDisable(false);
    }
}