package com.zml.shiro_test.util;

import java.io.IOException;
/**
 * 序列化工具
 * @Description: 
 * @author: 朱美炉
 * @date: 2017年6月2日
 * @version: V1.0
 * @类全名：com.zml.shiro_test.util.SerializeUtil
 */
public class SerializeUtil {

	public static byte[] serialize(Object object) {
		if (object != null) {
			java.io.ObjectOutputStream oos = null;
			java.io.ByteArrayOutputStream baos = null;
			try {
				baos = new java.io.ByteArrayOutputStream();
				oos = new java.io.ObjectOutputStream(baos);
				oos.writeObject(object);
				byte[] bytes = baos.toByteArray();
				return bytes;
			} catch (Exception e) {
				e.printStackTrace();
			} finally {
				try {
					oos.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
		return null;
	}

	public static Object unserialize(byte[] bytes) {
		if (bytes != null) {
			java.io.ByteArrayInputStream bais = null;
			java.io.ObjectInputStream ois = null;
			try {
				bais = new java.io.ByteArrayInputStream(bytes);
				ois = new java.io.ObjectInputStream(bais);
				return ois.readObject();
			} catch (Exception e) {
				e.printStackTrace();
			} finally {
				try {
					ois.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
		return null;
	}
}
