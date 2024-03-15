package pl.lodz.uni.gui;

import pl.lodz.uni.domain.Grid;
import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class GameFrame extends JFrame {
    private GridPanel gridPanel;
    private Grid grid;
    private static final int DELAY = 500;

    public GameFrame(Grid grid) {
        setTitle("Conway's Game of Life");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        this.grid = grid;
        gridPanel = new GridPanel(grid);
        getContentPane().add(gridPanel);

        pack(); // Adjust frame size
        setLocationRelativeTo(null); // Center the frame
        setVisible(true); // Make the frame visible

        startAnimation();
    }

    private void startAnimation() {
        Timer timer = new Timer(DELAY, new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                grid.prepareNextState();
                grid.setNextState();
                gridPanel.repaint();
            }
        });
        timer.start();
    }
}
