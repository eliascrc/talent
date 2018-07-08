package cr.talent.support.flexjson;

import cr.talent.model.*;
import flexjson.JSONSerializer;
import flexjson.transformer.BooleanTransformer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.lang.reflect.Field;
import java.util.*;

/**
 * Builder class that holds several static factory methods for creating JSONSerializers with different include & exclude
 * rules
 */
public class JSONSerializerBuilder {

    private static final Logger logger = LoggerFactory.getLogger(JSONSerializerBuilder.class);

    private static final List<String> GLOBAL_INCLUDES = new LinkedList<>();

    static {
        GLOBAL_INCLUDES.add("id");
        GLOBAL_INCLUDES.add("entityCreationTimestamp");
        GLOBAL_INCLUDES.add("lastUpdatedTimestamp");
        GLOBAL_INCLUDES.add("entityVersion");
    }

    /**
     * Returns a list of paths to be excluded when creating a JSON serializer.
     * This list should be used for all JSON serializers created for the
     * application.
     *
     * @return a list of exclude paths to be used when creating a JSON
     * serializer for the app.
     */
    private static List<String> getGlobalExcludes() {
        List<String> excludes = new LinkedList<>();
        excludes.add("*.class");
        excludes.add("*.handler");
        excludes.add("*.hibernateLazyInitializer");
        return excludes;
    }

    /**
     * Gets a list of exclude expressions.
     *
     * @param clazz         the class to be inspected to exclude all fields but the ones sent in the include list.
     * @param attributeName the attribute name to be used as prefix in the exclude expressions.
     * @param include       the list of fields to include.
     */
    @SuppressWarnings("rawtypes")
    private static List<String> getExcludesForObject(Class clazz, String attributeName, List<String> include) {
        String prefix = "";

        if (attributeName != null && !attributeName.equals("")) {
            prefix = attributeName + ".";
        }
        List<String> excludes = new LinkedList<>();

        List<Field> fields = getInheritedPrivateFields(clazz);
        for (Field field : fields) {
            String fieldName = field.getName();

            if (!GLOBAL_INCLUDES.contains(fieldName) && (include == null || !include.contains(fieldName))) {
                excludes.add(prefix + fieldName);
            }
        }

        return excludes;
    }

    /**
     * Returns a list of the field methods for a given class, including the
     * inherited fields.
     *
     * @param type class type that will be introspected
     * @return a list of all the fields in the class hierarchy.
     */
    private static List<Field> getInheritedPrivateFields(Class<?> type) {
        List<Field> result = new ArrayList<>();

        Class<?> i = type;
        while (i != null && i != Object.class) {
            result.addAll(Arrays.asList(i.getDeclaredFields()));
            i = i.getSuperclass();
        }

        return result;
    }

    /**
     * Basic factory method that returns a JSONSerializer that realizes a shallow
     * serialization of objects excluding the objects' class names. Some attributes
     * from {@link cr.talent.model.BasicEntity} are also excluded.
     *
     * @return an instance of JSONSerializer that does shallow serialization excluding classnames
     */
    public static JSONSerializer getBasicSerializer() {
        JSONSerializer serializer = new JSONSerializer();

        serializer.setExcludes(getGlobalExcludes());

        logger.trace("Basic Serializer {} created", serializer.toString());
        return serializer;
    }

    /**
     * Creates a basic serializer that returns the unique identifier, name and logo of an organization
     *
     * @return
     */
    public static JSONSerializer getOrganizationSerializer() {
        JSONSerializer serializer = getBasicSerializer(); // core serializer which excludes classnames
        List<String> excludes = new LinkedList<>(); // list which will store all excluded attributes
        List<String> includes = new LinkedList<>(); // list which will store all included attributes

        excludes.addAll(getGlobalExcludes()); // adds all the basic excludes

        includes.add("uniqueIdentifier");
        includes.add("name");
        includes.add("logo");
        includes.add("twoStepVerification");

        // adds all attributes of the Organization class as excludes except those in the includes list
        excludes.addAll(JSONSerializerBuilder.getExcludesForObject(Organization.class, "", includes));

        // sets the added excludes to the serializer
        serializer.setExcludes(excludes);

        serializer.transform(new ImageTransformer(), "logo");

        // logs the creation of the serializer
        logger.trace("Organization Serializer {} created", serializer.toString());

        return serializer;
    }

