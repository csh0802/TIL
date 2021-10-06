package package1.chat.client;

import heritage.exam02.People;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.*;
import java.net.ConnectException;
import java.net.Socket;
import java.net.UnknownHostException;
import java.io.DataOutputStream;
import java.util.zip.InflaterOutputStream;

public class ChatClient {

	String chatID;
	Socket s;
	DataInputStream din;
	DataOutputStream dout;
	TextArea ta;

	public void createUI() {
		Frame f = new Frame();
		ta = new TextArea();
		TextField tf = new TextField(15);
		TextField tf2 = new TextField(10);
		Button b = new Button("연결");
		Panel p = new Panel();

		f.add(ta);
		f.add(p, BorderLayout.SOUTH);
		p.add(tf);
		p.add(tf2);
		p.add(b);

		tf.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				try {
					dout.writeUTF(chatID + tf.getText());

				} catch (IOException ex) {
					ex.printStackTrace();
				}
				tf.setText("");
			}
		});
		b.addActionListener(new ActionListener() { //버튼 누르면 서버에 연결
			@Override
			public void actionPerformed(ActionEvent e) {
				//채팅연결
				chatID = tf2.getText();
				f.setTitle(chatID);

				try {
					s = new Socket("localhost", 9999);
					dout = new DataOutputStream(s.getOutputStream());
					din = new DataInputStream(s.getInputStream());
					ClientThread t = new ClientThread();
					t.start();
					tf2.setBackground(Color.RED);
					tf2.setEditable(false);
					b.setEnabled(false);
					chatID = "[" + chatID + "]";
					dout.writeUTF(chatID);
					ta.append(chatID + "님이 입장\n");
				}catch (UnknownHostException ex){
					ex.printStackTrace();
				}catch (IOException ex) {
					ex.printStackTrace();
				}
			}
		});
		f.addWindowListener(new WindowAdapter() { //x누르면 종료
			@Override
			public void windowClosing(WindowEvent e) {
				System.exit(0);
			}
		});

		f.setBounds(500, 150, 400, 500); //창 위치와 크기 한번에
		f.setVisible(true);
	}

	public static void main(String[] args) {
		new ChatClient().createUI();

	}
	class ClientThread extends Thread{
		@Override
		public void run(){
			while(true){			//서버에서는 클라이언트 통신 끊기면 프로그램 종료해야해서 while로 try만묶었지만
									//여기서는 읽을게 없어도 통신은 계속되어야 하므로 try catch 전체를 while로 묶는다.
				try {
					ta.append(din.readUTF()+"\n");
				} catch (IOException e) {
					e.printStackTrace();
				}

			}
		}
	}
}





//try{
//			Socket s =new Socket("localhost",9999);
//			DataOutputStream dout =new DataOutputStream(s.getOutputStream());
//			DataInputStream din = new DataInputStream(s.getInputStream());
//
//			dout.writeUTF("Hello");
//			String fromServerMSG = din.readUTF();
//			System.out.println("연결완료");
//			System.out.println("서버에서 온 메세지 : "+fromServerMSG);
//		}catch(ConnectException e){
//			e.printStackTrace();
//		}catch(IOException e){
//			e.printStackTrace();
//		}catch (Exception e){
//			e.printStackTrace();
//		}