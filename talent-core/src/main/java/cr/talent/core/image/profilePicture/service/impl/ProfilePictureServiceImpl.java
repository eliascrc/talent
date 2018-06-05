package cr.talent.core.image.profilePicture.service.impl;

import cr.talent.core.image.awsImage.ImageDao;
import cr.talent.core.image.profilePicture.dao.DatabaseImageDao;
import cr.talent.core.image.profilePicture.service.ProfilePictureService;
import cr.talent.model.ProfilePicture;
import cr.talent.model.TechnicalResource;
import cr.talent.support.SecurityUtils;
import cr.talent.support.service.impl.CrudServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

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

    @Autowired
    ImageDao imageDao;

    @Autowired
    DatabaseImageDao databaseImageDao;

    public void init() {
        setCrudDao(this.databaseImageDao);
    }

    @Override
    public void uploadProfilePicture(String file){

        TechnicalResource technicalResource= (TechnicalResource) SecurityUtils.getLoggedInUser();

        ProfilePicture profilePicture = new ProfilePicture();
        this.create(profilePicture);

        profilePicture.setLink(link + profilePicture.getId() + ".jpg");
        technicalResource.setProfilePicture(profilePicture);
        this.imageDao.uploadImage(profilePicture.getId() + ".jpg", file);
    }

    @Override
    public void deleteProfilePicture(){
        TechnicalResource technicalResource= (TechnicalResource) SecurityUtils.getLoggedInUser();

        ProfilePicture profilePicture= technicalResource.getProfilePicture();
        this.remove(profilePicture);

        this.imageDao.deleteImage(profilePicture.getId() + ".jpg");
    }

    @Override
    public void getProfilePicture(String link){

       // ProfilePicture profilePicture= technicalResource.getProfilePicture();

       // this.imageDao.getImage(profilePicture.getId() + ".jpg");
    }

    @Override
    public void updateProfilePicture(String file){
        this.deleteProfilePicture();
        this.uploadProfilePicture(file);

        TechnicalResource technicalResource= (TechnicalResource) SecurityUtils.getLoggedInUser();
        ProfilePicture profilePicture= technicalResource.getProfilePicture();

        this.update(profilePicture);
    }

}
