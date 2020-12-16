package ch.epfl.cs107.play.recorder.recordEntry;

import ch.epfl.cs107.play.window.Window;

import java.awt.*;

public abstract class RecordEntry implements java.io.Serializable{
	private static final long serialVersionUID = 1;
	private long time;
	
	public RecordEntry(long time) {
		this.time = time;
	}
	
	public long getTime() {
		return time;
	}
	
	public abstract void replay(Robot robot, Window window);
}
