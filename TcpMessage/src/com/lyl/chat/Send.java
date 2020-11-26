package com.lyl.chat;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.Socket;


public class Send implements Runnable{
	private Socket client;
	private BufferedReader console;
	private DataOutputStream dos;
	
	public Send(Socket client ,String name) throws IOException {
		// TODO Auto-generated constructor stub
		this.client = client;
		console = new BufferedReader(new InputStreamReader(System.in));
		dos = new DataOutputStream(this.client.getOutputStream());
		dos.writeUTF(name);
	}
	
	private void send() {
		try {
			String msg = console.readLine();
			dos.writeUTF(msg);
			dos.flush();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			release();
			e.printStackTrace();
		}
	}
	
	private void release() {
		ReleaseUtils.close(dos,client);
	}
	
	@Override
	public void run() {
		// TODO Auto-generated method stub
		while (true) {
			send();
		}
		
	}
	
}
