import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import javax.swing.JTextField;
import java.awt.BorderLayout;
import javax.swing.ImageIcon;
import java.awt.Color;
import javax.swing.JButton;
import javax.swing.SwingConstants;
import javax.swing.SwingUtilities;

import java.awt.Font;

public class Inputname extends JFrame {

	private JPanel contentPane;
	private JTextField txtInput;
	private JLabel lblNewLabel_1;
	private JButton NewPlayerButton;
	private GameGUI game;
    private Scoreboard scoreboard;
    private String playerName;
	
	/**
	 * Create the frame.
	 */
	public Inputname() {
		
		game = new GameGUI();
	    
		//scoreboard = new Scoreboard();  
        
		setTitle("Input your name");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 1280, 720);
		contentPane = new JPanel();
		contentPane.setBackground(new Color(255, 255, 255));
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		
		
		txtInput = new JTextField();
		txtInput.setFont(new Font("Yu Gothic UI Semibold", Font.BOLD, 30));
		txtInput.setHorizontalAlignment(SwingConstants.CENTER);
		txtInput.setBounds(182, 417, 852, 104);
		txtInput.setToolTipText("Enter your name here");
		contentPane.add(txtInput);
		txtInput.setColumns(10);
		
		
		lblNewLabel_1 = new JLabel("");
		lblNewLabel_1.setBounds(-110, -12, 1503, 911);
		lblNewLabel_1.setForeground(new Color(255, 255, 255));
		lblNewLabel_1.setBackground(new Color(255, 255, 255));
		lblNewLabel_1.setIcon(new ImageIcon(Inputname.class.getResource("/Img/Inputname.png")));
		
		
		NewPlayerButton = new JButton("");
		NewPlayerButton.setToolTipText("New");
		NewPlayerButton.setBounds(206, 543, 290, 119);
		NewPlayerButton.setIcon(new ImageIcon(Inputname.class.getResource("/Img/NewPlayer.png")));
		contentPane.add(NewPlayerButton);
		
		JButton PlayButton = new JButton("");
		PlayButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
			}
			
			
		});
		PlayButton.setToolTipText("Play");
		PlayButton.setIcon(new ImageIcon(Inputname.class.getResource("/Img/Play.png")));
		PlayButton.setBounds(702, 543, 290, 119);
		contentPane.add(PlayButton);
		contentPane.add(lblNewLabel_1);
		
		NewPlayerButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
		        if (game != null) {
		            game.frame.setVisible(true);
		            setVisible(false);

		            playerName = txtInput.getText();
		            int score = game.getScore();
		            
		            scoreboard = new Scoreboard();  
		            scoreboard.insertPlayer(new Player(playerName, score));
		            scoreboard.setVisible(true);
		            scoreboard.dispose();
		            
		         // Set the player name in the GameGUI
                    game.setPlayerName(playerName);
                    if (score == 5) {
                        // Create an instance of the Scoreboard
                        scoreboard.setScore(score);

                        // Make the Scoreboard visible
                        scoreboard.setVisible(true);

                        // Close the current GameGUI window
                        game.frame.dispose();
                    }
		        } else {
		            // Handle the case where scoreboard is null
		        }
		    }
		});
	}
	
	
}
	
	

	    
	