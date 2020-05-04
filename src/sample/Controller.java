package sample;

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
    private Long b = 0L;

    public void init() {
        labels = new Label[][]{{l0, l1, l2, l3}, {l4, l5, l6, l7}, {l8, l9, l10, l11}, {l12, l13, l14, l15}};
        panels = new Pane[][]{{p0, p1, p2, p3}, {p4, p5, p6, p7}, {p8, p9, p10, p11}, {p12, p13, p14, p15}};
        record.setText(rec.toString());
        setText();
    }

    private void setText() {
        for (int i = 0; i < labels.length; i++) {
            for (int i1 = 0; i1 < labels[i].length; i1++) {
                int value = driver.getDate()[i][i1];
                labels[i][i1].setText(String.valueOf(value));
                if (!showWin && value == 2048) {
                    showWin = true;
                    win.setVisible(true);
                }
                switch (value) {
                    case 0:
                        panels[i][i1].setStyle("-fx-background-color: #e0e0e0;");
                        break;
                    case 2:
                        panels[i][i1].setStyle("-fx-background-color: #f7d36f;");
                        break;
                    case 4:
                        panels[i][i1].setStyle("-fx-background-color: #d65d31;");
                        break;
                    case 8:
                        panels[i][i1].setStyle("-fx-background-color: #f07aaf;");
                        break;
                    case 16:
                        panels[i][i1].setStyle("-fx-background-color: #d1f08b;");
                        break;
                    case 32:
                        panels[i][i1].setStyle("-fx-background-color: #a9e7f5;");
                        break;
                    case 64:
                        panels[i][i1].setStyle("-fx-background-color: #90f07f;");
                        break;
                    case 128:
                        panels[i][i1].setStyle("-fx-background-color: #7bc96d;");
                        break;
                    case 256:
                        panels[i][i1].setStyle("-fx-background-color: #56a647;");
                        break;
                    case 512:
                        panels[i][i1].setStyle("-fx-background-color: #81bcf7;");
                        break;
                    case 1024:
                        panels[i][i1].setStyle("-fx-background-color: #65aef7;");
                        break;
                    case 2048:
                        panels[i][i1].setStyle("-fx-background-color: #fc1e7b;");
                        break;
                }
            }
        }
    }

    public void shutdown() {
        rec.save();
    }

    public void keyPressed(KeyEvent e) {
        win.setVisible(false);
        int sum = driver.keyPressed(e.getCode().impl_getCode(), false);
        if (sum > 0) {
            b += (long) sum;
            balls.setText(String.valueOf(b));
            if (Long.compare(b, rec.get()) == 1) {
                rec.set(b);
                record.setText(rec.toString());
            }
        }
        setText();
    }

    public void reset() {
        b = 0L;
        balls.setText("0");
        driver.init();
        setText();
        showWin = false;
        win.setVisible(false);
    }
}