    /*
     * Gets a JSONSerializer to use in order to obtain the JSON of a User's information, for the /ws/user/authenticated
     * web service, which includes the user's login token.
     *
     * @return the JSONSerializer to be used to serialize User.
     */
    public static JSONSerializer getTechnicalResourceAuthenticationSerializer() {
        JSONSerializer serializer = getBasicSerializer();
        List<String> excludes = new LinkedList<>();
        List<String> tempIncludes = new LinkedList<>();

        excludes.addAll(getGlobalExcludes());

        tempIncludes = new LinkedList<>();
        tempIncludes.add("link");

        excludes.addAll(JSONSerializerBuilder.getExcludesForObject(Image.class, "profilePicture", tempIncludes));

        tempIncludes = new LinkedList<>();
        tempIncludes.add("uniqueIdentifier");

        excludes.addAll(JSONSerializerBuilder.getExcludesForObject(Organization.class, "organization", tempIncludes));

        tempIncludes = new LinkedList<>();
        tempIncludes.add("nickname");
        tempIncludes.add("firstName");
        tempIncludes.add("lastName");
        tempIncludes.add("username");
        tempIncludes.add("isAdministrator");
        tempIncludes.add("token");

        excludes.addAll(JSONSerializerBuilder.getExcludesForObject(TechnicalResource.class, "", tempIncludes));

        serializer.setExcludes(excludes);

        serializer.transform(new BooleanTransformer(), "isAdministrator");

        // logs the creation of the serializer
        logger.trace("TermsOfService Serializer {} created", serializer.toString());
        return serializer;
    }

    /*
     * Gets a JSONSerializer to use in order to obtain the JSON of a User's information.
     *
     * @return the JSONSerializer to be used to serialize User.
     */
    public static JSONSerializer getTechnicalResourceSerializer() {
        JSONSerializer serializer = getBasicSerializer();
        List<String> excludes = new LinkedList<>();
        List<String> tempIncludes;

        excludes.addAll(getGlobalExcludes());

        tempIncludes = new LinkedList<>();
        tempIncludes.add("link");

        excludes.addAll(JSONSerializerBuilder.getExcludesForObject(Image.class, "profilePicture", tempIncludes));

        tempIncludes = new LinkedList<>();
        tempIncludes.add("uniqueIdentifier");

        excludes.addAll(JSONSerializerBuilder.getExcludesForObject(Organization.class, "organization", tempIncludes));

        tempIncludes = new LinkedList<>();
        tempIncludes.add("nickname");
        tempIncludes.add("firstName");
        tempIncludes.add("lastName");
        tempIncludes.add("isAdministrator");
        tempIncludes.add("profilePicture");
        tempIncludes.add("technicalPosition");
        tempIncludes.add("username");

        excludes.addAll(JSONSerializerBuilder.getExcludesForObject(TechnicalResource.class, "", tempIncludes));

        serializer.setExcludes(excludes);

        serializer.transform(new BooleanTransformer(), "isAdministrator");

        // logs the creation of the serializer
        logger.trace("TechnicalResource Serializer {} created", serializer.toString());
        return serializer;
    }

