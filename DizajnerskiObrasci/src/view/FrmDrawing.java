package view;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.ButtonGroup;
import javax.swing.DefaultListModel;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JToggleButton;
import javax.swing.border.EmptyBorder;
import javax.swing.border.EtchedBorder;
import javax.swing.border.TitledBorder;

import controller.DrawingController;
import observer.Observer;



public class FrmDrawing extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel mainPanel;
	private final ButtonGroup buttonsGroup;
	private DrawingView view;
	private DrawingController controller;
	private JToggleButton tglBtnSelect;
	private JToggleButton tglBtnDrawPoint;
	private JToggleButton tglBtnDrawCircle;
	private JToggleButton tglBtnDrawLine;
	private JToggleButton tglBtnDrawDonut;
	private JToggleButton tglBtnDrawRectangle;
	private JToggleButton tglBtnDrawHexagon;
	private JButton btnSaveDraw;
	private JButton btnUpdate;
	private JButton btnDelete;
	private JButton btnToFront;
	private JButton btnToBack;
	private JButton btnBringToFront;
	private JButton btnBringToBack;
	private JButton btnUndo;
	private JButton btnRedo;
	private JButton btnLog;
	private JButton btnNewDraw;
	private DefaultListModel <String> dlmList;
	private JScrollPane scrollPane;
	private Color color = Color.BLACK;
	
	private JButton btnInteriorColor;
	private JButton btnEdgeColor;
	private MouseAdapter mouseAdapterEdgeColor;
	private MouseAdapter mouseAdapterInteriorColor;
	private MouseAdapter mouseAdapterUpdate;
	private MouseAdapter mouseAdapterDelete;
	private MouseAdapter mouseAdapterToFront;
	private MouseAdapter mouseAdapterToBack;
	private MouseAdapter mouseAdapterBringToFront;
	private MouseAdapter mouseAdapterBringToBack;
	private MouseAdapter mouseAdapterUndo;
	private MouseAdapter mouseAdapterRedo;
	private MouseAdapter mouseAdapterLog;
	private MouseAdapter mouseAdapterSaveDrawing;
	private MouseAdapter mouseAdapterNewDraw;

	private JList<String> activityLog;


	/**
	 * Launch the application.
	 */
	/*
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					FrmDrawing frame = new FrmDrawing();
					frame.setVisible(true);
					frame.setTitle("Knezevic Aleksandra IT36-2017");
					frame.setSize(700, 500);
					frame.setLocation(400, 50);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}
*/
	/**
	 * Create the frame.
	 */
	public FrmDrawing() {
		
		setForeground(Color.BLUE);
		setBackground(Color.CYAN);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setExtendedState(JFrame.MAXIMIZED_BOTH); 
		setLocationRelativeTo(null);
		
		mainPanel = new JPanel();
		mainPanel.setBackground(Color.WHITE);
		mainPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		mainPanel.setLayout(new BorderLayout(0, 0));
		setContentPane(mainPanel);
		dlmList = new DefaultListModel<String>();
		scrollPane = new JScrollPane();

		JPanel buttonsPanelForDrawing = new JPanel();
		buttonsPanelForDrawing.setBorder(new TitledBorder(new EtchedBorder(EtchedBorder.LOWERED, null, null), "Shapes and save drawing options",
				TitledBorder.LEADING, TitledBorder.TOP, null, new Color(0, 0, 0)));
		buttonsPanelForDrawing.setBackground(Color.WHITE);
		JPanel buttonsPanel = new JPanel();
		buttonsPanel.setBorder(new TitledBorder(new EtchedBorder(EtchedBorder.LOWERED, null, null), "Options",
				TitledBorder.LEADING, TitledBorder.TOP, null, new Color(0, 0, 0)));
		buttonsPanel.setBackground(Color.WHITE);

		view = new DrawingView();
		view.setBackground(Color.WHITE);
		mainPanel.add(buttonsPanel, BorderLayout.NORTH);
		mainPanel.add(buttonsPanelForDrawing, BorderLayout.SOUTH);
		mainPanel.add(view, BorderLayout.CENTER);
		buttonsGroup = new ButtonGroup();

		tglBtnDrawPoint = new JToggleButton();
		tglBtnDrawPoint.setFont(new Font("Tahoma", Font.PLAIN, 13));
		tglBtnDrawPoint.setText("Point");
		tglBtnDrawPoint.setSelected(true);
		buttonsGroup.add(tglBtnDrawPoint);	
		buttonsPanelForDrawing.add(tglBtnDrawPoint);


		tglBtnDrawLine = new JToggleButton();
		tglBtnDrawLine.setFont(new Font("Tahoma", Font.PLAIN, 13));
		tglBtnDrawLine.setText("Line");
		buttonsGroup.add(tglBtnDrawLine);
		buttonsPanelForDrawing.add(tglBtnDrawLine);

		tglBtnDrawCircle = new JToggleButton();
		tglBtnDrawCircle.setFont(new Font("Tahoma", Font.PLAIN, 13));
		tglBtnDrawCircle.setText("Circle");
		buttonsGroup.add(tglBtnDrawCircle);
		buttonsPanelForDrawing.add(tglBtnDrawCircle);
		
		tglBtnDrawDonut = new JToggleButton();
		tglBtnDrawDonut.setFont(new Font("Tahoma", Font.PLAIN, 13));
		tglBtnDrawDonut.setText("Donut");
		buttonsGroup.add(tglBtnDrawDonut);
		buttonsPanelForDrawing.add(tglBtnDrawDonut);
		
		tglBtnDrawRectangle = new JToggleButton();
		tglBtnDrawRectangle.setFont(new Font("Tahoma", Font.PLAIN, 13));
		tglBtnDrawRectangle.setText("Rectangle");
		buttonsGroup.add(tglBtnDrawRectangle);
		buttonsPanelForDrawing.add(tglBtnDrawRectangle);
		
		tglBtnDrawHexagon = new JToggleButton();
		tglBtnDrawHexagon.setFont(new Font("Tahoma", Font.PLAIN, 13));
		tglBtnDrawHexagon.setText("Hexagon");
		buttonsGroup.add(tglBtnDrawHexagon);	
		buttonsPanelForDrawing.add(tglBtnDrawHexagon);
		
		btnSaveDraw = new JButton();
		btnSaveDraw.setEnabled(false);
		btnSaveDraw.setText("Save");
		btnSaveDraw.setFont(new Font("Tahoma", Font.PLAIN, 13));
		buttonsPanelForDrawing.add(btnSaveDraw);
		
		btnNewDraw = new JButton();
		btnNewDraw.setText("New draw");
		btnNewDraw.setEnabled(false);
		buttonsPanelForDrawing.add(btnNewDraw);
		btnNewDraw.setFont(new Font("Tahoma", Font.PLAIN, 13));
		
		JButton btnOpenDraw = new JButton();
		btnOpenDraw.setText("Open");
		btnOpenDraw.setFont(new Font("Tahoma", Font.PLAIN, 13));
		buttonsPanelForDrawing.add(btnOpenDraw);
		
	

		tglBtnSelect = new JToggleButton();
		tglBtnSelect.setText("Select");
		tglBtnSelect.setFont(new Font("Tahoma", Font.PLAIN, 13));
		tglBtnSelect.setEnabled(true);
		buttonsGroup.add(tglBtnSelect);
		buttonsPanel.add(tglBtnSelect);

		btnUpdate = new JButton();
		btnUpdate.setText("Update");
		btnUpdate.setFont(new Font("Tahoma", Font.PLAIN, 13));
		btnUpdate.setEnabled(false);
		buttonsGroup.add(btnUpdate);
		buttonsPanel.add(btnUpdate);

		btnDelete = new JButton();
		btnDelete.setText("Delete");
		btnDelete.setFont(new Font("Tahoma", Font.PLAIN, 13));
		btnDelete.setEnabled(false);
		buttonsGroup.add(btnDelete);
		buttonsPanel.add(btnDelete);
		
		mouseAdapterEdgeColor = new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent click) {
				color = controller.btnEdgeColorClicked();
				if (color != null) btnEdgeColor.setBackground(color);
			}
		};
		
		mouseAdapterInteriorColor = new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent click) {
				color = controller.btnInteriorColorClicked();
				if (color != null) {
					if (color.equals(Color.BLACK)) btnInteriorColor.setForeground(Color.WHITE);
					else if (color.equals(Color.WHITE)) btnInteriorColor.setForeground(Color.BLACK);
					btnInteriorColor.setBackground(color);
				}
			}
		};

		btnEdgeColor = new JButton();
		btnEdgeColor.setForeground(Color.WHITE);
		btnEdgeColor.setText("Edge color");
		btnEdgeColor.setFont(new Font("Tahoma", Font.PLAIN, 13));
		btnEdgeColor.setBackground(Color.BLACK);
		btnEdgeColor.addMouseListener(mouseAdapterEdgeColor);
		buttonsPanel.add(btnEdgeColor);

		btnInteriorColor = new JButton();
		btnInteriorColor.setText("Area color");
		btnInteriorColor.setFont(new Font("Tahoma", Font.PLAIN, 13));
		btnInteriorColor.setForeground(Color.BLACK);
		btnInteriorColor.setBackground(Color.WHITE);
		btnInteriorColor.addMouseListener(mouseAdapterInteriorColor);
		buttonsPanel.add(btnInteriorColor);
		
		btnToFront = new JButton();
		btnToFront.setText("To front");
		btnToFront.setFont(new Font("Tahoma", Font.PLAIN, 13));
		btnToFront.setEnabled(false);		
		buttonsPanel.add(btnToFront);
		
		btnToBack = new JButton();
		btnToBack.setText("To back");
		btnToBack.setFont(new Font("Tahoma", Font.PLAIN, 13));
		btnToBack.setEnabled(false);		
		buttonsPanel.add(btnToBack);
		
		btnBringToFront = new JButton();
		btnBringToFront.setText("Bring to front");
		btnBringToFront.setFont(new Font("Tahoma", Font.PLAIN, 13));
		btnBringToFront.setEnabled(false);	
		buttonsPanel.add(btnBringToFront);
		
		btnBringToBack = new JButton();
		btnBringToBack.setText("Bring to back");
		btnBringToBack.setFont(new Font("Tahoma", Font.PLAIN, 13));
		btnBringToBack.setEnabled(false);	
		buttonsPanel.add(btnBringToBack);
		
		activityLog = new JList<String>();
		activityLog.setEnabled(false);
		activityLog.setModel(dlmList);
		activityLog.setFont(new Font("Tahoma", Font.PLAIN, 13));
		scrollPane.setViewportView(activityLog);
		
		btnUndo = new JButton();
		btnUndo.setEnabled(false);
		btnUndo.setText("Undo");
		btnUndo.setFont(new Font("Tahoma", Font.PLAIN, 13));
		buttonsPanel.add(btnUndo);
		
		btnRedo = new JButton();
		btnRedo.setEnabled(false);
		btnRedo.setText("Redo");
		btnRedo.setFont(new Font("Tahoma", Font.PLAIN, 13));
		buttonsPanel.add(btnRedo);
		
		btnLog = new JButton();
		btnLog.setEnabled(false);
		btnLog.setText("Log");
		btnLog.setFont(new Font("Tahoma", Font.PLAIN, 13));
		buttonsPanel.add(btnLog);
	
	
		view.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent click) {
				if (tglBtnDrawPoint.isSelected()) controller.btnAddPointClicked(click);
				else if (tglBtnDrawLine.isSelected()) controller.btnAddLineClicked(click);
				else if (tglBtnDrawDonut.isSelected()) controller.btnAddDonutClicked(click);
				else if (tglBtnDrawRectangle.isSelected()) controller.btnAddRectangleClicked(click);
				else if (tglBtnDrawCircle.isSelected()) controller.btnAddCircleClicked(click);
				else if (tglBtnSelect.isSelected()) controller.btnSelectShapeClicked(click);
				else if (tglBtnDrawHexagon.isSelected()) controller.btnAddHexagonClicked(click);
			}
		});
		
		mouseAdapterUpdate = new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent click) {
				controller.updateShapeClicked();
			}
		};
		
		mouseAdapterDelete = new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent click) {
				controller.btnDeleteShapeClicked();
			}
		};
		
		mouseAdapterToBack = new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent click) {
				controller.toBack();
			}
		};
		
		mouseAdapterToFront = new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent click) {
				controller.toFront();
			}
		};
		
		mouseAdapterBringToBack = new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent click) {
				controller.bringToBack();
			}
		};
		
		mouseAdapterBringToFront = new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent click) {
				controller.bringToFront();
			}
		};
		
		mouseAdapterUndo = new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent click) {
				controller.undo();
			}
		};
		
		mouseAdapterRedo = new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent click) {
				controller.redo();
			}
		};
		
		mouseAdapterLog = new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent click) {
				if (btnLog.getText().equals("Log")) {
					mainPanel.remove(view);
					mainPanel.add(scrollPane, BorderLayout.CENTER);
					btnLog.setText("Draw");
					
				} else if (btnLog.getText().equals("Draw")) {
					mainPanel.remove(scrollPane);
					mainPanel.add(view, BorderLayout.CENTER);
					btnLog.setText("Log");
				}			
				repaint();
			}
		};
		
		mouseAdapterSaveDrawing = new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent click) {
				controller.save();
			}
		};
		
		btnOpenDraw.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				controller.open();
			}
		});
		
		mouseAdapterNewDraw = new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent click) {
				controller.newDraw();
			}
		};
		
		
	}
	
	
	
	

	public DefaultListModel<String> getList() {
		return dlmList;
	}
	
	public DrawingView getView() {
		return view;
	}

	public JToggleButton getTglBtnSelect() {
		return tglBtnSelect;
	}

	public JButton getBtnUpdate() {
		return btnUpdate;
	}

	public JButton getBtnDelete() {
		return btnDelete;
	}

	public JButton getBtnUndo() {
		return btnUndo;
	}

	public JButton getBtnRedo() {
		return btnRedo;
	}

	public JButton getBtnToFront() {
		return btnToFront;
	}

	public JButton getBtnToBack() {
		return btnToBack;
	}

	public JButton getBtnBringToFront() {
		return btnBringToFront;
	}

	public JButton getBtnBringToBack() {
		return btnBringToBack;
	}

	public JButton getBtnNewDraw() {
		return btnNewDraw;
	}

	public JButton getBtnSaveDraw() {
		return btnSaveDraw;
	}

	public JButton getBtnLog() {
		return btnLog;
	}

	public MouseAdapter getMouseAdapterUpdate() {
		return mouseAdapterUpdate;
	}

	public MouseAdapter getMouseAdapterDelete() {
		return mouseAdapterDelete;
	}

	public MouseAdapter getMouseAdapterUndo() {
		return mouseAdapterUndo;
	}

	public MouseAdapter getMouseAdapterRedo() {
		return mouseAdapterRedo;
	}

	public MouseAdapter getMouseAdapterNewDraw() {
		return mouseAdapterNewDraw;
	}

	public MouseAdapter getMouseAdapterSaveDrawing() {
		return mouseAdapterSaveDrawing;
	}

	public MouseAdapter getMouseAdapterLog() {
		return mouseAdapterLog;
	}

	public MouseAdapter getMouseAdapterToFront() {
		return mouseAdapterToFront;
	}

	public MouseAdapter getMouseAdapterToBack() {
		return mouseAdapterToBack;
	}

	public MouseAdapter getMouseAdapterBringToFront() {
		return mouseAdapterBringToFront;
	}

	public MouseAdapter getMouseAdapterBringToBack() {
		return mouseAdapterBringToBack;
	}

	public JToggleButton getTglBtnDrawPoint() {
		return tglBtnDrawPoint;
	}

	public JToggleButton getTglBtnDrawCircle() {
		return tglBtnDrawCircle;
	}


	public JToggleButton getTglBtnDrawLine() {
		return tglBtnDrawLine;
	}

	public JToggleButton getTglBtnDrawDonut() {
		return tglBtnDrawDonut;
	}

	public JToggleButton getTglBtnDrawRectangle() {
		return tglBtnDrawRectangle;
	}
	
	public JToggleButton getTglBtnDrawHexagon() {
		return tglBtnDrawHexagon;
	}

	public JButton getBtnInteriorColor() {
		return btnInteriorColor;
	}

	public JButton getBtnEdgeColor() {
		return btnEdgeColor;
	}

	public MouseAdapter getMouseAdapterEdgeColor() {
		return mouseAdapterEdgeColor;
	}

	public MouseAdapter getMouseAdapterInteriorColor() {
		return mouseAdapterInteriorColor;
	}
	
	public void setController(DrawingController controller) {
		this.controller = controller;	
		controller.addPropertyChangedListener(new Observer(this));
	}	
	
	
	
	
	}
		
	

	
	



