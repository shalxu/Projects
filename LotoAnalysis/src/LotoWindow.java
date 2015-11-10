import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;

import java.awt.BorderLayout;

import javax.swing.JSplitPane;

import java.awt.GridLayout;
import java.awt.FlowLayout;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JScrollPane;
import javax.swing.JTextField;
import javax.swing.JTextArea;
import javax.swing.ScrollPaneConstants;
import javax.swing.JTextPane;
import javax.swing.border.BevelBorder;

/**
 * 
 * This program is to analyze lottery winning numbers using existing algorithms.
 * 
 * LotoWindow Class
 * @author Shal Xu
 * August 2014
 *
 */
public class LotoWindow {

	private JFrame frame;
	private JTextField addEntry;
	private JTextField rangeEntry;
	private LotoController controller;
	private JLabel addDate;
	private JTextPane leftText;
	private JTextPane rightText;
	private ArrayList<String> selectedResults;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					LotoWindow window = new LotoWindow();
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public LotoWindow() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		controller=new LotoController();
		controller.initialize();
		selectedResults=new ArrayList<String>();
		frame = new JFrame();
		frame.setTitle("\u798F\u5F69\u5206\u6790\u5668");
		frame.setBounds(20, 20, 800, 400);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		JPanel addPanel = new JPanel();
		addPanel.setBorder(new BevelBorder(BevelBorder.LOWERED, null, null, null, null));
		frame.getContentPane().add(addPanel, BorderLayout.SOUTH);
		addPanel.setLayout(new FlowLayout(FlowLayout.RIGHT, 5, 5));
		
		addDate = new JLabel("\u6DFB\u52A0\u51FA\u5956\u53F7\uFF1A");
		int newYear=controller.currentLoto.getYear();
		int newDay=controller.currentLoto.getDay();
		if(newDay!=359)
		{
			newDay++;
		}
		else
		{
			newDay=0;
			newYear=newYear++;
		}
		String addTextTemp="";	
		addTextTemp+="添加出奖号";
		addTextTemp+=newYear;
		int tempInt=3-new Integer(newDay).toString().length();
		for(int i=0;i<tempInt;i++)
		{
			addTextTemp+="0";
		}
	
		addTextTemp+=newDay;
		addDate.setText(addTextTemp);
		addPanel.add(addDate);
		
		addEntry = new JTextField();
		addPanel.add(addEntry);
		addEntry.setColumns(10);
		
		JButton addButton = new JButton("\u786E\u8BA4");
		addButton.addMouseListener(new MouseListener()
		{
			@Override
			public void mouseClicked(MouseEvent e) 
			{
				if(addEntry.getText().length()==3&&
						Character.isDigit(addEntry.getText().charAt(0))&&
						Character.isDigit(addEntry.getText().charAt(1))&&
							Character.isDigit(addEntry.getText().charAt(2)))
				{
					int newYear=controller.currentLoto.getYear();
					int newDay=controller.currentLoto.getDay();
					if(newDay!=359)
					{
						newDay++;
					}
					else
					{
						newDay=0;
						newYear=newYear++;
					}
					try {
						controller.addNumber(newYear,newDay,addEntry.getText());
					} catch (IOException e1) {
						LotoWindow.this.throwErrorMessgae("文件写入错误");
					}
					if(newDay!=359)
					{
						newDay++;
					}
					else
					{
						newDay=0;
						newYear=newYear++;
					}
					String addTextTemp="";	
					addTextTemp+="添加出奖号";
					addTextTemp+=newYear;
					int tempInt=3-new Integer(newDay).toString().length();
					for(int i=0;i<tempInt;i++)
					{
						addTextTemp+="0";
					}
				
					addTextTemp+=newDay;
					addDate.setText(addTextTemp);
					addEntry.setText("");
				}
				else
				{
					rightText.setText("一定是你输入的方式不对!");
					addEntry.setText("");
				}
			}
			@Override
			public void mouseEntered(MouseEvent e) {
			}
			@Override
			public void mouseExited(MouseEvent e) {
			}
			@Override
			public void mousePressed(MouseEvent e) {
			}
			@Override
			public void mouseReleased(MouseEvent e) {
			}
		});
		addPanel.add(addButton);
		
		JPanel mainPanel = new JPanel();
		frame.getContentPane().add(mainPanel, BorderLayout.CENTER);
		mainPanel.setLayout(new GridLayout(0, 2, 0, 0));
		
		JPanel leftPanel = new JPanel();
		mainPanel.add(leftPanel);
		leftPanel.setLayout(new BorderLayout(0, 0));
		
		JPanel leftButtonPanel = new JPanel();
		leftPanel.add(leftButtonPanel, BorderLayout.SOUTH);
		leftButtonPanel.setLayout(new GridLayout(0, 4, 0, 0));
		
