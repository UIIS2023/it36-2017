package shapes;

import java.awt.Color;
import java.awt.Graphics;
import java.io.Serializable;

public abstract class Shape implements Moveable, Cloneable, Comparable<Shape>, Serializable {
	
	private static final long serialVersionUID = 1L;
	private boolean selected;
	private Color color = Color.BLACK; //po defaultu boja svih oblika da bude crna
	


	public Shape() {

	}

	public Shape(boolean selected) {
		this.selected = selected;
	}
	public Shape(boolean selected, Color color) {
		this.color = color;
		this.selected = selected;
	}

	public abstract boolean contains(int x, int y);

	public abstract void draw(Graphics g);

	public abstract void select(Graphics g);

	public boolean isSelected() {
		return selected;
	}

	public void setSelected(boolean selected) {
		this.selected = selected;
	}

	public Color getColor() {
		return color;
	}

	public void setColor(Color color) {
		this.color = color;
	}

	
}
