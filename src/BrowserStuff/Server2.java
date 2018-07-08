package BrowserStuff;

import java.io.BufferedReader;
import java.io.EOFException;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.PrintWriter;
import java.net.InetAddress;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.UnknownHostException;

import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextArea;

public class Server2 {
	
	private int port;

	private ServerSocket server;
	private Socket connection;

	public Server2(int port) {
		this.port = port;
	}

	public void start(){
		try {
			server = new ServerSocket(port, 100);
			System.out.println(1);
			connection = server.accept();
			System.out.println(2);
			BufferedReader in = new BufferedReader(new InputStreamReader(connection.getInputStream()));
			PrintWriter out = new PrintWriter(connection.getOutputStream());

			out.print("GET/HTTP/1.1 200 \r\n");
			out.print("Content-Type: text/plain \r\n");
			out.print("Connection:close \r\n");
			out.print("\r\n");
			out.print("<button> click me </button>");
			
			System.out.println(3);
			
			in.close();
			out.close();

		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public String getIPAddress() {
		try {
			return InetAddress.getLocalHost().getHostAddress();
		} catch (UnknownHostException e) {
			return "ERROR!!!!!";
		}
	}

	public int getPort() {
		return port;
	}
	
	public static void main(String[] args) {
		Server2 server = new Server2(8080);
		server.start();
	}
}
