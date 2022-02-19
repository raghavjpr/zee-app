package com.zee.zee5pp.utils;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;

import org.springframework.stereotype.Component;

@Component
public class FileUtils {

	public byte[] readFile(File file) {
		FileInputStream fileInputStream = null;
		try {
			fileInputStream = new FileInputStream(file);
			byte[] allBytes = new byte[(int) file.length()];
			fileInputStream.read(allBytes);
			fileInputStream.close();
			return allBytes;
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return null;
	}

	public String writeFile(byte[] allBytes, String fileName) {
		return fileName;
		// FileOutputStream
		// return success
		// return fail
	}

}
