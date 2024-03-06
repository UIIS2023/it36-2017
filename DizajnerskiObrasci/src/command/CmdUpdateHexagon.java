package command;

import adapter.AdapterHexagon;

public class CmdUpdateHexagon implements Command {
	
	private AdapterHexagon oldState;
	private AdapterHexagon newState;
	private AdapterHexagon originalState;

	public CmdUpdateHexagon(AdapterHexagon oldState, AdapterHexagon newState) {
		this.oldState = oldState;
		this.newState = newState;
	}

	@Override
	public void execute() {
		originalState = oldState.clone();
		oldState.moveTo(newState.getX(), newState.getY());
		oldState.setColor(newState.getColor());
		oldState.setInsideColor(newState.getInsideColor());
		oldState.setR(newState.getR());	
		
	}

	@Override
	public void unexecute() {
		oldState.moveTo(originalState.getX(), originalState.getY());
		oldState.setColor(originalState.getColor());
		oldState.setInsideColor(originalState.getInsideColor());
		oldState.setR(originalState.getR());
		
	}

	@Override
	public String toLogText() {
		return "Updated->" + oldState.toString() + "->" + newState.toString();
	}



}
