package minesweeper;

import javax.swing.JOptionPane;

public class Minesweeper {

    public static void main(String[] args) {
        JOptionPane.showMessageDialog(null, "Welcome to the Minesweeper game developed by Mr. Esmaeil Roohparvar Basmenj.");
        new MinesweeperJFrame().main(args);
    }
}
