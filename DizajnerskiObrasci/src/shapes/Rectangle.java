package shapes;

import java.awt.Color;
import java.awt.Graphics;

public class Rectangle extends SurfaceShape{
	
	private static final long serialVersionUID = 1L;
	private Point upperLeftPoint;
	private int width;
	private int height;
	
    public Rectangle() {}

    public Rectangle(Point upperLeftPoint, int width, int height, Color edgeColor, Color insideColor) {
        this.upperLeftPoint = upperLeftPoint;
        this.width = width;
        this.height = height;
        setColor(edgeColor);
        setInsideColor(insideColor);
    }

	@Override
	public void moveTo(int x, int y) {
		upperLeftPoint.moveTo(x, y);
		
	}

	@Override
	public int compareTo(Shape shape) {
		if(shape instanceof Rectangle){
			Rectangle r = (Rectangle) shape;
			return (int) (this.area() - r.area());
		}
		else
			return 0;
	}
	
	  public int area() {
			return width * height;
		}

	@Override
	public void fillInShape(Graphics g) {
		 g.setColor(getInsideColor());
	        g.fillRect(upperLeftPoint.getX() + 1, upperLeftPoint.getY() + 1, height - 1, width - 1);
		
	}

	@Override
	public boolean contains(int x, int y) {
        if (upperLeftPoint.getX() <= x && x <= (upperLeftPoint.getX() + width) && upperLeftPoint.getY() <= y && y <= upperLeftPoint.getY() + height) return true;
        return false;
	}

	@Override
	public void draw(Graphics g) {
        g.setColor(getColor());
        g.drawRect(upperLeftPoint.getX(), upperLeftPoint.getY(), height, width);
        fillInShape(g);
        if (isSelected()) select(g);
		
	}
	
	@Override
	public boolean equals(Object obj) {
		if (obj instanceof Rectangle) {
			Rectangle rectangle = (Rectangle) obj;
			return upperLeftPoint.equals(rectangle.upperLeftPoint) && width == rectangle.getWidth() && height == rectangle.getHeight();
		}
		return false;
	}

	@Override
	public void select(Graphics g) {
		g.setColor(Color.BLUE);
        new Line(getUpperLeftPoint(), new Point(upperLeftPoint.getX() + height, upperLeftPoint.getY())).select(g);
        new Line(getUpperLeftPoint(), new Point(upperLeftPoint.getX(), upperLeftPoint.getY() + width)).select(g);
        new Line(new Point(getUpperLeftPoint().getX() + height, upperLeftPoint.getY()), diagonal().getEndPoint()).select(g);
        new Line(new Point(upperLeftPoint.getX(), upperLeftPoint.getY() + width), diagonal().getEndPoint()).select(g);
		
	}
	
	  public Line diagonal() {
	        return new Line(upperLeftPoint, new Point(upperLeftPoint.getX() + height, upperLeftPoint.getY() + width));
	    }
	
	  @Override
	    public String toString() {
	    	return "Rectangle: x=" + upperLeftPoint.getX() + "; y=" + upperLeftPoint.getY() + "; height=" + height + "; width=" + width + "; edge color=" + getColor().toString().substring(14).replace('=', '-') + "; area color=" + getInsideColor().toString().substring(14).replace('=', '-');
	    }

	  
	    public Rectangle clone() {
	    	return new Rectangle(upperLeftPoint.clone(), width, height, getColor(), getInsideColor());
	    }
	public Point getUpperLeftPoint() {
		return upperLeftPoint;
	}

	public void setUpperLeftPoint(Point upperLeftPoint) {
		this.upperLeftPoint = upperLeftPoint;
	}

	public int getWidth() {
		return width;
	}

	public void setWidth(int width) {
		this.width = width;
	}

	public int getHeight() {
		return height;
	}

	public void setHeight(int height) {
		this.height = height;
	}
	
	

}
