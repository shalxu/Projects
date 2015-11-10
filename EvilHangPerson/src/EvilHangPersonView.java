import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JLabel;

import java.awt.BorderLayout;

import javax.swing.JPanel;
import javax.swing.JTextField;

import java.awt.GridLayout;

import javax.swing.SwingConstants;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.SystemColor;
import java.awt.Window;

import javax.swing.border.LineBorder;

import java.awt.Font;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.ArrayList;

/**
 * 
 * @author Shal Xu
 * 
 * July 24 2014
 * This is Evil Hang Person game implemented in Swing. 
 * This view class has the main method. It also owns a EvilHangPersonController to sort through words.
 * I apologize for the wrong display of text--tried to solve in different ways but didn't work.
 *
 */
public class EvilHangPersonView {
	/**
	 * the main frame
	 */
	private JFrame frmEvilhangperson;
	/**
	 * the blank to enter number and letter
	 */
	private JTextField entry;
	/**
	 * text above the blank for general instructions
	 */
	private JLabel instructions;
	/**
	 * text below the blank in response to content entered, to make sure the input is right
	 */
	private JLabel reply;
	/**
	 * labels on the right side of window.
	 * moves,lettersText are the labels displaying "moves left and letters guessed"
	 * movesNumber and lettersNumber are the actual content
	 */
	private JLabel movesText,movesNumber,lettersText,lettersNumber,text;
	/**
	 * panel at the center of the frame which also draws the hangman
	 */
	private drawingPanel drawingPanel;
	/**
	 * moves left for the player
	 */
	private int moves;
	/**
	 * number of letters in the words entered at the beginning of game
	 */
	private int numberOfWords;
	/**
	 * correct answer, randomized among all possible words. May not be realizable
	 */
	private String answer;
	/**
	 * EvilHangPersonController to sort words
	 */
	private EvilHangPersonController controller;
	/**
	 * the list of possible answers whose letters have not been guessed
	 */
	private ArrayList<String> currentWords;
	/**
	 * current status of game
	 */
	private enum Status
	{
		enterNumber,enterLetter,done;
	}
	private Status status;
	
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					EvilHangPersonView window = new EvilHangPersonView();
					window.frmEvilhangperson.setVisible(true);
					window.frmEvilhangperson.repaint();
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public EvilHangPersonView() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		moves=7;
		status=Status.enterNumber;
		currentWords=null;
		answer=null;
		controller=new EvilHangPersonController();
		frmEvilhangperson = new JFrame();
		frmEvilhangperson.setTitle("EvilHangPerson");
		frmEvilhangperson.setBounds(50, 50, 500, 400);
		frmEvilhangperson.getContentPane().setBackground(new Color(255,255,255,0));
		frmEvilhangperson.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		JPanel panel= new JPanel();
		panel.setBorder(new LineBorder(new Color(0, 0, 0)));
		frmEvilhangperson.getContentPane().add(panel, BorderLayout.SOUTH);
		panel.setLayout(new GridLayout(0, 1, 0, 0));
		
		instructions = new JLabel(" Please enter the number of letters in the word and press Enter.");
		panel.add(instructions);
		
		entry = new JTextField();
		panel.add(entry);
		entry.addKeyListener(new KeyListener()
		{
			public void keyPressed(KeyEvent arg0) {
				if(arg0.getKeyCode()==KeyEvent.VK_ENTER)
				{
					if(status==Status.enterNumber&&entry.getText().length()==1&&Character.isDigit(entry.getText().charAt(0)))
					{
						numberOfWords=new Integer(entry.getText());
						statusToEnterLetters();
					}
					else if(status==Status.enterLetter&&entry.getText().length()==1&&Character.isLetter(entry.getText().charAt(0)))
					{
						drawMove(Character.toLowerCase(entry.getText().charAt(0)));
						reply.setText("");
						entry.setText("");
					}
					else if(status==Status.done)
					{
						reply.setText("You are done!");
					}
					else
					{
						reply.setText("Invalid input. Try again.");
					}
				}
			}
			public void keyReleased(KeyEvent arg0) {
			}
			public void keyTyped(KeyEvent arg0) {
			}
		});
		
