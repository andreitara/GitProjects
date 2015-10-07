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

        //PermissionControllerTest.getAdminUserPermission(3);
        //PermissionControllerTest.getAdminMyPermission();
        //PermissionControllerTest.updatePermissionsByAdmin(3);

        //UserControllerTest.createUserByAdmin();
        //UserControllerTest.updateUserByAdmin(2);
        //UserControllerTest.deleteUserByAdmin(2);
        //UserControllerTest.getUserByAdmin(2);
        //UserControllerTest.getAllUsersByAdmin();
        //UserControllerTest.createUserByUser();
        //UserControllerTest.deleteUserByUser();

        //InstitutionsControllerTest.createInstitutionByAdmin();
        //InstitutionsControllerTest.getInstitutionsByAdmin(4);
        //InstitutionsControllerTest.getAllInstitutionsByAdmin();
        InstitutionsControllerTest.updateInstitutionByAdmin(4);
        //InstitutionsControllerTest.deleteInstitutionByAdmin(3);


    }
}
