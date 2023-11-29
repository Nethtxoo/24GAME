import javax.swing.*;
import java.awt.*;
import java.io.*;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Scoreboard extends JFrame {
    private List<Player> players;
    private JTextField nameField;
    private JTextField scoreField;
    private int score;
    

    public Scoreboard() {
        players = new ArrayList<>();
        initializeUI();
        loadPlayersFromFile(); // เรียกใช้เมื่อเริ่มต้นเพื่อโหลดข้อมูลจากไฟล์
    }

    private void initializeUI() {
        setTitle("Scoreboard");
        setSize(1280, 720);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setResizable(false);

        // สร้างแผงที่ใช้แสดงผล
        JPanel panel = new JPanel(null);
        panel.setBackground(Color.GRAY);

        ImageIcon backgroundImage = new ImageIcon(",/image/Scoreboard.png");

        JButton addButton = new JButton("Insert Player");
        addButton.setBounds(0, 0, 500, 50);
        addButton.addActionListener(e -> {
            // เพิ่มผู้เล่นลงในรายชื่อ
            String name = nameField.getText();
        	String scoreText = scoreField.getText();
            
            
            if (!scoreText.isEmpty()) {
                int score = Integer.parseInt(scoreText);
                Player newPlayer = new Player(name, score);
                insertPlayer(newPlayer);
                updateDisplay();
                savePlayersToFile();
            } else {
                // Handle the case where the score is empty
                JOptionPane.showMessageDialog(this, "Score cannot be empty", "Error", JOptionPane.ERROR_MESSAGE);
            }
        });


        JButton deleteButton = new JButton("Delete Player");
        deleteButton.setBounds(0, 60, 500, 50);
        deleteButton.addActionListener(e -> {
            // ลบผู้เล่นจากรายชื่อ
            String name = nameField.getText();
            deletePlayer(name);
            updateDisplay();
            savePlayersToFile(); // บันทึกข้อมูลลงในไฟล์ทุกครั้งที่มีการเพิ่มข้อมูล
        });

        JButton retrieveButton = new JButton("Retrieve Players");
        retrieveButton.setBounds(0, 120, 500, 50);
        retrieveButton.addActionListener(e -> {
            // ดึงข้อมูลผู้เล่นทั้งหมด
            List<Player> allPlayers = getAllPlayers();
            StringBuilder message = new StringBuilder("All Players:\n");
            for (Player player : allPlayers) {
                message.append(player.getName()).append(" - ").append(player.getScore()).append("\n");
            }
            JOptionPane.showMessageDialog(this, message.toString());
        });

        JButton findBtn = new JButton("Find Player");
        findBtn.setBounds(0,180,500,50);
        findBtn.addActionListener(e -> {
            String playerNameToFind = nameField.getText();
            findPlayerByName(playerNameToFind);
        });
        panel.add(addButton);
        panel.add(deleteButton);
        panel.add(retrieveButton);
        panel.add(findBtn);

        getContentPane().add(panel);
        
        nameField = new JTextField();
        nameField.setBounds(556, 58, 128, 19);
        panel.add(nameField);
        nameField.setColumns(10);
        
        scoreField = new JTextField();
        scoreField.setBounds(556, 136, 128, 19);
        panel.add(scoreField);
        scoreField.setColumns(10);
        
        JLabel lblNewLabel = new JLabel("NAME:");
        lblNewLabel.setFont(new Font("Tahoma", Font.BOLD, 20));
        lblNewLabel.setBounds(556, 19, 106, 43);
        panel.add(lblNewLabel);
        
        JLabel lblScore = new JLabel("SCORE:");
        lblScore.setFont(new Font("Tahoma", Font.BOLD, 20));
        lblScore.setBounds(556, 87, 85, 50);
        panel.add(lblScore);
        setVisible(true);
    }


    public void insertPlayer(Player player) {
        // Check if a player with the same name already exists
        for (Player existingPlayer : players) {
            if (existingPlayer.getName().equals(player.getName())) {
                // If found, update the existing player's score
                existingPlayer.setScore(player.getScore());
                Collections.sort(players);  // Re-sort the list
                return;  // Exit the method
            }
            
            
        }

        // If the player is not found, add the new player to the list
        players.add(player);
        Collections.sort(players);  // Re-sort the list

        // Debugging print statement
        System.out.println("Player inserted: " + player.getName() + " - " + player.getScore());
    }


    public void deletePlayer(String playerName) {
        players.removeIf(player -> player.getName().equals(playerName));
    }

    public List<Player> getAllPlayers() {
        return players;
    }

    public Player findPlayerByName(String playerName) {
        for (Player player : players) {
            if (player.getName().equalsIgnoreCase(playerName)) {
                // ถ้าเจอผู้เล่น
                JOptionPane.showMessageDialog(null, "Player Found: \n" + player.getName() + " - " + player.getScore(), "Player Found", JOptionPane.INFORMATION_MESSAGE);
                return player;
            }
        }
        // ถ้าไม่เจอผู้เล่น
        JOptionPane.showMessageDialog(null, "Player Not Found", "Player Not Found", JOptionPane.INFORMATION_MESSAGE);
        return null;
    }



    public void updateDisplay() {
        // แสดงผู้เล่นที่มีคะแนนมากที่สุด
        if (!players.isEmpty()) {
            StringBuilder message = new StringBuilder("Players:\n");
            for (int i = 0; i < players.size(); i++) {
                Player player = players.get(i);
                message.append(i + 1).append(". ").append(player.getName()).append(" - ").append(player.getScore()).append("\n");
            }

            JOptionPane.showMessageDialog(this, message.toString());
        }
    }

    private void loadPlayersFromFile() {
        try (BufferedReader reader = new BufferedReader(new FileReader("Players.txt"))) {
            String line;
            while ((line = reader.readLine()) != null) {
                String[] parts = line.split(",");
                if (parts.length == 2) {
                    String name = parts[0];
                    int score = Integer.parseInt(parts[1]);
                    Player player = new Player(name, score);
                    players.add(player);
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void savePlayersToFile() {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter("Players.txt"))) {
            for (Player player : players) {
                writer.write(player.getName() + "," + player.getScore());
                writer.newLine();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

	public void setScore(int score) {
		this.score = score;
		
	}

   
}