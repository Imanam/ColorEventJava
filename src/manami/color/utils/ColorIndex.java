package manami.color.utils;

public enum ColorIndex {

	RED(0),
	
	GREEN(1),
	
	BLUE(2);
	
	private final int idx;

	ColorIndex(int idx) {
        this.idx = idx;
    }
    
    public int getIndex() {
        return this.idx;
    }

}
