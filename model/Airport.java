package mp230569.model;

public class Airport {
	private String name;
	private String code;
	private int x;
	private int y;
	private boolean selected = false;
	
	public Airport() {
	}
	
	public Airport(String name, String code, int x, int y) {
		this.name = name;
		this.code = code.toUpperCase();
		this.x = x;
		this.y = y;
	}
	

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
	
	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public int getX() {
		return x;
	}

	public void setX(int x) {
		this.x = x;
	}

	public int getY() {
		return y;
	}

	public void setY(int y) {
		this.y = y;
	}
	
	public boolean isSelected() {
		return selected;
	}

	public void setSelected(boolean selected) {
		this.selected = selected;
	}

	public String validate()
	{
		String err = "";
		if(this.name.isEmpty())
			err += "Name cannot be empty \n";
		if(this.code.length() != 3)
			err += "Code must be 3 characters lenght \n";
		if(this.x < -90 || this.x > 90)
			err+= "X coordinate must be between -90 and 90 \n";
		if(this.y < -90 || this.y > 90)
			err+= "Y coordinate must be between -90 and 90 \n";
			
		return err;
	}

	@Override
	public String toString() {
		return "Airport [name=" + name + ", x=" + x + ", y=" + y +  "]";
	}

	
	
}
