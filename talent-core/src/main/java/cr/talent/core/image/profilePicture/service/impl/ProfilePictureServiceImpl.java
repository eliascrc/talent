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
    private TechnicalResourceService technicalResourceService;

    @Autowired
    private ProfilePictureDao profilePictureDao;

    public void init() {
        setCrudDao(this.profilePictureDao);
    }

    @Override
    public void uploadProfilePicture(InputStream file){

        TechnicalResource technicalResource= (TechnicalResource) SecurityUtils.getLoggedInUser();

        if (technicalResource.getProfilePicture() != null)
            this.deleteProfilePicture();

        ProfilePicture profilePicture = new ProfilePicture();
        this.create(profilePicture);

        profilePicture.setLink(link + profilePicture.getId() + FILE_EXTENSION);
        technicalResource.setProfilePicture(profilePicture);
        this.technicalResourceService.update(technicalResource);

        this.imageDao.uploadImage(profilePicture.getId() + FILE_EXTENSION, file, FOLDER);
    }

    @Override
    public void deleteProfilePicture(){
        TechnicalResource technicalResource= (TechnicalResource) SecurityUtils.getLoggedInUser();

        ProfilePicture profilePicture= technicalResource.getProfilePicture();
        this.remove(profilePicture);

        this.imageDao.deleteImage(profilePicture.getId() + FILE_EXTENSION, FOLDER);
    }

    @Override
    public void updateProfilePicture(InputStream file){
        this.deleteProfilePicture();
        this.uploadProfilePicture(file);

        TechnicalResource technicalResource= (TechnicalResource) SecurityUtils.getLoggedInUser();
        ProfilePicture profilePicture= technicalResource.getProfilePicture();

        this.update(profilePicture);
    }
}
