package shapes;

import java.awt.Color;
import java.awt.Graphics;

public abstract class SurfaceShape extends Shape {
	
	private static final long serialVersionUID = 1L;
	private Color insideColor = Color.WHITE;
	
	public abstract void fillInShape(Graphics g);

	public Color getInsideColor() {
		return insideColor;
	}

	public void setInsideColor(Color insideColor) {
		this.insideColor = insideColor;
	}
	
	 

}
