package strategy;

import java.awt.Color;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

import javax.swing.DefaultListModel;

import adapter.AdapterHexagon;
import command.CmdAdd;
import command.CmdBringToBack;
import command.CmdBringToFront;
import command.CmdSelect;
import command.CmdToFront;
import command.CmdUpdateCircle;
import command.CmdUpdateDonut;
import command.CmdUpdateHexagon;
import command.CmdUpdateLine;
import command.CmdUpdatePoint;
import command.CmdUpdateRectangle;
import controller.DrawingController;
import dialogs.DlgParser;
import hexagon.Hexagon;
import model.DrawingModel;
import shapes.Circle;
import shapes.Donut;
import shapes.Line;
import shapes.Point;
import shapes.Rectangle;
import shapes.Shape;
import view.FrmDrawing;

public class LogFile implements SaveOpenFile{
	
	private BufferedWriter writer;
	private FrmDrawing frame;
	private DrawingModel model;
	private DrawingController controller;
	private BufferedReader reader;
	private DlgParser parser;
	
	
	public LogFile(FrmDrawing frame, DrawingModel model, DrawingController controller) {
		this.frame = frame;
		this.model = model; 
		this.controller = controller;
	}
	
	public LogFile(FrmDrawing frame) {
		this.frame = frame;

	}

	@Override
	public void save(File file) {
		try {
			writer = new BufferedWriter(new FileWriter(file + ".log"));
			DefaultListModel<String> list = frame.getList();
			for (int i = 0; i < frame.getList().size(); i++) {
				writer.write(list.getElementAt(i));
				writer.newLine(); 
			}
		} catch (IOException e) {
			System.out.println(e.getMessage());
		}
		try {
			writer.close();
		} catch (IOException e) {
			System.out.println(e.getMessage());
		}
		
	}

