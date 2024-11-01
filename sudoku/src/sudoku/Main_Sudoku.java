package sudoku;

import javax.swing.JOptionPane;

public class Main_Sudoku {

    public static void main(String[] args) {
        boolean go = true;
        JOptionPane.showMessageDialog(null, "Welcome to the Sudoku game developed by Mr. Esmaeil Roohparvar Basmenj.");

        while (go) {
            try {
                String s = JOptionPane.showInputDialog("If you wish for this program to solve your Sudoku puzzle, select option 1. Alternatively, if you would like us to present a Sudoku puzzle for you to solve, choose option 2.");
                if (s == null || s.isEmpty()) {
                    break;
                }
                int r = Integer.parseInt(s);
                if (r < 1 || r > 2) {
                    throw new Exception();
                }
                if (r == 1) {
                    You_Solve u = new You_Solve();
                } else if (r == 2) {
                    Computer_Solve v = new Computer_Solve();
                }
                go = false;
            } catch (Exception o) {
                JOptionPane.showMessageDialog(null, "Warning !!!" + "\n" + "Only values \"1\" or \"2\" are permissible for use");
            }
        }
    }
}