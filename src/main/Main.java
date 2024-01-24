package main;

import javax.swing.JFrame;

public class Main {

    public static void main(String[] args) {
        
        JFrame window = new JFrame();
        
        // This lets the window properly close when the user clicks the close ("x") button.
        window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        window.setResizable(false);
        window.setTitle("2D Adventure");
        
        // Calling GamePanel class.
        GamePanel gamePanel = new GamePanel();
        window.add(gamePanel);
        
        // Causes this window to be sized to fit the preferred size
        // and layouts of its subcomponents (GamePanel).
        window.pack();
        
        // Not specify the location of the window
        // The window will be displayed at the center of the screen.
        window.setLocationRelativeTo(null);
        window.setVisible(true);
        
        gamePanel.startGameThread();
        
        // Github test.
        
    }

}
