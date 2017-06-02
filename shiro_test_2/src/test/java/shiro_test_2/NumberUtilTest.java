package shiro_test_2;

import java.math.BigInteger;
import java.security.MessageDigest;

import org.junit.Test;

import com.zml.shiro_test.util.NumberUtil;

public class NumberUtilTest {
	@Test
	public void test(){
//		String numToChinese = NumberUtil.NumToChinese(123.22D);
//		System.out.println(numToChinese);
		Double d = 1001D;
		d = d/100;
		System.out.println(d.toString());
	}
	@Test
	public void test2(){
		System.out.println(getMD5("123"));
	}
	private static String getMD5(String str) {
		try {
			MessageDigest md = MessageDigest.getInstance("MD5");
			md.update(str.getBytes());
			return new BigInteger(1, md.digest()).toString(16);
		} catch (Exception e) {
		};
		return str;
	}
}
