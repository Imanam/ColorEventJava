package manami.color.events;

public class Event {

	private EventType type;
	
	private String value;

	public Event(EventType type, String value)
	{
		this.type = type;
		this.value = value;
	}
	
	public EventType getType() {
		return type;
	}

	public void setType(EventType type) {
		this.type = type;
	}

	public String getValue() {
		return value;
	}

	public void setValue(String value) {
		this.value = value;
	}
}
