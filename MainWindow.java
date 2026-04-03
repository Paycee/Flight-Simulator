package mp230569;

import java.awt.*;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

import mp230569.form.AirportForm;
import mp230569.form.FileForm;
import mp230569.form.FlightForm;
import mp230569.manager.AirportManager;
import mp230569.manager.FlightManager;
import mp230569.map.Map;
import mp230569.model.Airport;
import mp230569.model.Flight;
import mp230569.table.AirportTable;
import mp230569.table.FlightTable;
import mp230569.timer.InactivityTimerThread;

public class MainWindow extends Frame implements Listeners{

    private CardLayout cardLayout;
    private Panel mainPanel;
    private Label subtitle;
    
    private AirportManager airportManager = new AirportManager();
    private FlightManager flightManager = new FlightManager();

    private AirportTable airportTable;
    private FlightTable flightTable;
  
    private MenuItem airportTableItem;
    private MenuItem flightTableItem;
    

    private FlightForm flightForm;
    private AirportForm airportForm;
    private FileForm fileForm;
    private Map map;

    private InactivityTimerThread timer;
    
    public MainWindow() {
        super("Flight Simulator");
        setBackground(Color.decode("#CCD8D9"));
        timer = new InactivityTimerThread(this);
        timer.start();

        Panel headerPanel = new Panel(new BorderLayout());

        Label head = new Label("Flight Simulator", Label.CENTER);
        head.setFont(new Font("Arial", Font.BOLD, 20));
        headerPanel.add(head, BorderLayout.NORTH);

        subtitle = new Label("Welcome", Label.CENTER);
        subtitle.setFont(new Font("Arial", Font.ITALIC, 14));
        headerPanel.add(subtitle, BorderLayout.SOUTH);

        add(headerPanel, BorderLayout.NORTH);

        populate();
        

        addWindowListener(new WindowAdapter() {
            public void windowClosing(WindowEvent e) {
                dispose();
                System.exit(0);
            }
        });

        add(mainPanel);
        mainPanel.setPreferredSize(new Dimension(700, 460));
        pack();
        setResizable(false);
        validate();
        setVisible(true);
        
    }
    
    private void populate()
    {
    	MenuBar menuBar = new MenuBar();
    	
        Menu importMenu = new Menu("Add");
        Menu tableMenu = new Menu("Table");
        Menu mapMenu = new Menu("Map");

        MenuItem airportItem = new MenuItem("Airport Form");
        MenuItem fileItem = new MenuItem("File Form");
        MenuItem flightItem = new MenuItem("Flight Form");
        airportTableItem = new MenuItem("Airport Table");
        flightTableItem = new MenuItem("Flight Table");
        MenuItem mapItem = new MenuItem("Map");

        importMenu.add(airportItem);
        importMenu.add(flightItem);
        importMenu.add(fileItem);
        
        tableMenu.add(airportTableItem);
        tableMenu.add(flightTableItem);
        
        mapMenu.add(mapItem);

        menuBar.add(importMenu);
        menuBar.add(tableMenu);
        menuBar.add(mapMenu);
        
        setMenuBar(menuBar);

        cardLayout = new CardLayout();
        mainPanel = new Panel(cardLayout);

        // ---------------------- AIRPORT ------------------
        airportForm = new AirportForm(airportManager, timer);
        airportForm.setAirportListener(this);
        mainPanel.add(airportForm, "airports");
        airportItem.addActionListener(e -> 
        {
        	cardLayout.show(mainPanel, "airports");
        	setSubtitle("Airport Form");
        });
        // ---------------------- FILE ------------------
        
        fileForm = new FileForm(airportManager, flightManager, timer);
        fileForm.setAirportListener(this);
        fileForm.setFlightListener(this);
        mainPanel.add(fileForm, "file");
        fileItem.addActionListener(e -> 
        {
        	cardLayout.show(mainPanel, "file");
        	setSubtitle("File Form");
        });

        // ---------------------- FLIGHT ------------------
        
        flightForm = new FlightForm(flightManager, airportManager, timer); 
        flightForm.setFlightListener(this);
        mainPanel.add(flightForm, "flights");
        flightItem.addActionListener(e -> 
        {
        	cardLayout.show(mainPanel, "flights");
        	setSubtitle("Flight Form");
        });
        // ---------------------- MAP ------------------
        
        map = new Map(airportManager, timer);
        mainPanel.add(map, "map");
        mapItem.addActionListener(e -> 
        {
        	cardLayout.show(mainPanel, "map");
        	setSubtitle("Map");
        });
    }

    @Override
    public void airportAdded(Airport airport) {
        if (airportTable == null) {
            airportTable = new AirportTable(airportManager, flightManager, timer);
            airportTable.setAirportListener(this);
            mainPanel.add(airportTable, "airportTable");

            airportTableItem.addActionListener(e -> {
                airportTable.refresh();
                cardLayout.show(mainPanel, "airportTable");
                setSubtitle("Airport Table");
            });
        } else {
            airportTable.refresh();
        }

        if (flightForm != null) {
            flightForm.refreshAirports();
        }
        
        if (map != null) {
            map.refreshAirports(); 
        }
    }

	@Override
	public void flightAdded(Flight flight) {
		if(flightTable == null)
		{
			flightTable = new FlightTable(flightManager);
			mainPanel.add(flightTable, "flightTable");
			
			flightTableItem.addActionListener(e -> {
				flightTable.refresh();
				cardLayout.show(mainPanel, "flightTable");
				setSubtitle("Flight Table");
			});
		}else
			flightTable.refresh();
		
	}
	
	private void setSubtitle(String text)
	{
		subtitle.setText(text);
	}
}

