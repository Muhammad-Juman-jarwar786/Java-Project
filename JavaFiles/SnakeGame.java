//Make a fully functional snake game
//with a score counter and a high score counter
//that saves the high score to a file
//and loads it when the game is started
//and a game over screen
//and a start screen
//and a pause screen
//and a restart button
//and a quit button
//and a main menu button

import java.awt.*;
import java.awt.event.*;
import java.io.*;
import java.util.*;

//The constructor Timer(int, new ActionListener(){}) is undefined

import javax.swing.*;
import javax.swing.Timer;
import javax.swing.filechooser.FileFilter;
import javax.swing.filechooser.FileNameExtensionFilter;

public class SnakeGame {

    //set up the JFrame
    JFrame frame = new JFrame("Snake Game");
    //set up the JPanel
    JPanel panel = new JPanel();
    //set up the JLabels
    JLabel scoreLabel = new JLabel("Score: 0");
    JLabel highScoreLabel = new JLabel("High Score: 0");
    //set up the JButtons
    JButton startButton = new JButton("Start");
    JButton pauseButton = new JButton("Pause");
    JButton restartButton = new JButton("Restart");
    JButton quitButton = new JButton("Quit");
    JButton mainMenuButton = new JButton("Main Menu");
    //set up the JPanels
    JPanel startPanel = new JPanel();
    JPanel pausePanel = new JPanel();
    JPanel gameOverPanel = new JPanel();
    JPanel mainMenuPanel = new JPanel();
    //set up the JMenuItems
    JMenuItem newGameMenuItem = new JMenuItem("New Game");
    JMenuItem pauseMenuItem = new JMenuItem("Pause");
    JMenuItem restartMenuItem = new JMenuItem("Restart");
    JMenuItem highScoreMenuItem = new JMenuItem("High Score");
    JMenuItem quitMenuItem = new JMenuItem("Quit");
    JMenuItem mainMenuMenuItem = new JMenuItem("Main Menu");
    //set up the JMenus
    JMenu gameMenu = new JMenu("Game");
    //The local variable fileMenu may not have been initialized
    // JMenu fileMenu = new JMenu("File");
    //set up the JMenuBar
    JMenuBar menuBar = new JMenuBar();
    //set up the JFileChooser
    JFileChooser fileChooser = new JFileChooser();
    //set up the FileFilter
    FileFilter filter = new FileNameExtensionFilter("Text Files", "txt");
    //set up the File
    File file = new File("highScore.txt");
    //set up the Scanner
    Scanner scanner;
    //set up the PrintWriter
    PrintWriter writer;
    //set up the Timer
    Timer timer;
    //set up the TimerTask
    TimerTask task;
    //set up the Random
    Random random = new Random();
    //set up the int variables
    int score = 0;
    int highScore = 0;
    int x = 0;
    int y = 0;
    int xSpeed = 0;
    int ySpeed = 0;
    int appleX = 0;
    int appleY = 0;
    int appleWidth = 10;
    int appleHeight = 10;
    int snakeWidth = 10;
    int snakeHeight = 10;
    int snakeX[] = new int[100];
    int snakeY[] = new int[100];
    int snakeLength = 3;
    //set up the boolean variables
    boolean gameOver = false;
    boolean start = false;
    boolean pause = false;
    boolean restart = false;
    boolean quit = false;
    boolean mainMenu = false;
    boolean newGame = false;
    boolean appleEaten = false;
    //set up the ArrayLists
    ArrayList<Integer> snakeXList = new ArrayList<Integer>();
    ArrayList<Integer> snakeYList = new ArrayList<Integer>();
    //set up the Color
    Color snakeColor = Color.GREEN;
    Color appleColor = Color.RED;
    //set up the String
    String highScoreString = "";
    String scoreString = "";

