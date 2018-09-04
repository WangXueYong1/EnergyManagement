package util;
import java.net.*;
import java.io.*;

public class GreetingClient {
	public static void main(String[] args) {
		if (args.length==0) {
			args = new String[2];
			args[0] = "192.168.142.254";
			args[1] = "6066";
		}
		String serverName = args[0];
		int port = Integer.parseInt(args[1]);
		try {
			System.out.println("连接到主机：" + serverName + " ，端口号：" + port);
			Socket client = new Socket(serverName, port);
			System.out.println("远程主机地址：" + client.getRemoteSocketAddress());
			OutputStream outToServer = client.getOutputStream();
			DataOutputStream out = new DataOutputStream(outToServer);

			out.writeUTF("Hello from " + client.getLocalSocketAddress());
			InputStream inFromServer = client.getInputStream();
			DataInputStream in = new DataInputStream(inFromServer);
			System.out.println("服务器响应： " + in.readUTF());
			client.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}