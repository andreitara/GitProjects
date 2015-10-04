package md.pharm.restservice.service;

import md.pharm.hibernate.user.ManageUser;
import md.pharm.hibernate.user.User;
import md.pharm.restservice.util.ErrorCodes;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Andrei on 9/7/2015.
 */
@RestController
@RequestMapping("toppharm/user/")
public class UserController {

    @RequestMapping("/all")
     public ResponseEntity<?> getUsers(){
        Response response = new Response();
        ManageUser manageUser = new ManageUser();
        List<User> list = manageUser.getUsers();
        if(list!=null){
            response.setResponseCode(ErrorCodes.OK.name);
            response.setResponseMessage(ErrorCodes.OK.userMessage);
            response.addMapItem("users", list);
            return new ResponseEntity<Object>(response, HttpStatus.OK);
        }else{
            response.setResponseCode(ErrorCodes.InternalError.name);
            response.setResponseMessage(ErrorCodes.InternalError.userMessage);
            return new ResponseEntity<Object>(response, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @RequestMapping(value = "/create", method = RequestMethod.POST)
    public ResponseEntity<?> createUser(@RequestBody User user){
        Response response = new Response();
        ManageUser manageUser = new ManageUser();
        User existUser = manageUser.getUserByLogin(user.getUsername());
        if(existUser == null) {
            if(user.getPermission() == null){
                user.createDefaultPermission();
            }
            Integer id = manageUser.addUser(user);
            if (id != null) {
                response.setResponseCode(ErrorCodes.Created.name);
                response.setResponseMessage(ErrorCodes.Created.userMessage);
                response.addMapItem("user", user);
                return new ResponseEntity<Object>(response, HttpStatus.CREATED);
            } else {
                response.setResponseCode(ErrorCodes.InternalError.name);
                response.setResponseMessage(ErrorCodes.InternalError.userMessage);
                return new ResponseEntity<Object>(response, HttpStatus.INTERNAL_SERVER_ERROR);
            }
        }else{
            response.setResponseCode(ErrorCodes.AccountAlreadyExists.name);
            response.setResponseMessage(ErrorCodes.AccountAlreadyExists.userMessage);
            return new ResponseEntity<>(null, HttpStatus.CONFLICT);
        }
    }

    @RequestMapping(value = "/{username}",method = RequestMethod.GET)
    public ResponseEntity<?> getUser(@PathVariable(value = "username") String login){
        Response response = new Response();
        ManageUser manageUser = new ManageUser();
        User user = manageUser.getUserByLogin(login);
        response.setResponseCode(ErrorCodes.OK.name);
        response.setResponseMessage(ErrorCodes.OK.userMessage);
        response.addMapItem("user", user);
        return  new ResponseEntity<Object>(response, HttpStatus.OK);
    }

    @RequestMapping("/delete/{username}")
    public ResponseEntity<?> deleteUser(@PathVariable(value = "username") String username){
        Response response = new Response();
        ManageUser manageUser = new ManageUser();
        User user = manageUser.getUserByLogin(username);
        if(user!=null){
            if(manageUser.deleteUser(user)){
                response.setResponseCode(ErrorCodes.OK.name);
                response.setResponseMessage(ErrorCodes.OK.userMessage);
                return new ResponseEntity<Object>(response, HttpStatus.OK);
            }else{
                response.setResponseCode(ErrorCodes.InternalError.name);
                response.setResponseMessage(ErrorCodes.InternalError.userMessage);
                return new ResponseEntity<Object>(response, HttpStatus.INTERNAL_SERVER_ERROR);
            }
        }else{
            response.setResponseCode(ErrorCodes.InternalError.name);
            response.setResponseMessage(ErrorCodes.InternalError.userMessage);
            return new ResponseEntity<Object>(response, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @RequestMapping(value = "/update", method = RequestMethod.POST)
    public ResponseEntity<?> updateUser(@RequestBody User user){
        Response response = new Response();
        ManageUser manageUser = new ManageUser();
        if(manageUser.updateUser(user)){
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
