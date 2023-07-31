/**
 * 
 */
package com.org.schedular.services;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;

import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.org.schedular.response.ServiceResponse;
import com.org.schedular.util.FileUtility;

import jakarta.servlet.http.HttpServletRequest;

/**
 * 
 */
@Service
public class FileUploadService {
	public String printString(String str) {
		System.out.println("printing string: " + str);
		return str;
	}

	public ServiceResponse<String, String> saveFilesOnTheServer(MultipartFile file, HttpServletRequest request) {
		String appPath = request.getServletContext().getRealPath("");
		final String SAVE_DIR = "datafiles";
		String savePath = FileUtility.createDir(SAVE_DIR, appPath);
		ServiceResponse<String, String> response = new ServiceResponse<String, String>();
		try {
			// get filename to be uploaded
			String fileName = file.getOriginalFilename();
			String filePath = savePath + File.separator + fileName;
			System.out.println("full file path is  " + filePath
					+ " and file name is " + fileName);
			if (null != filePath && !"".equalsIgnoreCase(filePath)) {
				// write & upload file to server
				InputStream inputStream = file.getInputStream();
				FileUtility.writeToFileServer(inputStream, filePath);
				response.setCode("FILE_UPLOAD_SUCCESSFUL");
				response.setResult("File uploaded successfully");
				response.setStatus(true);			
			} else {
				response.setCode("FILE_NOT_FOUND");
				response.setResult("File not found");
				response.setStatus(false);
			}
		} catch (IOException ioException) {
			System.out.println("Exception occured ..." + ioException.getMessage());
			response.setCode("ERROR");
			response.setResult("Failed to uppload file. Please try again later.");
			response.setStatus(false);
		}
		return response;
	}
}
