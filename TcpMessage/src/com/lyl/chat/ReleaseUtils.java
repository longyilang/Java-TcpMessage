package com.lyl.chat;

import java.io.Closeable;

public class ReleaseUtils {
	public static void close(Closeable... targets) {
		for(Closeable target:targets) {
			try {
				if (null != target) {
					target.close();
				}
			} catch (Exception e) {
				// TODO: handle exception
			}
		}
	}
}
