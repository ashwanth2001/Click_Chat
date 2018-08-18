package networking;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.net.UnknownHostException;

import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextArea;

public class Client {
	String name;
	
	private String ip;
	private int port;

	Socket connection;

	ObjectOutputStream os;
	ObjectInputStream is;

	public Client(String ip, int port, String name) {
		this.ip = ip;
		this.port = port;
		this.name = name;
	}

	public void start(JLabel label){
		try {

			connection = new Socket(ip, port);

			os = new ObjectOutputStream(connection.getOutputStream());
			is = new ObjectInputStream(connection.getInputStream());

			os.flush();

			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		while (connection.isConnected()) {
			try {
				label.setText((String)is.readObject());
			} catch (Exception e) {
				// TODO Auto-generated catch block
				System.out.println("Disconnected from server");
				break;
			}
		}
	}
	
	public void sendClick(JTextArea area, JLabel label) {
		String s = "<html>" + "<br> " + name + ": " + area.getText() + "<html>";
		label.setText(label.getText() + s);
		area.setText("");
		try {
			if (os != null) {
				os.flush();
				os.writeObject(label.getText());
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
