package manami.color.model;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import manami.color.controller.ColorController;
import manami.color.events.Event;
import manami.color.events.EventConsumer;
import manami.color.events.EventProvider;
import manami.color.events.EventType;
import manami.color.utils.ColorIndex;

public class ColorModel implements EventProvider, EventConsumer {

	private ColorController ctrl = ColorController.getInstance();
	
	private int[] rgb = {0,0,0};
	private String[] hexArray;
	
	private String hex;
	private static final String HEX_PATTERN = "^#([A-Fa-f0-9]{6})$";

	public ColorModel(int red, int green, int blue) {
		hexArray = new String[3];
		setRgb(red, green, blue);
		registerToEvents();
	}

	public ColorModel(String paramHex) {
		hexArray = new String[3];
		setHexValue(paramHex);
		registerToEvents();
	}
	
	private void registerToEvents() {
		// the model registers to events sent by a UI or another program
		List<EventType> events = new ArrayList<EventType>();
		events.add(EventType.NEW_INPUT_RED);
		events.add(EventType.NEW_INPUT_GREEN);
		events.add(EventType.NEW_INPUT_BLUE);
		events.add(EventType.NEW_INPUT_HEX);
		
		this.ctrl.registerConsumer(this, events);
	}
	
	private void createHexArray() {
		hexArray[ColorIndex.RED.getIndex()] = this.hex.substring( 1, 3 );
		hexArray[ColorIndex.GREEN.getIndex()] = this.hex.substring( 3, 5 );
		hexArray[ColorIndex.BLUE.getIndex()] = this.hex.substring( 5, 7 );
	}
	
	private static String decimalToHexdecimal(int decimal) {
		return String.format("%02x", decimal);
	}
	
	public void setColor(int color, ColorIndex idx) {
		if (checkRgbValue(color)) {
			// set color in rgb array according to ColorIndex
			rgb[idx.getIndex()] = color;
			// translate to hex
			String valueHex = decimalToHexdecimal(color);
			// set color in hex array according to ColorIndex
			hexArray[idx.getIndex()] = valueHex;
		}
	}
	
	public void setRgb(int red, int green, int blue) {
		setColor(red, ColorIndex.RED);
		setColor(green, ColorIndex.GREEN);
		setColor(blue, ColorIndex.BLUE);
		
		generateEvents();
	}
	
	public String getHexValue() {
		return "#" + String.join("", hexArray);
	}

	public void setHexValue(String hex) {
		if (checkHexValue(hex)) {
			this.hex = hex;
			createHexArray();
			updateRGB();
			
			generateEvents();
		}
	}

	public int getColor(ColorIndex colorIndex) {
		return this.rgb[colorIndex.getIndex()];
	}
	
	
	private void updateRGB() {
		this.rgb[ColorIndex.RED.getIndex()] = Integer.valueOf(hexArray[ColorIndex.RED.getIndex()], 16);
		this.rgb[ColorIndex.GREEN.getIndex()] = Integer.valueOf(hexArray[ColorIndex.GREEN.getIndex()], 16);
		this.rgb[ColorIndex.BLUE.getIndex()] = Integer.valueOf(hexArray[ColorIndex.BLUE.getIndex()], 16);
	}
	
	private boolean checkHexValue(String hex) {
		boolean valid = false;
		if (hex == null) {
			throw new IllegalArgumentException("Input value is null");
		} else if (7 != hex.length()) {
			throw new IllegalArgumentException("Hex must be 7 caracters.");
		} else if (!hex.startsWith("#")) {
			throw new IllegalArgumentException("Hex must be started with #.");
		} else {
			  Pattern pattern = Pattern.compile(HEX_PATTERN);

		        Matcher matcher = pattern.matcher(hex);
		      
		      if (!matcher.matches()) {
					throw new IllegalArgumentException("Something wrong with your hex value.");
		      }
		      else {
		    	  valid = true;
		      }
		}
		return valid;
	}

	private boolean checkRgbValue(int value) {
		boolean valid = false;
		if (value < 0 || value > 255) {
			throw new IllegalArgumentException("Rgb value must be between 0 to 255.");
		} else {
			valid = true;
		}
		return valid;
	}
	
	@Override
	public String toString() {
		return "[value=" + getHexValue() + ", r=" + getColor(ColorIndex.RED) + ", g=" + getColor(ColorIndex.GREEN) + ", b=" + getColor(ColorIndex.BLUE) + "]";
	}

	private void generateEvents() {
		// generate events
		Event newRed = new Event(EventType.NEW_RED, String.valueOf(rgb[ColorIndex.RED.getIndex()]));
		Event newGreen = new Event(EventType.NEW_GREEN, String.valueOf(rgb[ColorIndex.GREEN.getIndex()]));
		Event newBlue = new Event(EventType.NEW_BLUE, String.valueOf(rgb[ColorIndex.BLUE.getIndex()]));
		Event newHex = new Event(EventType.NEW_HEX, getHexValue());
		// push
		sendEvent(newRed);
		sendEvent(newGreen);
		sendEvent(newBlue);
		sendEvent(newHex);
	}
	
	
	@Override
	public boolean sendEvent(Event e) {
		// TODO Auto-generated method stub
		this.ctrl.receiveEvent(e);
		
		return true;
	}

	@Override
	public boolean consume(Event e) {
		boolean consumed = true;
		// the model has received an event
		if (e != null) {
			EventType type = e.getType();
			System.out.println("Model has received event type: "+type.toString());
			if (type == EventType.NEW_INPUT_RED) {
				this.setRgb(Integer.parseInt(e.getValue()), this.rgb[ColorIndex.GREEN.getIndex()], this.rgb[ColorIndex.BLUE.getIndex()]);
			} else if (type == EventType.NEW_INPUT_GREEN) {
				this.setRgb(this.rgb[ColorIndex.RED.getIndex()], Integer.parseInt(e.getValue()), this.rgb[ColorIndex.BLUE.getIndex()]);
			} else if (type == EventType.NEW_INPUT_BLUE) {
				this.setRgb(this.rgb[ColorIndex.RED.getIndex()], this.rgb[ColorIndex.GREEN.getIndex()], Integer.parseInt(e.getValue()));
			} else if (type == EventType.NEW_INPUT_HEX) {
				this.setHexValue(e.getValue());
			}
			else {
				consumed = false;
			}
		}
		else {
			consumed = false;
		}
		
		return consumed;
	}

	 

	  
}
