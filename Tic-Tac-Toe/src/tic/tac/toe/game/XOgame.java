package tic.tac.toe.game;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;

public class XOgame extends javax.swing.JFrame {

    public JButton[][] buttons = new JButton[3][3];
    public int[][] a = new int[3][3];
    int z = 1;

    public XOgame() {
        initComponents();
        initialize();
    }

    private void initialize() {
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(240, 286);
        setTitle("Tic Tac Toe Game");
        Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
        int x = (dim.width - getWidth()) / 2;
        int y = (dim.height - getHeight()) / 2;
        setLocation(x, y);
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                a[i][j] = 0;
            }
        }

        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                buttons[i][j] = new JButton();
                buttons[i][j].setSize(75, 75);
                buttons[i][j].setLocation(i * 75, j * 75);
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
        //
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
        JMenuItem HelpMe = new JMenuItem("Guide to play Tic Tac Toe");
        howtosolve.add(HelpMe);
        HelpMe.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JOptionPane.showMessageDialog(null,
                        "Tic Tac Toe Game Guide:\n\n"
                        + "1. The Game Board:\n"
                        + "   - The game is played on a 3x3 grid.\n\n"
                        + "2. Player Turns:\n"
                        + "   - There are two players: Red and Blue.\n"
                        + "   - Red starts the game, and players take turns.\n\n"
                        + "3. Making Moves:\n"
                        + "   - Click on an empty square to place your color (Red or Blue).\n\n"
                        + "4. Winning the Game:\n"
                        + "   - A player wins by placing three of their colors in a horizontal, vertical, or diagonal row.\n"
                        + "   - The game ends when a player wins or the board is full (a draw).\n\n"
                        + "5. Restarting the Game:\n"
                        + "   - After the game ends, you can restart by starting a new game.\n\n"
                );
            }
        });

        setJMenuBar(jMenuBar);

        //
    }

    private void mouseClick(java.awt.event.MouseEvent evt) {
        JButton b = (JButton) evt.getSource();
        String s = b.getName();
        String[] t = s.split(",");
        int Row = Integer.parseInt(t[1]);
        int column = Integer.parseInt(t[0]);

        if (z == 1) {
            if (a[Row][column] == 0) {
                z = 2;
                buttons[column][Row].setBackground(Color.red);
                a[Row][column] = 1;

                check();
            }

        } else if (z == 2) {
            if (a[Row][column] == 0) {
                z = 1;
                buttons[column][Row].setBackground(Color.blue);
                a[Row][column] = 2;
                check();
            }

        }

    }

    public void check() {
        for (int i = 0; i < 3; i++) {
            if (a[i][0] == a[i][1] && a[i][1] == a[i][2] && a[i][0] != 0) {
                announceWinner(a[i][0]);
                return;
            }

            if (a[0][i] == a[1][i] && a[1][i] == a[2][i] && a[0][i] != 0) {
                announceWinner(a[0][i]);
                return;
            }
        }

        if (a[0][0] == a[1][1] && a[1][1] == a[2][2] && a[0][0] != 0) {
            announceWinner(a[0][0]);
            return;
        }

        if (a[0][2] == a[1][1] && a[1][1] == a[2][0] && a[0][2] != 0) {
            announceWinner(a[0][2]);
            return;
        }

        if (isBoardFull()) {
            JOptionPane.showMessageDialog(rootPane, "It's a draw! Try again.");
            new XOgame().setVisible(true);
        }
    }

    private void announceWinner(int player) {
        String winnerColor = (player == 1) ? "red" : "blue";
        JOptionPane.showMessageDialog(rootPane, winnerColor + " is the winner!");
        System.exit(0);
    }

    private boolean isBoardFull() {
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                if (a[i][j] == 0) {
                    return false;
                }
            }
        }
        return true;
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 400, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 300, Short.MAX_VALUE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    public static void main(String args[]) {

        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException | InstantiationException | IllegalAccessException | javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(XOgame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }

        java.awt.EventQueue.invokeLater(() -> {
            new XOgame().setVisible(true);
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    // End of variables declaration//GEN-END:variables
}
