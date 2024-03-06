package shapes;

import java.awt.Color;
import java.awt.Graphics;

public class Circle extends SurfaceShape {
	
	private static final long serialVersionUID = 1L;
	protected Point center;
	protected int radius;
	
	public Circle() {

	}
	
	  public Circle(Point center, int radius){
			this.center = center;
			this.radius = radius;
		}
	  
	    public Circle(Point center, int r, Color edgeColor, Color insideColor) {
	    	this(center, r);
	        setColor(edgeColor);
	        setInsideColor(insideColor);
	    }

	@Override
	public int compareTo(Shape shape) {
		if (shape instanceof Circle) return radius - ((Circle) shape).getRadius();
		return 0;
	}

	@Override
	public void fillInShape(Graphics g) {
		   g.setColor(getInsideColor());
	        g.fillOval(center.getX() - radius + 1, center.getY() - radius + 1, 2 * radius - 2, 2 * radius - 2);
	    
		
	}

	@Override
	public boolean contains(int x, int y) {
		 if (new Point(x, y).distance(getCenter()) <= radius) return true;
	        return false;
	}
	
	  public Circle clone() {
	    	return new Circle(center.clone(), radius, getColor(), getInsideColor());
	    }
	@Override
	public void draw(Graphics g) {
        g.setColor(getColor());
        g.drawOval(center.getX() - radius, center.getY() - radius, 2 * radius, 2 * radius);
        fillInShape(g);
        if (isSelected()) select(g);
		
	}
	
	   @Override
		public boolean equals(Object obj) {
			if (obj instanceof Circle) {
				Circle circle = (Circle) obj;
				return center.equals(circle.getCenter()) && radius == circle.getRadius();
			}
			return false;
		}

	@Override
	public void select(Graphics g) {
	     new Line(new Point(center.getX(), center.getY() - radius), new Point(center.getX(), center.getY() + radius)).select(g);
	      new Line(new Point(center.getX() - radius, center.getY()), new Point(center.getX() + radius, center.getY())).select(g);
	    
		
	}

	@Override
	public void moveTo(int x, int y) {
		 center.moveTo(x, y);
		
	}
	
    @Override
    public String toString() {
    	return "Circle: radius=" + radius + "; x=" + center.getX() + "; y=" + center.getY() + "; edge color=" + getColor().toString().substring(14).replace('=', '-') + "; area color=" + getInsideColor().toString().substring(14).replace('=', '-');
    }

	public Point getCenter() {
		return center;
	}

	public void setCenter(Point center) {
		this.center = center;
	}

	public int getRadius() {
		return radius;
	}

	public void setRadius(int radius) {
		this.radius = radius;
	}
	
	
	
	
}
