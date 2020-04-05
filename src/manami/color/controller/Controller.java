package manami.color.controller;

import java.util.List;

import manami.color.events.Event;
import manami.color.events.EventConsumer;
import manami.color.events.EventType;

public interface Controller {

	public void registerConsumer(EventConsumer ec, List<EventType> events);

	public void receiveEvent(Event e);
}
