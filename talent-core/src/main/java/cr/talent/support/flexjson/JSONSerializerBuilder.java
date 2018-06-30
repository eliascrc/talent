package cr.talent.support.flexjson;

import cr.talent.model.*;
import flexjson.JSONSerializer;
import flexjson.transformer.BooleanTransformer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

/**
 * Builder class that holds several static factory methods for creating JSONSerializers with different include & exclude
 * rules
 */
public class JSONSerializerBuilder {

    private static final Logger logger = LoggerFactory.getLogger(JSONSerializerBuilder.class);

    private static final List<String> GLOBAL_INCLUDES = new LinkedList<>();

    static{
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

            if(!GLOBAL_INCLUDES.contains(fieldName) && (include == null || !include.contains(fieldName))) {
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
        logger.trace("TermsOfService Serializer {} created", serializer.toString());
        return serializer;
    }

    /**
     * Creates a basic serializer that returns the category and skill of an OrganizationSkillCategory
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
     * Gets a JSONSerializer to use in order to obtain the JSON of a project information, for the /ws/organization/project/get web service.
     *
     * @return the JSONSerializer to be used to serialize the project information.
     */
    public static JSONSerializer getProjectInformationSerializer() {
        JSONSerializer serializer = getBasicSerializer();
        List<String> excludes = new LinkedList<>();
        List<String> tempIncludes = new LinkedList<>();

        excludes.addAll(getGlobalExcludes());

        tempIncludes.add("id");
        tempIncludes.add("entityCreationTimestamp");
        tempIncludes.add("lastUpdatedTimestamp");
        tempIncludes.add("entityVersion");
        tempIncludes.add("name");
        tempIncludes.add("description");
        tempIncludes.add("startDate");

        excludes.add("state");
        excludes.add("organization");

        tempIncludes.add("endDate");
        tempIncludes.add("jiraLink");
        tempIncludes.add("confluenceLink");
        tempIncludes.add("versionControlLink");

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

        includes.add("firstName");
        includes.add("lastName");

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

}
