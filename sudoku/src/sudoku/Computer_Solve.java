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
import java.util.Timer;
import java.util.TimerTask;
import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.SwingUtilities;

public class Computer_Solve extends JFrame implements Serializable {

    JFileChooser filechooser = new JFileChooser();
    public JButton[][] buttons = new JButton[9][9];
    int[][] m = {{0, 0, 0, 0, 0, 0, 0, 0, 0}, {0, 0, 0, 0, 0, 0, 0, 0, 0}, {0, 0, 0, 0, 0, 0, 0, 0, 0}, {0, 0, 0, 0, 0, 0, 0, 0, 0}, {0, 0, 0, 0, 0, 0, 0, 0, 0}, {0, 0, 0, 0, 0, 0, 0, 0, 0}, {0, 0, 0, 0, 0, 0, 0, 0, 0}, {0, 0, 0, 0, 0, 0, 0, 0, 0}, {0, 0, 0, 0, 0, 0, 0, 0, 0}};
    int[][] k = new int[9][9];
    int[][] ee = new int[9][9];
    Random rand = new Random();
    boolean go1 = true;
    boolean go2 = true;
    boolean go3 = true;
    int checkcont = 0;
    int chechhelp = 0;
    int w;
    int second = 0, minutes = 0;

    public Computer_Solve() {

        Timer timer = new Timer();
        timer.scheduleAtFixedRate(new TimerTask() {

            @Override
            public void run() {
                second++;
                if (second == 60) {
                    minutes++;
                    second = 0;
                }
            }
        }, 0, 1000);

        while (go2) {
            try {
                String s = JOptionPane.showInputDialog("Choose the difficulty level of the Sudoku puzzle" + "\n" + " 1 for hard, 2 for normal and 3 for easy");
                if (s == null || s.isEmpty()) {
                    break;
                }

                int f = Integer.parseInt(s);
                if (f < 1 || f > 3) {
                    throw new Exception();
                }

                w = f;
                go2 = false;
            } catch (Exception o) {
                JOptionPane.showMessageDialog(null, "Warning !!!" + "\n" + "Only values \"1\", \"2\" or \"3\" are permissible for use");
            }
        }
        go2 = true;

        JMenuBar jMenuBar = new JMenuBar();

        JMenu file = new JMenu("File");
        JMenu help = new JMenu("Help");

        jMenuBar.add(file);
        jMenuBar.add(help);

        JMenuItem again = new JMenuItem("New");
        JMenuItem exit = new JMenuItem("Exit");
        JMenuItem save = new JMenuItem("Save");
        JMenuItem load = new JMenuItem("Load");
        JMenuItem check = new JMenuItem("Verify the numbers");
        JMenuItem helpMe = new JMenuItem("Assist me in solving the puzzle");
        JMenuItem howtosolve = new JMenuItem("Guide to Solve Sudoku Puzzle");
        JMenuItem about = new JMenuItem("About");

        file.add(again);
        file.add(exit);
        file.add(save);
        file.add(load);
        help.add(check);
        help.add(helpMe);
        help.add(howtosolve);
        help.add(about);

        setJMenuBar(jMenuBar);

        howtosolve.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                for (int i = 0; i < 9; i++) {
                    for (int j = 0; j < 9; j++) {
                        buttons[i][j].setBackground(Color.pink);
                    }
                }
                JOptionPane.showMessageDialog(rootPane, "Sudoku Puzzle Guide:\n"
                        + "The Sudoku grid consists of 9 columns, 9 rows, and 9 blocks.\n"
                        + "Using the numbers 1 through 9, fill the 81 cells in the puzzle.\n"
                        + "Ensure that every column, row, and 3x3 block contains the numbers 1 through 9.\n"
                        + "No number can be repeated within any column, row, or block.");
            }
        });

        helpMe.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                chechhelp++;
                for (int i = 0; i < 9; i++) {
                    for (int j = 0; j < 9; j++) {
                        if (buttons[i][j].getBackground() != Color.blue) {
                            buttons[i][j].setBackground(Color.pink);
                        }
                    }
                }
                if (chechhelp < 10) {
                    for (int i = 0; i < 9; i++) {
                        for (int j = 0; j < 9; j++) {
                            ee[i][j] = k[i][j];
                        }
                    }

                    while (go1) {
                        try {
                            String a = JOptionPane.showInputDialog("Enter a number, and I'll suggest potential cells for placement."
                                    + "\nNote: You cannot use this button more than 9 times");
                            if (a == null || a.isEmpty()) {
                                break;
                            }

                            int p = Integer.parseInt(a);
                            if (p < 1 || p > 9) {
                                throw new Exception();
                            }

                            for (int i = 0; i < 9; i++) {
                                for (int j = 0; j < 9; j++) {
                                    if (checkcolhelp(j, p) && cheekrowhelp(i, p) && checksquhelp(i, j, p) && k[i][j] == 0) {
                                        buttons[i][j].setBackground(Color.YELLOW);
                                    }
                                }
                            }

                            go1 = false;
                        } catch (Exception o) {
                            JOptionPane.showMessageDialog(rootPane, "Please input an integer between 1 and 9");
                        }
                    }
                    go1 = true;

                } else {
                    JOptionPane.showMessageDialog(rootPane, "You cannot use this button more than nine times");
                }
            }
        });

        check.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                for (int i = 0; i < 9; i++) {
                    for (int j = 0; j < 9; j++) {
                        buttons[i][j].setBackground(Color.pink);
                    }
                }

                checkcont++;
                if (checkcont < 10) {
                    for (int i = 0; i < 9; i++) {
                        for (int j = 0; j < 9; j++) {
                            if (k[i][j] != m[i][j] && k[i][j] != 0) {
                                buttons[i][j].setBackground(Color.red);
                            }
                        }
                    }

                    for (int i = 0; i < 9; i++) {
                        for (int j = 0; j < 9; j++) {
                            if (k[i][j] == m[i][j]) {
                                buttons[i][j].setBackground(Color.green);
                            }
                        }
                    }
                }

                if (checkcont >= 10) {
                    JOptionPane.showMessageDialog(rootPane, "You cannot use this button more than 10 times");
                }
            }
        });

        again.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                checkcont = 0;
                chechhelp = 0;
                while (go3) {
                    try {
                        String s = JOptionPane.showInputDialog("Choose the difficulty level of the Sudoku puzzle" + "\n" + " 1 for hard and 2 for normal and 3 for easy");
                        if (s == null || s.isEmpty()) {
                            break;
                        }

                        int f = Integer.parseInt(s);
                        if (f < 1 || f > 3) {
                            throw new Exception();
                        }

                        int w = f;
                        go3 = false;
                    } catch (Exception o) {
                        JOptionPane.showMessageDialog(null, "Warning !!!" + "\n" + "Only values \"1\", \"2\" or \"3\" are permissible for use");
                    }
                }

                go3 = true;

                for (int i = 0; i < 9; i++) {
                    for (int j = 0; j < 9; j++) {
                        buttons[i][j].setEnabled(true);
                    }
                }

                for (int h = 0; h < 9; h++) {
                    for (int o = 0; o < 9; o++) {
                        buttons[h][o].setBackground(Color.PINK);
                    }
                }

                for (int i = 0; i < 9; i++) {
                    for (int j = 0; j < 9; j++) {
                        m[i][j] = 0;
                    }
                }

                AddnumtoM();
                addnumtokeymain();
            }
        });

        exit.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.exit(0);
            }
        });

        save.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JFileChooser chooser = new JFileChooser();
                chooser.setCurrentDirectory(new File("Desktop"));
                int status = chooser.showSaveDialog(chooser);
                if (status == JFileChooser.APPROVE_OPTION) {
                    File file = chooser.getSelectedFile();
                    try {
                        FileOutputStream w = new FileOutputStream(file);
                        for (int i = 0; i < 9; i++) {
                            for (int j = 0; j < 9; j++) {
                                w.write(k[i][j]);
                            }
                        }

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

        load.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                for (int i = 0; i < 9; i++) {
                    for (int j = 0; j < 9; j++) {
                        k[i][j] = 0;
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
                                k[i][u] = t.read();
                            }
                        }

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
                                buttons[l][i].setText("" + k[l][i]);
                            }
                        }

                        for (int i = 0; i < 9; i++) {
                            for (int j = 0; j < 9; j++) {
                                if (buttons[i][j].getText().equals("0")) {
                                    buttons[i][j].setText(null);
                                }
                            }
                        }

                        for (int i = 0; i < 9; i++) {
                            for (int j = 0; j < 9; j++) {
                                if (buttons[i][j].getText() != null) {
                                    buttons[i][j].setEnabled(false);
                                }
                            }
                        }

                    } catch (IOException ex) {
                        JOptionPane.showMessageDialog(chooser, "An I/O exception has occurred.");
                    }
                } else {
                    JOptionPane.showMessageDialog(chooser, "File not open");
                }
            }
        });

        about.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                for (int i = 0; i < 9; i++) {
                    for (int j = 0; j < 9; j++) {
                        buttons[i][j].setBackground(Color.pink);
                    }
                }
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
        AddnumtoM();
        addnumtokeymain();
        for (int h = 0; h < 9; h++) {
            for (int o = 0; o < 9; o++) {
                buttons[h][o].setBackground(Color.PINK);
            }
        }
    }

    public final void AddnumtoM() {
        FirstRow();
        setVal(1, 0);
    }

    public void FirstRow() {
        for (int i = 0; i < 9; i++) {
            m[0][i] = i + 1;
        }

        for (int r = 0; r <= 20; r++) {
            int p = rand.nextInt(9);
            int f = rand.nextInt(9);
            int tmp = m[0][p];
            m[0][p] = m[0][f];
            m[0][f] = tmp;
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

    boolean setVal(int x, int y) {
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
        return false;
    }

    public final void addnumtokeymain() {
        while (true) {
            for (int i = 0; i < 9; i++) {
                for (int j = 0; j < 9; j++) {
                    k[i][j] = m[i][j];
                }
            }
            if (w == 1) {
                int d = rand.nextInt(5) + 40;
                for (int i = 0; i < d; i++) {
                    while (true) {
                        int u = rand.nextInt(9);
                        int q = rand.nextInt(9);
                        if (k[u][q] != 0) {
                            k[u][q] = 0;
                            break;
                        }
                    }
                }
            }
            if (w == 2) {
                int d = rand.nextInt(5) + 35;
                for (int i = 0; i < d; i++) {
                    while (true) {
                        int u = rand.nextInt(9);
                        int q = rand.nextInt(9);
                        if (k[u][q] != 0) {
                            k[u][q] = 0;
                            break;
                        }
                    }
                }
            }
            if (w == 3) {
                int d = rand.nextInt(5) + 30;
                for (int i = 0; i < d; i++) {
                    while (true) {
                        int u = rand.nextInt(9);
                        int q = rand.nextInt(9);
                        if (k[u][q] != 0) {
                            k[u][q] = 0;
                            break;
                        }
                    }
                }
            }

            for (int i = 0; i < 9; i++) {
                for (int j = 0; j < 9; j++) {
                    ee[i][j] = k[i][j];
                }
            }

            setValbarrasi(0, 0);

            if (barrasi()) {
                break;
            }
        }

        for (int i = 0; i < 9; i++) {
            for (int j = 0; j < 9; j++) {
                buttons[i][j].setText("" + k[i][j]);
                if (buttons[i][j].getText().equals("0")) {
                    buttons[i][j].setText(null);
                }
            }
        }

        Enable();
    }

    boolean setValbarrasi(int x, int y) {
        if (ee[x][y] != 0) {
            if (y < 8) {
                if (setValbarrasi(x, y + 1)) {
                    return true;
                }
            } else if (x < 8) {
                if (setValbarrasi(x + 1, 0)) {
                    return true;
                }
            } else {
                return true;
            }
        } else {
            for (int l = 1; l <= 9; l++) {
                ee[x][y] = 0;
                if (checkcolhelp(y, l) && cheekrowhelp(x, l) && checksquhelp(x, y, l)) {
                    ee[x][y] = l;
                    if (y < 8) {
                        if (setValbarrasi(x, y + 1)) {
                            return true;
                        }
                    } else if (x < 8) {
                        if (setValbarrasi(x + 1, 0)) {
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

    public boolean barrasi() {
        for (int i = 0; i < 9; i++) {
            for (int j = 0; j < 9; j++) {
                if (ee[i][j] != m[i][j]) {
                    return false;
                }
            }
        }
        return true;
    }

    public void Enable() {
        for (int i = 0; i < 9; i++) {
            for (int j = 0; j < 9; j++) {
                if (k[i][j] != 0) {
                    buttons[i][j].setEnabled(false);
                }
            }
        }
    }

    public boolean finish() {
        for (int i = 0; i < 9; i++) {
            for (int j = 0; j < 9; j++) {
                if (k[i][j] != m[i][j]) {
                    return false;
                }
            }
        }
        return true;
    }

    public boolean cheekrowhelp(int row, int x) {
        for (int i = 0; i < 9; i++) {
            if (ee[row][i] == x) {
                return false;
            }
        }
        return true;
    }

    public boolean checkcolhelp(int col, int x) {
        for (int i = 0; i < 9; i++) {
            if (ee[i][col] == x) {
                return false;
            }
        }
        return true;
    }

    public boolean checksquhelp(int row, int col, int x) {
        int r = row / 3;
        int c = col / 3;
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                if (ee[3 * r + i][3 * c + j] == x) {
                    return false;
                }
            }
        }
        return true;
    }

    private void mouseClick(java.awt.event.MouseEvent evt) {
        JButton b = (JButton) evt.getSource();
        String s = b.getName();
        String[] t = s.split(",");
        int i = Integer.parseInt(t[0]);
        int j = Integer.parseInt(t[1]);

        if (SwingUtilities.isLeftMouseButton(evt)) {
            for (int h = 0; h < 9; h++) {
                for (int o = 0; o < 9; o++) {
                    if (buttons[h][o].getBackground() != Color.blue) {
                        buttons[h][o].setBackground(Color.pink);
                    }
                }
            }

            while (go1) {
                try {
                    if (buttons[i][j].isEnabled() == true) {
                        String q = JOptionPane.showInputDialog("Please enter the number you want to place here");
                        if (q == null || q.isEmpty()) {
                            break;
                        }

                        int p = Integer.parseInt(q);
                        if (p < 1 || p > 9) {
                            throw new Exception();
                        }

                        k[i][j] = Integer.parseInt(q);
                        buttons[i][j].setText(q);
                    }

                    go1 = false;

                } catch (Exception o) {
                    JOptionPane.showMessageDialog(rootPane, "Please input an integer between 1 and 9.");
                }
            }
            go1 = true;

            if (finish()) {
                JOptionPane.showMessageDialog(rootPane, "Congratulations! Bravooooooo! You've won; you are incredibly smart.");
                JOptionPane.showMessageDialog(rootPane, "You solved the puzzle in " + "\t" + minutes + " minutes and " + second + " second");
            }
        }

        if (SwingUtilities.isRightMouseButton(evt)) {
            for (int l = 0; l < 9; l++) {
                for (int n = 0; n < 9; n++) {
                    if (buttons[l][n].getBackground() == Color.green || buttons[l][n].getBackground() == Color.red) {
                        buttons[l][n].setBackground(Color.pink);
                    }
                }
            }

            if (buttons[i][j].getBackground() == Color.blue) {
                buttons[i][j].setBackground(Color.pink);
            } else {
                buttons[i][j].setBackground(Color.blue);
            }
        }
    }
}