    /**
     * Creates a basic serializer that returns the category and skill of an OrganizationSkillCategory
     *
     * @return the JSONSerializer to be used to serialize a OrganizationSkill
     */
    public static JSONSerializer getSkillSerializer() {
        JSONSerializer serializer = getBasicSerializer();
        List<String> excludes = new LinkedList<>();
        List<String> tempIncludes = new LinkedList<>();

        excludes.addAll(getGlobalExcludes()); // adds all the basic excludes

        // Excludes all attributes of the SkillCategory class except its name
        tempIncludes.add("name");
        excludes.addAll(JSONSerializerBuilder.getExcludesForObject(SkillCategory.class, "category", tempIncludes));

        // Excludes all attributes of Skill except its name, category and skillType
        tempIncludes = new LinkedList<>();
        tempIncludes.add("name");
        tempIncludes.add("category");
        tempIncludes.add("skillType");

        excludes.addAll(JSONSerializerBuilder.getExcludesForObject(Skill.class, "", tempIncludes));

        // sets the added excludes to the serializer
        serializer.setExcludes(excludes);

        // logs the creation of the serializer
        logger.trace("OrganizationSkill Serializer {} created", serializer.toString());

        return serializer;
    }

    /**
     * Creates a basic serializer that returns a set of project skills.
     *
     * @return the JSONSerializer to be used to serialize a Skill.
     */
    public static JSONSerializer getProjectSkillsSerializer() {
        JSONSerializer serializer = getBasicSerializer();
        List<String> excludes = new LinkedList<>();
        List<String> tempIncludes = new LinkedList<>();

        excludes.addAll(getGlobalExcludes()); // adds all the basic excludes

        // Excludes all attributes of the SkillCategory class except its name
        tempIncludes.add("name");
        excludes.addAll(JSONSerializerBuilder.getExcludesForObject(Skill.class, "", tempIncludes));

        // sets the added excludes to the serializer
        serializer.setExcludes(excludes);

        // logs the creation of the serializer
        logger.trace("ProjectSkill Serializer {} created", serializer.toString());

        return serializer;
    }

    /**
     * Gets a JSONSerializer to use in order to obtain the JSON of a ProjectPosition.
     *
     * @return the JSONSerializer to be used to serialize ProjectPosition.
     */
    public static JSONSerializer getProjectPositionHolderSerializer() {
        JSONSerializer serializer = getBasicSerializer();
        List<String> excludes = new LinkedList<>();
        List<String> tempIncludes = new LinkedList<>();

        excludes.addAll(getGlobalExcludes());

        // Exclude all attributes of capability except name
        tempIncludes.add("name");
        excludes.addAll(JSONSerializerBuilder.getExcludesForObject(Capability.class, "projectPosition.capabilityLevel.capability", tempIncludes));

        // Exclude all attributes of capabilityLevel except name, hierarchyPosition and capability
        tempIncludes = new LinkedList<>();
        tempIncludes.add("name");
        tempIncludes.add("hierarchyPosition");
        tempIncludes.add("capability");
        excludes.addAll(JSONSerializerBuilder.getExcludesForObject(CapabilityLevel.class, "projectPosition.capabilityLevel", tempIncludes));

        // Exclude all attributes of projectPosition except projectPositionStatus, totalHours and capabilityLevel
        tempIncludes = new LinkedList<>();
        tempIncludes.add("projectPositionStatus");
        tempIncludes.add("totalHours");
        tempIncludes.add("capabilityLevel");
        excludes.addAll(JSONSerializerBuilder.getExcludesForObject(ProjectPosition.class, "projectPosition", tempIncludes));

        // Exclude all attributes of projectPositionHolder except startDate, endDate, reviewed, assignedHours,
        // active and projectPosition
        tempIncludes = new LinkedList<>();
        tempIncludes.add("startDate");
        tempIncludes.add("endDate");
        tempIncludes.add("reviewed");
        tempIncludes.add("assignedHours");
        tempIncludes.add("active");
        tempIncludes.add("projectPosition");
        excludes.addAll(JSONSerializerBuilder.getExcludesForObject(ProjectPositionHolder.class, "", tempIncludes));

        serializer.setExcludes(excludes);

        serializer.transform(new BooleanTransformer(), "reviewed","active");

        // logs the creation of the serializer
        logger.trace("ProjectPositionHolder Serializer {} created", serializer.toString());

        return serializer;
    }

