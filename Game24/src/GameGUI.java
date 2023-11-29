import java.awt.EventQueue;

import javax.swing.JOptionPane;
import javax.swing.JFrame;
import javax.swing.JButton;
import javax.swing.JTextPane;
import javax.swing.JTextField;
import javax.swing.JLabel;
import javax.swing.AbstractAction;
import javax.swing.AbstractButton;

import java.awt.event.ActionEvent;
import javax.swing.Action;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Random;
import java.awt.SystemColor;
import java.awt.Color;
import java.awt.Font;
import javax.swing.SwingConstants;
import javax.swing.ImageIcon;

public class GameGUI  extends JFrame {
	
    
	
	JFrame frame;
	private JTextField textnum1;
	private JTextField textnum2;
	private JTextField textnum3;
	private JTextField textnum4;
	private JTextField textans;
	private JTextField textsolution;
	private final Action action = new SwingAction();
	private JTextField textop1;
	private JTextField textop2;
	private JTextField textop3;
	private Random random;
	private final ArrayList<String> operators;
    private final List<String> validOperators = Arrays.asList("+", "-", "*", "/");
	private final Action action_1 = new SwingAction_1();
	private JTextField textR1;
	private JTextField textR2;
	private JTextField textR3;
	private JTextField textR4;
	private JTextField textScore;
	private Scoreboard scoreboard;
	private int score;
	private JTextField textname;
	
	
	
	
	
	

	/**
	 * Create the application.
	 */
	public GameGUI() {
		random = new Random();
		operators = new ArrayList<>();
		score = 0;
		
		initialize();
		
		
		
	}
	
	
	/**
	 * Initialize the contents of the frame.
	 */
	
	public void initialize() {
		frame = new JFrame();
		frame.setBackground(new Color(0, 0, 0));
		frame.getContentPane().setBackground(Color.BLACK);
		frame.setTitle("24GAME");
		frame.setBounds(100, 100, 1280, 720);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		
	   
		JButton B1 = new JButton("");
		B1.setAction(action);
		B1.setIcon(new ImageIcon(GameGUI.class.getResource("/Img/Check buttom.png")));
		B1.setFont(new Font("Tahoma", Font.BOLD, 30));
		B1.addActionListener(new ActionListener() {
		public void actionPerformed(ActionEvent e) {
				
			int num1,num2,num3,num4;
			int R1,R2,R3,R4;
		    String op1,op2,op3;
			try {
			    R1 = Integer.parseInt(textR1.getText());
			    R2 = Integer.parseInt(textR2.getText());
			    R3 = Integer.parseInt(textR3.getText());
			    R4 = Integer.parseInt(textR4.getText());
			    
			    num1 = Integer.parseInt(textnum1.getText());
			    num2 = Integer.parseInt(textnum2.getText());
			    num3 = Integer.parseInt(textnum3.getText());
			    num4 = Integer.parseInt(textnum4.getText());
			    
		        
				op1 = textop1.getText();
		        op2 = textop2.getText();
		        op3 = textop3.getText();
		        
		        

		        // Check if the entered numbers match the randomly generated numbers
		        if (!(R1 == num1 || R1 == num2 || R1 == num3 || R1 == num4) ||
		            !(R2 == num1 || R2 == num2 || R2 == num3 || R2 == num4) ||
		            !(R3 == num1 || R3 == num2 || R3 == num3 || R3 == num4) ||
		            !(R4 == num1 || R4 == num2 || R4 == num3 || R4 == num4)) {

		            
		            JOptionPane.showMessageDialog(null, "Please fill in the numbers correctly.");
		            return;
		        }
		        	
				
		        // Check if the entered operators are valid
		        if (!isValidOperator(op1) || !isValidOperator(op2) || !isValidOperator(op3)) {
                    JOptionPane.showMessageDialog(null, "Please enter valid operators (+, -, *, /)");
                    return;
                }
		        
		        // Evaluate the expression
		        int result = evaluateExpression(num1, num2, num3, num4, op1, op2, op3);
		        
		        if (result == 24) {
		        	textans.setForeground(new Color(0, 255, 0));
		            textans.setText("Correct");
		            textsolution.setForeground(new Color(0, 255, 0));
		            textsolution.setText("Ans = "+num1+op1+num2+op2+num3+op3+num4+"="+result );
		            // Check if the score is less than 5 before incrementing
	                if (score < 5) {
	                    updateScore();
	                    
	                }
	                
		            		
		            
		        } else {
		        	textans.setForeground(new Color(255, 0, 0));
		            textans.setText("Wrong");
		            textsolution.setForeground(new Color(255, 0, 0));
		            textsolution.setText("Ans = "+num1+op1+num2+op2+num3+op3+num4+"="+result );
		            
		        }
		    } catch (Exception e1) {
		        JOptionPane.showMessageDialog(null, "Please Enter Valid number");
		    }
		}
		
		private void updateScore() {
		    if (score < 5) {
		        score++;
		        textScore.setText("Score: " + score + "/5");
		        if (score == 5) {
		        	JOptionPane.showMessageDialog(null, "Congratulations! You've completed the game!");
		        	// Create an instance of the Scoreboard
		            Scoreboard newScoreboard = new Scoreboard();
		            newScoreboard.setScore(score);

		            // Make the Scoreboard visible
		            newScoreboard.setVisible(true);

		            // Optionally, you can pass the player name to the Scoreboard
		            newScoreboard.setName(textname.getText());

		            // Close the current GameGUI window
		            frame.dispose();
		        	
		        }
		    }
		}

		private boolean isValidOperator(String operator) {
			return validOperators.stream().anyMatch(validOp -> validOp.equalsIgnoreCase(operator));
        }
		
		private int evaluateExpression(int num1, int num2, int num3, int num4, String op1, String op2, String op3) {
		    int result = 0;

		    // Evaluate the first operation (num1 op1 num2)
		    result = applyOperator(num1, num2, op1);

		    // Evaluate the second operation (result op2 num3)
		    result = applyOperator(result, num3, op2);

		    // Evaluate the third operation (result op3 num4)
		    result = applyOperator(result, num4, op3);

		    return result;
		}

		private int applyOperator(int operand1, int operand2, String operator) {
		    switch (operator) {
		        case "+":
		            return operand1 + operand2;
		        case "-":
		            return operand1 - operand2;
		        case "*":
		            return operand1 * operand2;
		        case "/":
		            return operand1 / operand2;
		        default:
		            throw new IllegalArgumentException("Invalid operator: " + operator);
		    }
		}
		
		
		
		
		});
		
		
		B1.setBounds(195, 554, 290, 119);
		frame.getContentPane().add(B1);
		
		JButton B2 = new JButton("");
		B2.setAction(action_1);
		B2.setIcon(new ImageIcon(GameGUI.class.getResource("/Img/Random buttom.png")));
		B2.setBounds(630, 554, 290, 119);
		B2.setFont(new Font("Tahoma", Font.BOLD, 30));
		B2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				refreshNumbers();
			}

