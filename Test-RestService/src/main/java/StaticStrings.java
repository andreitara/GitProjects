/**
 * Created by Andrei on 10/3/2015.
 */
public class StaticStrings {

    public static String REST_IP_PORT = "http://localhost:8181";

    public static String ADMIN_AUTH_TOKEN = "0b7faed1-93d3-47c1-a124-ebf404ebbae5";
    public static String USER_AUTH_TOKEN = "2a1f8617-9188-4a59-a5d2-8ab5401760c0";

    public static String LOGIN_URI = REST_IP_PORT + "/toppharm/login";
    public static String LOGOUT_URI = REST_IP_PORT + "/toppharm/logout";

    public static String GET_USER_PERMISSION_URI = REST_IP_PORT + "/toppharm/user/permission/{username}";
    public static String GET_MY_PERMISSION_URI = REST_IP_PORT + "/toppharm/user/permission/my";
    public static String UPDATE_PERMISSION_URI = REST_IP_PORT + "/toppharm/user/permission/update";

    public static String DELETE_USER_URI = REST_IP_PORT + "/toppharm/user/delete/{username}";
    public static String UPDATE_USER_URI = REST_IP_PORT + "/toppharm/user/update";
    public static String CREATE_USER_URI = REST_IP_PORT + "/toppharm/user/create";
    public static String GET_USER_URI = REST_IP_PORT + "/toppharm/user/{username}";
    public static String GET_ALL_USERS_URI = REST_IP_PORT + "/toppharm/user/all";

    public static String GET_ALL_INSTITUTIONS_URI = REST_IP_PORT + "/toppharm/medical/institution/all";
    public static String GET_INSTITUTION_URI = REST_IP_PORT + "/toppharm/medical/institution/{id}";
    public static String CREATE_INSTITUTION_URI = REST_IP_PORT + "/toppharm/medical/institution/create";
    public static String UPDATE_INSTITUTION_URI = REST_IP_PORT + "/toppharm/medical/institution/update";
    public static String DELETE_INSTITUTION_URI = REST_IP_PORT + "/toppharm/medical/institution/delete/{id}";
}
