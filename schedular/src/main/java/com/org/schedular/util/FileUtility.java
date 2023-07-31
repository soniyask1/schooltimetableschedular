package com.org.schedular.util;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

public class FileUtility {
	
	/**
	 * 
	 * @param SAVE_DIR
	 * @param appPath
	 * @return
	 */
	public static String createDir(final String SAVE_DIR, String appPath) {
		String savePath = appPath + File.separator + SAVE_DIR;
		if (appPath == null || "".equals(appPath)) {
			savePath = SAVE_DIR;
		}
		File fileSaveDir = new File(savePath);
		if (!fileSaveDir.exists()) {
			fileSaveDir.mkdir();
		}
		return savePath;
	}
	
	/**
	 * 
	 * @param inputStream
	 * @param fileName
	 * @throws IOException
	 */
	public static void writeToFileServer(InputStream inputStream,
			String fileName) throws IOException {

		OutputStream outputStream = null;
		try {
			outputStream = new FileOutputStream(new File(fileName));
			int read = 0;
			byte[] bytes = new byte[1024];
			while ((read = inputStream.read(bytes)) != -1) {
				outputStream.write(bytes, 0, read);
			}
			// closed already
			// inputStream.close();
			outputStream.flush();
			inputStream.close();
			outputStream.close();

		} catch (IOException ioException) {
			System.out.println("Exception occured while writing file on server..."
					+ ioException.getMessage());
		}
	}
}