			private void refreshNumbers() {
				textR1.setText(String.valueOf(random.nextInt(9)+1)); 
		        textR2.setText(String.valueOf(random.nextInt(9)+1));
		        textR3.setText(String.valueOf(random.nextInt(9)+1));
		        textR4.setText(String.valueOf(random.nextInt(9)+1));
		        
		        /*textR1.setEditable(false);
			    textR2.setEditable(false);
			    textR3.setEditable(false);
			    textR4.setEditable(false);*/
			    
			   textR1.setEditable(true);
			    textR2.setEditable(true);
			    textR3.setEditable(true);
			    textR4.setEditable(true);
				
		        textop1.setText(null);
		        textop2.setText(null);
		        textop3.setText(null);
		        
		        textnum1.setText(null);
		        textnum2.setText(null);
		        textnum3.setText(null);
		        textnum4.setText(null);
		        
		        textsolution.setText(null);
		        textans.setText(null);
		        
		        
			}
		});
		frame.getContentPane().add(B2);
		
		textnum1 = new JTextField();
		textnum1.setForeground(new Color(255, 255, 255));
		textnum1.setBackground(new Color(102, 0, 255));
		textnum1.setHorizontalAlignment(SwingConstants.CENTER);
		textnum1.setFont(new Font("Yu Gothic UI Semibold", Font.BOLD, 30));
		textnum1.setBounds(145, 351, 109, 107);
		frame.getContentPane().add(textnum1);
		textnum1.setColumns(10);
		
		textnum2 = new JTextField();
		textnum2.setForeground(new Color(255, 255, 255));
		textnum2.setBackground(new Color(102, 0, 255));
		textnum2.setHorizontalAlignment(SwingConstants.CENTER);
		textnum2.setFont(new Font("Yu Gothic UI Semibold", Font.BOLD, 30));
		textnum2.setColumns(10);
		textnum2.setBounds(410, 351, 109, 107);
		frame.getContentPane().add(textnum2);
		
		textnum3 = new JTextField();
		textnum3.setForeground(new Color(255, 255, 255));
		textnum3.setBackground(new Color(102, 0, 255));
		textnum3.setHorizontalAlignment(SwingConstants.CENTER);
		textnum3.setFont(new Font("Yu Gothic UI Semibold", Font.BOLD, 30));
		textnum3.setColumns(10);
		textnum3.setBounds(700, 351, 109, 107);
		frame.getContentPane().add(textnum3);
		
		textnum4 = new JTextField();
		textnum4.setForeground(new Color(255, 255, 255));
		textnum4.setBackground(new Color(102, 0, 255));
		textnum4.setHorizontalAlignment(SwingConstants.CENTER);
		textnum4.setFont(new Font("Yu Gothic UI Semibold", Font.BOLD, 30));
		textnum4.setColumns(10);
		textnum4.setBounds(1007, 351, 109, 107);
		frame.getContentPane().add(textnum4);
		
		textans = new JTextField();
		textans.setHorizontalAlignment(SwingConstants.CENTER);
		textans.setBackground(new Color(0, 0, 51));
		textans.setForeground(new Color(0, 0, 0));
		textans.setFont(new Font("Yu Gothic UI Semibold", Font.BOLD, 30));
		textans.setColumns(10);
		textans.setBounds(930, 590, 311, 53);
		frame.getContentPane().add(textans);
		
		textsolution = new JTextField();
		textsolution.setBackground(new Color(0, 0, 51));
		textsolution.setForeground(new Color(0, 0, 0));
		textsolution.setFont(new Font("Yu Gothic UI Semibold", Font.BOLD, 30));
		textsolution.setColumns(10);
		textsolution.setBounds(433, 491, 359, 53);
		frame.getContentPane().add(textsolution);
		
		textop1 = new JTextField();
		textop1.setForeground(new Color(0, 255, 0));
		textop1.setBackground(new Color(102, 0, 255));
		textop1.setHorizontalAlignment(SwingConstants.CENTER);
		textop1.setFont(new Font("Yu Gothic UI Semibold", Font.BOLD, 25));
		textop1.setColumns(10);
		textop1.setBounds(303, 381, 56, 53);
		frame.getContentPane().add(textop1);
		
		textop2 = new JTextField();
		textop2.setForeground(new Color(0, 255, 0));
		textop2.setBackground(new Color(102, 0, 255));
		textop2.setHorizontalAlignment(SwingConstants.CENTER);
		textop2.setFont(new Font("Yu Gothic UI Semibold", Font.BOLD, 25));
		textop2.setColumns(10);
		textop2.setBounds(580, 381, 56, 53);
		frame.getContentPane().add(textop2);
		
		textop3 = new JTextField();
		textop3.setForeground(new Color(0, 255, 0));
		textop3.setBackground(new Color(102, 0, 255));
		textop3.setHorizontalAlignment(SwingConstants.CENTER);
		textop3.setFont(new Font("Yu Gothic UI Semibold", Font.BOLD, 25));
		textop3.setColumns(10);
		textop3.setBounds(878, 381, 56, 53);
		frame.getContentPane().add(textop3);
		
		textR1 = new JTextField();
		textR1.setHorizontalAlignment(SwingConstants.CENTER);
		textR1.setForeground(new Color(255, 255, 0));
		textR1.setFont(new Font("Yu Gothic UI Semibold", Font.BOLD, 30));
		textR1.setColumns(10);
		textR1.setBackground(new Color(102, 0, 255));
		textR1.setBounds(303, 154, 109, 107);
		frame.getContentPane().add(textR1);
		
		textR2 = new JTextField();
		textR2.setHorizontalAlignment(SwingConstants.CENTER);
		textR2.setForeground(new Color(255, 255, 0));
		textR2.setFont(new Font("Yu Gothic UI Semibold", Font.BOLD, 30));
		textR2.setColumns(10);
		textR2.setBackground(new Color(102, 0, 255));
		textR2.setBounds(475, 154, 109, 107);
		frame.getContentPane().add(textR2);
		
		textR3 = new JTextField();
		textR3.setHorizontalAlignment(SwingConstants.CENTER);
		textR3.setForeground(new Color(255, 255, 0));
		textR3.setFont(new Font("Yu Gothic UI Semibold", Font.BOLD, 30));
		textR3.setColumns(10);
		textR3.setBackground(new Color(102, 0, 255));
		textR3.setBounds(630, 154, 109, 107);
		frame.getContentPane().add(textR3);
		
		textR4 = new JTextField();
		textR4.setHorizontalAlignment(SwingConstants.CENTER);
		textR4.setForeground(new Color(255, 255, 0));
		textR4.setFont(new Font("Yu Gothic UI Semibold", Font.BOLD, 30));
		textR4.setColumns(10);
		textR4.setBackground(new Color(102, 0, 255));
		textR4.setBounds(801, 154, 109, 107);
		frame.getContentPane().add(textR4);
		
		textScore = new JTextField();
		textScore.setHorizontalAlignment(SwingConstants.CENTER);
		textScore.setFont(new Font("Tahoma", Font.BOLD, 30));
		textScore.setBackground(new Color(102, 0, 255));
		textScore.setBounds(980, 42, 203, 69);
		frame.getContentPane().add(textScore);
		textScore.setColumns(10);
		textScore.setText("Score: " + score + "/5");
		
		textname = new JTextField();
		textname.setFont(new Font("Tahoma", Font.BOLD, 30));
		textname.setHorizontalAlignment(SwingConstants.CENTER);
		textname.setBounds(83, 42, 171, 53);
		frame.getContentPane().add(textname);
		textname.setColumns(10);
		
	}
	private class SwingAction extends AbstractAction {
		public SwingAction() {
			
			putValue(SHORT_DESCRIPTION, "Check number result 24");
		}
		public void actionPerformed(ActionEvent e) {
		}
	}
	private class SwingAction_1 extends AbstractAction {
		public SwingAction_1() {
			
			putValue(SHORT_DESCRIPTION, "random number and refresh");
		}
		public void actionPerformed(ActionEvent e) {
		}
	}
	public int getScore() {
		// TODO Auto-generated method stub
		return score;
	}
	
	 public void setPlayerName(String playerName) {
	        textname.setText(playerName);
	    }

}