package com.stargate.demo.web.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MaxUploadSizeExceededException;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.HandlerExceptionResolver;
import org.springframework.web.servlet.ModelAndView;

import com.stargate.demo.mail.EmailService;

//import net.sourceforge.tess4j.Tesseract;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import java.io.File;
import java.io.FileOutputStream;
import java.io.InputStream;
//import java.nio.file.Files;
//import java.nio.file.Path;
//import java.nio.file.Paths;
import java.util.Arrays;
import java.util.List;

@Controller
public class MultipartController implements HandlerExceptionResolver {

    @Autowired
    ServletContext context;
    @Autowired
    EmailService emailService;
    private static final List<String> contentTypes = Arrays.asList("image/png", "image/jpeg", "image/gif","application/pdf");
    
    @GetMapping(value = "/upload")
    public String uploadScreen() {
    	return "uploadFile";
    }
    @RequestMapping(value = "/upload", method = RequestMethod.POST)
    public ModelAndView FileuploadController(@RequestParam("file") MultipartFile file) {
        ModelAndView modelAndView = new ModelAndView("index");
        String fileContentType = file.getContentType();
	    
        if(contentTypes.contains(fileContentType)) {
	        try {
	            InputStream in = file.getInputStream();
	            String path = new File(".").getAbsolutePath();
	            FileOutputStream f = new FileOutputStream(path.substring(0, path.length() - 1) + "/uploads/" + file.getOriginalFilename());
	            try {
	                int ch;
	                while ((ch = in.read()) != -1) {
	                    f.write(ch);
	                }
	                modelAndView.getModel().put("message", "File uploaded successfully!");
	                emailService.sendSimpleMessage("gerwalkjt@gmail.com", "File Upload Successful", "File Name: "+file.getOriginalFilename()+ " File Size: "+file.getSize());
	            } catch (Exception e) {
	                System.out.println("Exception uploading multipart: " + e);
	                modelAndView.getModel().put("message", "Exception uploading multipart: " + e);
	            } finally {
	                f.flush();
	                f.close();
	                in.close();
	            }
            //**for converting image to text**
//        	  byte[] bytes = file.getBytes();
//            Path path = Paths.get("D:\\simpleocr\\src\\main\\resources\\static\\" + file.getOriginalFilename());
//            Files.write(path, bytes);
//
//            File convFile = convert(file);
//            Tesseract tesseract = new Tesseract();
//            tesseract.setDatapath("C:\\Users\\stargazer\\eclipse-workspace\\Tess4J\\tessdata");
//            tesseract.setLanguage("eng");
//            String text = tesseract.doOCR(convFile);
	        } catch (Exception e) {
	            System.out.println("Exception uploading multipart: " + e);
                modelAndView.getModel().put("message", "Exception uploading multipart: " + e);
	        }
        }else {
        	System.out.println("Invalid File Content Type, only accept: "+contentTypes.toString());
            modelAndView.getModel().put("message", "Invalid File Content Type, only accept: "+contentTypes.toString());
        }
        return modelAndView;
    }
    
    @Override
    public ModelAndView resolveException(HttpServletRequest request, HttpServletResponse response, Object object, Exception exc) {        
        ModelAndView modelAndView = new ModelAndView("uploadFile");
        if (exc instanceof MaxUploadSizeExceededException) {
            modelAndView.getModel().put("message", "File size exceeds limit!");
        }
        return modelAndView;
    }
}
