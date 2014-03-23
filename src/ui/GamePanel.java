package ui;

import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JPanel;

import src.Output;

import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.event.MouseEvent;
import java.awt.event.MouseAdapter;
import java.awt.Toolkit;

@SuppressWarnings("serial")
public class GamePanel extends JPanel implements Runnable {
    private static final int WIDTH = 800;
    private static final int HEIGHT = 600;
    
    private Thread animator;
    private volatile boolean running = false;
    private volatile boolean gameOver = false;
    private volatile boolean isPaused = false;
    
    private Image dbImage;
    private Graphics dbg;
    
    private ImageIcon imgbg;
    private JLabel lblbg;
    
    public GamePanel () {
        super();
        initComponents();
        addComponents();
        setPanel();
        
        // create game components
        
        addMouseListener (new MouseAdapter() {
            public void mousePressed(MouseEvent e) {
                testPress(e.getX(), e.getY());
            }
        }); 
    }
    
    private void initComponents(){
    	imgbg = new ImageIcon("images/game/background.png");
    	lblbg = new JLabel();
    	
    	imgbg.getImage().flush();
    	lblbg.setSize(WIDTH, HEIGHT);
    	lblbg.setIcon(imgbg);
    	lblbg.setLocation(50, 50);
    	lblbg.setText("Test");
    	lblbg.setOpaque(false);
    	lblbg.setVisible(true);
    }
    
    private void addComponents(){
    	add(lblbg);
    }
    
    private void setPanel(){
    	setPreferredSize(new Dimension(WIDTH, HEIGHT));
        setFocusable(true);
        requestFocus();
        setVisible(true);
        setOpaque(true);
        setDoubleBuffered(true);
    }
    
    private void testPress (int x, int y) {
        if (!gameOver) {
        
        }
    }
    
    public void addNotify () {
        super.addNotify();
        startGame();
    }
    
    private void startGame () {
        if (animator == null || !running) {
            animator = new Thread(this);
            animator.start();
        }
    }
    
    public void stopGame () {
        running = false;
    }
    public void pauseGame () {
        isPaused = true;
    }
    public void resumeGame () {
        isPaused = false;
    }
    
    public void run () {
        running = true;
        while (running) {
            gameUpdate();
            gameRender();
            paintScreen();
            
            try {
                Thread.sleep(20);
            }
            catch (InterruptedException ex) {}
        }
        System.exit(0);
    }
    
    private void gameUpdate () {
        if (!isPaused && !gameOver) {
        
        } 
    }
    
    private void gameRender () {
        if (dbImage == null) {
            dbImage = createImage(WIDTH, HEIGHT);
            if (dbImage == null) {
                System.out.println("dbImage is null.");
                return;
            }
            else {
                dbg = dbImage.getGraphics();
            }
            
            // clear background
            // draw game elements
            if (gameOver)
                gameOverMessage(dbg);
        }
    }
    
    private void gameOverMessage (Graphics g) {
        // display Game Over
    }
    
    private void paintScreen () {
        Graphics g;
        
        try {
            g = this.getGraphics();
            if ((g != null) && (dbImage != null))
                g.drawImage(dbImage, 0, 0, null);
            Toolkit.getDefaultToolkit().sync();
            g.dispose();
        }
        catch (Exception e) {
            System.out.println("Graphics context error: " + e);
        }
    }
}
