package manami.color;

import manami.color.controller.ColorController;
import manami.color.events.Event;
import manami.color.events.EventProvider;
import manami.color.events.EventType;
import manami.color.model.ColorModel;
import manami.color.view.ColorView;

public class ColorValueGenerator implements EventProvider {

	private ColorView myView = null;
	private ColorModel myModel = null;
	private ColorController myCtrl = null;
	
	public ColorValueGenerator() {
		this.myView = new ColorView();
		this.myModel = new ColorModel(0, 0, 0);
		this.myCtrl = ColorController.getInstance();
	}
	
	public void generate() {
		for (int r = 0; r < 15; r++) {
			Event e = new Event(EventType.NEW_INPUT_RED, String.valueOf(r));
			sendEvent(e);
		}
		for (int g = 120; g < 135; g++) {
			Event e = new Event(EventType.NEW_INPUT_GREEN, String.valueOf(g));
			sendEvent(e);
		}
		for (int b = 240; b < 255; b++) {
			Event e = new Event(EventType.NEW_INPUT_BLUE, String.valueOf(b));
			sendEvent(e);
		}
	}
		
		/*
		 * 
		 * System.out.println(color); color.setGreen(215); System.out.println(color);
		 * color.setHexValue("#ffffAA"); System.out.println(color);
		 *
		 * 255, 255); System.out.println(color1); color1.setBlue(100);
		 * System.out.println(color1);
		 * 
		 * ColorModel color2 = new ColorModel("#AAAAAA"); System.out.println(color2);
		 * color2.setHexValue("#FFFFAA"); System.out.println(color2);
		 * 
		 * 
		 */

	@Override
	public boolean sendEvent(Event e) {
		try {
			myCtrl.receiveEvent(e);
			Thread.sleep(500);
		} catch (InterruptedException ex) {
			ex.printStackTrace();
		}
		return false;
	}

	
	public static void main(String[] args) {
		
		ColorValueGenerator m = new ColorValueGenerator();
		m.generate();
	}


}