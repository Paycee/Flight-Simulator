package mp230569.form;

import java.awt.*;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

import mp230569.Listeners;
import mp230569.manager.AirportManager;
import mp230569.model.Airport;
import mp230569.timer.InactivityTimerThread;

public class AirportForm extends Panel{
	private TextField name, code, x, y;
	private Button addButton;
	private AirportManager manager;
	private Listeners listener;
	private Label nameStatus, codeStatus, xyStatus;
	

	
	public AirportForm(AirportManager manager, InactivityTimerThread timer)
	{
		this.manager = manager;
		
		setLayout(new GridLayout(5, 3, 10, 10)); 

		
		add(new Label("Name", Label.CENTER) {{ setFont(new Font("", Font.ITALIC, 15)); }});
		name = new TextField();
		nameStatus = new Label(); 
		add(nameStatus);
		add(name);

		
		add(new Label("Code", Label.CENTER) {{ setFont(new Font("", Font.ITALIC, 15)); }});
		code = new TextField();
		codeStatus = new Label(); 
		add(codeStatus);
		add(code);

		
		add(new Label("X coordinate", Label.CENTER) {{ setFont(new Font("", Font.ITALIC, 15)); }});
		x = new TextField();
		add(new Label(""));
		add(x);

		
		add(new Label("Y coordinate", Label.CENTER) {{ setFont(new Font("", Font.ITALIC, 15)); }});
		y = new TextField();
		xyStatus = new Label(); 
		add(xyStatus);
		add(y);

		add(new Label("")); 
		addButton = new Button("Add airport") {{ setFont(new Font("", Font.ITALIC, 15)); }};
		addButton.setBackground(Color.decode("#ADF1F7"));
		add(addButton);
		add(new Label(""));
		
		addButton.addActionListener(e -> {  
			
			timer.resetTimer();
			
			if(validateInput())
			{
		    Airport a = new Airport(
		            name.getText(),
		            code.getText(),
		            Integer.parseInt(x.getText()),
		            Integer.parseInt(y.getText())
		    );
		            
		            
		    manager.addAirport(a);
		                
		    if (listener != null) {
		         listener.airportAdded(a); 
		    }
		    
		    name.setText("");
			code.setText("");
			x.setText("");
			y.setText("");
			}
        });
		
		name.addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent e) {
				timer.resetTimer();
			}
		});
		
		code.addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent e) {
				timer.resetTimer();
			}
		});
		
		x.addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent e) {
				timer.resetTimer();
			}
		});
		
		y.addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent e) {
				timer.resetTimer();
			}
		});
	
	}
	
	public void setAirportListener(Listeners listener) {
	    this.listener = listener;
	}
	
	private boolean validateInput()
	{
		boolean valid = true;
		
		String nameValid = name.getText().trim();
		if(nameValid.isEmpty())
		{
			nameStatus.setText("Name cannot be empty");
			nameStatus.setForeground(Color.RED);
			valid = false;
		}else
			nameStatus.setText("");
		
		String codeValid = code.getText().trim();
		if (!codeValid.matches("[A-Z]{3}")) {
	        codeStatus.setText("Code must be 3 uppercase letters");
	        codeStatus.setForeground(Color.RED);
	        valid = false;
		}else if(manager.isAirportExists(codeValid))
		{
			codeStatus.setText("Code already exists");
	        codeStatus.setForeground(Color.RED);
	        valid = false;
		}
		else
			codeStatus.setText("");
		
		try {
	        double xValid = Integer.parseInt(x.getText().trim());
	        double yValid = Integer.parseInt(y.getText().trim());

	        if (xValid < -90 || xValid > 90 || yValid < -90 || yValid > 90) {
	            xyStatus.setText("Coordinates must be between -90 and 90");
	            xyStatus.setForeground(Color.RED);
	            valid = false;
	        } else {
	            xyStatus.setText("");
	        }

	    } catch (NumberFormatException ex) {
	        xyStatus.setText("X and Y must be numbers");
	        xyStatus.setForeground(Color.RED);
	        valid = false;
	    }
		
		
		return valid;
	}
}
