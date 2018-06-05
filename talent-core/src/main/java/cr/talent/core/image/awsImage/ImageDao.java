package cr.talent.core.image.awsImage;

/**
 * Data access object for all the {@link cr.talent.model.Image} in aws related operations.
 *
 * @author María José Cubero
 */
public interface ImageDao {

    /**
     * Upload or updates an image in the S3 bucket.
     * key: This is the full path to the file. file: The actual file containing the data to be uploaded.
     * @param key
     * @param file
     */
    void uploadImage (String key, String file);

    /**
     * delete image from the bucket.
     * @param key
     */
    void deleteImage (String key);

    /**
     * Get an image from the bucket.
     * @param key
     */
    void getImage (String key);

}
