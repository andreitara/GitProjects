package md.pharm.restservice.util;

import java.util.Arrays;
import java.util.List;

/**
 * Created by Andrei on 9/26/2015.
 */
public class StaticStrings {
    final public static String HEADER_SECURITY_TOKEN = "auth-token";
    final public static String HEADER_USERNAME = "toppharm-username";
    final public static String HEADER_COUNTRY = "toppharm-country";

    final public static List<String> ACCESSIBLE_PAGES = Arrays.asList("toppharm/user/login");

    final public static String ERROR_PAGES = "toppharm/error";

    final public static String USER_PAGES = "/toppharm/user";
    final public static String MEDICAL_ENTITY_PAGES = "/toppharm/medical";
    final public static String TASK_PAGES = "/toppharm/task";

}
