package com.cr.home.controller;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStream;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;

import org.apache.commons.io.IOUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.HandlerMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class ResourceController {

	@Autowired
	protected ServletContext servletContext;
	
	@RequestMapping(value="/getHome", method=RequestMethod.GET)
	public String getHome(){
		
		System.out.println("Controller here");
		return "home";
	}
	
	@RequestMapping(value = "/css/**", method = RequestMethod.GET)
	public ResponseEntity<String> getCssFile(HttpServletRequest request) {
		String prefix = servletContext.getRealPath("/");
		String fileName = (String) request.getAttribute(HandlerMapping.PATH_WITHIN_HANDLER_MAPPING_ATTRIBUTE);
		final String type = "css";
		String filePath = ResourceController.getPath(prefix, type, fileName);
		String fileString = ResourceController.fileToString(filePath);
		
		if (fileString.indexOf("File is not existed") != -1) {
			return new ResponseEntity<String>(null, null, HttpStatus.NOT_FOUND);
		}
		
		return serveResponseIfModified(request, filePath, fileString, "text/css");
	}

	private ResponseEntity<String> serveResponseIfModified(HttpServletRequest request, String filePath, String fileString, String contentMIME){
		HttpHeaders responseHeaders = new HttpHeaders();
		responseHeaders.add("Content-Type", contentMIME+"; charset=utf-8");
		responseHeaders.add("Cache-Control", "max-age=604800");	// 1 week
		long modifiedSinceValue = 0;
		long fileLastModifiedDate = getFileLastModifiedDate(filePath);
		String modifiedSinceHeader = request.getHeader("If-Modified-Since");
		if(modifiedSinceHeader != null){
			modifiedSinceValue = Long.valueOf(modifiedSinceHeader);
			if(fileLastModifiedDate<=modifiedSinceValue) {
				responseHeaders.add("Content-Length", "0"); // content-length header make sure it's zero for a 304 response else Safari will hang for about 30 seconds waiting for you to finish the response
				return new ResponseEntity<String>(null, responseHeaders, HttpStatus.NOT_MODIFIED);
			}
		}
		responseHeaders.add("Last-Modified", String.valueOf(fileLastModifiedDate)); // Firefox will only send conditional GET requests (i.e. include an if-modified-since) if you've previously set a last-modified date on the response
		return new ResponseEntity<String>(fileString, responseHeaders, HttpStatus.OK);
	} 
	@RequestMapping(value = "/js/**", method = RequestMethod.GET)
	public ResponseEntity<String> getJsFile(HttpServletRequest request) {
		String prefix = servletContext.getRealPath("/");
		String fileName = (String) request
				.getAttribute(HandlerMapping.PATH_WITHIN_HANDLER_MAPPING_ATTRIBUTE);
		final String type = "js";
		String filePath = ResourceController.getPath(prefix, type, fileName);
		String fileString = ResourceController.fileToString(filePath);
		
		if (fileString.indexOf("File is not existed") != -1) {
			return new ResponseEntity<String>(null, null,
					HttpStatus.NOT_FOUND);
		}

		return serveResponseIfModified(request, filePath, fileString, "application/javascript");
	}

	@RequestMapping("/img/**")
	public ResponseEntity<byte[]> getImgFile(HttpServletRequest request)
			throws IOException {
		String prefix = servletContext.getRealPath("/");
		String fileName = (String) request.getAttribute(HandlerMapping.PATH_WITHIN_HANDLER_MAPPING_ATTRIBUTE);
		String type = "img";

		String filePath = ResourceController.getPath(prefix, type, fileName);

		HttpHeaders headers = new HttpHeaders();
		headers.add("Cache-Control", "max-age=604800");	// 1 week
		long ifModifiedSince = 0;
		long fileLastModifiedDate = getFileLastModifiedDate(filePath);
		
		// Set contentType base on the file extension
		String lowerCaseFileName = fileName.toLowerCase();
		if (lowerCaseFileName.indexOf("png") != -1) {
			headers.setContentType(MediaType.IMAGE_PNG);
		} else if (lowerCaseFileName.indexOf("jpg") != -1) {
			headers.setContentType(MediaType.IMAGE_JPEG);
		} else if (lowerCaseFileName.indexOf("gif") != -1) {
			headers.setContentType(MediaType.IMAGE_GIF);
		}
		
		InputStream is;
		try {
			String modifiedSince = request.getHeader("If-Modified-Since");
			if(modifiedSince == null){
				is = new FileInputStream(filePath);
				byte[] imgByte = IOUtils.toByteArray(is);
				is.close();
				headers.add("Last-Modified", String.valueOf(fileLastModifiedDate));		// Firefox will only send conditional GET requests (i.e. include an if-modified-since) if you've previously set a last-modified date on the response
				return new ResponseEntity<byte[]>(imgByte, headers, HttpStatus.OK);
			}
			
			ifModifiedSince = Long.valueOf(modifiedSince);
			if(fileLastModifiedDate > ifModifiedSince){
				is = new FileInputStream(filePath);
				byte[] imgByte = IOUtils.toByteArray(is);
				is.close();
				headers.add("Last-Modified", String.valueOf(fileLastModifiedDate));		// Firefox will only send conditional GET requests (i.e. include an if-modified-since) if you've previously set a last-modified date on the response
				return new ResponseEntity<byte[]>(imgByte, headers, HttpStatus.OK);
			}else{
				headers.add("Content-Length", "0");		// content-length header make sure it's zero for a 304 response else Safari will hang for about 30 seconds waiting for you to finish the response
				return new ResponseEntity<byte[]>(null, headers, HttpStatus.NOT_MODIFIED);
			}
		} catch (IOException e) {
			return new ResponseEntity<byte[]>(null, headers,
					HttpStatus.NOT_FOUND);
		}
	}

	private static String getPath(String prefix, String type, String path) {
		if (path.substring(0, 1).equalsIgnoreCase("/")) {
			return prefix + "/WEB-INF/view" + path;
		} else {
			return prefix + "/WEB-INF/view/" + type + "/" + path;
		}

	}
	protected static long getFileLastModifiedDate(String filePath){
		File file = new File(filePath);
		return file.lastModified();	// returns milliseconds in local time
	}
	private static String fileToString(String path) {
		File fl = new File(path);
		try {
			FileReader fr = new FileReader(fl);
			BufferedReader br = new BufferedReader(fr);
			StringBuilder text = new StringBuilder();
			String NL = System.getProperty("line.separator");
			String temp;
			while ((temp = br.readLine()) != null) {
				text.append(temp);
				text.append(NL);
			}
			return text.toString();
		} catch (IOException e) {
			return "File is not existed";
		}
	}
	
	private ModelAndView getModelAndView(HttpServletRequest request) {
		String path = (String) request.getAttribute(HandlerMapping.PATH_WITHIN_HANDLER_MAPPING_ATTRIBUTE);
		ModelAndView modelAndView = new ModelAndView(path);
		modelAndView.addObject("pathhandler", path);
		return modelAndView;
	}

	@RequestMapping(value = "/partials/**", method = RequestMethod.GET)
	public ModelAndView getPartial(Model m, HttpServletRequest request) {
		return getModelAndView(request);
	}
	
}
