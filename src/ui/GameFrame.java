package ui;

import javax.swing.JFrame;
import java.awt.event.WindowListener;
import java.awt.event.WindowEvent;
import java.awt.Container;
import java.awt.Toolkit;

@SuppressWarnings({ "serial", "unused" })
public class GameFrame extends JFrame implements WindowListener {
    private static GamePanel gp;

    public GameFrame () {
        super("Flying TonCATsu");

        initComponents();
        addComponents();
        setFrame();
        //Container c = getContentPane();
        //addWindowListener(this);
    }
    
    private void setFrame() {
		setLayout(null);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setUndecorated(false);
		setSize(800, 600);
		setLocationRelativeTo(null);
		setResizable(false);
		
		
		setVisible(true);
		addWindowListener(this);
	}
    
    private void initComponents(){
    	gp = new GamePanel();
    }
    
    private void addComponents(){
    	add(gp);
    }
    
    public void windowActivated (WindowEvent e) {
        gp.resumeGame();
    }    
    public void windowDeactivated (WindowEvent e) {
        gp.pauseGame();
    }
    public void windowDeiconified (WindowEvent e) {
        gp.resumeGame();
    }
    public void windowIconified (WindowEvent e) {
        gp.pauseGame();
    }
    public void windowClosing (WindowEvent e) {
        gp.stopGame();
    }
    public void windowClosed (WindowEvent e) {
        gp.stopGame();
    }
    public void windowOpened (WindowEvent e) {
    }
}
