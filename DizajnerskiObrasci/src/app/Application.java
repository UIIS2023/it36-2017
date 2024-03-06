package app;

import controller.DrawingController;
import model.DrawingModel;
import view.FrmDrawing;

public class Application {

	public static void main(String[] args) {
		FrmDrawing frame = new FrmDrawing();
		frame.setVisible(true);
		frame.setTitle("Knezevic Aleksandra IT36-2017");
		frame.setSize(1100, 500);
		frame.setLocation(300, 50);
		DrawingModel model = new DrawingModel();
		frame.getView().setModel(model);
		frame.setController(new DrawingController(model, frame));

	}

}
