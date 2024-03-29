package shapes;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Shape;
import java.awt.geom.Area;
import java.awt.geom.Ellipse2D;

public class Donut extends Circle {
	
	private static final long serialVersionUID = 1L;
	private int innerRadius;
	
	
public Donut() {}
	
	public Donut(Point center, int r, int innerRadius, Color edgeColor, Color insideColor) {
		super(center, r);
		this.innerRadius = innerRadius;	
		setColor(edgeColor);
        setInsideColor(insideColor);
	}
	
	
	public void draw(Graphics g) {

		Graphics2D g2D=(Graphics2D)g;
		Shape outer=new Ellipse2D.Double(center.getX() - radius, center.getY() - radius, 2 * radius, 2 * radius);
		Shape inner=new Ellipse2D.Double(this.getCenter().getX() - this.getInnerRadius(), this.getCenter().getY() - this.getInnerRadius(), this.getInnerRadius()*2, this.getInnerRadius()*2);
		Area circle=new Area(outer);
		circle.subtract(new Area(inner)); //oduzima oblik inner od oblika circle
		g2D.setColor(getInsideColor());
		g2D.fill(circle);
		g2D.setColor(getColor());
		g2D.draw(circle);
		
		
		if (isSelected()) select(g);
	}
	
	public boolean equals(Object obj) {
		if (obj instanceof Donut) {
			Donut d = (Donut) obj;
			if (this.getCenter().equals(d.getCenter()) &&
					this.getRadius() == d.getRadius()
					&& this.innerRadius == d.getInnerRadius()) {
				return true;
			} else {
				return false;
			}
		} else {
			return false;
		}
	}
	
	public void select(Graphics g) {	
		super.select(g);
		g.drawRect(getCenter().getX() - 3, getCenter().getY() - 3, 6, 6);
		g.drawRect(getCenter().getX() + getInnerRadius() - 3, getCenter().getY() - 3, 6, 6);
		g.drawRect(getCenter().getX() - getInnerRadius() - 3, getCenter().getY() - 3, 6, 6);
		g.drawRect(getCenter().getX()- 3, getCenter().getY() + getInnerRadius() - 3, 6, 6);
		g.drawRect(getCenter().getX() - 3, getCenter().getY() - getInnerRadius() - 3, 6, 6);
		g.setColor(Color.BLACK);
	}
	
	public boolean contains(Point p) {
		double dFromCenter = this.getCenter().distance(p);
		return super.contains(p.getX(), p.getY()) && dFromCenter > innerRadius;
	}
	
	public Donut clone() {
    	return new Donut(center.clone(), radius, innerRadius, getColor(), getInsideColor());
    }

	
	public String toString() {
		return "Donut: radius=" + radius + "; x=" + center.getX() + "; y=" + center.getY() + "; edge color=" + getColor().toString().substring(14).replace('=', '-') + "; area color=" + getInsideColor().toString().substring(14).replace('=', '-') + "; inner radius=" + innerRadius;
	}
	public int getInnerRadius() {
		return innerRadius;
	}
	

	public void setInnerRadius(int innerRadius) {
		this.innerRadius = innerRadius;
	}
	
	

}
