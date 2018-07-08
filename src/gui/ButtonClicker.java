package gui;

import java.awt.GridLayout;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextArea;

import networking.Client;
import networking.Server;

public class ButtonClicker extends JFrame {
	JPanel panel = new JPanel();
	JButton button = new JButton("Send");
	JTextArea area = new JTextArea(1, 25);
	JLabel messages = new JLabel();
	
	Server server;
	Client client;
	
	public static void main(String[] args) {
		new ButtonClicker();
	}
	
	public ButtonClicker(){
		
		int response = JOptionPane.showConfirmDialog(null, "Would you like to host a connection?", "Buttons!", JOptionPane.YES_NO_OPTION);
		if(response == JOptionPane.YES_OPTION){
			String s = JOptionPane.showInputDialog("What is your name?");
			server = new Server(8080, s);
			setTitle("SERVER");
			JOptionPane.showMessageDialog(null, "Server started at: " + server.getIPAddress() + "\nPort: " + server.getPort());
			button.addActionListener((e)->{
				server.sendClick(area, messages);
			});
			panel.setLayout(new GridLayout(3,1));
			panel.add(messages);
			panel.add(area);
			panel.add(button);
			add(panel);
			setVisible(true);
			setSize(400, 300);
			setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
			server.start(messages);
			
		}else{
			setTitle("CLIENT");
			String ipStr = JOptionPane.showInputDialog("Enter the IP Address");
			String prtStr = JOptionPane.showInputDialog("Enter the port number");
			String s = JOptionPane.showInputDialog("What is your name?");
			int port = Integer.parseInt(prtStr);
			client = new Client(ipStr, port, s);
			button.addActionListener((e)->{
				client.sendClick(area, messages);
			});
			panel.setLayout(new GridLayout(3,1));
			panel.add(messages);
			panel.add(area);
			panel.add(button);
			add(panel);
			setVisible(true);
			setSize(400, 300);
			setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
			client.start(messages);
		}
	}
}
