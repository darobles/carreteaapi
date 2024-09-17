package cl.carretea.api.controller;

import java.util.List;
import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.amazonaws.auth.AWSCredentials;
import com.amazonaws.auth.BasicAWSCredentials;
import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.AmazonS3Client;
import com.amazonaws.services.s3.model.ListObjectsV2Request;
import com.amazonaws.services.s3.model.ListObjectsV2Result;
import com.amazonaws.services.s3.model.S3ObjectSummary;

import cl.carretea.api.model.ImageServiceModel;
import jakarta.annotation.PostConstruct;

@CrossOrigin(origins = "*", allowedHeaders = "*")
@RestController
@RequestMapping("/api/content")
public class ContentController {
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
	
	@GetMapping("/banner")
	public List<ImageServiceModel> getCards(){
		//this.s3client = new AmazonS3Client(credentials);
		ListObjectsV2Request request = new ListObjectsV2Request().withBucketName(bucketName).withPrefix("banner/");;
		ListObjectsV2Result  result = this.s3client.listObjectsV2(request);
		List<ImageServiceModel> list = new ArrayList<>();
	    for (S3ObjectSummary objectSummary : result.getObjectSummaries()) {
	    	ImageServiceModel img = new ImageServiceModel();
	    	img.setAlt(objectSummary.getKey().replace("banner/", ""));
	    	img.setItemImageSrc("https://s3.amazonaws.com/"+bucketName+ "/" +objectSummary.getKey());
	    	img.setTittle(objectSummary.getKey().replace("banner/", ""));
	    	if(!img.getTittle().equals(""))
	    		list.add(img);
	    }
		return list;
	}

}
