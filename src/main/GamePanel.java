package main;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;

import javax.swing.JPanel;

import entity.Player;

public class GamePanel extends JPanel implements Runnable { // This class inherits JPanel class.
    
    // SCREEN SETTINGS
    final int originalTileSize = 16; // 16x16 tile
    final int scale = 3;
    
    final public int tileSize = originalTileSize * scale; // 48x48 actual tile size
    final int maxScreenCol = 16;
    final int maxScreenRow = 12;
    final int screenWidth = tileSize * maxScreenCol; // 768 pixels
    final int screenHeight = tileSize * maxScreenRow; // 576 pixels
    
    // FPS
    int FPS = 60;
    
    // Instantiate key handler class.
    KeyHandler keyH = new KeyHandler();
    // Thread keeps your program running until you stop it.
    Thread gameThread;
    Player player = new Player(this, keyH);
    
    // Set player's default position.
    int playerX = 100;
    int playerY = 100;
    int playerSpeed = 4;
    
    public GamePanel() {
        
        // Sets the size of this class (JPanel)
        this.setPreferredSize(new Dimension(screenWidth, screenHeight));
        this.setBackground(Color.black);
        this.setDoubleBuffered(true); // enabling this can improve game's rendering performance.
        this.addKeyListener(keyH);
        this.setFocusable(true);
    }
    
    public void startGameThread() {
        
        gameThread = new Thread(this); // pass this class.
        gameThread.start();
    }
    
    @Override
//    public void run() {
//        
//        double drawInterval = 1000000000 / FPS; // draw the screen every 0.01666 seconds.
//        double nextDrawTime = System.nanoTime() + drawInterval; // draws the next interval at current time + interval.
//        
//        while(gameThread != null) {
//            
//            // Returns the current value of the running Java VM high-resolution time source, in seconds.
//            // long currentTime = System.nanoTime();
//            // System.out.println("Current Time: " + currentTime);
//            
//            // 1 UPDATE: update information such as character positions.
//            update();
//            
//            // 2 DRAW: draw the screen with the updated information.
//            repaint();
//            
//            try {
//                double remainingTime = nextDrawTime - System.nanoTime();
//                remainingTime = remainingTime / 1000000;
//                
//                if(remainingTime < 0) {
//                    remainingTime = 0;
//                }
//                
//                Thread.sleep((long) remainingTime); // Pauses the game loop.
//                
//                nextDrawTime += drawInterval;
//                
//            } catch (InterruptedException e) {
//                // TODO Auto-generated catch block
//                e.printStackTrace();
//            }
//        }
//    }
    
    public void run() {
        
        double drawInterval = 1000000000 / FPS;
        double delta = 0;
        long lastTime = System.nanoTime();
        long currentTime;
        long timer = 0;
        int drawCount = 0;
        
        while(gameThread != null) {
            
            currentTime = System.nanoTime();
            
            delta += (currentTime - lastTime) / drawInterval;
            timer += (currentTime - lastTime);
            lastTime = currentTime;
            
            if(delta >= 1) {
                update();
                repaint();
                delta--;
                drawCount++;
            }
            
            if(timer >= 1000000000) {
                System.out.println("FPS: " + drawCount); // prints fps in console
                drawCount = 0;
                timer = 0;
            }
        }
    }
    
    public void update() {
        
        player.update();
    }
    
    public void paintComponent(Graphics g) {
        
        super.paintComponent(g);
        
        // Graphics2D class extends Graphics class to provide more sophisticated 
        // control over geometry, coordinate transformations, color management,
        // and text layout.
        Graphics2D g2 = (Graphics2D)g;
        
        player.draw(g2);
        
        g2.dispose();
    }
}
