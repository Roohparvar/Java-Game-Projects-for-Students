package sudoku;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.Serializable;
import java.util.Random;
import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.SwingUtilities;

public class You_Solve extends JFrame implements Serializable {

    JFileChooser filechooserv = new JFileChooser();
    public JButton[][] buttons = new JButton[9][9];
    int[][] m = {{0, 0, 0, 0, 0, 0, 0, 0, 0}, {0, 0, 0, 0, 0, 0, 0, 0, 0}, {0, 0, 0, 0, 0, 0, 0, 0, 0}, {0, 0, 0, 0, 0, 0, 0, 0, 0}, {0, 0, 0, 0, 0, 0, 0, 0, 0}, {0, 0, 0, 0, 0, 0, 0, 0, 0}, {0, 0, 0, 0, 0, 0, 0, 0, 0}, {0, 0, 0, 0, 0, 0, 0, 0, 0}, {0, 0, 0, 0, 0, 0, 0, 0, 0}};
    boolean go = true;
    Random rand = new Random();
    int infinity = 0;

    public You_Solve() {

        JMenuBar jMenuBar = new JMenuBar();

        JMenu file = new JMenu("File");
        JMenu help = new JMenu("Help");

        jMenuBar.add(file);
        jMenuBar.add(help);

        JMenuItem again = new JMenuItem("New");
        JMenuItem exit = new JMenuItem("Exit");
        JMenuItem save = new JMenuItem("Save");
        JMenuItem load = new JMenuItem("Load");
        JMenuItem about = new JMenuItem("About");
        JMenuItem solve = new JMenuItem("Solve");

        file.add(again);
        file.add(exit);
        file.add(save);
        file.add(load);
        help.add(about);
        help.add(solve);

        setJMenuBar(jMenuBar);

        again.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                for (int i = 0; i < 9; i++) {
                    for (int j = 0; j < 9; j++) {
                        m[i][j] = 0;
                    }
                }
                for (int j = 0; j < 9; j++) {
                    for (int k = 0; k < 9; k++) {
                        buttons[j][k].setText(null);
                    }
                }
            }
        });
        solve.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (barrasiSolve()) {
                    setVal(0, 0);
                    infinity = 0;
                    if (barrasiinfinity()) {
                        for (int i = 0; i < 9; i++) {
                            for (int j = 0; j < 9; j++) {
                                buttons[i][j].setText("" + m[i][j]);
                            }
                        }
                    } else {
                        JOptionPane.showMessageDialog(rootPane, "We were unable to solve this puzzle and suspect that there may be no valid solutions. We apologize for the inconvenience.");
                        for (int i = 0; i < 9; i++) {
                            for (int j = 0; j < 9; j++) {
                                m[i][j] = 0;
                            }
                        }
                        for (int j = 0; j < 9; j++) {
                            for (int k = 0; k < 9; k++) {
                                buttons[j][k].setText(null);
                            }
                        }
                    }
                } else {
                    JOptionPane.showMessageDialog(rootPane, "The Sudoku puzzle has been solved. If you wish to solve a new Sudoku, press the \"New\" button.");
                }
            }
        });
        exit.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.exit(0);
            }
        });
        load.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                for (int i = 0; i < 9; i++) {
                    for (int j = 0; j < 9; j++) {
                        m[i][j] = 0;
                        buttons[i][j].setText(null);
                        buttons[i][j].setBackground(Color.pink);
                        buttons[i][j].setEnabled(true);
                    }
                }

                JFileChooser chooser = new JFileChooser();
                chooser.setCurrentDirectory(new File("Desktop"));

                int status = chooser.showOpenDialog(chooser);
                if (status == JFileChooser.APPROVE_OPTION) {
                    File file = chooser.getSelectedFile();
                    try {
                        FileInputStream t = new FileInputStream(file);
                        FileReader reader = new FileReader(file);

                        for (int i = 0; i < 9; i++) {
                            for (int u = 0; u < 9; u++) {
                                m[u][i] = t.read();
                            }
                        }

                        for (int i = 0; i < 9; i++) {
                            for (int l = 0; l < 9; l++) {
                                buttons[i][l].setText(null);
                                buttons[i][l].setBackground(Color.pink);
                            }
                        }

                        for (int i = 0; i < 9; i++) {
                            for (int l = 0; l < 9; l++) {
                                buttons[l][i].setText("" + m[l][i]);
                            }
                        }

                        for (int i = 0; i < 9; i++) {
                            for (int j = 0; j < 9; j++) {
                                if (buttons[i][j].getText().equals("0")) {
                                    buttons[i][j].setText(null);
                                }
                            }
                        }

                    } catch (IOException ex) {
                        JOptionPane.showMessageDialog(chooser, "An I/O exception has occurred.");
                    }
                } else {
                    JOptionPane.showMessageDialog(chooser, "File not opened");
                }
            }
        });
        save.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JFileChooser chooser = new JFileChooser();
                int status = chooser.showSaveDialog(chooser);

                if (status == JFileChooser.APPROVE_OPTION) {
                    File file = chooser.getSelectedFile();
                    try {
                        FileOutputStream w = new FileOutputStream(file);
                        for (int i = 0; i < 9; i++) {
                            for (int j = 0; j < 9; j++) {
                                w.write(m[j][i]);
                            }
                        }
                    } catch (IOException ex) {
                        JOptionPane.showMessageDialog(chooser, "An I/O exception has occurred.");
                    }
                } else {
                    JOptionPane.showMessageDialog(chooser, "File not opened");
                }
            }
        });
        about.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JOptionPane.showMessageDialog(rootPane, "This game has been developed by Mr. Esmaeil Roohparvar Basmenj.");
            }
        });

        setSize(470, 515);
        setVisible(true);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        for (int i = 0; i < 9; i++) {
            for (int j = 0; j < 9; j++) {
                buttons[i][j] = new JButton();
                buttons[i][j].setLocation(i * 50, j * 50);
                buttons[i][j].setSize(50, 50);
                buttons[i][j].setVisible(true);
                buttons[i][j].setName(i + "," + j);
                buttons[i][j].addMouseListener(new java.awt.event.MouseAdapter() {
                    @Override
                    public void mouseClicked(java.awt.event.MouseEvent evt) {
                        mouseClick(evt);
                    }
                });
                setLayout(null);
                this.add(buttons[i][j]);
            }
        }
        repaint();
        for (int i = 0; i < 9; i++) {
            for (int j = 0; j < 9; j++) {
                buttons[i][j].setBackground(Color.pink);
            }
        }
    }

    public boolean cheekrow(int row, int x) {
        for (int i = 0; i < 9; i++) {
            if (m[row][i] == x) {
                return false;
            }
        }
        return true;
    }

    public boolean checkcol(int col, int x) {
        for (int i = 0; i < 9; i++) {
            if (m[i][col] == x) {
                return false;
            }
        }
        return true;
    }

    public boolean checksqu(int row, int col, int x) {
        int r = row / 3;
        int c = col / 3;
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                if (m[3 * r + i][3 * c + j] == x) {
                    return false;
                }
            }
        }
        return true;
    }

    public boolean barrasiinfinity() {
        for (int i = 0; i < 9; i++) {
            for (int j = 0; j < 9; j++) {
                if (m[i][j] == 0) {
                    return false;
                }
            }
        }
        return true;
    }

    public boolean barrasiSolve() {
        for (int i = 0; i < 9; i++) {
            for (int j = 0; j < 9; j++) {
                if (m[i][j] == 0) {
                    return true;
                }
            }
        }
        return false;
    }

    boolean setVal(int x, int y) {
        infinity++;
        if (infinity == 1000000) {
            return true;
        }
        if (m[x][y] != 0) {
            if (y < 8) {
                if (setVal(x, y + 1)) {
                    return true;
                }
            } else if (x < 8) {
                if (setVal(x + 1, 0)) {
                    return true;
                }
            } else {
                return true;
            }
        } else {
            for (int l = 1; l <= 9; l++) {
                m[x][y] = 0;
                if (checkcol(y, l) && cheekrow(x, l) && checksqu(x, y, l)) {
                    m[x][y] = l;
                    if (y < 8) {
                        if (setVal(x, y + 1)) {
                            return true;
                        }
                    } else if (x < 8) {
                        if (setVal(x + 1, 0)) {
                            return true;
                        }
                    } else {
                        return true;
                    }
                }
            }
        }
        return false;
    }

    private void mouseClick(java.awt.event.MouseEvent evt) {
        JButton b = (JButton) evt.getSource();
        String s = b.getName();
        String[] t = s.split(",");
        int i = Integer.parseInt(t[0]);
        int j = Integer.parseInt(t[1]);

        if (SwingUtilities.isLeftMouseButton(evt)) {
            while (go) {
                try {
                    String x = "";
                    if (barrasiSolve()) {
                        x = JOptionPane.showInputDialog(rootPane, "Please input your number");
                    } else {
                        JOptionPane.showMessageDialog(rootPane, "The Sudoku puzzle has been solved. If you wish to solve a new Sudoku, press the \"New\" button.");
                    }

                    if (x == null || x.isEmpty()) {
                        break;
                    }
                    int r = Integer.parseInt(x);
                    if (r < 1 || r > 9) {
                        throw new Exception();
                    }
                    if (checkcol(j, r) && cheekrow(i, r) && checksqu(i, j, r)) {
                        m[i][j] = r;
                        buttons[i][j].setText(x);
                    }
                    go = false;
                } catch (Exception o) {
                    JOptionPane.showMessageDialog(rootPane, "Please input the correct number");
                }
            }
            go = true;
        }
    }
}
