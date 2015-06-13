package compton.ui;



import compton.animation.SinusoidComponent;
import java.util.PropertyResourceBundle;
import java.util.ResourceBundle;
import java.util.Timer;

import javax.swing.*;

import net.miginfocom.swing.MigLayout;


public class MenuCompton extends JFrame
{
	final ResourceBundle bundle = PropertyResourceBundle.getBundle("compton");
	
	//Tworzenie obiektu
	JFrame mainFrame = new JFrame(bundle.getString("app_title")); //Ramka Główna
	
	JPanel menuPanel = new JPanel(new MigLayout());
	JPanel animationPanel = new JPanel(new MigLayout());
	//Animation
	SinusoidComponent sinusoid = new SinusoidComponent();
	
	OptionsPanel optionPanel = new OptionsPanel();
	

	public MenuCompton()//Konstruktor domyślny
	{
		//Ramka Główna
		mainFrame.setDefaultCloseOperation(EXIT_ON_CLOSE); // zamykanie ramki
		mainFrame.setResizable(false);	//skalowanie ramki przez użytkownika
		mainFrame.setVisible(true); //wywołanie ramki
		mainFrame.setSize (800, 800); //rozmiar
		mainFrame.setLayout(new MigLayout());
		mainFrame.add(menuPanel,  "north");
		mainFrame.add(animationPanel, "center");
		mainFrame.add(optionPanel,"west,south");
		
		
		//Pasek
		
		//Panel
		menuPanel.add(new MenuBuilder().build());
		
		//Sinusioda
		
		final SinusoidComponent.Sinusoider t = new SinusoidComponent.Sinusoider(sinusoid);
		final SinusoidComponent.Repainter r = new SinusoidComponent.Repainter(sinusoid);

        final Timer tickTimer = new Timer();
        final Timer paintTimer = new Timer();

        paintTimer.schedule(r, 1000, 50);
        tickTimer.schedule(t, 1000, 10);
        
        animationPanel.add(sinusoid);
	}
	
}