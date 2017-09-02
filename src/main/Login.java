package main;


import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;

import model.PainterPerson;
import service.Controller;
import service.Sequrity;

import javax.swing.JPasswordField;
import javax.swing.JButton;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class Login {

	private JFrame frame;
	private JTextField textField;
	private JPasswordField passwordField;

	public void main(String[] args) {

		initialize();
		this.frame.setVisible(true);

	}


	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.setBounds(100, 100, 450, 300);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);

		JLabel lblUserName = new JLabel("user name ");
		lblUserName.setBounds(68, 63, 75, 20);
		frame.getContentPane().add(lblUserName);

		textField = new JTextField();
		textField.setBounds(153, 63, 202, 20);
		frame.getContentPane().add(textField);
		textField.setColumns(10);

		JLabel passWdLb = new JLabel("password");
		passWdLb.setBounds(68, 113, 75, 14);
		frame.getContentPane().add(passWdLb);

		passwordField = new JPasswordField();
		passwordField.setBounds(153, 110, 202, 20);
		frame.getContentPane().add(passwordField);

		JButton submitBtn = new JButton("login");
		submitBtn.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
				PainterPerson pp = new PainterPerson(null, null);
				pp = Controller.checkUser(textField.getText());
				if (pp != null) {
					if (Controller.checkPass(Sequrity.encrypt(new String(passwordField.getPassword()), "MaKtAb")  , pp.getPasswd())) {
						frame.setVisible(false);
						MainWin.getInstance(pp);
					} else {
						JOptionPane.showMessageDialog(frame, "incorect password! \n retry please", "Warning",
								JOptionPane.ERROR_MESSAGE);
					}
				} else {
					JOptionPane.showMessageDialog(frame, "there is no such user", "Warning!!",
							JOptionPane.ERROR_MESSAGE);

				}

			}
		});
		submitBtn.setBounds(99, 164, 114, 23);
		frame.getContentPane().add(submitBtn);

		JButton registerBtn = new JButton("register now");
		registerBtn.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
				if (Controller.checkUser(textField.getText()) == null) {
					PainterPerson pp = new PainterPerson(null, null);
					pp.setUserName(textField.getText());
					pp.setPasswd(Sequrity.encrypt(new String(passwordField.getPassword()), "MaKtAb"));
					Controller.create(pp);
					frame.setVisible(false);
					MainWin.getInstance(pp);
				} else {
					JOptionPane.showMessageDialog(frame, "this userName is already exist! \n please chose another", "Warning",
							JOptionPane.ERROR_MESSAGE);
				}
			}
		});
		registerBtn.setBounds(241, 164, 114, 23);
		frame.getContentPane().add(registerBtn);
	}

}
