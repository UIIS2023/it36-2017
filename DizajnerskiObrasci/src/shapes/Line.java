package shapes;

import java.awt.Color;
import java.awt.Graphics;

public class Line extends Shape{
	
	private static final long serialVersionUID = 1L;
	private Point startPoint;
	private Point endPoint;
	
    public Line() {}
    
  

    public Line(Point startPoint, Point endPoint) {
        this.startPoint = startPoint;
        this.endPoint = endPoint;
    }

    public Line(Point startPoint, Point endPoint, Color color) {
        this(startPoint, endPoint);
        setColor(color);
    }
    
    

	@Override
	public boolean equals(Object obj) {
		if (obj instanceof Line) {
			Line line = (Line) obj;
			return startPoint.equals(line.startPoint) && endPoint.equals(line.endPoint);
		}
		return false;
	}


	@Override
	public void moveTo(int x, int y) {
		startPoint.moveTo(x, y);
    	endPoint.moveTo(x, y);
		
	}

	@Override
	public int compareTo(Shape shape) {
		if (shape instanceof Line) return (int) distance() - (int) ((Line) shape).distance();
		return 0;
	}
	
	/*
	public double length() {
		return startPoint.distance(endPoint.getX(), endPoint.getY());
	}*/
	
	public String toString() {
    	return "Line: start point x=" + startPoint.getX() + "; start point y=" + startPoint.getY() + "; end point x=" + endPoint.getX() + "; end point y=" + endPoint.getY() + "; color=" + getColor().toString().substring(14).replace('=', '-');
    }


	@Override
	public boolean contains(int x, int y) {
		  if ((startPoint.distance(new Point(x, y)) + endPoint.distance(new Point(x, y))) - distance() <= 1) return true;
	        return false;
	}
	
	public double distance() {
        return startPoint.distance(endPoint);
    }

	  public Line clone() {
			return new Line(startPoint.clone(), endPoint.clone(), getColor());
		}
	  
	@Override
	public void draw(Graphics g) {
		g.setColor(getColor());
		g.drawLine(this.getStartPoint().getX(), this.getStartPoint().getY(), this.getEndPoint().getX(), this.getEndPoint().getY());

		if (isSelected()) {
			select(g);

		}
		
	}

	@Override
	public void select(Graphics g) {
        g.setColor(Color.BLUE);
        startPoint.select(g);
        endPoint.select(g);
        centerOfLine().select(g);
		
	}
	   public Point centerOfLine() {
	        return new Point((startPoint.getX() + endPoint.getX()) / 2, (startPoint.getY() + endPoint.getY()) / 2);
	    }
	

	public Point middleOfLine() {
		int middleByX = (this.getStartPoint().getX() + this.getEndPoint().getX()) / 2;
		int middleByY = (this.getStartPoint().getY() + this.getEndPoint().getY()) / 2;
		Point p = new Point(middleByX, middleByY);
		return p;
	}

	public Point getStartPoint() {
		return startPoint;
	}

	public void setStartPoint(Point startPoint) {
		this.startPoint = startPoint;
	}

	public Point getEndPoint() {
		return endPoint;
	}

	public void setEndPoint(Point endPoint) {
		this.endPoint = endPoint;
	}
	
	

}
