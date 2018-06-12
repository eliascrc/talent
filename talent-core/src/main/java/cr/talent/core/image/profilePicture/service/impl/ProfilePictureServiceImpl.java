package cr.talent.core.image.profilePicture.service.impl;

import cr.talent.core.image.dao.ImageDao;
import cr.talent.core.image.profilePicture.dao.ProfilePictureDao;
import cr.talent.core.image.profilePicture.service.ProfilePictureService;
import cr.talent.core.security.technicalResource.service.TechnicalResourceService;
import cr.talent.model.ProfilePicture;
import cr.talent.model.TechnicalResource;
import cr.talent.support.SecurityUtils;
import cr.talent.support.service.impl.CrudServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.io.InputStream;

/**
 * Default implementation of the {@link cr.talent.core.image.profilePicture.service.ProfilePictureService}.
 *
 * @author María José Cubero
 */
@Service("profilePictureService")
@Transactional
public class ProfilePictureServiceImpl extends CrudServiceImpl<ProfilePicture, String> implements ProfilePictureService{

    @Value("${talent.s3.image_basic_link}")
    private String link;

    private static final String FILE_EXTENSION = ".jpg";

    private static final String FOLDER = "/profile-pictures";

    @Autowired
    private ImageDao imageDao;

    @Autowired
    private ProfilePictureDao profilePictureDao;

    @Autowired
    private TechnicalResourceService technicalResourceService;

    public void init() {
        setCrudDao(this.profilePictureDao);
    }

    /**
     * @see ProfilePictureService#uploadProfilePicture(InputStream)
     */
    @Override
    public void uploadProfilePicture(InputStream file){

        TechnicalResource technicalResource= (TechnicalResource) SecurityUtils.getLoggedInUser();
        TechnicalResource technicalResource1= this.technicalResourceService.findById(technicalResource.getId());

        if (technicalResource1.getProfilePicture() != null)
            this.deleteProfilePicture();

        ProfilePicture profilePicture = new ProfilePicture();
        this.create(profilePicture);

        profilePicture.setLink(link + FOLDER + "/" + profilePicture.getId() + FILE_EXTENSION);
        technicalResource.setProfilePicture(profilePicture);
        technicalResource1.setProfilePicture(profilePicture);
        this.technicalResourceService.update(technicalResource1);

        this.imageDao.uploadImage(profilePicture.getId() + FILE_EXTENSION, file, FOLDER);
    }

    /**
     * @see ProfilePictureService#deleteProfilePicture()
     */
    @Override
    public void deleteProfilePicture(){
        TechnicalResource technicalResource= (TechnicalResource) SecurityUtils.getLoggedInUser();
        TechnicalResource technicalResource1 = this.technicalResourceService.findById(technicalResource.getId());
        ProfilePicture profilePicture= technicalResource1.getProfilePicture();

        if (profilePicture != null){
            technicalResource1.setProfilePicture(null);
            this.technicalResourceService.update(technicalResource1);
            technicalResource.setProfilePicture(null);

            this.remove(profilePicture);
            this.imageDao.deleteImage(profilePicture.getId() + FILE_EXTENSION, FOLDER);
        }
    }
}
