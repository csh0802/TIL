package package1.chat.server;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.ConnectException;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.ArrayList;
import java.util.zip.InflaterOutputStream;

public class ChatServer {
	ArrayList<DataOutputStream> list = new ArrayList<>();

	public void broadcast(String msg) {//데이터 전송을 위한 메소드
		synchronized (list) {
			for (DataOutputStream out : list) {        //for each
				try {

						out.writeUTF(msg);

				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
	}

	public void serverReady() {
		try {
			ServerSocket ss = new ServerSocket(9999);
			System.out.println("server ready");
			while (true) {
				Socket s = ss.accept();
				DataInputStream din = new DataInputStream(s.getInputStream());
				DataOutputStream dout = new DataOutputStream(s.getOutputStream());
				list.add(dout);
				ServerThread t = new ServerThread(s, din, dout);
				t.start();
			}
		} catch (UnknownHostException | ConnectException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public static void main(String[] args) {

		new ChatServer().serverReady();
	}

	class ServerThread extends Thread {
		Socket s;
		DataInputStream din;
		DataOutputStream dout;
		String chatID;

		public ServerThread(Socket s, DataInputStream din, DataOutputStream dout) {
			this.s = s;
			this.din = din;
			this.dout = dout;
		}

		@Override
		public void run() {
			try {
				while(true) {
					String receiveMSG = din.readUTF();
					if (chatID == null) {
						chatID = receiveMSG;
					}else {
						broadcast(receiveMSG);
					}
				}
			} catch (IOException e) {
				System.out.println(chatID + "client 퇴실");
			}
				synchronized (dout) {
					list.remove(dout);
				}

			}
	}
}