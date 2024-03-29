package command;

import shapes.Point;

public class CmdUpdatePoint implements Command {
	
	private Point oldState;
	private Point newState;
	private Point originalState;
	
	public CmdUpdatePoint(Point oldState, Point newState) {
		this.oldState = oldState;
		this.newState = newState;
	}

	@Override
	public void execute() {
		originalState = oldState.clone();
		oldState.moveTo(newState.getX(), newState.getY());
		oldState.setColor(newState.getColor());
		
	}

	@Override
	public void unexecute() {
		oldState.moveTo(originalState.getX(), originalState.getY());
		oldState.setColor(originalState.getColor());
		
	}

	@Override
	public String toLogText() {
		return "Updated->" + oldState.toString() + "->" + newState.toString();
	}


}
