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

public class LostFrame extends JDialog {

	private JPanel contentPane;

	/**
	 * Create the frame.
	 */
	public LostFrame() {
		
		this.setUndecorated(true);
		this.setBackground(Color.BLUE);
		
		setType(Type.POPUP);
		setResizable(false);
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 365, 253);
		contentPane = new JPanel();
		contentPane.setBackground(Color.BLUE);
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblTitle = new JLabel("You clicked on the mine!");
		lblTitle.setForeground(Color.BLACK);
		lblTitle.setHorizontalAlignment(SwingConstants.CENTER);
		lblTitle.setFont(new Font("Times New Roman", Font.PLAIN, 25));
		lblTitle.setBounds(0, 32, 369, 61);
		contentPane.add(lblTitle);
		
		JLabel lblPickup = new JLabel("Close Try");
		lblPickup.setHorizontalAlignment(SwingConstants.CENTER);
		lblPickup.setForeground(Color.BLACK);
		lblPickup.setFont(new Font("Times New Roman", Font.PLAIN, 48));
		lblPickup.setBounds(0, 87, 369, 61);
		contentPane.add(lblPickup);
		
		JButton btnPlay = new JButton("END GAME");
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
