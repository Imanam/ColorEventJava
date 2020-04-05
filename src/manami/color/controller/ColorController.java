package manami.color.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import manami.color.events.Event;
import manami.color.events.EventConsumer;
import manami.color.events.EventType;
import manami.color.view.View;

public class ColorController implements Controller {

	private Map<EventType, List<EventConsumer>> consumers;
	
	private static ColorController instance = null;
	
	private ColorController() {
		consumers = new HashMap<EventType, List<EventConsumer>>();
	}
	
	public static ColorController getInstance() {
		if (instance == null) {
			instance = new ColorController();
		}
		
		return instance;
	}

	@Override
	public void receiveEvent(Event e) {
		if (e != null && consumers != null) {
			List<EventConsumer> interestedConsumers = consumers.get(e.getType());
			if (interestedConsumers != null) {
				for (EventConsumer ec : interestedConsumers) {
					ec.consume(e);
				}
			}
		}
	}

	@Override
	public void registerConsumer(EventConsumer ec, List<EventType> events) {
		if (events != null && ec != null) {
			for (EventType e: events) {
				if (consumers.containsKey(e)) {
					List<EventConsumer> consumerInterestedByEvent = consumers.get(e);
					if (consumerInterestedByEvent == null) {
						consumerInterestedByEvent = new ArrayList<EventConsumer>();
					}
					consumerInterestedByEvent.add(ec);
				}
				else {
					List<EventConsumer> l = new ArrayList<EventConsumer>();
					l.add(ec);
					consumers.put(e, l);
				}
			}
		}
	}
}
