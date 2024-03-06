package shapes;

import java.awt.Color;
import java.awt.Graphics;

public class Point extends Shape {
	
	private static final long serialVersionUID = 1L;
	private int x;
	private int y;
	//private Color color;
	
	public Point() {

	}

	public Point(int x, int y) {
		this.x = x;
		setY(y);
	}

	public Point(int x, int y, Color color) {
		this(x, y);
		setColor(color);
	}
	
	public Point(int x, int y, boolean selected) {
		this(x, y);
		setSelected(selected);
	}


	@Override
	public void moveTo(int x, int y) {
		this.x = x;
        this.y = y;
		
	}

	@Override
	public int compareTo(Shape shape) {
		if (shape instanceof Point) {
			Point startCoordinates = new Point(0, 0);
			return (int) (distance(startCoordinates) - ((Point) shape).distance(startCoordinates));
		}
		return 0;
	}
	
	/*public double distance(int x2, int y2) {
		double dx = this.x - x2;
		double dy = this.y - y2;
		double d = Math.sqrt(dx * dx + dy * dy);
		return d;
	}*/
	
	 public double distance(Point point) {
	        return Math.sqrt(Math.pow(x - point.x, 2) + Math.pow(y - point.y, 2));
	    }
	
    public String toString() {
        return "Point: x=" + x + "; y=" + y + "; color=" + getColor().toString().substring(14).replace('=', '-');
    }


	@Override
	public boolean contains(int x, int y) {
		  if (new Point(x, y).distance(this) <= 2) return true;
	        return false;
	}

	@Override
	public void draw(Graphics g) {
	       g.setColor(getColor());
	        g.drawLine(x - 2, y, x + 2, y);
	        g.drawLine(x, y + 2, x, y - 2);
	        if (isSelected()) select(g);
	}
	
	public boolean equals(Object obj) {
		if (obj instanceof Point) {
			Point p = (Point) obj;
			if (this.x == p.getX() && this.y == p.getY()) {
				return true;
			} else {
				return false;
			}
		} else {
			return false;
		}
	}

	@Override
	public void select(Graphics g) {
		g.setColor(Color.BLUE);
		g.drawRect(x - 3, y - 3, 6, 6);
		//g.setColor(color.BLACK);
		
	}
	
	public Point clone() {
    	return new Point(x, y, getColor());
    }

	public int getX() {
		return x;
	}

	public void setX(int x) {
		this.x = x;
	}

	public int getY() {
		return y;
	}

	public void setY(int y) {
		this.y = y;
	}


	
	

}
