import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.Border;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import java.awt.Font;
import javax.swing.SwingConstants;
import java.awt.Color;
import java.awt.Window.Type;
import javax.swing.JButton;
import javax.swing.UIManager;
import javax.swing.JRadioButton;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class WinFrame extends JDialog {

	private JPanel contentPane;

	/**
	 * Create the frame.
	 */
	public WinFrame(String message) {

		this.setUndecorated(true);
		this.setBackground(Color.LIGHT_GRAY);

		setType(Type.POPUP);
		setResizable(false);
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 365, 253);
		contentPane = new JPanel();
		contentPane.setBackground(Color.LIGHT_GRAY);
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);

		JLabel lblTitle = new JLabel("YOU FOUND ALL THE");
		lblTitle.setForeground(Color.BLACK);
		lblTitle.setHorizontalAlignment(SwingConstants.CENTER);
		lblTitle.setFont(new Font("Times New Roman", Font.BOLD, 30));
		lblTitle.setBounds(0, 64, 369, 61);
		contentPane.add(lblTitle);
		
		JLabel lblTitle2 = new JLabel("MINES!");
		lblTitle2.setForeground(Color.BLACK);
		lblTitle2.setHorizontalAlignment(SwingConstants.CENTER);
		lblTitle2.setFont(new Font("Times New Roman", Font.BOLD, 30));
		lblTitle2.setBounds(0, 96, 369, 61);
		contentPane.add(lblTitle2);

		JButton btnPlay = new JButton(message);
		btnPlay.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				btnPlay_mouseClicked(e);
			}
		});
		btnPlay.setBorder(null);
		btnPlay.setForeground(Color.DARK_GRAY);
		btnPlay.setOpaque(true);
		btnPlay.setBackground(new Color(211, 211, 211));
		btnPlay.setFont(new Font("Tahoma", Font.PLAIN, 16));
		btnPlay.setBounds(114, 185, 145, 41);
		contentPane.add(btnPlay);
	}

	protected void btnPlay_mouseClicked(MouseEvent e) {
		this.setVisible(false);
	}
}
