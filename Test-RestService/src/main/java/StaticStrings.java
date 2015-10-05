/**
 * Created by Andrei on 10/3/2015.
 */
public class StaticStrings {

    public static String REST_IP_PORT = "http://localhost:8181";

    public static String ADMIN_AUTH_TOKEN = "0b7faed1-93d3-47c1-a124-ebf404ebbae5";
    public static String USER_AUTH_TOKEN = "2a1f8617-9188-4a59-a5d2-8ab5401760c0";

    public static String LOGIN_URI =  REST_IP_PORT + "/toppharm/login";
    public static String LOGOUT_URI = REST_IP_PORT + "/toppharm/logout";

    public static String GET_USER_PERMISSION_URI = REST_IP_PORT + "/toppharm/user/permission/{username}";
    public static String GET_MY_PERMISSION_URI =   REST_IP_PORT + "/toppharm/user/permission/my";
    public static String UPDATE_PERMISSION_URI =   REST_IP_PORT + "/toppharm/user/permission/update";

    public static String DELETE_USER_URI =   REST_IP_PORT + "/toppharm/user/delete/{username}";
    public static String UPDATE_USER_URI =   REST_IP_PORT + "/toppharm/user/update";
    public static String CREATE_USER_URI =   REST_IP_PORT + "/toppharm/user/create";
    public static String GET_USER_URI =      REST_IP_PORT + "/toppharm/user/{username}";
    public static String GET_ALL_USERS_URI = REST_IP_PORT + "/toppharm/user/all";

    public static String GET_ALL_INSTITUTIONS_URI = REST_IP_PORT + "/toppharm/medical/institution/all";
    public static String GET_INSTITUTION_URI =      REST_IP_PORT + "/toppharm/medical/institution/{id}";
    public static String CREATE_INSTITUTION_URI =   REST_IP_PORT + "/toppharm/medical/institution/create";
    public static String UPDATE_INSTITUTION_URI =   REST_IP_PORT + "/toppharm/medical/institution/update";
    public static String DELETE_INSTITUTION_URI =   REST_IP_PORT + "/toppharm/medical/institution/delete/{id}";

    public static String GET_ALL_DOCTORS_URI = REST_IP_PORT + "/toppharm/medical/doctor/all";
    public static String GET_DOCTORS_URI =     REST_IP_PORT + "/toppharm/medical/doctor/{id}";
    public static String CREATE_DOCTORS_URI =  REST_IP_PORT + "/toppharm/medical/doctor/create";
    public static String UPDATE_DOCTORS_URI =  REST_IP_PORT + "/toppharm/medical/doctor/update";
    public static String DELETE_DOCTORS_URI =  REST_IP_PORT + "/toppharm/medical/doctor/delete/{id}";

    public static String GET_ALL_OFFICES_OF_DOCTOR_URI = REST_IP_PORT + "/toppharm/medical/doctor/{doctorID}/office/all";
    public static String GET_OFFICE_URI =                REST_IP_PORT + "/toppharm/medical/doctor/{doctorID}/office/{id}";
    public static String CREATE_OFFICE_FOR_DOCTOR_URI =  REST_IP_PORT + "/toppharm/medical/doctor/{doctorID}/office/create/institution/{institutionID}";
    public static String UPDATE_OFFICE_FOR_DOCTOR_URI =  REST_IP_PORT + "/toppharm/medical/doctor/{doctorID}/office/update/institution/{institutionID}";
    public static String DELETE_OFFICE_FOR_DOCTOR_URI =  REST_IP_PORT + "/toppharm/medical/doctor/{doctorID}/office/delete/{id}";

    public static String GET_ALL_PRODUCTS_URI = REST_IP_PORT + "/toppharm/medical/product/all";
    public static String GET_PRODUCT_URI =      REST_IP_PORT + "/toppharm/medical/product/{id}";
    public static String CREATE_PRODUCT_URI =   REST_IP_PORT + "/toppharm/medical/product/create";
    public static String UPDATE_PRODUCT_URI =   REST_IP_PORT + "/toppharm/medical/product/update";
    public static String DELETE_PRODUCT_URI =   REST_IP_PORT + "/toppharm/medical/product/delete/{id}";
}
