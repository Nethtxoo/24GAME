import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import javax.swing.ImageIcon;
import javax.swing.JButton;

public class Start extends JFrame {

	private JPanel contentPane;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Start frame = new Start();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public Start() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 1450, 911);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblNewLabel = new JLabel("Start");
		lblNewLabel.setIcon(new ImageIcon(Start.class.getResource("/Img/Interface.png")));
		lblNewLabel.setBounds(0, 0, 1450, 911);
		
		JButton ButtonStart = new JButton("");
		ButtonStart.setIcon(new ImageIcon(Start.class.getResource("/Img/Start Design.png")));
		ButtonStart.setBounds(574, 659, 290, 119);
		contentPane.add(ButtonStart);
		contentPane.add(lblNewLabel);
		
		ButtonStart.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				openInputName();
			}
		});
	}
	
	public class InputName extends JFrame {
	    public InputName() {
	        
	        setSize(1450, 911);
	        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

	        
	        JLabel label = new JLabel("InputName JFrame");
	        add(label);
	    }
	}
	
	private void openInputName() {
	    Inputname inputName = new Inputname();
	    inputName.setVisible(true); 
	    this.setVisible(false); 
	}
}
