package cl.carretea.api.service;

import java.io.File;
import java.util.Date;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.amazonaws.auth.AWSCredentials;
import com.amazonaws.auth.BasicAWSCredentials;
import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.AmazonS3Client;
import com.amazonaws.services.s3.model.CannedAccessControlList;
import com.amazonaws.services.s3.model.DeleteObjectRequest;
import com.amazonaws.services.s3.model.PutObjectRequest;
import com.amazonaws.services.s3.model.PutObjectResult;

import jakarta.annotation.PostConstruct;




@Service
public class AmazonClient {
    private AmazonS3 s3client;
    @Value("${amazonProperties.endpointUrl}")
    private String endpointUrl;    
    @Value("${amazonProperties.bucketName}")
    private String bucketName;    
    @Value("${amazonProperties.accessKey}")
    private String accessKey;    
    @Value("${amazonProperties.secretKey}")
    private String secretKey;
    @PostConstruct
    private void initializeAmazon() {
       AWSCredentials credentials = new BasicAWSCredentials(this.accessKey, this.secretKey);
       
       this.s3client = new AmazonS3Client(credentials);
       
    }
    
    
    
    
    public PutObjectResult uploadFileTos3bucket(String fileName, File file) {
	     return s3client.putObject(new PutObjectRequest(bucketName, "equipos/"+fileName, file)
	            .withCannedAcl(CannedAccessControlList.PublicRead));
	}
    
    public PutObjectResult uploadAyttentionFileTos3bucket(String fileName, File file) {
	    return s3client.putObject(new PutObjectRequest(bucketName, "cheklists/"+fileName, file)
	            .withCannedAcl(CannedAccessControlList.PublicRead));
	}
    
    public void deleteFileToS3Bucket( String url, String find) {
    	String textToFind = find;
        int startIndex = url.indexOf(textToFind);

        // Verificar si se encuentra el texto
        if (startIndex != -1) {
            // Extraer el texto /checklist
        	 String extractedText = url.substring(startIndex);
            s3client.deleteObject(new DeleteObjectRequest(bucketName, extractedText));
            System.out.println("Texto extraído: " + extractedText);
        } else {
            System.out.println("Texto no encontrado en la URL.");
        }
    	 
    }
    
    private String generateFileName(MultipartFile multiPart) {
        return new Date().getTime() + "-" + multiPart.getOriginalFilename().replace(" ", "_");
    }
    

}