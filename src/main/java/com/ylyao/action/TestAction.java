package com.ylyao.action;

import java.io.File;
import java.io.IOException;
import java.util.Hashtable;

import com.google.zxing.BarcodeFormat;
import com.google.zxing.EncodeHintType;
import com.google.zxing.MultiFormatWriter;
import com.google.zxing.WriterException;
import com.google.zxing.common.BitMatrix;
import com.ylyao.service.TestService;

public class TestAction {

	private TestService testService;

	public static void main(String[] args) throws Exception {

		String text = "www.baidu.com";
		int width = 100;
		int height = 100;
		// 二维码的图片格式
		String format = "gif";
		String filePath = "d:\\new.gif";
		File file = new File(filePath);
		if (file.exists()){
			return ;
		}
		
		Hashtable hints = new Hashtable();
		// 内容所使用编码
		hints.put(EncodeHintType.CHARACTER_SET, "utf-8");
		BitMatrix bitMatrix;
		try {
			bitMatrix = new MultiFormatWriter().encode(text,
					BarcodeFormat.QR_CODE, width, height, hints);
			// 生成二维码
			File outputFile = new File(filePath);
			MatrixToImageWriter.writeToFile(bitMatrix, format, outputFile);
		} catch (WriterException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	public void test() {
		testService.test();
	}

	public TestService getTestService() {
		return testService;
	}

	public void setTestService(TestService testService) {
		this.testService = testService;
	}

}