		JButton button1 = new JButton("\u76F4\u9009\u672A\u51FA\u53F7");
		button1.addMouseListener(new MouseListener()
		{
			@Override
			public void mouseClicked(MouseEvent arg0) 
			{
				selectedResults.clear();
				ArrayList<LotoResult> results=controller.filterNumber(new Integer(rangeEntry.getText()));
				Collections.sort(results);
				String result="未出号      本期遗漏     上期遗漏     平均遗漏\n";
				for(LotoResult l:results)
				{
					result+=l;
					result+="\n";
					selectedResults.add(l.number);
				}
				result+="\n共"+results.size()+"个结果";
				leftText.setText(result);
			}
			@Override
			public void mouseEntered(MouseEvent arg0) {
			}
			@Override
			public void mouseExited(MouseEvent arg0) {
			}
			@Override
			public void mousePressed(MouseEvent arg0) {
			}
			@Override
			public void mouseReleased(MouseEvent arg0) {
			}
		});
		leftButtonPanel.add(button1);
		
		JButton button2 = new JButton("\u767E\u5341\u672A\u51FA\u53F7");
		button2.addMouseListener(new MouseListener()
		{
			@Override
			public void mouseClicked(MouseEvent arg0) 
			{
				selectedResults.clear();
				ArrayList<LotoResult> results=controller.filterReCombination(new Integer(rangeEntry.getText()));
				Collections.sort(results);
				String result="未出号      本期遗漏     上期遗漏     平均遗漏\n";
				for(LotoResult l:results)
				{
					result+=l;
					result+="\n";
					selectedResults.add(l.number);
				}
				result+="\n共"+results.size()+"个结果";
				leftText.setText(result);
			}
			@Override
			public void mouseEntered(MouseEvent arg0) {
			}
			@Override
			public void mouseExited(MouseEvent arg0) {
			}
			@Override
			public void mousePressed(MouseEvent arg0) {
			}
			@Override
			public void mouseReleased(MouseEvent arg0) {
			}
		});
		leftButtonPanel.add(button2);
		
		JButton button3 = new JButton("\u7EC4\u5408\u672A\u51FA\u53F7");
		button3.addMouseListener(new MouseListener()
		{
			@Override
			public void mouseClicked(MouseEvent arg0) 
			{
				selectedResults.clear();
				ArrayList<LotoResult> results=controller.filterCombination(new Integer(rangeEntry.getText()));
				Collections.sort(results);
				String result="未出号      本期遗漏     上期遗漏     平均遗漏\n";
				for(LotoResult l:results)
				{
					result+=l;
					result+="\n";
					selectedResults.add(l.number);
				}
				result+="\n共"+results.size()+"个结果";
				leftText.setText(result);	
			}
			@Override
			public void mouseEntered(MouseEvent arg0) {
			}
			@Override
			public void mouseExited(MouseEvent arg0) {
			}
			@Override
			public void mousePressed(MouseEvent arg0) {
			}
			@Override
			public void mouseReleased(MouseEvent arg0) {
			}
		});
		leftButtonPanel.add(button3);
		
		JButton button4 = new JButton("\u4E09\u8054\u53F7");
		button4.addMouseListener(new MouseListener()
		{
			@Override
			public void mouseClicked(MouseEvent arg0) 
			{
				selectedResults.clear();
				ArrayList<LotoResult> results=controller.filterConstants();
				Collections.sort(results);
				String result="未出号      本期遗漏     上期遗漏     平均遗漏\n";
				for(LotoResult l:results)
				{
					result+=l;
					result+="\n";
					selectedResults.add(l.number);
				}
				result+="\n共"+results.size()+"个结果";
				leftText.setText(result);	
			}
			@Override
			public void mouseEntered(MouseEvent arg0) {
			}
			@Override
			public void mouseExited(MouseEvent arg0) {
			}
			@Override
			public void mousePressed(MouseEvent arg0) {
			}
			@Override
			public void mouseReleased(MouseEvent arg0) {
			}
		});
		leftButtonPanel.add(button4);
		
		JLabel rangeText = new JLabel("\u7B5B\u9009\u8303\u56F4");
		leftButtonPanel.add(rangeText);
		
		rangeEntry = new JTextField();
		rangeEntry.setText("1000");
		rangeEntry.addKeyListener(new KeyEventListener());
				
		leftButtonPanel.add(rangeEntry);
		rangeEntry.setColumns(10);
		
		JLabel lblNewLabel_1 = new JLabel("");
		leftButtonPanel.add(lblNewLabel_1);
		
		JScrollPane leftTextPane = new JScrollPane();
		leftPanel.add(leftTextPane, BorderLayout.CENTER);
		
		leftText = new JTextPane();
		leftTextPane.setViewportView(leftText);
		
		JPanel rightPanel = new JPanel();
		mainPanel.add(rightPanel);
		rightPanel.setLayout(new BorderLayout(0, 0));
		
		JPanel RightButtonPanel = new JPanel();
		rightPanel.add(RightButtonPanel, BorderLayout.SOUTH);
		RightButtonPanel.setLayout(new FlowLayout(FlowLayout.CENTER, 5, 5));
		
