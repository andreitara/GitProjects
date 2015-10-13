package md.pharm.restservice.service.user;

import md.pharm.hibernate.connection.Connection;
import md.pharm.hibernate.connection.ManageConnection;
import md.pharm.hibernate.user.ManageUser;
import md.pharm.hibernate.user.User;
import md.pharm.restservice.service.Response;
import md.pharm.restservice.util.ErrorCodes;
import md.pharm.restservice.util.StaticStrings;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by Andrei on 9/24/2015.
 */

@RestController
@RequestMapping("toppharm/user")
public class LoginController {

    @RequestMapping(value = "/login", method = RequestMethod.POST)
    public ResponseEntity<?> createUser(@RequestBody User user){
        Response response = new Response();
        if(user!=null) {
            ManageUser manageUser = new ManageUser();
            ManageConnection manageConnection = new ManageConnection();
            User userFromDB = manageUser.getUserByUsername(user.getUsername());
            if (userFromDB.getPassword().equals(user.getPassword())) {
                Connection connection = new Connection();
                userFromDB.setConnection(connection);
                connection.setUser(userFromDB);
                manageConnection.addConnection(connection);
                response.setObject(connection.getConnectionKey());
                response.setResponseCode(ErrorCodes.ValidAuthenticationInfo.name);
                response.setResponseMessage(ErrorCodes.ValidAuthenticationInfo.userMessage);
                return new ResponseEntity<Object>(response, HttpStatus.OK);
            } else {
                response.setResponseCode(ErrorCodes.InvalidAuthenticationInfo.name);
                response.setResponseMessage(ErrorCodes.InvalidAuthenticationInfo.userMessage);
                return new ResponseEntity<Object>(response, HttpStatus.BAD_REQUEST);
            }
        }else{
            response.setResponseCode(ErrorCodes.InvalidAuthenticationInfo.name);
            response.setResponseMessage(ErrorCodes.InvalidAuthenticationInfo.userMessage);
            return new ResponseEntity<Object>(response, HttpStatus.BAD_REQUEST);
        }
    }

    @RequestMapping(value = "/logout", method = RequestMethod.GET)
    public ResponseEntity<?> getUser(@RequestHeader(StaticStrings.HEADER_USERNAME) String username){
        Response response = new Response();
        User user = new ManageUser().getUserByUsername(username);
        if(user!=null) {
            Connection connection = user.getConnection();
            if (connection != null && new ManageConnection().deleteConnection(connection) == false) {
                response.setResponseCode(ErrorCodes.InternalError.name);
                response.setResponseMessage(ErrorCodes.InternalError.userMessage);
                return new ResponseEntity<Object>(response, HttpStatus.INTERNAL_SERVER_ERROR);
            }
            response.setResponseCode(ErrorCodes.OK.name);
            response.setResponseMessage(ErrorCodes.OK.userMessage);
            return new ResponseEntity<Object>(response, HttpStatus.OK);
        }else{
            response.setResponseCode(ErrorCodes.InternalError.name);
            response.setResponseMessage(ErrorCodes.InternalError.userMessage);
            return new ResponseEntity<Object>(response, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
