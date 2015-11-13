import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JScrollPane;

import java.awt.BorderLayout;

import javax.swing.JPanel;
import javax.swing.JLabel;
import javax.swing.JButton;
import javax.swing.JTextPane;

import java.awt.FlowLayout;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.ArrayList;


public class Window {
	private JTextPane textPane;
	private JFrame frame;
	private Controller controller;
	private JLabel label;
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Window window = new Window();
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
	public Window() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.setTitle("\u798F\u5F69\u6570\u636E\u8FC7\u6EE4\u5668");
		frame.setBounds(100, 100, 600, 400);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		controller=new Controller();
		controller.setWindow(this);
		JPanel panel = new JPanel();
		frame.getContentPane().add(panel, BorderLayout.SOUTH);
		panel.setLayout(new FlowLayout(FlowLayout.LEFT, 5, 5));
		
		JButton button = new JButton("\u7B5B\u9664\u5BF9\u5B50\u53F7");
		panel.add(button);
		button.addMouseListener(new MouseListener()
		{

			@Override
			public void mouseClicked(MouseEvent e) 
			{
				String[] toAnalyze=textPane.getText().split("\n");
				if(toAnalyze.length>0)
				{
				loop:
				{
				for(int i=0;i<toAnalyze.length;i++)
				{
					if(!controller.isLegid(toAnalyze[i]))
					{
						Window.this.notLegid();
						break loop;
					}
					else
					{
						if(controller.isCombination(toAnalyze[i]))
						{
							toAnalyze[i]="";
						}
					}
				}
				String result="";
				for(int i=0;i<toAnalyze.length;i++)
				{
					if(!toAnalyze[i].equals(""))
					{
						result+=toAnalyze[i];
						result+="\n";
					}
				}
				textPane.setText(result);
				label.setText("");
				}
				}
			}

			@Override
			public void mouseEntered(MouseEvent e) {
				// TODO Auto-generated method stub
				
			}

			@Override
			public void mouseExited(MouseEvent e) {
				// TODO Auto-generated method stub
				
			}

			@Override
			public void mousePressed(MouseEvent e) {
				// TODO Auto-generated method stub
				
			}

			@Override
			public void mouseReleased(MouseEvent e) {
				// TODO Auto-generated method stub
				
			}
			
		});
		
		label = new JLabel("");
		panel.add(label);
		
		JScrollPane scrollPane = new JScrollPane();
		frame.getContentPane().add(scrollPane, BorderLayout.CENTER);
		
		textPane = new JTextPane();
		scrollPane.setViewportView(textPane);
	}

	private void notLegid()
	{
		label.setText("一定是你输入的方式不对！");
	}
}
