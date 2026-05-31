package com.mathetrainer;

import com.mathetrainer.ui.MatheTrainerGUI;

public class Main {
    public static void main(String[] args) {
        javax.swing.SwingUtilities.invokeLater(() -> {
            MatheTrainerGUI gui = new MatheTrainerGUI();
            gui.setVisible(true);
        });
    }
}
