package manami.color.view;

import java.util.ArrayList;
import java.util.List;

import manami.color.controller.ColorController;
import manami.color.events.Event;
import manami.color.events.EventConsumer;
import manami.color.events.EventType;

public class ColorView implements EventConsumer {

	private String red;
	private String green;
	private String blue;
	private String hex;
	
	private ColorController ctrl = null;

	public ColorView() {
		
		this.ctrl = ColorController.getInstance();
		
		List<EventType> types = new ArrayList<EventType>();
		types.add(EventType.NEW_RED);
		types.add(EventType.NEW_GREEN);
		types.add(EventType.NEW_BLUE);
		types.add(EventType.NEW_HEX);
		this.ctrl.registerConsumer(this, types);

		this.red = "---";
		this.green = "---";
		this.blue = "---";
		this.hex = "---";
		print();
	}
	
	@Override
	public boolean consume(Event e) {
		boolean consumed = true;
		if (e != null) {
			System.out.println("View has received event type: "+e.getType().toString());
			if (e.getType() == EventType.NEW_RED) {
				this.red = e.getValue();
			} else if (e.getType() == EventType.NEW_GREEN) {
				this.green = e.getValue();
			} else if (e.getType() == EventType.NEW_BLUE) {
				this.blue = e.getValue();
			} else if (e.getType() == EventType.NEW_HEX) {
				this.hex = e.getValue();
			}
			else {
				consumed = false;
			}
			if (consumed) {
				print();				
			}
		} else {
			consumed = false;
		}
		return consumed;
	}
	
	private void print() {
		System.out.println(toXml());
	}
	
	public String toXml() {
		String s = "<color>\n";
		s+= "\t<red>" + red + "</red>\n";
		s+= "\t<green>" + green + "</red>\n";
		s+= "\t<blue>" + blue + "</red>\n";
		s+= "\t<hex>" + hex + "</red>\n";
		s+= "</color>\n";
		return s;
	}
	
	
}
