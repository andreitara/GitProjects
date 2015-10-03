package md.pharm.restservice.service;

import md.pharm.hibernate.user.ManageUser;
import md.pharm.hibernate.user.User;
import md.pharm.hibernate.user.permission.Permission;
import md.pharm.restservice.util.ErrorCodes;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

/**
 * Created by Andrei on 9/28/2015.
 */

@RestController
@RequestMapping("toppharm/user/permission/")
public class PermissionController {

    @RequestMapping(value = "/{username}", method = RequestMethod.GET)
    public ResponseEntity<?> getUserPermission(@PathVariable(value = "username") String username) {
        Response response = new Response();
        User user = new ManageUser().getUserByLogin(username);
        if(user!=null) {
            Permission permission = user.getPermission();
            response.setResponseCode(ErrorCodes.OK.name);
            response.setResponseMessage(ErrorCodes.OK.userMessage);
            response.addMapItem("permission",permission);
            return new ResponseEntity<Object>(response, HttpStatus.OK);
        }else{
            response.setResponseCode(ErrorCodes.ResourceNotFound.name);
            response.setResponseMessage(ErrorCodes.ResourceNotFound.userMessage);
            return new ResponseEntity<Object>(response, HttpStatus.NOT_FOUND);
        }
    }

    @RequestMapping(value = "/my", method = RequestMethod.GET)
    public ResponseEntity<?> getMyPermission(@RequestHeader(value = "username") String username) {
        Response response = new Response();
        User user = new ManageUser().getUserByLogin(username);
        if(user!=null) {
            Permission permission = user.getPermission();
            response.setResponseCode(ErrorCodes.OK.name);
            response.setResponseMessage(ErrorCodes.OK.userMessage);
            response.addMapItem("permission",permission);
            return new ResponseEntity<Object>(response, HttpStatus.OK);
        }else{
            response.setResponseCode(ErrorCodes.ResourceNotFound.name);
            response.setResponseMessage(ErrorCodes.ResourceNotFound.userMessage);
            return new ResponseEntity<Object>(response, HttpStatus.NOT_FOUND);
        }
    }

    @RequestMapping(value = "/update/{username}", method = RequestMethod.POST)
    public ResponseEntity<?> addRightsToUser(@PathVariable(value = "username") String username, @RequestBody Permission permission) {
        Response response = new Response();
        User user = new ManageUser().getUserByLogin(username);
        if(user!=null){
            permission.setId(user.getPermission().getId());
            permission.setUser(user);
            user.setPermission(permission);
            if(new ManageUser().updateUser(user)){
                response.setResponseCode(ErrorCodes.Updated.name);
                response.setResponseMessage(ErrorCodes.Updated.userMessage);
                return new ResponseEntity<Object>(response, HttpStatus.OK);
            }else{
                response.setResponseCode(ErrorCodes.InternalError.name);
                response.setResponseMessage(ErrorCodes.InternalError.userMessage);
                return new ResponseEntity<Object>(response, HttpStatus.INTERNAL_SERVER_ERROR);
            }
        }else {
            response.setResponseCode(ErrorCodes.ResourceNotFound.name);
            response.setResponseMessage(ErrorCodes.ResourceNotFound.userMessage);
            return new ResponseEntity<Object>(response, HttpStatus.NOT_FOUND);
        }
    }
}