	@Override
	public void open(File file) {
		try {
			reader = new BufferedReader(new FileReader(file));
			parser = new DlgParser();
			parser.setFileLog(this);
			parser.addCommand(reader.readLine());
			parser.setVisible(true);
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
		
	}
	
	public void readLine(String command) {
		try {
			String[] commands1 = command.split("->");
			switch(commands1[0]) {
				case "Undo":
					controller.undo();
					break;
				case "Redo":
					controller.redo();
					break;
				case "Added":
					Shape shape = parseShape(commands1[1].split(":")[0], commands1[1].split(":")[1]);
					controller.executeCommand(new CmdAdd(shape, model));
					frame.getList().addElement("Added->" + shape.toString());
					break;
				case "Updated":
					Shape oldShape = parseShape(commands1[1].split(":")[0], commands1[1].split(":")[1]);
					//System.out.println(model.getIndexOf(oldShape));
					int index = model.getIndexOf(oldShape);
					if (oldShape instanceof Point) {
						Point newPoint = parsePoint(commands1[2].split(":")[1]);
						controller.executeCommand(new CmdUpdatePoint((Point) model.getByIndex(index), newPoint));
						frame.getList().addElement("Updated->" + oldShape.toString() + "->" + newPoint.toString());
					}
					else if (oldShape instanceof Line) {
						Line newLine = parseLine(commands1[2].split(":")[1]);
						controller.executeCommand(new CmdUpdateLine((Line) model.getByIndex(index), newLine));
						frame.getList().addElement("Updated->" + oldShape.toString() + "->" + newLine.toString());
					}
					else if (oldShape instanceof Rectangle) {
						Rectangle newRectangle = parseRectangle(commands1[2].split(":")[1]);
						controller.executeCommand(new CmdUpdateRectangle((Rectangle) model.getByIndex(index), newRectangle));
						frame.getList().addElement("Updated->" + oldShape.toString() + "->" + newRectangle.toString());
					}
					else if (oldShape instanceof Donut) {
						Donut newDonut = parseDonut(commands1[2].split(":")[1]);
						controller.executeCommand(new CmdUpdateDonut((Donut) model.getByIndex(index), newDonut));
						frame.getList().addElement("Updated->" + oldShape.toString() + "->" + newDonut.toString());
					}
					else if (oldShape instanceof Circle) {
						Circle newCircle = parseCircle(commands1[2].split(":")[1]);
						controller.executeCommand(new CmdUpdateCircle((Circle) model.getByIndex(index), newCircle));
						frame.getList().addElement("Updated->" + oldShape.toString() + "->" + newCircle.toString());
					}
					else if (oldShape instanceof AdapterHexagon) {
						AdapterHexagon newHexagon = parseHexagon(commands1[2].split(":")[1]);
						controller.executeCommand(new CmdUpdateHexagon((AdapterHexagon) model.getByIndex(index), newHexagon));
						frame.getList().addElement("Updated->" + oldShape.toString() + "->" + newHexagon.toString());
					}
					break;
				case "Deleted":
					controller.DeleteSelected(); 
					break;
				case "Moved to front":
					Shape shapeMovedToFront = parseShape(commands1[1].split(":")[0], commands1[1].split(":")[1]);
					controller.executeCommand(new CmdToFront(model, shapeMovedToFront));
					frame.getList().addElement("Moved to front->" + shapeMovedToFront.toString());
					break;
				case "Moved to back":
					/*Shape shapeMovedToBack = parseShape(commands1[1].split(":")[0], commands1[1].split(":")[1]);
					controller.executeCommand(new CmdToBack(model, shapeMovedToBack));
					frame.getList().addElement("Moved to back->" + shapeMovedToBack.toString());*/
					controller.toBack();
					break;
				case "Bringed to front":
					Shape shapeBringedToFront = parseShape(commands1[1].split(":")[0], commands1[1].split(":")[1]);
					controller.executeCommand(new CmdBringToFront(model, shapeBringedToFront, model.getAll().size() - 1));
					frame.getList().addElement("Bringed to front->" + shapeBringedToFront.toString());
					break;
				case "Bringed to back":
					Shape shapeBringedToBack = parseShape(commands1[1].split(":")[0], commands1[1].split(":")[1]);
					controller.executeCommand(new CmdBringToBack(model, shapeBringedToBack));
					frame.getList().addElement("Bringed to back->" + shapeBringedToBack.toString());
					break;
				case "Selected":
					Shape selectedShape = parseShape(commands1[1].split(":")[0], commands1[1].split(":")[1]);
					int index1 = model.getIndexOf(selectedShape);
					controller.executeCommand(new CmdSelect(model.getByIndex(index1), true));
					frame.getList().addElement("Selected->" + selectedShape.toString());
					controller.handleSelect("Selected", "parser");
					break;
				case "Unselected":
					Shape unselectedShape = parseShape(commands1[1].split(":")[0], commands1[1].split(":")[1]);
					int index2 = model.getIndexOf(unselectedShape);
					controller.executeCommand(new CmdSelect(model.getByIndex(index2), false));
					frame.getList().addElement("Unselected->" + unselectedShape.toString());
					controller.handleSelect("Unselected", "parser");
					break;
			}
		
			String line = reader.readLine();
			if (line != null) parser.addCommand(line);
			else {
				parser.closeDialog();
				return;
			}
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
	}
	
	private Shape parseShape(String shape, String shapeParameters) {
		if (shape.equals("Point")) return parsePoint(shapeParameters);
		else if (shape.equals("Hexagon")) return parseHexagon(shapeParameters);
		else if (shape.equals("Line")) return parseLine(shapeParameters);
		else if (shape.equals("Circle")) return parseCircle(shapeParameters);
		else if (shape.equals("Rectangle")) return parseRectangle(shapeParameters);
		else if (shape.equals("Donut")) return parseDonut(shapeParameters);
		else return parseDonut(shapeParameters);
	}

	
	private Point parsePoint(String string) {
		String [] pointParts = string.split(";"); 		
		String s = pointParts[2].split("=")[1].substring(1, pointParts[2].split("=")[1].length() - 1);
		String [] colors = s.split(",");
		return new Point(Integer.parseInt(pointParts[0].split("=")[1]), Integer.parseInt(pointParts[1].split("=")[1]), new Color(Integer.parseInt(colors[0].split("-")[1]), Integer.parseInt(colors[1].split("-")[1]), Integer.parseInt(colors[2].split("-")[1])));
	}
	
	
	private Circle parseCircle(String string) {
		String [] circleParts = string.split(";"); 	
		int radius = Integer.parseInt(circleParts[0].split("=")[1]);
		int x = Integer.parseInt(circleParts[1].split("=")[1]);
		int y = Integer.parseInt(circleParts[2].split("=")[1]);
		String s = circleParts[3].split("=")[1].substring(1, circleParts[3].split("=")[1].length() - 1);
		String [] edgeColors = s.split(",");
		String s1 = circleParts[4].split("=")[1].substring(1, circleParts[4].split("=")[1].length() - 1);
		String [] interiorColors = s1.split(",");
		return new Circle(new Point(x, y), radius, new Color(Integer.parseInt(edgeColors[0].split("-")[1]), Integer.parseInt(edgeColors[1].split("-")[1]), Integer.parseInt(edgeColors[2].split("-")[1])), new Color(Integer.parseInt(interiorColors[0].split("-")[1]), Integer.parseInt(interiorColors[1].split("-")[1]), Integer.parseInt(interiorColors[2].split("-")[1])));
	}

	private Donut parseDonut(String string) {
		String [] donutParts = string.split(";"); 	
		int radius = Integer.parseInt(donutParts[0].split("=")[1]);
		int x = Integer.parseInt(donutParts[1].split("=")[1]);
		int y = Integer.parseInt(donutParts[2].split("=")[1]);
		String s = donutParts[3].split("=")[1].substring(1, donutParts[3].split("=")[1].length() - 1);
		String [] edgeColors = s.split(",");
		String s1 = donutParts[4].split("=")[1].substring(1, donutParts[4].split("=")[1].length() - 1);
		String [] interiorColors = s1.split(",");
		int innerRadius = Integer.parseInt(donutParts[5].split("=")[1]);
		return new Donut(new Point(x, y), radius, innerRadius, new Color(Integer.parseInt(edgeColors[0].split("-")[1]), Integer.parseInt(edgeColors[1].split("-")[1]), Integer.parseInt(edgeColors[2].split("-")[1])), new Color(Integer.parseInt(interiorColors[0].split("-")[1]), Integer.parseInt(interiorColors[1].split("-")[1]), Integer.parseInt(interiorColors[2].split("-")[1])));
	}
	

	private Rectangle parseRectangle(String string) {
		String [] rectangleParts = string.split(";"); 	
		int x = Integer.parseInt(rectangleParts[0].split("=")[1]);
		int y = Integer.parseInt(rectangleParts[1].split("=")[1]);
		int height = Integer.parseInt(rectangleParts[2].split("=")[1]);
		int width = Integer.parseInt(rectangleParts[3].split("=")[1]);
		String s = rectangleParts[4].split("=")[1].substring(1, rectangleParts[4].split("=")[1].length() - 1);
		String [] edgeColors = s.split(",");
		String s1 = rectangleParts[5].split("=")[1].substring(1, rectangleParts[5].split("=")[1].length() - 1);
		String [] interiorColors = s1.split(",");
		return new Rectangle(new Point(x, y), width, height, new Color(Integer.parseInt(edgeColors[0].split("-")[1]), Integer.parseInt(edgeColors[1].split("-")[1]), Integer.parseInt(edgeColors[2].split("-")[1])), new Color(Integer.parseInt(interiorColors[0].split("-")[1]), Integer.parseInt(interiorColors[1].split("-")[1]), Integer.parseInt(interiorColors[2].split("-")[1])));
	}

	private Line parseLine(String string) {
		String [] lineParts = string.split(";"); 	
		int xStart = Integer.parseInt(lineParts[0].split("=")[1]);
		int yStart = Integer.parseInt(lineParts[1].split("=")[1]);
		int xEnd = Integer.parseInt(lineParts[2].split("=")[1]);
		int yEnd = Integer.parseInt(lineParts[3].split("=")[1]);
		String s = lineParts[4].split("=")[1].substring(1, lineParts[4].split("=")[1].length() - 1);
		String [] edgeColors = s.split(",");
		return new Line(new Point(xStart, yStart), new Point(xEnd, yEnd), new Color(Integer.parseInt(edgeColors[0].split("-")[1]), Integer.parseInt(edgeColors[1].split("-")[1]), Integer.parseInt(edgeColors[2].split("-")[1])));
	}
	

	private AdapterHexagon parseHexagon(String string) {
		String [] hexagonParts = string.split(";"); 	
		int radius = Integer.parseInt(hexagonParts[0].split("=")[1]);
		int x = Integer.parseInt(hexagonParts[1].split("=")[1]);
		int y = Integer.parseInt(hexagonParts[2].split("=")[1]);
		String s = hexagonParts[3].split("=")[1].substring(1, hexagonParts[3].split("=")[1].length() - 1);
		String [] edgeColors = s.split(",");
		String s1 = hexagonParts[4].split("=")[1].substring(1, hexagonParts[4].split("=")[1].length() - 1);
		String [] interiorColors = s1.split(",");
		Hexagon h = new Hexagon(x, y, radius);
		h.setBorderColor(new Color(Integer.parseInt(edgeColors[0].split("-")[1]), Integer.parseInt(edgeColors[1].split("-")[1]), Integer.parseInt(edgeColors[2].split("-")[1])));
		h.setAreaColor(new Color(Integer.parseInt(interiorColors[0].split("-")[1]), Integer.parseInt(interiorColors[1].split("-")[1]), Integer.parseInt(interiorColors[2].split("-")[1])));
		return new AdapterHexagon(h);
	}


}
