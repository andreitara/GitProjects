import com.fasterxml.jackson.core.JsonProcessingException;
import md.pharm.hibernate.institution.Institution;
import md.pharm.hibernate.user.permission.Permission;

/**
 * Created by Andrei on 9/22/2015.
 */
public class Main {

    public static void main(String[] args) throws JsonProcessingException {
        //LoginControllerTest.loginAdmin();
        //LoginControllerTest.logoutAdmin();
        //LoginControllerTest.loginUser();
        //LoginControllerTest.logoutUser();

        //PermissionControllerTest.getAdminUserPermission();
        //PermissionControllerTest.getAdminMyPermission();

        //UserControllerTest.createUserByAdmin();
        //UserControllerTest.updateUserByAdmin();
        //UserControllerTest.deleteUserByAdmin();
        //UserControllerTest.getUserByAdmin();
        //UserControllerTest.getAllUsersByAdmin();
        //UserControllerTest.createUserByUser();
        //UserControllerTest.deleteUserByUser();

        InstitutionsControllerTest.createInstitutionByAdmin();
        //InstitutionsControllerTest.getInstitutionsByAdmin(12);
        //InstitutionsControllerTest.getAllInstitutionsByAdmin();
        //InstitutionsControllerTest.updateInstitutionByAdmin(10);
        //InstitutionsControllerTest.deleteInstitutionByAdmin(5);


    }
}
