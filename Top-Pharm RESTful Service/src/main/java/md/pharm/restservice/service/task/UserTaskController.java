package md.pharm.restservice.service.task;

import md.pharm.hibernate.task.ManageTask;
import md.pharm.hibernate.task.Task;
import md.pharm.hibernate.user.ManageUser;
import md.pharm.hibernate.user.User;
import md.pharm.restservice.service.Response;
import md.pharm.restservice.util.ErrorCodes;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.Set;

/**
 * Created by Andrei on 10/10/2015.
 */

@RestController
@RequestMapping("/toppharm/task/{taskID}/user/")
public class UserTaskController {

    @RequestMapping(value = "/all", method = RequestMethod.GET)
    public ResponseEntity<?> getAll(@PathVariable(value = "taskID") int taskID){
        Response response = new Response();
        ManageTask manageTask = new ManageTask();
        Task task = manageTask.getTaskByID(taskID);
        if(task!=null){
            Set<User> users = task.getUsers();
            response.setResponseCode(ErrorCodes.OK.name);
            response.setResponseMessage(ErrorCodes.OK.userMessage);
            response.setObject(users);
            //response.addMapItem("doctors", list);
            return new ResponseEntity<Object>(response, HttpStatus.OK);
        }else{
            response.setResponseCode(ErrorCodes.InternalError.name);
            response.setResponseMessage(ErrorCodes.InternalError.userMessage);
            return new ResponseEntity<Object>(response, HttpStatus.OK);
        }
    }

    @RequestMapping(value = "/add/{userID}", method = RequestMethod.POST)
    public ResponseEntity<?> add(@PathVariable(value = "taskID") int taskID, @PathVariable(value = "userID") int userID){
        Response response = new Response();
        ManageTask manageTask = new ManageTask();
        Task task = manageTask.getTaskByID(taskID);
        if(task!=null){
            ManageUser manageUser = new ManageUser();
            User user = manageUser.getUserByID(userID);
            if(user!=null) {
                //doctor.getTasks().add(task);
                task.getUsers().add(user);
                manageTask.updateTask(task);
                response.setResponseCode(ErrorCodes.OK.name);
                response.setResponseMessage(ErrorCodes.OK.userMessage);
                return new ResponseEntity<Object>(response, HttpStatus.OK);
            }else{
                response.setResponseCode(ErrorCodes.InternalError.name);
                response.setResponseMessage(ErrorCodes.InternalError.userMessage);
                return new ResponseEntity<Object>(response, HttpStatus.OK);
            }
        }else{
            response.setResponseCode(ErrorCodes.InternalError.name);
            response.setResponseMessage(ErrorCodes.InternalError.userMessage);
            return new ResponseEntity<Object>(response, HttpStatus.OK);
        }
    }

    @RequestMapping(value = "/delete/{userID}", method = RequestMethod.DELETE)
    public ResponseEntity<?> delete(@PathVariable(value = "taskID") int taskID, @PathVariable(value = "userID") int userID){
        Response response = new Response();
        ManageTask manageTask = new ManageTask();
        Task task = manageTask.getTaskByID(taskID);
        if(task!=null){
            ManageUser manageUser = new ManageUser();
            User user = manageUser.getUserByID(userID);
            if(user!=null) {
                if(manageTask.deleteUserTask(taskID,userID)) {
                    response.setResponseCode(ErrorCodes.OK.name);
                    response.setResponseMessage(ErrorCodes.OK.userMessage);
                    return new ResponseEntity<Object>(response, HttpStatus.OK);
                }else{
                    response.setResponseCode(ErrorCodes.InternalError.name);
                    response.setResponseMessage(ErrorCodes.InternalError.userMessage);
                    return new ResponseEntity<Object>(response, HttpStatus.OK);
                }
            }else{
                response.setResponseCode(ErrorCodes.InternalError.name);
                response.setResponseMessage(ErrorCodes.InternalError.userMessage);
                return new ResponseEntity<Object>(response, HttpStatus.OK);
            }
        }else{
            response.setResponseCode(ErrorCodes.InternalError.name);
            response.setResponseMessage(ErrorCodes.InternalError.userMessage);
            return new ResponseEntity<Object>(response, HttpStatus.OK);
        }
    }


}


