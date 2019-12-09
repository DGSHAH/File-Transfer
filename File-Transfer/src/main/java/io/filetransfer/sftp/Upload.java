package io.filetransfer.sftp;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.time.LocalDateTime;

import org.springframework.context.annotation.Bean;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.integration.sftp.session.DefaultSftpSessionFactory;
import org.springframework.integration.sftp.session.SftpSession;

@RestController
@RequestMapping
@CrossOrigin
public class Upload {

	 private DefaultSftpSessionFactory gimmeFactory(){
	        DefaultSftpSessionFactory factory = new DefaultSftpSessionFactory();
	        factory.setHost("0.0.0.0");
	        factory.setPort(22);
	        factory.setAllowUnknownKeys(true);
	        factory.setUser("ansible");
	        factory.setPassword("ansible");
	        return factory;
	    }
	 
	 @Bean
	 public WebMvcConfigurer corsConfigurer() {
		 return new WebMvcConfigurer() {
			 public void addCorsMapping(CorsRegistry registry) {
				registry.addMapping("/").allowedOrigins("*"); 
			 }
			 
		 };
	 }
	 
	 @RequestMapping(value="/upload", method=RequestMethod.POST, consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
	 public ResponseEntity<Object> upload(@RequestParam(required=true, value="file") MultipartFile file) throws IOException{
		 	
		 	File convertFile = new File("C:\\Users\\dipsh\\eclipse-workspace\\File-Transfer\\src\\main\\resources"+file.getOriginalFilename());
		 	convertFile.createNewFile();
		 	FileOutputStream fout = new FileOutputStream(convertFile);
		 	fout.write(file.getBytes());
		 	fout.close();
		 	
	      /*  SftpSession session = gimmeFactory().getSession();
	        InputStream resourceAsStream =
	                Upload.class.getClassLoader().getResourceAsStream(file.getOriginalFilename());
	        try {
	            session.write(resourceAsStream, "upload/mynewfile" + LocalDateTime.now() +".txt");
	        } catch (IOException e) {
	            throw new RuntimeException(e);
	        }
	        session.close();
	        */
	        return new ResponseEntity<> ("File is uploaded successfully", HttpStatus.OK);
	    }
}