    /**
     * 
     */
    public SnakeGame() {
        //i found no any snake in this code
        //so i have to add it
        //set up the snake
        snakeX[0] = 100;
        snakeY[0] = 100;
        snakeX[1] = 90;
        snakeY[1] = 100;
        snakeX[2] = 80;
        snakeY[2] = 100;
        //set up the apple
        appleX = random.nextInt(500);
        appleY = random.nextInt(500);
        //set up the JFrame
        frame.setSize(500, 500);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLocationRelativeTo(null);
        frame.setResizable(false);
        frame.setVisible(true);
        //set up the JPanel
        panel.setLayout(null);
        panel.setBackground(Color.BLACK);
        panel.setFocusable(true);
        panel.addKeyListener(new KeyAdapter() {
            public void keyPressed(KeyEvent e) {
                if (e.getKeyCode() == KeyEvent.VK_UP) {
                    xSpeed = 0;
                    ySpeed = -1;
                }
                if (e.getKeyCode() == KeyEvent.VK_DOWN) {
                    xSpeed = 0;
                    ySpeed = 1;
                }
                if (e.getKeyCode() == KeyEvent.VK_LEFT) {
                    xSpeed = -1;
                    ySpeed = 0;
                }
                if (e.getKeyCode() == KeyEvent.VK_RIGHT) {
                    xSpeed = 1;
                    ySpeed = 0;
                }
            }
        });
        //set up the JLabels
        scoreLabel.setBounds(10, 10, 100, 20);
        scoreLabel.setForeground(Color.WHITE);
        highScoreLabel.setBounds(10, 30, 100, 20);
        highScoreLabel.setForeground(Color.WHITE);
        //set up the JButtons
        startButton.setBounds(200, 200, 100, 50);
        startButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                start = true;
                startButton.setVisible(false);
                startPanel.setVisible(false);
                panel.requestFocus();
            }
        });
        pauseButton.setBounds(200, 200, 100, 50);
        pauseButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                pause = true;
                pauseButton.setVisible(false);
                pausePanel.setVisible(false);
                panel.requestFocus();
            }
        });
        restartButton.setBounds(200, 200, 100, 50);
        restartButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                restart = true;
                restartButton.setVisible(false);
                gameOverPanel.setVisible(false);
                panel.requestFocus();
            }
        });
        quitButton.setBounds(200, 200, 100, 50);
        quitButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                quit = true;
                quitButton.setVisible(false);
                gameOverPanel.setVisible(false);
                panel.requestFocus();
            }
        });
        mainMenuButton.setBounds(200, 200, 100, 50);
        mainMenuButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                mainMenu = true;
                mainMenuButton.setVisible(false);
                gameOverPanel.setVisible(false);
                panel.requestFocus();
            }
        });
        //set up the JPanels
        startPanel.setBounds(0, 0, 500, 500);
        startPanel.setBackground(Color.BLACK);
        startPanel.setLayout(null);
        startPanel.add(startButton);
        pausePanel.setBounds(0, 0, 500, 500);
        pausePanel.setBackground(Color.BLACK);
        pausePanel.setLayout(null);
        pausePanel.add(pauseButton);
        gameOverPanel.setBounds(0, 0, 500, 500);
        gameOverPanel.setBackground(Color.BLACK);
        gameOverPanel.setLayout(null);
        gameOverPanel.add(restartButton);
        gameOverPanel.add(quitButton);
        gameOverPanel.add(mainMenuButton);
        //set up the JMenuItems
        newGameMenuItem.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                newGame = true;
                panel.requestFocus();
            }
        });
        highScoreMenuItem.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                JOptionPane.showMessageDialog(null, "High Score: " + highScoreString);
                panel.requestFocus();
            }
        });
        quitMenuItem.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                System.exit(0);
            }
        });
        //set up the JMenus
        gameMenu.add(newGameMenuItem);
        gameMenu.add(highScoreMenuItem);
        gameMenu.add(quitMenuItem);
        //set up the JMenuBar
        menuBar.add(gameMenu);

        
        //add the JPanels to the JFrame
        frame.add(startPanel);
        frame.add(pausePanel);
        frame.add(gameOverPanel);
        //add the JLabels to the JPanel
        panel.add(scoreLabel);
        panel.add(highScoreLabel);
        //add the JPanel to the JFrame
        frame.add(panel);
        //add the JMenuBar to the JFrame
        frame.setJMenuBar(menuBar);
        //set up the snake
        for (int i = 0; i < snakeLength; i++) {
            snakeX[i] = 250 - i * 10;
            snakeY[i] = 250;
        }
        //set up the apple
        appleX = (int) (Math.random() * 49) * 10;
        appleY = (int) (Math.random() * 49) * 10;
        //set up the ArrayLists
        snakeXList.add(snakeX[0]);
        snakeYList.add(snakeY[0]);
        //set up the Timer
        //The constructor Timer(int, new ActionListener(){}) is undefined
        //Duplicate method actionPerformed(ActionEvent) in type new ActionListener(){})

        timer = new Timer(100, new ActionListener() {
            //Duplicate method actionPerformed(ActionEvent) in type new ActionListener(){})
            public void actionPerformed(ActionEvent e) {
                //move the snake
                for (int i = snakeLength; i > 0; i--) {
                    snakeX[i] = snakeX[i - 1];
                    snakeY[i] = snakeY[i - 1];
                }
                snakeX[0] = snakeX[0] + xSpeed * 10;
                snakeY[0] = snakeY[0] + ySpeed * 10;
                //check if the snake has eaten the apple
                if (snakeX[0] == appleX && snakeY[0] == appleY) {
                    snakeLength++;
                    appleX = (int) (Math.random() * 49) * 10;
                    appleY = (int) (Math.random() * 49) * 10;
                    score++;
                    //scoreString cannot be resolved to a variable
                    
                    scoreString = Integer.toString(score);
                    scoreLabel.setText("Score: " + scoreString);
                    if (score > highScore) {
                        highScore = score;
                        highScoreString = Integer.toString(highScore);
                        highScoreLabel.setText("High Score: " + highScoreString);
                    }
                }
                //check if the snake has hit the wall
                if (snakeX[0] < 0 || snakeX[0] > 490 || snakeY[0] < 0 || snakeY[0] > 490) {
                    gameOver = true;
                }
                //check if the snake has hit itself
                for (int i = 1; i < snakeLength; i++) {
                    if (snakeX[0] == snakeX[i] && snakeY[0] == snakeY[i]) {
                        gameOver = true;
                    }
                }
                //check if the game is over
                if (gameOver) {
                    timer.stop();
                    gameOverPanel.setVisible(true);
                    restartButton.setVisible(true);
                    quitButton.setVisible(true);
                    mainMenuButton.setVisible(true);
                }
                //repaint the JPanel
                panel.repaint();
            }
        });
        //set up the KeyListener
        panel.addKeyListener(new KeyListener() {
            public void keyPressed(KeyEvent e) {
                if (e.getKeyCode() == KeyEvent.VK_UP && ySpeed != 1) {
                    xSpeed = 0;
                    ySpeed = -1;
                }
                if (e.getKeyCode() == KeyEvent.VK_DOWN && ySpeed != -1) {
                    xSpeed = 0;
                    ySpeed = 1;
                }
                if (e.getKeyCode() == KeyEvent.VK_LEFT && xSpeed != 1) {
                    xSpeed = -1;
                    ySpeed = 0;
                }
                if (e.getKeyCode() == KeyEvent.VK_RIGHT && xSpeed != -1) {
                    xSpeed = 1;
                    ySpeed = 0;
                }
                if (e.getKeyCode() == KeyEvent.VK_P) {
                    if (timer.isRunning()) {
                        timer.stop();
                        pausePanel.setVisible(true);
                        pauseButton.setVisible(true);
                    } else {
                        timer.start();
                        pausePanel.setVisible(false);
                        pauseButton.setVisible(false);
                    }
                }
            }
            public void keyReleased(KeyEvent e) {
            }
            public void keyTyped(KeyEvent e) {
            }
        });
        //set up the MouseListener
        startButton.addMouseListener(new MouseListener() {
            public void mouseClicked(MouseEvent e) {
                startPanel.setVisible(false);
                startButton.setVisible(false);
                panel.requestFocus();
                timer.start();
            }
            public void mouseEntered(MouseEvent e) {
            }
            public void mouseExited(MouseEvent e) {
            }
            public void mousePressed(MouseEvent e) {
            }
            public void mouseReleased(MouseEvent e) {
            }
        });
        pauseButton.addMouseListener(new MouseListener() {
            public void mouseClicked(MouseEvent e) {
                timer.start();
                pausePanel.setVisible(false);
                pauseButton.setVisible(false);
                panel.requestFocus();
            }
            public void mouseEntered(MouseEvent e) {
            }
            public void mouseExited(MouseEvent e) {
            }
            public void mousePressed(MouseEvent e) {
            }
            public void mouseReleased(MouseEvent e) {
            }
        });
        restartButton.addMouseListener(new MouseListener() {
            public void mouseClicked(MouseEvent e) {
                gameOverPanel.setVisible(false);
                restartButton.setVisible(false);
                quitButton.setVisible(false);
                mainMenuButton.setVisible(false);
                snakeLength = 3;
                score = 0;
                scoreString = Integer.toString(score);
                scoreLabel.setText("Score: " + scoreString);
                xSpeed = 1;
                ySpeed = 0;
                for (int i = 0; i < snakeLength; i++) {
                    snakeX[i] = 250 - i * 10;
                    snakeY[i] = 250;
                }
                appleX = (int) (Math.random() * 49) * 10;
                appleY = (int) (Math.random() * 49) * 10;
                gameOver = false;
                timer.start();
                panel.requestFocus();
            }
            public void mouseEntered(MouseEvent e) {
            }
            public void mouseExited(MouseEvent e) {
            }
            public void mousePressed(MouseEvent e) {
            }
            public void mouseReleased(MouseEvent e) {
            }
        });
        quitButton.addMouseListener(new MouseListener() {
            public void mouseClicked(MouseEvent e) {
                System.exit(0);
            }
            public void mouseEntered(MouseEvent e) {
            }
            public void mouseExited(MouseEvent e) {
            }
            public void mousePressed(MouseEvent e) {
            }
            public void mouseReleased(MouseEvent e) {
            }
        });
        mainMenuButton.addMouseListener(new MouseListener() {
            public void mouseClicked(MouseEvent e) {
                gameOverPanel.setVisible(false);
                restartButton.setVisible(false);
                quitButton.setVisible(false);
                mainMenuButton.setVisible(false);
                startPanel.setVisible(true);
                startButton.setVisible(true);
                snakeLength = 3;
                score = 0;
                scoreString = Integer.toString(score);
                scoreLabel.setText("Score: " + scoreString);
                xSpeed = 1;
                ySpeed = 0;
                for (int i = 0; i < snakeLength; i++) {
                    snakeX[i] = 250 - i * 10;
                    snakeY[i] = 250;
                }
                appleX = (int) (Math.random() * 49) * 10;
                appleY = (int) (Math.random() * 49) * 10;
                gameOver = false;
                panel.requestFocus();
            }
            public void mouseEntered(MouseEvent e) {
            }
            public void mouseExited(MouseEvent e) {
            }
            public void mousePressed(MouseEvent e) {
            }
            public void mouseReleased(MouseEvent e) {
            }
        });
        //add the components to the JFrame
        frame.add(panel);
        frame.add(startPanel);
        frame.add(pausePanel);
        frame.add(gameOverPanel);
        frame.add(scoreLabel);
        //set the JFrame properties
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(500, 500);
        frame.setResizable(false);
        frame.setVisible(true);
    }
    public static void main(String[] args) {
        new SnakeGame();
    }
}
