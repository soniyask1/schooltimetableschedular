package com.org.schedular.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.org.schedular.services.FileUploadService;

//Annotation
@Controller
//Class
public class DemoController {
	@Autowired FileUploadService csvUploadService;
	@RequestMapping("/hello")
    @ResponseBody
 
    // Method
    public String helloWorld()
    {
 
		String str = csvUploadService.printString("Soniya");
        // Print statement
        return "Hello World!" + " Good Morning " + str + " !!!";
    }
}
