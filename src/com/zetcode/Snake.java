package com.zetcode;

import java.awt.EventQueue;
import javax.swing.JFrame;

public class Snake extends JFrame {

    public Snake() {
        
        initUI();
    }
    
    private void initUI() {
        
        add(new Board());
        // Create the Help button
        JButton helpButton = new JButton("Help");
        helpButton.addActionListener(e -> showHelpDialog());

        // Add the Help button to the bottom of the frame
        add(helpButton, BorderLayout.SOUTH);

        setResizable(false);
        pack();
        
        setTitle("Snake");
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }
    
        private void showHelpDialog() {
        String message = """
            Welcome to Snake!
            
            How to Play:
            - Use the arrow keys to move the snake.
            - Eat the food to grow your snake and earn points.
            - Avoid running into walls or yourself.
            
            Objective:
            - Keep growing and achieve the highest score possible!
            
            Enjoy the game!
            """;
        JOptionPane.showMessageDialog(this, message, "How to Play", JOptionPane.INFORMATION_MESSAGE);
    }



    public static void main(String[] args) {
        
        EventQueue.invokeLater(() -> {
            JFrame ex = new Snake();
            ex.setVisible(true);
        });
    }
}
