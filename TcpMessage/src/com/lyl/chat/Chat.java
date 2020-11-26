package com.lyl.chat;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.concurrent.CopyOnWriteArrayList;

public class Chat {
	private static CopyOnWriteArrayList<Channel> all = new CopyOnWriteArrayList<Channel>();
	
	public static void main(String[] args) throws IOException {
		System.out.println("---Server---");
		ServerSocket server = new ServerSocket(8888);
		while(true) {
			Socket client = server.accept();
			Channel c = new Channel(client);
			all.add(c);
		    new Thread(c).start();
		}
	}
	
	static class Channel implements Runnable{
		private Socket client;
		private DataInputStream dis;
		private DataOutputStream dos;
		private String name;
		
		public Channel(Socket client) throws IOException {
			super();
			this.client = client;
			dis = new DataInputStream(this.client.getInputStream());
			dos = new DataOutputStream(this.client.getOutputStream());
			this.name = receive();
		}
		
		private void sendOthers(String string) {
			for (Channel others : all) {
				if (this == others) {
					continue;
				}
				others.send(this.name+string);
			}
		}
		
		private void send(String string) {
			try {
				dos.writeUTF(string);
			} catch (IOException e) {
				// TODO Auto-generated catch block
				release();
				e.printStackTrace();
			}
		}
		
		private String receive() {
			String string = "";
			try {
				string = dis.readUTF();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				release();
				e.printStackTrace();
			}
			return string;
		}
		
		private void release() {
			ReleaseUtils.close(dis,dos,client);
		}

		@Override
		public void run() {
			// TODO Auto-generated method stub
			while (true) {
				String dataString = receive();
				sendOthers(dataString);
			}
		}
	}
}


