package main;

import javax.swing.JFrame;
import javax.swing.JPanel;

import model.MyCircle;
import model.MyLine;
import model.MyRectangle;
import model.MyShape;
import model.PainterPerson;
import service.Controller;

import java.awt.Panel;
import java.awt.Point;

import javax.swing.JButton;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.image.BufferedImage;
import java.util.ArrayList;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;

public class MainWin {

	private JFrame frmWelcom;
	private boolean cirFlag = false;
	private boolean linFlag = false;
	private boolean recFlag = false;
	private boolean clearFlag = false;
	PainterPerson pper = null;
	private static MainWin window = null;
	// MouseAdapter mouseAdapter = null;
	MyJPanel padPanel = null;
	// private Graphics2D graphics2D;

	private MainWin(PainterPerson pp) {
		this.pper = pp;
		padPanel = new MyJPanel();
		initialize(pper);
		// padPanel=
	}

	public static MainWin getInstance(PainterPerson pp) {
		if (window == null)
			window = new MainWin(pp);
		window.frmWelcom.setVisible(true);
		return window;
	}

	private void initialize(PainterPerson pp) {
		frmWelcom = new JFrame();
		// frmWelcom.setBackground(Color.RED);
		// frmWelcom.setForeground(Color.LIGHT_GRAY);
		// frmWelcom.setBackground(Color.LIGHT_GRAY);
		// DefaultBoundedRangeModel
		frmWelcom.setTitle("WELCOM " + pp.getUserName());
		frmWelcom.setBounds(100, 100, 584, 300);
		frmWelcom.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frmWelcom.getContentPane().setBackground(Color.WHITE);
		frmWelcom.getContentPane().setLayout(null);
		// frmWelcom.getContentPane().setBackground(Color.lightGray);
		// frmWelcom.getContentPane().setForeground(Color.BLACK);

		Panel menuPanel = new Panel();
		menuPanel.setBackground(Color.LIGHT_GRAY);
		menuPanel.setBounds(0, 0, 141, 261);
		frmWelcom.getContentPane().add(menuPanel);
		menuPanel.setLayout(null);

		JButton rectbtn = new JButton("rectangle");
		JButton circlebtn = new JButton("circle");
		JButton linebtn = new JButton("line");
		rectbtn.setBounds(10, 11, 99, 23);
		menuPanel.add(rectbtn);
		circlebtn.setBounds(10, 45, 99, 23);
		menuPanel.add(circlebtn);
		linebtn.setBounds(10, 79, 99, 23);
		menuPanel.add(linebtn);
		frmWelcom.getContentPane().add(padPanel);
		rectbtn.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {

				clearFlag = false;
				cirFlag = false;
				linFlag = false;
				recFlag = true;
			}
		});

		circlebtn.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
				clearFlag = false;
				cirFlag = true;
				linFlag = false;
				recFlag = false;
			}
		});

		linebtn.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
				clearFlag = false;
				cirFlag = false;
				linFlag = true;
				recFlag = false;
			}
		});

		JButton clearebtn = new JButton("cleare");
		clearebtn.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
				clearFlag = true;
				cirFlag = false;
				linFlag = false;
				recFlag = false;
				padPanel.clear();
			}
		});
		clearebtn.setBounds(10, 207, 99, 23);
		menuPanel.add(clearebtn);

		// this.padPanel.loadPainting();
	}

	class MyJPanel extends JPanel {
		int x1, y1, x2, y2;
		boolean laodFlag = true;
		// PainterPerson p;
		private Graphics g = new BufferedImage(WIDTH, HEIGHT, BufferedImage.TYPE_BYTE_BINARY).getGraphics();

		MyJPanel() {
			// this.p=p;
			MyJPanel.this.setBounds(147, 10, 411, 241);
			MyJPanel.this.setBackground(Color.WHITE);
			addMouseListener(new MouseAdapter() {
				@Override
				public void mousePressed(MouseEvent e) {
					x1 = e.getX();
					y1 = e.getY();
				}

				@Override
				public void mouseReleased(MouseEvent e) {
					x2 = e.getX();
					y2 = e.getY();
					paintComponent(getGraphics());

				}
			});
		}

		public void clear() {
			paintComponent(getGraphics());
		}

		void loadPainting(Graphics2D g2) {
			// Graphics g = getGraphics();
			// Graphics2D g2 = (Graphics2D) g;

			ArrayList<MyShape> paintings = Controller.load(pper.getId());
			System.out.println(paintings.size());
			for (MyShape shapes : paintings) {
				x1 = shapes.getStartPoint().x;
				y1 = shapes.getStartPoint().y;
				x2 = shapes.getEndPoint().x;
				y2 = shapes.getEndPoint().y;
				String type = shapes.getType();
				if (type.equalsIgnoreCase("line")) {
					// clearFlag = false;
					// cirFlag = false;
					// linFlag = true;
					// recFlag = false;
					g2.drawLine(x1, y1, x2, y2);
				} else if (type.equalsIgnoreCase("circle")) {
					// clearFlag = false;
					// cirFlag = true;
					// linFlag = false;
					// recFlag = false;
					g2.drawOval(x1, y1, x2, y2);
				} else if (type.equalsIgnoreCase("rectangle")) {
					// clearFlag = false;
					// cirFlag = false;
					// linFlag = false;
					// recFlag = true;
					g2.drawRect(x1, y1, x2 - x1, y2 - y1);
				}
			}
			laodFlag = false;
		}

		@Override
		protected void paintComponent(Graphics g) {
			Graphics2D g2 = (Graphics2D) g;
			g2.setColor(Color.BLACK);
			if (laodFlag)
				loadPainting(g2);
			if (linFlag) {
				g2.drawLine(x1, y1, x2, y2);
				Controller.create(new MyLine(new Point(x1, y1), new Point(x2, y2)), pper.getId());
			} else if (cirFlag) {
				g2.drawOval(x1, y1, x2, y2);
				Controller.create(new MyCircle(new Point(x1, y1), new Point(x2, y2)), pper.getId());

			} else if (recFlag) {
				g2.drawRect(x1, y1, x2 , y2);
				Controller.create(new MyRectangle(new Point(x1, y1), new Point(x2, y2)), pper.getId());
			} else if (clearFlag) {
				g2.setPaint(Color.white);
				System.out.println(x1 + " " + y1 + " " + x2 + " " + y2);
				g2.fillRect(0, 0, 411, 241);
				Controller.clear(pper.getId());
				//// graphics2D.fillRect(147, 10, 411, 241);
				//// graphics2D.setPaint(Color.black);
				// repaint();
			}
		}

	}

}
