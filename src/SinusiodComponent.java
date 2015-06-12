

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.util.TimerTask;

import javax.swing.JPanel;


public class SinusiodComponent extends JPanel
{

    private final Dimension size = new Dimension(768, 494);//(szeroko��(width),wysoko��(height))
    
    private int amplitude = 20; //ampltida
    private int frequency = 2;	//cz�stotliwo��
    
    

    private int x = 0; //deklaracja x w moencie pocz�tkowym
    private double y = size.height / 2; //deklaracja y 
    private int yBase = 0;

    
    
    SinusiodComponent() {

        super(true);

    }
  @Override
    protected void paintComponent(final Graphics g) //rysowanie
    {

        final Graphics2D g2 = (Graphics2D)g;

        g2.setColor(Color.WHITE);//obszar animacji
        g2.fillRect(0, 0, size.width, size.height);

        g2.setColor(Color.BLACK);
        g2.drawOval(393, 246, 20, 20);
        
        
        	g2.setColor(Color.BLACK);//kulka
        	g2.fillOval(x, (int)y, 20, 20);
        	
        
        g2.setColor(Color.BLACK);
        g2.drawLine (403,0, 403, 246);
        
        g2.setColor(Color.BLACK);
        g2.drawLine (403,266, 403, 494);
        
        g2.setColor(Color.BLACK);
        g2.drawLine (0,256, 392, 256);
        
        g2.setColor(Color.BLACK);
        g2.drawLine (414,256, 985, 256);
        
        g2.setColor(Color.BLACK);
        g2.drawLine (398,256, 408, 256);
        
        g2.setColor(Color.BLACK);//kulka
        g2.fillOval(x1, (int)y1, 20, 20);
    }

    @Override
 
    public Dimension getPreferredSize() //preferowany rozmiar
    {
        return size;
    }

    @Override
    public Dimension getMinimumSize() //minimalny rozmiar
    {
        return size;
    }

    @Override
    public Dimension getMaximumSize() //maksymalny romiar
    {
        return size;
    }

    public void fotonsinusiod1() {
    	
        //Przenies piksel w prawo; P�tla na po lewej stronie po osi�gni�ciu kraw�dzi
        x = (++x) % 376;

        //D�ugo�� fali = jeden pe�ny panel szeroko�� podzielona przez cz�stotliwo��
        final int waveLength = size.width / frequency;

        //Zwi�kszanie yBase; ograniczenie si� przy d�ugo�ci fali
        yBase = (++yBase) % waveLength;

        //Normalizacja [0..1]
        final double normalized = (double)yBase / (double)waveLength;

        //Full wave at 2*pi, means...
        final double radians = normalized * Math.PI * 2;

        //Getting the sine
        final double sine = Math.sin(radians);

        //Multiplying with amplitude, add to center position and we have our y
        y = (int)(sine * amplitude) + size.height/2;
    	
    }
    private int amplitude1 = 20; //ampltida
    private int frequency1 = 5;	//cz�stotliwo��
    
    private int x1 = 412; //deklaracja x w moencie pocz�tkowym
    private double y1 = size.height / 2; //deklaracja y 
    private int yBase1 = 0;
    
    public void fotonsinusiod2() {

        //Przenies piksel w prawo; P�tla na po lewej stronie po osi�gni�ciu kraw�dzi
        x1 = (++x1) % size.width;

        //D�ugo�� fali = jeden pe�ny panel szeroko�� podzielona przez cz�stotliwo��
        final int waveLength = size.width / frequency1;

        //Zwi�kszanie yBase; ograniczenie si� przy d�ugo�ci fali
        yBase1 = (++yBase1) % waveLength;

        //Normalizacja [0..1]
        final double normalized = (double)yBase1 / (double)waveLength;

        //Full wave at 2*pi, means...
        final double radians = normalized * Math.PI * 2;

        final double kol = Math.sqrt(2) ;
        
        //Getting the sine
        final double sine =  Math.sin(radians);
        //final double sin2e = kol*Math.sin(radians);
        
        

        //Multiplying with amplitude, add to center position and we have our y
        y1 = (int)(sine * amplitude1) + (amplitude1*radians) + size.height/2  ;

    }

    public static class Sinusoider extends TimerTask {

        private final SinusiodComponent panel;

        Sinusoider(final SinusiodComponent panel) {

            this.panel = panel;

        }

        @Override
        public void run() {

            panel.fotonsinusiod1();
            panel.fotonsinusiod2();

        }

    }

    public static class Repainter extends TimerTask {

        private final SinusiodComponent panel;

        Repainter(final SinusiodComponent panel) {

            this.panel = panel;

        }

        @Override
        public void run() {

            panel.repaint();

        }

    }
}