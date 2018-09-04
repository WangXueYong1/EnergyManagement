package util;
import java.net.*;
import java.io.*;

public class GreetingServer extends Thread {
	private ServerSocket serverSocket;

	public GreetingServer(int port) throws IOException {
		serverSocket = new ServerSocket(port);
		serverSocket.setSoTimeout(1000000);
	}

	public void run() {
		while (true) {
			try {
				System.out.println("等待远程连接，端口号为：" + serverSocket.getLocalPort() + "...");
				Socket server = serverSocket.accept();
				System.out.println("远程主机地址：" + server.getRemoteSocketAddress());
				DataInputStream in = new DataInputStream(server.getInputStream());
				System.out.println(in.readUTF());
				DataOutputStream out = new DataOutputStream(server.getOutputStream());
				out.writeUTF("谢谢连接我：" + server.getLocalSocketAddress() + "\nGoodbye!");
				server.close();
			} catch (SocketTimeoutException s) {
				System.out.println("Socket timed out!");
				break;
			} catch (IOException e) {
				e.printStackTrace();
				break;
			}
		}
	}

	/*public static void main(String[] args) {
		if (args.length==0) {
			args = new String[1];
			args[0] = "6066";
		}
		int port = Integer.parseInt(args[0]);
		try {
			Thread t = new GreetingServer(port);
			t.run();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}*/
}