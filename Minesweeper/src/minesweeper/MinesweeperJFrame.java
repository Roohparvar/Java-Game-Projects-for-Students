package minesweeper;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Random;
import javax.swing.JButton;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.SwingUtilities;

public class MinesweeperJFrame extends javax.swing.JFrame {

    public JButton[][] buttons = new JButton[9][9];
    String[][] a = new String[9][9];
    int x, y, z = 81;
    int count = 0;

    public MinesweeperJFrame() {
        initComponents();

        JMenuBar jMenuBar = new JMenuBar();

        JMenu jMenu1 = new JMenu("File");
        jMenuBar.add(jMenu1);

        JMenuItem jMenuItem1 = new JMenuItem("Exit");
        JMenuItem jMenuItem2 = new JMenuItem("About");
        jMenu1.add(jMenuItem1);
        jMenu1.add(jMenuItem2);

        jMenuItem1.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.exit(0);
            }
        });
        jMenuItem2.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JOptionPane.showMessageDialog(rootPane, "This game has been developed by Mr. Esmaeil Roohparvar Basmenj.");
            }
        });

        JMenu howtosolve = new JMenu("Help");
        // Guide to Solve Minesweeper
        jMenuBar.add(howtosolve);
        JMenuItem HelpMe = new JMenuItem("Guide to Solve minesweeper Puzzle");
        howtosolve.add(HelpMe);
        HelpMe.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JOptionPane.showMessageDialog(rootPane, "Minesweeper Game Guide:\n\n"
                        + "1. Understand the Grid:\n"
                        + "   - The game is played on a grid of squares.\n"
                        + "   - Some squares contain mines, while others are safe.\n\n"
                        + "2. Start with Corners:\n"
                        + "   - Begin by clicking on the corners or edges of the grid.\n"
                        + "   - This often opens up more squares and gives you a good start.\n\n"
                        + "3. Use Number Clues:\n"
                        + "   - Numbers indicate how many mines are adjacent to a square.\n"
                        + "   - Use this information to deduce safe squares and potential mine locations.\n\n"
                        + "4. Flag Mines:\n"
                        + "   - Right-click on squares where you think mines are located to flag them.\n"
                        + "   - This helps you keep track of potential dangers.\n\n"
                        + "5. Clear Open Spaces:\n"
                        + "   - If a square has no mines nearby, it will reveal an open area.\n"
                        + "   - Continue clearing open spaces to uncover more of the grid.\n\n"
                        + "6. Be Cautious:\n"
                        + "   - If unsure, take a cautious approach and avoid randomly clicking.\n"
                        + "   - Minesweeper requires logical deduction and strategy.\n\n"
                        + "7. Winning the Game:\n"
                        + "   - The game is won when all safe squares are uncovered, and all mines are flagged.");
            }
        });

        setJMenuBar(jMenuBar);

        for (int i = 0; i < 9; i++) {
            for (int j = 0; j < 9; j++) {
                buttons[i][j] = new JButton();
                buttons[i][j].setLocation(i * 50, j * 50);
                buttons[i][j].setSize(50, 50);
                buttons[i][j].setName(i + "," + j);
                buttons[i][j].addMouseListener(new java.awt.event.MouseAdapter() {
                    @Override
                    public void mouseClicked(java.awt.event.MouseEvent evt) {
                        mouseClick(evt);
                    }
                });
                this.add(buttons[i][j]);
            }
        }

        for (int i = 0; i < 10; i++) {
            while (true) {
                Random esmaeil = new Random();
                x = esmaeil.nextInt(9);
                y = esmaeil.nextInt(9);
                if (a[x][y] != "*") {
                    a[x][y] = "*";
                    break;
                }
            }
        }

        int count = 0;
        for (x = 0; x < 9; x++) {
            for (y = 0; y < 9; y++) {
                count = 0;

                if (a[x][y] == "*") {
                    continue;
                }

                if (x - 1 >= 0 && y - 1 >= 0) {
                    if (a[x - 1][y - 1] == "*") {
                        count++;
                    }
                }

                if (x - 1 >= 0) {
                    if (a[x - 1][y] == "*") {
                        count++;
                    }
                }

                if (x - 1 >= 0 && y + 1 < 9) {
                    if (a[x - 1][y + 1] == "*") {
                        count++;
                    }
                }

                if (y - 1 >= 0) {
                    if (a[x][y - 1] == "*") {
                        count++;
                    }
                }

                if (y + 1 < 9) {
                    if (a[x][y + 1] == "*") {
                        count++;
                    }
                }

                if (x + 1 < 9 && y - 1 >= 0) {
                    if (a[x + 1][y - 1] == "*") {
                        count++;
                    }
                }

                if (x + 1 < 9) {
                    if (a[x + 1][y] == "*") {
                        count++;
                    }
                }

                if (x + 1 < 9 && y + 1 < 9) {
                    if (a[x + 1][y + 1] == "*") {
                        count++;
                    }
                }

                a[x][y] = Integer.toString(count);
            }
        }
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 449, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 474, Short.MAX_VALUE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void mouseClick(java.awt.event.MouseEvent evt) {
        JButton b = (JButton) evt.getSource();

        String s = b.getName();
        String[] t = s.split(",");
        int i = Integer.parseInt(t[0]);
        int j = Integer.parseInt(t[1]);

        if (SwingUtilities.isLeftMouseButton(evt)) {
            buttons[i][j].setEnabled(false);
            if (a[i][j] == "*") {
                for (int x = 0; x < 9; x++) {
                    for (int y = 0; y < 9; y++) {
                        if (a[x][y] == "*") {
                            buttons[x][y].setText("*");
                        }
                    }
                }
                JOptionPane.showMessageDialog(this, "You didn't win this time, but don't worry. Give it another try!");
                System.exit(0);
            } else {
                if (a[i][j].equals("0")) {
                    clean(i, j);
                } else {
                    buttons[i][j].setText(a[i][j]);
                }
            }
        }

        if (SwingUtilities.isRightMouseButton(evt)) {
            if (buttons[i][j].getText() == "M") {
                buttons[i][j].setText(" ");
            } else {
                buttons[i][j].setText("M");
            }
        }
        int count = 0;
        for (int x = 0; x < 9; x++) {
            for (int y = 0; y < 9; y++) {
                if (buttons[x][y].getText().equals("M") && a[x][y].equals("*")) {
                    count++;
                }
            }
        }

        if (count == 10) {
            JOptionPane.showMessageDialog(this, "Congratulations! Bravooooooo! You've won; you are incredibly smart.");
            System.exit(0);
        }
    }

    public void clean(int x, int y) {
        if (buttons[x][y].isVisible() == true) {
            buttons[x][y].setVisible(false);
            if (x - 1 >= 0) {
                if (a[x - 1][y].equals("0")) {
                    clean(x - 1, y);
                } else {
                    buttons[x - 1][y].setText(a[x - 1][y]);
                    buttons[x - 1][y].setEnabled(false);
                }
            }

            if (x - 1 >= 0 && y + 1 < 9) {
                if (a[x - 1][y + 1].equals("0")) {
                    clean(x - 1, y + 1);
                } else {
                    buttons[x - 1][y + 1].setText(a[x - 1][y + 1]);
                    buttons[x - 1][y + 1].setEnabled(false);
                }
            }

            if (y + 1 < 9) {
                if (a[x][y + 1].equals("0")) {
                    clean(x, y + 1);
                } else {
                    buttons[x][y + 1].setText(a[x][y + 1]);
                    buttons[x][y + 1].setEnabled(false);
                }
            }

            if (x + 1 < 9 && y + 1 < 9) {
                if (a[x + 1][y + 1].equals("0")) {
                    clean(x + 1, y + 1);
                } else {
                    buttons[x + 1][y + 1].setText(a[x + 1][y + 1]);
                    buttons[x + 1][y + 1].setEnabled(false);
                }
            }

            if (x + 1 < 9) {
                if (a[x + 1][y].equals("0")) {
                    clean(x + 1, y);
                } else {
                    buttons[x + 1][y].setText(a[x + 1][y]);
                    buttons[x + 1][y].setEnabled(false);
                }
            }

            if (x + 1 < 9 && y - 1 >= 0) {
                if (a[x + 1][y - 1].equals("0")) {
                    clean(x + 1, y - 1);
                } else {
                    buttons[x + 1][y - 1].setText(a[x + 1][y - 1]);
                    buttons[x + 1][y - 1].setEnabled(false);
                }
            }

            if (y - 1 >= 0) {
                if (a[x][y - 1].equals("0")) {
                    clean(x, y - 1);
                } else {
                    buttons[x][y - 1].setText(a[x][y - 1]);
                    buttons[x][y - 1].setEnabled(false);
                }
            }

            if (x - 1 >= 0 && y - 1 >= 0) {
                if (a[x - 1][y - 1].equals("0")) {
                    clean(x - 1, y - 1);
                } else {
                    buttons[x - 1][y - 1].setText(a[x - 1][y - 1]);
                    buttons[x - 1][y - 1].setEnabled(false);
                }
            }
        }
    }

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(MinesweeperJFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(MinesweeperJFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(MinesweeperJFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(MinesweeperJFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            @Override
            public void run() {
                new MinesweeperJFrame().setVisible(true);
            }
        });
    }
    // Variables declaration - do not modify//GEN-BEGIN:variables
    // End of variables declaration//GEN-END:variables
}