    /**
     * Creates a basic serializer that returns every project position in a project, along with each project's position
     * holders over time.
     *
     * @return the JSONSerializer to be used to serialize a ProjectPosition object
     */
    public static JSONSerializer getProjectPositionSerializer() {
        JSONSerializer serializer = getBasicSerializer();
        List<String> excludes = new LinkedList<>();
        List<String> tempIncludes;

        excludes.addAll(getGlobalExcludes()); // adds all the basic excludes


        // Exclude all attributes of the TechnicalResource resource attribute in ProjectPositionHolder except
        // firstName, lastName and profilePicture
        tempIncludes = new LinkedList<>();
        tempIncludes.add("firstName");
        tempIncludes.add("lastName");
        tempIncludes.add("profilePicture");
        tempIncludes.add("username");
        excludes.addAll(JSONSerializerBuilder.getExcludesForObject(TechnicalResource.class, "holderHistory.resource", tempIncludes));

        // Exclude all attributes of the ProjectPositionHolder holderHistory attribute in ProjectPosition except resource
        tempIncludes = new LinkedList<>();
        tempIncludes.add("resource");
        tempIncludes.add("assignedHours");
        tempIncludes.add("active");
        tempIncludes.add("startDate");
        tempIncludes.add("endDate");
        excludes.addAll(JSONSerializerBuilder.getExcludesForObject(ProjectPositionHolder.class, "holderHistory", tempIncludes));

        // Exclude all attributes of the Capability capability attribute in CapabilityLevel except name and capability
        tempIncludes = new LinkedList<>();
        tempIncludes.add("name");
        excludes.addAll(JSONSerializerBuilder.getExcludesForObject(Capability.class, "capabilityLevel.capability", tempIncludes));

        // Exclude all attributes of the CapabilityLevel capabilityLevel attribute in ProjectPosition except name and capability
        tempIncludes = new LinkedList<>();
        tempIncludes.add("name");
        tempIncludes.add("capability");
        excludes.addAll(JSONSerializerBuilder.getExcludesForObject(CapabilityLevel.class, "capabilityLevel", tempIncludes));

        // Exclude all attributes of ProjectPosition except capabilityLevel and holderHistory
        tempIncludes = new LinkedList<>();
        tempIncludes.add("capabilityLevel");
        tempIncludes.add("holderHistory");
        excludes.addAll(JSONSerializerBuilder.getExcludesForObject(ProjectPosition.class, "", tempIncludes));

        serializer.setExcludes(excludes);
        serializer.transform(new ImageTransformer(), "holderHistory.resource.profilePicture");

        // logs the creation of the serializer
        logger.trace("ProjectPosition Serializer {} created", serializer.toString());

        return serializer;
    }

    /**
     * TODO implement the method to return a serializer for a SystemAdministrator, in order to obtain the JSON of its
     * main information with its respective includes and excludes
     *
     * @return an instance of JSONSerializer to serialize SystemAdministrator objects
     */
    public static JSONSerializer getSystemAdministratorSerializer() {
        JSONSerializer serializer = getBasicSerializer();
        List<String> excludes = new LinkedList<>();
        List<String> tempIncludes = new LinkedList<>();

        excludes.addAll(getGlobalExcludes());

        return serializer;
    }

    /*
     * Gets a JSONSerializer to use in order to obtain the JSON of a User's active projects information, for the /ws/technicalResource
     * /project/get web service.
     *
     * @return the JSONSerializer to be used to serialize the project list.
     */
    public static JSONSerializer getTechnicalResourceProjectsSerializer() {
        JSONSerializer serializer = getBasicSerializer();
        List<String> excludes = new LinkedList<>();
        List<String> tempIncludes = new LinkedList<>();

        excludes.addAll(getGlobalExcludes());
        excludes.add("*.class");

        tempIncludes = new LinkedList<>();
        tempIncludes.add("name");
        tempIncludes.add("description");
        tempIncludes.add("startDate");
        tempIncludes.add("endDate");
        tempIncludes.add("jiraLink");
        tempIncludes.add("confluenceLink");
        tempIncludes.add("versionControlLink");
        excludes.addAll(JSONSerializerBuilder.getExcludesForObject(Project.class, "", tempIncludes));

        serializer.setExcludes(excludes);

        // logs the creation of the serializer
        logger.trace("Project list Serializer {} created", serializer.toString());
        return serializer;
    }

