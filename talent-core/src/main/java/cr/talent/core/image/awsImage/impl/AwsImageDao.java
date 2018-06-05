package cr.talent.core.image.awsImage.impl;

import com.amazonaws.services.s3.model.GetObjectRequest;
import com.amazonaws.services.s3.AmazonS3;
import cr.talent.core.image.awsImage.ImageDao;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Repository;
import com.amazonaws.services.s3.model.PutObjectRequest;

import java.io.File;

/**
 * AWS implementation of the {@link cr.talent.core.image.awsImage.ImageDao}.
 *
 * @author María José Cubero
 */
@Repository("imageDao")
public class AwsImageDao implements ImageDao{

    @Autowired
    private AmazonS3 s3Client;

    @Value("${talent.s3.bucket}")
    private String bucketName;

    public AwsImageDao(){
    }

    private Logger logger = LoggerFactory.getLogger(AwsImageDao.class);

    @Override
    public void uploadImage(String key, String filePath) {
       File file = new File(filePath);
        s3Client.putObject(new PutObjectRequest(bucketName, key, file));
    }

    @Override
    public void deleteImage(String key) {
        s3Client.deleteObject(bucketName,key);
    }

    @Override
    public void getImage(String key) {
        s3Client.getObject(new GetObjectRequest(bucketName, key));
    }

    public AmazonS3 getS3Client() {
        return s3Client;
    }

    public void setS3Client(AmazonS3 s3Client) {
        this.s3Client = s3Client;
    }


}
