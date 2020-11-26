package com.lyl.chat;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.Socket;
import java.net.UnknownHostException;


public class UserClient {
	public static void main(String[] args) throws UnknownHostException, IOException {
		System.out.println("---Client---");
		System.out.println("---请输入用户名---");
		BufferedReader console = new BufferedReader(new InputStreamReader(System.in));
		String name = console.readLine()+"说：";
		Socket client = new Socket("localhost", 8888);
		
		new Thread(new Send(client,name)).start();
		new Thread(new Receive(client)).start();
	}
}
