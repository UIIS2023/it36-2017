package dialogs;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Cursor;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.JButton;
import javax.swing.JColorChooser;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;

import adapter.AdapterHexagon;

public class DlgHexagon extends JDialog {

	private static final long serialVersionUID = 1L;
	private final JPanel mainPanel;
    private JTextField txtXcoordinate;
    private JTextField txtYcoordinate;
    private JTextField txtRadiusLength;
    private JLabel lblXcoordinate;
    private JLabel lblRadiusLength;
    private JLabel lblYcoordinate;
    private int xCoordinate;
    private int yCoordinate;
    private int radiusLength;
    private Color edgeColor;
    private Color interiorColor;
    private boolean confirmed;
    private Color edgeColorOfHexagon;
    private Color interiorColorOfHexagon;
    private JButton btnEdgeColor;
    private JButton btnInteriorColor;
	private int drawWidth;
	private int drawHeight;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		try {
			DlgHexagon dialog = new DlgHexagon();
			dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
			dialog.setVisible(true);
			dialog.setTitle("Knezevic Aleksandra IT36-2017");
			dialog.setSize(500, 300);
			dialog.setLocation(400, 50);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * Create the dialog.
	 */
	 public DlgHexagon() {
	        setModal(true);
	        setResizable(false);
	        setTitle("Hexagon");
	        setBounds(100, 100, 449, 342);
	        setLocationRelativeTo(null);
	        getContentPane().setLayout(new BorderLayout());
	        mainPanel = new JPanel();
	        mainPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
	        getContentPane().add(mainPanel, BorderLayout.CENTER);
	        GridBagLayout gbl_mainPanel = new GridBagLayout();
	        gbl_mainPanel.columnWidths = new int[]{0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0};
	        gbl_mainPanel.rowHeights = new int[]{0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0};
	        gbl_mainPanel.columnWeights = new double[]{0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 1.0, Double.MIN_VALUE};
	        gbl_mainPanel.rowWeights = new double[]{0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, Double.MIN_VALUE};

	        mainPanel.setLayout(gbl_mainPanel);
	        {
	            lblXcoordinate = new JLabel("X coordinate");
	            lblXcoordinate.setFont(new Font("Tahoma", Font.PLAIN, 13));
	            GridBagConstraints gbc_lblXcoordinateOfCenter = new GridBagConstraints();
	            gbc_lblXcoordinateOfCenter.insets = new Insets(0, 0, 5, 5);
	            gbc_lblXcoordinateOfCenter.gridx = 3;
	            gbc_lblXcoordinateOfCenter.gridy = 2;
	            mainPanel.add(lblXcoordinate, gbc_lblXcoordinateOfCenter);
	        }
	        {
	            txtXcoordinate = new JTextField();
	            lblXcoordinate.setLabelFor(txtXcoordinate);
	            GridBagConstraints gbc_txtXcoordinateOfCenter = new GridBagConstraints();
	            gbc_txtXcoordinateOfCenter.fill = GridBagConstraints.HORIZONTAL;
	            gbc_txtXcoordinateOfCenter.insets = new Insets(0, 0, 5, 5);
	            gbc_txtXcoordinateOfCenter.gridx = 7;
	            gbc_txtXcoordinateOfCenter.gridy = 2;
	            txtXcoordinate.setColumns(10);
	            mainPanel.add(txtXcoordinate, gbc_txtXcoordinateOfCenter);
	        }
	        {
	            lblYcoordinate = new JLabel("Y coordinate");
	            lblYcoordinate.setFont(new Font("Tahoma", Font.PLAIN, 13));
	            GridBagConstraints gbc_lblYcoordinateOfCenter = new GridBagConstraints();
	            gbc_lblYcoordinateOfCenter.insets = new Insets(0, 0, 5, 5);
	            gbc_lblYcoordinateOfCenter.gridx = 3;
	            gbc_lblYcoordinateOfCenter.gridy = 4;
	            mainPanel.add(lblYcoordinate, gbc_lblYcoordinateOfCenter);
	        }
	        {
	            txtYcoordinate = new JTextField();
	            lblYcoordinate.setLabelFor(txtYcoordinate);
	            GridBagConstraints gbc_txtYcoordinateOfCenter = new GridBagConstraints();
	            gbc_txtYcoordinateOfCenter.fill = GridBagConstraints.HORIZONTAL;
	            gbc_txtYcoordinateOfCenter.insets = new Insets(0, 0, 5, 5);
	            gbc_txtYcoordinateOfCenter.gridx = 7;
	            gbc_txtYcoordinateOfCenter.gridy = 4;
	            txtYcoordinate.setColumns(10);
	            mainPanel.add(txtYcoordinate, gbc_txtYcoordinateOfCenter);
	        }
	        {
	            lblRadiusLength = new JLabel("Radius length");
	            lblRadiusLength.setFont(new Font("Tahoma", Font.PLAIN, 13));
	            GridBagConstraints gbc_lblRadiusLength = new GridBagConstraints();
	            gbc_lblRadiusLength.insets = new Insets(0, 0, 5, 5);
	            gbc_lblRadiusLength.gridx = 3;
	            gbc_lblRadiusLength.gridy = 6;
	            mainPanel.add(lblRadiusLength, gbc_lblRadiusLength);
	        }
	        {
	            txtRadiusLength = new JTextField();
	            lblRadiusLength.setLabelFor(txtRadiusLength);
	            GridBagConstraints gbc_txtRadiusLength = new GridBagConstraints();
	            gbc_txtRadiusLength.fill = GridBagConstraints.HORIZONTAL;
	            gbc_txtRadiusLength.insets = new Insets(0, 0, 5, 5);
	            gbc_txtRadiusLength.gridx = 7;
	            gbc_txtRadiusLength.gridy = 6;
	            mainPanel.add(txtRadiusLength, gbc_txtRadiusLength);
	            txtRadiusLength.setColumns(10);
	        }

	        btnInteriorColor = new JButton("Choose interior color");
	        btnInteriorColor.setFont(new Font("Tahoma", Font.PLAIN, 13));
	        btnInteriorColor.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
	        btnInteriorColor.addMouseListener(new MouseAdapter() {
				@Override
				public void mouseClicked(MouseEvent click) {
	                interiorColor = JColorChooser.showDialog(null, "Color pallete", interiorColorOfHexagon);
	                if (interiorColor != null) {
						interiorColorOfHexagon = interiorColor;
						if (interiorColorOfHexagon.equals(Color.BLACK)) btnInteriorColor.setForeground(Color.WHITE);
						else if (interiorColorOfHexagon.equals(Color.WHITE)) btnInteriorColor.setForeground(Color.BLACK);
						btnInteriorColor.setBackground(interiorColorOfHexagon);
					}
	            }
	        });

	        btnEdgeColor = new JButton("Choose edge color");
	        btnEdgeColor.setFont(new Font("Tahoma", Font.PLAIN, 13));
	        btnEdgeColor.setForeground(Color.WHITE);
	        btnEdgeColor.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
	        btnEdgeColor.addMouseListener(new MouseAdapter() {
				@Override
				public void mouseClicked(MouseEvent click) {
	                edgeColor = JColorChooser.showDialog(null, "Color pallete", edgeColorOfHexagon);
	                if (edgeColor != null) {
						if (edgeColor.equals(Color.WHITE)) JOptionPane.showMessageDialog(null, "Background is white :D");
						else {
							edgeColorOfHexagon = edgeColor;
							btnEdgeColor.setBackground(edgeColorOfHexagon);
						}
					}
	            }
	        });

	        GridBagConstraints gbc_btnEdgeColor = new GridBagConstraints();
	        gbc_btnEdgeColor.insets = new Insets(0, 0, 5, 5);
	        gbc_btnEdgeColor.gridx = 3;
	        gbc_btnEdgeColor.gridy = 8;
	        mainPanel.add(btnEdgeColor, gbc_btnEdgeColor);
	        btnEdgeColor.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
	        GridBagConstraints gbc_btnInteriorColor = new GridBagConstraints();
	        gbc_btnInteriorColor.anchor = GridBagConstraints.EAST;
	        gbc_btnInteriorColor.insets = new Insets(0, 0, 5, 5);
	        gbc_btnInteriorColor.gridx = 7;
	        gbc_btnInteriorColor.gridy = 8;
	        mainPanel.add(btnInteriorColor, gbc_btnInteriorColor);
	        {
	            JPanel buttonsPanel = new JPanel();
	            buttonsPanel.setLayout(new FlowLayout(FlowLayout.RIGHT));
	            getContentPane().add(buttonsPanel, BorderLayout.SOUTH);
	            {
	                JButton btnConfirm = new JButton("Confirm");
	                btnConfirm.setFont(new Font("Tahoma", Font.BOLD, 13));
	                btnConfirm.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
	                btnConfirm.addActionListener(new ActionListener() {
	                    public void actionPerformed(ActionEvent click) {
	                        if (txtXcoordinate.getText().isEmpty() || txtYcoordinate.getText().isEmpty() || txtRadiusLength.getText().isEmpty()) JOptionPane.showMessageDialog(getParent(), "Values cannot be empty!", "Error", JOptionPane.ERROR_MESSAGE);
	                        else {
	                            try {
	                            	xCoordinate = Integer.parseInt(txtXcoordinate.getText());
	                            	yCoordinate = Integer.parseInt(txtYcoordinate.getText());
	                            	radiusLength = Integer.parseInt(txtRadiusLength.getText());
	                                if (xCoordinate <= 0 || yCoordinate <= 0 || radiusLength <= 0) JOptionPane.showMessageDialog(getParent(), "X and Y coordinates and radius length of hexagon must be positive numbers!", "Error", JOptionPane.ERROR_MESSAGE);
	                                else if (radiusLength + xCoordinate > drawWidth || radiusLength + yCoordinate > drawHeight || yCoordinate - radiusLength <= 0 || xCoordinate - radiusLength < 0) JOptionPane.showMessageDialog(null, "The hexagon goes out of drawing!");
	                        		else {
	                        			confirmed = true;
	                        			setVisible(false);
	                        			dispose();
	                                }
	                            } catch (NumberFormatException nfe) {
	                                JOptionPane.showMessageDialog(getParent(), "X and Y coordinates and radius length of hexagon must be whole numbers!", "Error", JOptionPane.ERROR_MESSAGE);
	                            }
	                        }
	                    }
	                });

	                btnConfirm.setActionCommand("OK");
	                buttonsPanel.add(btnConfirm);
	                getRootPane().setDefaultButton(btnConfirm);
	            }
	            {
	                JButton btnCancel = new JButton("Cancel");
	                btnCancel.addActionListener(new ActionListener() {
	                	public void actionPerformed(ActionEvent e) {
	                	}
	                });
	                btnCancel.setFont(new Font("Tahoma", Font.BOLD, 13));
	                btnCancel.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
	                btnCancel.addMouseListener(new MouseAdapter() {
	                	@Override
	        			public void mouseClicked(MouseEvent click) {
	                		setVisible(false);
	                		dispose();
	                    }
	                });

	                btnCancel.setActionCommand("Cancel");
	                buttonsPanel.add(btnCancel);
	            }
	        }
	    }


	    public void write(int xOfClick, int yOfClick, int drawWidth, int drawHeight) {
	        txtXcoordinate.setText(String.valueOf(xOfClick));
	        txtXcoordinate.setEnabled(false);
	        txtYcoordinate.setText(String.valueOf(yOfClick));
	        txtYcoordinate.setEnabled(false);
	        this.drawWidth = drawWidth;
			this.drawHeight = drawHeight;
	    }


	    public void deleteButtons() {
	        btnEdgeColor.setVisible(false);
	        btnInteriorColor.setVisible(false);
	    }


	    public void fillUp(AdapterHexagon hexagon, int drawWidth, int drawHeight) {
	        txtXcoordinate.setText(String.valueOf(hexagon.getX()));
	        txtYcoordinate.setText(String.valueOf(hexagon.getY()));
	        txtRadiusLength.setText(String.valueOf(hexagon.getR()));
	        edgeColorOfHexagon = hexagon.getColor();
	        interiorColorOfHexagon = hexagon.getInsideColor();
	        if (interiorColorOfHexagon.equals(Color.BLACK)) btnInteriorColor.setForeground(Color.WHITE);
			else if (interiorColorOfHexagon.equals(Color.WHITE)) btnInteriorColor.setForeground(Color.BLACK);
			btnEdgeColor.setBackground(edgeColorOfHexagon);
			btnInteriorColor.setBackground(interiorColorOfHexagon);
	        this.drawWidth = drawWidth;
			this.drawHeight = drawHeight;
	    }

	    public boolean isConfirmed() {
	        return confirmed;
	    }
	    
	    public int getXcoordinate() {
	        return xCoordinate;
	    }

	    public int getYcoordinate() {
	        return yCoordinate;
	    }

	    public int getRadiusLength() {
	        return radiusLength;
	    }

	    public Color getEdgeColor() {
	        return edgeColorOfHexagon;
	    }

	    public Color getInteriorColor() {
	    	return interiorColorOfHexagon;
	    }

}