		JButton toNumberButton = new JButton("\u7EC4\u53D8\u5355");
		toNumberButton.addMouseListener(new MouseListener()
		{
			@Override
			public void mouseClicked(MouseEvent arg0) {
				ArrayList<String> results=new ArrayList<String>();
				for(String s:selectedResults)
				{
					for(String c:controller.generateNumber(s))
					{
						if(!results.contains(c))
							results.add(c);
					}
				}
				selectedResults=results;
				String resultString="";
				for(String s:selectedResults)
				{
					resultString+=s;
					resultString+="\n";
				}
				rightText.setText(resultString);
			}
			@Override
			public void mouseEntered(MouseEvent arg0) {
				// TODO Auto-generated method stub	
			}
			@Override
			public void mouseExited(MouseEvent arg0) {
				// TODO Auto-generated method stub
			}
			@Override
			public void mousePressed(MouseEvent arg0) {
				// TODO Auto-generated method stub	
			}
			@Override
			public void mouseReleased(MouseEvent arg0) {
				// TODO Auto-generated method stub
			}
		});
		
		JButton toListButton = new JButton("\u53F7\u7801\u5BFC\u51FA");
		toListButton.addMouseListener(new MouseListener()
		{
			@Override
			public void mouseClicked(MouseEvent arg0) {
				ArrayList<String> results=new ArrayList<String>();
				for(String s:selectedResults)
				{
					if(!results.contains(s))
					results.add(s);
				}
				selectedResults=results;
				String resultString="";
				for(String s:selectedResults)
				{
					resultString+=s;
					resultString+="\n";
				}
				rightText.setText(resultString);
			}
			@Override
			public void mouseEntered(MouseEvent arg0) {
				// TODO Auto-generated method stub	
			}
			@Override
			public void mouseExited(MouseEvent arg0) {
				// TODO Auto-generated method stub
			}
			@Override
			public void mousePressed(MouseEvent arg0) {
				// TODO Auto-generated method stub	
			}
			@Override
			public void mouseReleased(MouseEvent arg0) {
				// TODO Auto-generated method stub
			}
		});
		RightButtonPanel.add(toListButton);
		
		RightButtonPanel.add(toNumberButton);
		
		JButton toCombinationButton = new JButton("\u5355\u53D8\u7EC4");
		toCombinationButton.addMouseListener(new MouseListener()
		{
			@Override
			public void mouseClicked(MouseEvent arg0) {
				ArrayList<String> results=new ArrayList<String>();
				for(String s:selectedResults)
				{
					if(!results.contains(controller.generateCombination(s)))
					results.add(controller.generateCombination(s));
				}
				selectedResults=results;
				String resultString="";
				for(String s:selectedResults)
				{
					resultString+=s;
					resultString+="\n";
				}
				rightText.setText(resultString);
			}
			@Override
			public void mouseEntered(MouseEvent arg0) {
				// TODO Auto-generated method stub	
			}
			@Override
			public void mouseExited(MouseEvent arg0) {
				// TODO Auto-generated method stub
			}
			@Override
			public void mousePressed(MouseEvent arg0) {
				// TODO Auto-generated method stub	
			}
			@Override
			public void mouseReleased(MouseEvent arg0) {
				// TODO Auto-generated method stub
			}
		});
		RightButtonPanel.add(toCombinationButton);
		
		JScrollPane rightTextPane = new JScrollPane();
		rightTextPane.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
		rightPanel.add(rightTextPane, BorderLayout.CENTER);
		
		rightText = new JTextPane();
		rightTextPane.setViewportView(rightText);
	}
	private class KeyEventListener implements KeyListener
	{
		public void keyPressed(KeyEvent arg0) {
			if(arg0.getKeyCode()==KeyEvent.VK_ENTER)
			{
				int newYear=controller.currentLoto.getYear();
				int newDay=controller.currentLoto.getDay();
				if(newDay!=359)
				{
					newDay++;
				}
				else
				{
					newDay=0;
					newYear=newYear++;
				}
				try {
					controller.addNumber(newYear,newDay,addEntry.getText());
				} catch (Exception e1) {
					LotoWindow.this.throwErrorMessgae("文件加载错误");
				}
				if(newDay!=359)
				{
					newDay++;
				}
				else
				{
					newDay=0;
					newYear=newYear++;
				}
				String addTextTemp="";	
				addTextTemp+="添加出奖号";
				addTextTemp+=newYear;
				int tempInt=3-new Integer(newDay).toString().length();
				for(int i=0;i<tempInt;i++)
				{
					addTextTemp+="0";
				}
			
				addTextTemp+=newDay;
				addDate.setText(addTextTemp);
				addEntry.setText("");
			}
		}
		@Override
		public void keyReleased(KeyEvent arg0) {
		}
		@Override
		public void keyTyped(KeyEvent arg0) {
		}	
	}
	public void throwErrorMessgae(String error)
	{
		rightText.setText(error);
	}

}