		reply = new JLabel("");
		reply.setForeground(Color.RED);
		panel.add(reply);
		
		JPanel panel_1 = new JPanel();
		panel_1.setBorder(new LineBorder(new Color(0, 0, 0)));
		frmEvilhangperson.getContentPane().add(panel_1, BorderLayout.EAST);
		panel_1.setLayout(new GridLayout(0, 1, 0, 0));
		
		movesText = new JLabel(" Moves Left");
		panel_1.add(movesText);
		
		movesNumber = new JLabel("");
		movesNumber.setForeground(Color.RED);
		panel_1.add(movesNumber);
		
		lettersText = new JLabel(" Letters Guessed");
		panel_1.add(lettersText);
		
		lettersNumber = new JLabel("");
		panel_1.add(lettersNumber);
		
		drawingPanel = new drawingPanel();
		drawingPanel.setBackground(new Color(0,0,0,100));
		frmEvilhangperson.getContentPane().add(drawingPanel, BorderLayout.CENTER);
		drawingPanel.setLayout(new BorderLayout(0, 0));
		
		text = new JLabel("You Are Not Going To Win This  Game! ");
		text.setFont(new Font("Arial Black", Font.PLAIN, 16));
		text.setHorizontalAlignment(SwingConstants.CENTER);
		drawingPanel.add(text);
	}
	/**
	 * Change the status of the game to enterLetters after the number of letters in the word is entered.
	 */
	private void statusToEnterLetters()
	{
		status=Status.enterLetter;
		entry.setText("");
		reply.setText("");
		instructions.setText("Enter a letter you guess.");
		movesNumber.setText(new Integer(moves).toString());
		text.setFont(new Font("Arial Black", Font.PLAIN, 40));
		text.setText("");
		drawingPanel.repaint();
		for(int i=0;i<numberOfWords;i++)
		{
			text.setText(text.getText()+"-");
		}
	}
	/**
	 * The game terminates after all moves are used.
	 */
	private void die()
	{
		status=Status.done;
		text.setText("You died!");
		instructions.setText("There's nothing you can do now...");
	}
	/**
	 * The game wins after the correct answer is guessed (not realizable?).
	 */
	private void win()
	{
		status=Status.done;
		text.setText("You won the game!");
		instructions.setText("Wow");
	}
	/**
	 * The program processes the letter guessed and determines whether it exists in the word.
	 * @param c the letter guessed.
	 */
	private void drawMove(char c)
	{
		if(answer==null)
		{
			reply.setText("Loading...");
			frmEvilhangperson.repaint();
			ArrayList<String> newWords=controller.searchWords(currentWords,c,numberOfWords);
			reply.setText("");
			if(newWords==null)
			{
				answer=currentWords.get((int)(Math.random()*currentWords.size()));
			}
			else
			{
				currentWords=newWords;
			}
			moves--;
		}
		else
		{
			if(text.getText().indexOf(c)==-1&&answer.indexOf(c)!=-1)
			{
				while(answer.indexOf(c)!=-1)
				{
					int location=answer.indexOf(c);
					text.setText(text.getText().substring(0, location)+c+text.getText().substring(location+1,text.getText().length()));
					answer=answer.substring(0,location)+"-"+answer.substring(location+1,answer.length());
				}
				if(text.getText().indexOf('-')==-1)
					win();
			}
			else
			{
				moves--;
			}
		}
		if(moves==0)
			die();
		lettersNumber.setText(lettersNumber.getText()+c+",");
		movesNumber.setText(new Integer(moves).toString());
	}
	/**
	 * drawingPanel that draws the hangman depending on moves left.
	 */
	private class drawingPanel extends JPanel
	{
		public void paintComponent(Graphics g)
		{
			switch(moves)
			{
			case 0:g.drawLine(300, 200, 350, 250);;
			case 1:g.drawLine(300, 200, 250, 250);;
			case 2:g.drawLine(300, 125, 350, 150);;
			case 3:g.drawLine(300, 125, 250, 150);;
			case 4:g.drawLine(300, 100, 300, 200);;
			case 5:g.drawOval(275, 50, 50, 50);;
			case 6:g.drawLine(300,0, 300, 50);
			case 7:super.repaint();;
			}
		}
	}
}
