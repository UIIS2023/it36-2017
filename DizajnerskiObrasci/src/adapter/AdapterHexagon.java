package adapter;


import java.awt.Color;
import java.awt.Graphics;

import hexagon.Hexagon;
import shapes.Point;
import shapes.Shape;
import shapes.SurfaceShape;

public class AdapterHexagon extends SurfaceShape{
	
	private static final long serialVersionUID = 1L;
	private Hexagon hexagon;
	
	public AdapterHexagon(Hexagon hexagon) {
		this.hexagon = hexagon;
	}
	
	public AdapterHexagon(Point startPoint, int radius) {
		
		hexagon = new Hexagon(startPoint.getX(), startPoint.getY(), radius);
	}

	@Override
	public void moveTo(int x, int y) {
		hexagon.setX(x);
		hexagon.setY(y);
		
	}

	@Override
	public int compareTo(Shape o) {
		if (o instanceof AdapterHexagon) return hexagon.getR() - ((AdapterHexagon) o).getR();
		return 0;
	}


	@Override
	public void fillInShape(Graphics g) {
		
	}

	@Override
	public boolean contains(int x, int y) {
		return hexagon.doesContain(x, y);
	}

	@Override
	public void draw(Graphics g) {
		hexagon.paint(g);
		hexagon.setSelected(isSelected());
	
		
	}
	

	public AdapterHexagon clone() {
		Hexagon h = new Hexagon(getX(), getY(), getR());
		h.setBorderColor(getColor());
		h.setAreaColor(getInsideColor());
		return new AdapterHexagon(h);
	}



	@Override
	public boolean isSelected() {
		return hexagon.isSelected();
	}
	
	

	@Override
	public void setSelected(boolean selected) {
		hexagon.setSelected(selected);
		super.setSelected(selected);
	}

	@Override
	public boolean equals(Object obj) {
		if (obj instanceof AdapterHexagon) {
			Hexagon hex = ((AdapterHexagon) obj).hexagon;
			return hexagon.getX() == hex.getX() && hexagon.getY() == hex.getY() && hexagon.getR() == hex.getR();
		}
		return false;
	}

	@Override
	public String toString() {
		return "Hexagon: radius=" + hexagon.getR() + "; x=" + hexagon.getX() + "; y=" + hexagon.getY() + "; edge color=" + getColor().toString().substring(14).replace('=', '-') + "; area color=" + getInsideColor().toString().substring(14).replace('=', '-');
	}



	@Override
	public void select(Graphics g) {
		
	}
	
	public Color getColor() {
		return hexagon.getBorderColor();
	}
	
	public void setColor(Color color) {
		hexagon.setBorderColor(color);
		super.setColor(color);
	}
	
	public Color getInsideColor() {
		return hexagon.getAreaColor();
	}
	
	public void setInsideColor(Color color) {
		hexagon.setAreaColor(color);
		super.setInsideColor(color);
	}
	
	public int getR() {
		return hexagon.getR();
	}
	
	public void setR(int r) {
		hexagon.setR(r);
	}
	
	public int getX() {
		return hexagon.getX();
	}
	
	public int getY() {
		return hexagon.getY();
	}

}
