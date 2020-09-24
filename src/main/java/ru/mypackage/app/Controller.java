package ru.mypackage.app;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.Pane;

public class Controller {

    private Driver driver = new Driver();

    @FXML
    private Pane p0, p1, p2, p3, p4, p5, p6, p7, p8, p9, p10, p11, p12, p13, p14, p15;

    @FXML
    private Label l0, l1, l2, l3, l4, l5, l6, l7, l8, l9, l10, l11, l12, l13, l14, l15, win, record, balls;

    private boolean showWin = false;

    private Label[][] labels;
    private Pane[][] panels;
    private Record rec = Record.load();
    private Long totalBalls = 0L;

    public void init() {
        labels = new Label[][]{{l0, l1, l2, l3}, {l4, l5, l6, l7}, {l8, l9, l10, l11}, {l12, l13, l14, l15}};
        panels = new Pane[][]{{p0, p1, p2, p3}, {p4, p5, p6, p7}, {p8, p9, p10, p11}, {p12, p13, p14, p15}};
        record.setText(rec.toString());
        setTextToLabelAndChangeColorAndShowWin();
    }

    private void setTextToLabelAndChangeColorAndShowWin() {
        for (int i = 0; i < labels.length; i++) {
            for (int j = 0; j < labels[i].length; j++) {
                int value = driver.getData()[i][j];
                labels[i][j].setText(String.valueOf(value));
                winCheck(value);
                setColor(panels[i][j], value);
            }
        }
    }

    private void winCheck(int value) {
        if (!showWin && value == 2048) {
            showWin = true;
            win.setVisible(true);
        }
    }

    private void setColor(Pane pane, int value) {
        switch (value) {
            case 0:
                pane.setStyle("-fx-background-color: #e0e0e0;");
                break;
            case 2:
                pane.setStyle("-fx-background-color: #f7d36f;");
                break;
            case 4:
                pane.setStyle("-fx-background-color: #d65d31;");
                break;
            case 8:
                pane.setStyle("-fx-background-color: #f07aaf;");
                break;
            case 16:
                pane.setStyle("-fx-background-color: #d1f08b;");
                break;
            case 32:
                pane.setStyle("-fx-background-color: #a9e7f5;");
                break;
            case 64:
                pane.setStyle("-fx-background-color: #90f07f;");
                break;
            case 128:
                pane.setStyle("-fx-background-color: #7bc96d;");
                break;
            case 256:
                pane.setStyle("-fx-background-color: #56a647;");
                break;
            case 512:
                pane.setStyle("-fx-background-color: #81bcf7;");
                break;
            case 1024:
                pane.setStyle("-fx-background-color: #65aef7;");
                break;
            case 2048:
                pane.setStyle("-fx-background-color: #fc1e7b;");
                break;
        }
    }

    public void shutdown() {
        rec.save();
    }

    public void keyPressed(KeyEvent e) {
        win.setVisible(false);
        int takenBalls = driver.keyPressed(e.getCode().impl_getCode());
        if (takenBalls > 0) {
            totalBalls += (long) takenBalls;
            balls.setText(String.valueOf(totalBalls));
            if (Long.compare(totalBalls, rec.get()) == 1) {
                rec.set(totalBalls);
                record.setText(rec.toString());
            }
        }
        setTextToLabelAndChangeColorAndShowWin();
    }

    public void reset() {
        totalBalls = 0L;
        balls.setText("0");
        driver.init();
        setTextToLabelAndChangeColorAndShowWin();
        showWin = false;
        win.setVisible(false);
    }
}
