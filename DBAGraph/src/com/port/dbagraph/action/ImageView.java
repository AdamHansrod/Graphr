package com.port.dbagraph.action;

import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Map;

import javax.imageio.ImageIO;
import javax.servlet.ServletContext;

import com.opensymphony.xwork2.ActionSupport;

public class ImageView extends ActionSupport {

	private static final long serialVersionUID = 1L;
	private ByteArrayInputStream inputStream;
	private final String filepath = "//opt//tomcat7//data//";
	private String Filename;

	public String execute() throws IOException {
		File sourceimage = new File(filepath + Filename);
		byte[] cardImageBytes = getBytesFromFile(sourceimage);
		inputStream = new ByteArrayInputStream(cardImageBytes);
		return SUCCESS;
	}

	public static byte[] getBytesFromFile(File file) throws IOException {
		InputStream is = new FileInputStream(file);
		long length = file.length();
		if (length > Integer.MAX_VALUE) {
			throw new IOException("File is too large");
		}
		byte[] bytes = new byte[(int)length];
		int offset = 0;
		int numRead = 0;
		while (offset < bytes.length && (numRead=is.read(bytes, offset, bytes.length-offset)) >= 0) {
			offset += numRead;
		}
		if (offset < bytes.length) {
			throw new IOException("Could not completely read file "+file.getName());
		}
		is.close();
		return bytes;
	}

	public String getFilename() {
		return Filename;
	}

	public void setFilename(String filename) {
		Filename = filename;
	}

	public ByteArrayInputStream getInputStream() {
		return inputStream;
	}

	public void setInputStream(ByteArrayInputStream inputStream) {
		this.inputStream = inputStream;
	}
	
	
}
