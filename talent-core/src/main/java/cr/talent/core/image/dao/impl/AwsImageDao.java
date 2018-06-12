package cr.talent.core.image.dao.impl;

import com.amazonaws.services.s3.model.CannedAccessControlList;
import com.amazonaws.services.s3.model.GetObjectRequest;
import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.model.ObjectMetadata;
import cr.talent.core.image.dao.ImageDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Repository;
import com.amazonaws.services.s3.model.PutObjectRequest;

import java.io.File;
import java.io.InputStream;

/**
 * AWS implementation of the {@link cr.talent.core.image.dao.ImageDao}.
 *
 * @author María José Cubero
 */
@Repository("imageDao")
public class AwsImageDao implements ImageDao{

    private static String CONTENT_TYPE = "image/jpeg";

    @Autowired
    private AmazonS3 s3Client;

    @Value("${talent.s3.bucket}")
    private String bucketName;

    public AwsImageDao(){
    }

    @Override
    public void uploadImage(String key, InputStream file, String folder) {
        ObjectMetadata metadata = new ObjectMetadata();
        metadata.setContentType(CONTENT_TYPE);
        metadata.addUserMetadata("x-amz-meta-title", "metadata");

        s3Client.putObject(new PutObjectRequest(bucketName + folder, key, file, metadata).withCannedAcl(CannedAccessControlList.PublicRead));
    }

    @Override
    public void deleteImage(String key, String folder) {
        s3Client.deleteObject(bucketName + folder,key);
    }

    @Override
    public void getImage(String key, String folder) {
        s3Client.getObject(new GetObjectRequest(bucketName + folder, key));
    }

    public AmazonS3 getS3Client() {
        return s3Client;
    }

    public void setS3Client(AmazonS3 s3Client) {
        this.s3Client = s3Client;
    }

}
