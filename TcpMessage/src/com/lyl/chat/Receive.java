package com.lyl.chat;

import java.io.DataInputStream;
import java.io.IOException;
import java.net.Socket;

public class Receive implements Runnable{
	private Socket client;
	private DataInputStream dis;

	public Receive(Socket client) throws IOException {
		super();
		this.client = client;
		dis = new DataInputStream(this.client.getInputStream());
	}
	
	private void release() {
		ReleaseUtils.close(dis,client);
	}
	
	@Override
	public void run() {
		// TODO Auto-generated method stub
		while (true) {
			String string = "";
			try {
				string = dis.readUTF();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				release();
				e.printStackTrace();
			}
			//System.out.println(string);
		}
		
	}
}
