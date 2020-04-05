package manami.color.events;

public enum EventType {

	// events for which view is interested
	NEW_RGB(0),
	
	NEW_RED(1),
	
	NEW_GREEN(2),
	
	NEW_BLUE(3),
	
	NEW_HEX(4),

	// events for which model is interested
	NEW_INPUT_RED(5),
	
	NEW_INPUT_GREEN(6),
	
	NEW_INPUT_BLUE(7),
	
	NEW_INPUT_HEX(8);
	
	private final int idx;

	EventType(int idx) {
        this.idx = idx;
    }
    
    public int getIndex() {
        return this.idx;
    }
	
	
	
}