    /*
     * Gets a JSONSerializer to use in order to obtain the JSON of a project information with its TRs.
     *
     * @return the JSONSerializer to be used to serialize the project information.
     */
    public static JSONSerializer getProjectInformationWithTRSerializer() {
        JSONSerializer serializer = getBasicSerializer();
        List<String> excludes = new LinkedList<>();
        List<String> tempIncludes = new LinkedList<>();

        excludes.addAll(getGlobalExcludes());
        excludes.add("*.class");

        tempIncludes.add("firstName");
        tempIncludes.add("lastName");
        tempIncludes.add("username");
        excludes.addAll(JSONSerializerBuilder.getExcludesForObject(TechnicalResource.class, "projectPositions.holderHistory.resource", tempIncludes));
        excludes.addAll(JSONSerializerBuilder.getExcludesForObject(ProjectPositionHolder.class, "projectPositions.holderHistory", new LinkedList<>()));
        excludes.addAll(JSONSerializerBuilder.getExcludesForObject(ProjectPosition.class, "projectPositions", new LinkedList<>()));

        tempIncludes = new LinkedList<>();
        tempIncludes.add("firstName");
        tempIncludes.add("lastName");
        tempIncludes.add("username");
        excludes.addAll(JSONSerializerBuilder.getExcludesForObject(TechnicalResource.class, "leadHistory.lead", tempIncludes));
        excludes.addAll(JSONSerializerBuilder.getExcludesForObject(LeadPosition.class, "leadHistory", new LinkedList<>()));

        tempIncludes = new LinkedList<>();
        tempIncludes.add("name");
        tempIncludes.add("description");
        tempIncludes.add("startDate");
        tempIncludes.add("endDate");
        tempIncludes.add("jiraLink");
        tempIncludes.add("confluenceLink");
        tempIncludes.add("versionControlLink");
        excludes.addAll(JSONSerializerBuilder.getExcludesForObject(Project.class, "", tempIncludes));

        serializer.setExcludes(excludes);

        // logs the creation of the serializer
        logger.trace("Project Serializer {} created", serializer.toString());
        return serializer;
    }

    /*
     * Gets a JSONSerializer to use in order to obtain the JSON of a project basic information.
     *
     * @return the JSONSerializer to be used to serialize the project information.
     */
    public static JSONSerializer getProjectInformationSerializer() {
        JSONSerializer serializer = getBasicSerializer();
        List<String> excludes = new LinkedList<>();
        List<String> tempIncludes = new LinkedList<>();

        excludes.addAll(getGlobalExcludes());
        excludes.add("*.class");

        tempIncludes.add("name");
        tempIncludes.add("description");
        tempIncludes.add("startDate");
        tempIncludes.add("endDate");
        tempIncludes.add("jiraLink");
        tempIncludes.add("confluenceLink");
        tempIncludes.add("versionControlLink");
        tempIncludes.add("state");
        excludes.addAll(JSONSerializerBuilder.getExcludesForObject(Project.class, "", tempIncludes));

        serializer.setExcludes(excludes);

        // logs the creation of the serializer
        logger.trace("Project Serializer {} created", serializer.toString());
        return serializer;
    }

