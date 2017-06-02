package com.zml.shiro_test.util;


public class NumberUtil {
	public static String[]	ChineseNum	= { "零", "壹", "贰", "叁", "肆", "伍", "陆", "柒", "捌", "玖" };
	public static String NumToChinese(double num)
	{
		if ((num > 99999999999999.984D) || (num < -99999999999999.984D))
			throw new IllegalArgumentException("参数值超出允许范围 (-99999999999999.99 ～ 99999999999999.99)！");
		boolean negative = false;
		if (num < 0.0D) {
			negative = true;
			num *= -1.0D;
		}
		long temp = Math.round(num * 100.0D);
		int numFen = (int) (temp % 10L);
		temp /= 10L;
		int numJiao = (int) (temp % 10L);
		temp /= 10L;
		int[] parts = new int[20];
		int numParts = 0;
		int numi = 0;
		while (temp != 0L)
		{
			int part = (int) (temp % 10000L);
			parts[numi] = part;
			temp /= 10000L;
			numParts++;
			numi++;
		}
		boolean beforeWanIsZero = true;
		String chineseStr = "";
		for (int i = 0; i < numParts; i++) {
			String partChinese = partConvert(parts[i]);
			if (i % 2 == 0) {
				if ("".equals(partChinese))
					beforeWanIsZero = true;
				else
					beforeWanIsZero = false;
			}
			if (i != 0) {
				if (i % 2 == 0) {
					chineseStr = "亿" + chineseStr;
				}
				else if (("".equals(partChinese)) && (!beforeWanIsZero)) {
					chineseStr = "零" + chineseStr;
				} else {
					if ((parts[(i - 1)] < 1000) && (parts[(i - 1)] > 0))
						chineseStr = "零" + chineseStr;
					chineseStr = "万" + chineseStr;
				}
			}
			chineseStr = partChinese + chineseStr;
		}
		if ("".equals(chineseStr))
			chineseStr = ChineseNum[0];
		else if (negative)
			chineseStr = "负" + chineseStr;
		chineseStr = chineseStr + "元";
		if ((numFen == 0) && (numJiao == 0)) {
			chineseStr = chineseStr + "整";
		}
		else if (numFen == 0) {
			chineseStr = chineseStr + ChineseNum[numJiao] + "角";
		}
		else if (numJiao == 0)
			chineseStr = chineseStr + "零" + ChineseNum[numFen] + "分";
		else {
			chineseStr = chineseStr + ChineseNum[numJiao] + "角" + ChineseNum[numFen] + "分";
		}
		return chineseStr;
	}

	public static String partConvert(int partNum)
	{
		if ((partNum < 0) || (partNum > 10000)) {
			throw new IllegalArgumentException("参数必须是大于等于0或小于10000的整数");
		}
		String[] units = { "", "拾", "佰", "仟" };
		int temp = partNum;
		String partResult = new Integer(partNum).toString();
		int partResultLength = partResult.length();
		boolean lastIsZero = true;
		String chineseStr = "";
		for (int i = 0; (i < partResultLength) &&
				(temp != 0); i++)
		{
			int digit = temp % 10;
			if (digit == 0) {
				if (!lastIsZero)
					chineseStr = "零" + chineseStr;
				lastIsZero = true;
			}
			else {
				chineseStr = ChineseNum[digit] + units[i] + chineseStr;
				lastIsZero = false;
			}
			temp /= 10;
		}
		return chineseStr;
	}
}
