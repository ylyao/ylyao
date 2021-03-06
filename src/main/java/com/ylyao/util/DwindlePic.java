package com.ylyao.util;

import java.io.*;
import com.sun.image.codec.jpeg.*;
import java.awt.image.*;
import java.awt.*;
import java.applet.*;
//缩略图类，
//本java类能将jpg图片文件，进行等比或非等比的大小转换。
//具体使用方法
//s_pic(大图片路径,生成小图片路径,大图片文件名,生成小图片文名,生成小图片宽度,生成小图片高度,是否等比缩放(默认为true))

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

public class DwindlePic {

	public  Log log = LogFactory.getLog(getClass());

	String InputDir; // 输入图路径
	String OutputDir; // 输出图路径
	String InputFileName; // 输入图文件名
	String OutputFileName; // 输出图文件名
	int OutputWidth = 80; // 默认输出图片宽
	int OutputHeight = 80; // 默认输出图片高
	int rate = 0;
	boolean proportion = true; // 是否等比缩放标记(默认为等比缩放)

	public DwindlePic() {
		// 初始化变量
		InputDir = "";
		OutputDir = "";
		InputFileName = "";
		OutputFileName = "";
		OutputWidth = 80;
		OutputHeight = 80;
		rate = 0;
	}

	@SuppressWarnings("restriction")
	public boolean s_pic() {
		Runtime rt = Runtime.getRuntime();
		log.info("1="+rt.freeMemory());
		// BufferedImage image;
		// String NewFileName;
		// 建立输出文件对象
		File file = new File(OutputDir);
		log.info("file="+rt.freeMemory());

		FileOutputStream tempout = null;
		try {
			tempout = new FileOutputStream(file);

		log.info("tempout="+rt.freeMemory());
		} catch (Exception ex) {
			System.out.println(ex.toString());
		}
		Image img = null;
		Toolkit tk = Toolkit.getDefaultToolkit();
		Applet app = new Applet();
		MediaTracker mt = new MediaTracker(app);
		try {
			img = tk.getImage(InputDir);
			log.info("img="+rt.freeMemory());

			tk = null;
			mt.addImage(img, 0);
			mt.waitForID(0);
			log.info("mt="+rt.freeMemory());

		} catch (Exception e) {
			e.printStackTrace();
		}
		if (img.getWidth(null) == -1) {
			return false;
		} else {
			int new_w;
			int new_h;
			if (this.proportion == true) { // 判断是否是等比缩放.
			// 为等比缩放计算输出的图片宽度及高度
				double rate1 = ((double) img.getWidth(null))
						/ (double) OutputWidth + 0.1;
				double rate2 = ((double) img.getHeight(null))
						/ (double) OutputHeight + 0.1;
				double rate = rate1 > rate2 ? rate1 : rate2;
				new_w = (int) (((double) img.getWidth(null)) / rate);
				new_h = (int) (((double) img.getHeight(null)) / rate);
			} else {
				new_w = OutputWidth; // 输出的图片宽度
				new_h = OutputHeight; // 输出的图片高度
			}
			BufferedImage buffImg = new BufferedImage(new_w, new_h,
					BufferedImage.TYPE_INT_RGB);

			Graphics g = buffImg.createGraphics();

			g.setColor(Color.white);
			g.fillRect(0, 0, new_w, new_h);

			g.drawImage(img, 0, 0, new_w, new_h, null);
			g.dispose();
			log.info("g="+rt.freeMemory());

			img = null;
			JPEGImageEncoder encoder = JPEGCodec.createJPEGEncoder(tempout);
			try {
				encoder.encode(buffImg);
				tempout.close();
				tempout = null;
			} catch (IOException ex) {
				System.out.println(ex.toString());
			}
			File  delFile = new File(InputDir);
			log.info("delFile="+rt.freeMemory());

			if (delFile.isFile() && delFile.exists()) {  
				delFile.delete();
			}
			encoder = null;
			buffImg = null;
			buffImg = null;
			mt = null;
		}
		return true;
	}

	public boolean s_pic(String InputDir, String OutputDir,
			String InputFileName, String OutputFileName) {
		// 输入图路径
		this.InputDir = InputDir;
		// 输出图路径
		this.OutputDir = OutputDir;
		// 输入图文件名
		this.InputFileName = InputFileName;
		// 输出图文件名
		this.OutputFileName = OutputFileName;
		return s_pic();
	}

	public boolean s_pic(String InputDir, String OutputDir,
			String InputFileName, String OutputFileName, int width, int height,
			boolean gp) {
		// 输入图路径
		this.InputDir = InputDir;
		// 输出图路径
		this.OutputDir = OutputDir;
		// 输入图文件名
		this.InputFileName = InputFileName;
		// 输出图文件名
		this.OutputFileName = OutputFileName;
		// 设置图片长宽
		setW_H(width, height);
		// 是否是等比缩放 标记
		this.proportion = gp;
		return s_pic();
	}

	public void setInputDir(String InputDir) {
		this.InputDir = InputDir;
	}

	public void setOutputDir(String OutputDir) {
		this.OutputDir = OutputDir;
	}

	public void setInputFileName(String InputFileName) {
		this.InputFileName = InputFileName;
	}

	public void setOutputFileName(String OutputFileName) {
		this.OutputFileName = OutputFileName;
	}

	public void setOutputWidth(int OutputWidth) {
		this.OutputWidth = OutputWidth;
	}

	public void setOutputHeight(int OutputHeight) {
		this.OutputHeight = OutputHeight;
	}

	public void setW_H(int width, int height) {
		this.OutputWidth = width;
		this.OutputHeight = height;
	}

	public static void main(String[] a) {
		// s_pic(大图片路径,生成小图片路径,大图片文件名,生成小图片文名,生成小图片宽度,生成小图片高度)
		DwindlePic mypic = new DwindlePic();
		System.out.println(mypic.s_pic("c:/", "d:/", "aa.bmp", "wwww.jpg", 120,
				80, true));
	}
}