    /**
     * Creates a basic serializer that returns the first name, last name and organization logo for an invitation
     * @return a JSON of the invitation model.
     */
    public static JSONSerializer getInvitationSerializer() {
        JSONSerializer serializer = getBasicSerializer(); // core serializer which excludes classnames
        List<String> excludes = new LinkedList<>(); // list which will store all excluded attributes
        List<String> includes = new LinkedList<>(); // list which will store all included attributes

        excludes.addAll(getGlobalExcludes()); // adds all the basic excludes

        includes.add("invitedResourceFirstName");
        includes.add("invitedResourceLastName");

        excludes.addAll(JSONSerializerBuilder.getExcludesForObject(Invitation.class, "", includes));

        includes = new LinkedList<>();
        includes.add("logo");

        // adds all attributes of the Organization class as excludes except those in the includes list
        excludes.addAll(JSONSerializerBuilder.getExcludesForObject(Organization.class, "", includes));

        // sets the added excludes to the serializer
        serializer.setExcludes(excludes);

        serializer.transform(new ImageTransformer(), "logo");

        // logs the creation of the serializer
        logger.trace("Invitation Serializer {} created", serializer.toString());

        return serializer;
    }

    /*
     * Gets a JSONSerializer to use in order to obtain the JSON of a User's education records
     *
     * @return the JSONSerializer to be used to serialize the project list.
     */
    public static JSONSerializer getTechnicalResourceEducationRecordsSerializer() {
        JSONSerializer serializer = getBasicSerializer();
        List<String> excludes = new LinkedList<>();
        List<String> tempIncludes;

        excludes.addAll(getGlobalExcludes());
        excludes.add("*.class");

        tempIncludes = new LinkedList<>();
        tempIncludes.add("institution");
        tempIncludes.add("startDate");
        tempIncludes.add("endDate");
        tempIncludes.add("title");
        tempIncludes.add("description");

        excludes.addAll(JSONSerializerBuilder.getExcludesForObject(EducationRecord.class, "", tempIncludes));
        serializer.setExcludes(excludes);

        // logs the creation of the serializer
        logger.trace("Education record list Serializer {} created", serializer.toString());
        return serializer;
    }

    /**
     * Creates a serializer that returns the id, firstName, lastName, technical position and profile picture in order to
     * search them.
     * @return
     */
    public static JSONSerializer getTechnicalResourceSearchSerializer() {
        JSONSerializer serializer = getBasicSerializer();
        List<String> excludes = new LinkedList<>();
        List<String> tempIncludes;

        excludes.addAll(getGlobalExcludes());

        // Add firstName, lastName, profilePicture and technicalPosition
        tempIncludes = new LinkedList<>();
        tempIncludes.add("firstName");
        tempIncludes.add("lastName");
        tempIncludes.add("profilePicture");
        tempIncludes.add("technicalPosition");
        excludes.addAll(JSONSerializerBuilder.getExcludesForObject(TechnicalResource.class, "", tempIncludes));

        // Add the profile picture's link
        tempIncludes = new LinkedList<>();
        tempIncludes.add("link");
        excludes.addAll(JSONSerializerBuilder.getExcludesForObject(Image.class, "profilePicture", tempIncludes));

        // Add the capability level from the technical position
        tempIncludes = new LinkedList<>();
        tempIncludes.add("capabilityLevel");
        excludes.addAll(JSONSerializerBuilder.getExcludesForObject(TechnicalPosition.class, "technicalPosition", tempIncludes));

        // Add the capability levels name and capability from the capability level
        tempIncludes = new LinkedList<>();
        tempIncludes.add("name");
        tempIncludes.add("capability");
        excludes.addAll(JSONSerializerBuilder.getExcludesForObject(CapabilityLevel.class,
                "technicalPosition.capabilityLevel", tempIncludes));

        // Add the capability's name from the capability
        tempIncludes = new LinkedList<>();
        tempIncludes.add("name");
        excludes.addAll(JSONSerializerBuilder.getExcludesForObject(Capability.class,
                "technicalPosition.capabilityLevel.capability", tempIncludes));

        serializer.setExcludes(excludes);

        // logs the creation of the serializer
        logger.trace("TechnicalResourceSearch Serializer {} created", serializer.toString());
        return serializer;
    }

}