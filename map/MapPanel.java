package mp230569.map;

import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionListener;
import java.util.HashSet;
import java.util.Set;
import java.util.Timer;
import java.util.TimerTask;

import mp230569.manager.AirportManager;
import mp230569.model.Airport;
import mp230569.timer.InactivityTimerThread;

public class MapPanel extends Panel {
    private Set<Airport> visibleAirports = new HashSet<>();
    private Timer colorTimer;
    private boolean blinkState = false;
    private InactivityTimerThread timer;

    private static final int MAP_SIZE = 450;     
    private static final int COORD_MIN = -90;     
    private static final int COORD_MAX = 90;      
    private static  double SCALE = MAP_SIZE / (double)(COORD_MAX - COORD_MIN); 
    
    private int hoverX;
    private int hoverY;
    private String hoverText = null;
    
    private int offsetX = 0;
    private int offsetY = 0;
    private int lastX;
    private int lastY;

    public MapPanel(AirportManager airportManager, InactivityTimerThread timer) {
        this.timer = timer;

        visibleAirports.addAll(airportManager.getAirports());
        setPreferredSize(new Dimension(MAP_SIZE, MAP_SIZE));
        setBackground(Color.decode("#0c7909"));

        colorTimer = new Timer();
        colorTimer.scheduleAtFixedRate(new TimerTask() {
            @Override
            public void run() {
                if (isAnyAirportSelected()) {
                    blinkState = !blinkState;
                    repaint();
                }
            }
        }, 0, 500);

        addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                for (Airport a : visibleAirports) {
                    int drawX = worldToScreenX(a.getX());
                    int drawY = worldToScreenY(a.getY());

                    Rectangle rect = new Rectangle(drawX - 4, drawY - 4, 8, 8);
                    if (rect.contains(e.getPoint())) {
                        a.setSelected(!a.isSelected());

                        if (isAnyAirportSelected())
                            timer.pauseThread();
                        else
                            timer.resumeThread();

                        repaint();
                        break;
                    }
                }
            }
            
            @Override
            public void mousePressed(MouseEvent e) {
            	lastX = e.getX();
            	lastY = e.getY();
            }
        });
        
        addMouseWheelListener(e -> {
        	if(e.getWheelRotation() < 0)
        		SCALE *= 1.1;
        	else
        		SCALE /= 1.1;
        	repaint();
        });
        
        addMouseMotionListener((MouseMotionListener) new MouseMotionListener() {

			@Override
			public void mouseDragged(MouseEvent e) {
				int dx = e.getX() - lastX;
				int dy = e.getY() - lastY;
				
				offsetX += dx;
				offsetY += dy;
				
				lastX = e.getX();
				lastY = e.getY(); 
				
			}

			@Override
			public void mouseMoved(MouseEvent e) {
				for (Airport airport : visibleAirports) {
					int drawX = worldToScreenX(airport.getX());
					int drawY = worldToScreenY(airport.getY());
					
					Rectangle rect = new Rectangle(drawX - 4, drawY - 4, 8, 8);
					if(rect.contains(e.getPoint()))
					{
						hoverText = drawX+" "+drawY;
						hoverX = drawX + 10;
						hoverY = drawY - 10;
						break;
					}
				}
				repaint();
			}
        	
        });
    }

    private int worldToScreenX(double x) {
        return (int) ((x - COORD_MIN) * SCALE) + 6 + offsetX;
    }

    private int worldToScreenY(double y) {
        return (int) ((COORD_MAX - y) * SCALE) + 6 + offsetY;
    }

    public void toggleAirport(Airport a, boolean visible) {
        if (visible)
            visibleAirports.add(a);
        else
            visibleAirports.remove(a);

        repaint();
    }

    @Override
    public void paint(Graphics g) {
        super.paint(g);

        for (Airport a : visibleAirports) {
            int drawX = worldToScreenX(a.getX());
            int drawY = worldToScreenY(a.getY());

            if (a.isSelected())
                g.setColor(blinkState ? Color.RED : Color.LIGHT_GRAY);
            else
                g.setColor(Color.LIGHT_GRAY);

            g.fillRect(drawX - 4, drawY - 4, 8, 8);

            g.setColor(Color.BLACK);
            g.drawString(a.getCode(), drawX + 6, drawY);
            if(hoverText != null)
            {
            	g.drawString(hoverText, hoverX, hoverY);

				hoverText = null;
            }
        }
    }

    private boolean isAnyAirportSelected() {
        for (Airport a : visibleAirports)
            if (a.isSelected()) return true;
        return false;
    }
}