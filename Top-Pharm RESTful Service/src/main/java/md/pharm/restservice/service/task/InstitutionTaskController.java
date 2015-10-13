package md.pharm.restservice.service.task;

import md.pharm.hibernate.institution.Institution;
import md.pharm.hibernate.institution.ManageInstitution;
import md.pharm.hibernate.product.ManageProduct;
import md.pharm.hibernate.product.Product;
import md.pharm.hibernate.task.ManageTask;
import md.pharm.hibernate.task.Task;
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
@RequestMapping("/toppharm/task/{taskID}/institution")
public class InstitutionTaskController {

    @RequestMapping(value = "/", method = RequestMethod.GET)
    public ResponseEntity<?> getAll(@PathVariable(value = "taskID") int taskID){
        Response response = new Response();
        ManageTask manageTask = new ManageTask();
        Task task = manageTask.getTaskByID(taskID);
        if(task!=null){
            Institution institution = task.getInstitution();
            response.setResponseCode(ErrorCodes.OK.name);
            response.setResponseMessage(ErrorCodes.OK.userMessage);
            response.setObject(institution);
            return new ResponseEntity<Object>(response, HttpStatus.OK);
        }else{
            response.setResponseCode(ErrorCodes.InternalError.name);
            response.setResponseMessage(ErrorCodes.InternalError.userMessage);
            return new ResponseEntity<Object>(response, HttpStatus.OK);
        }
    }

    @RequestMapping(value = "/add/{institutionID}", method = RequestMethod.POST)
    public ResponseEntity<?> add(@PathVariable(value = "taskID") int taskID, @PathVariable(value = "institutionID") Integer institutionID){
        Response response = new Response();
        ManageTask manageTask = new ManageTask();
        Task task = manageTask.getTaskByID(taskID);
        if(task!=null){
            ManageInstitution manageInstitution = new ManageInstitution();
            Institution institution = manageInstitution.getInstitutionByID(institutionID);
            if(institution!=null) {
                //doctor.getTasks().add(task);
                task.setInstitution(institution);
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

    @RequestMapping(value = "/update/{institutionID}", method = RequestMethod.POST)
    public ResponseEntity<?> update(@PathVariable(value = "taskID") int taskID, @PathVariable(value = "institutionID") Integer institutionID){
        Response response = new Response();
        ManageTask manageTask = new ManageTask();
        Task task = manageTask.getTaskByID(taskID);
        if(task!=null){
            ManageInstitution manageInstitution = new ManageInstitution();
            Institution institution = manageInstitution.getInstitutionByID(institutionID);
            if(institution!=null) {
                //doctor.getTasks().add(task);
                task.setInstitution(institution);
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

    @RequestMapping(value = "/delete", method = RequestMethod.DELETE)
    public ResponseEntity<?> delete(@PathVariable(value = "taskID") int taskID) {
        Response response = new Response();
        ManageTask manageTask = new ManageTask();
        Task task = manageTask.getTaskByID(taskID);
        if (task != null) {
            task.setInstitution(null);
            if (manageTask.updateTask(task)) {
                response.setResponseCode(ErrorCodes.OK.name);
                response.setResponseMessage(ErrorCodes.OK.userMessage);
                return new ResponseEntity<Object>(response, HttpStatus.OK);
            } else {
                response.setResponseCode(ErrorCodes.InternalError.name);
                response.setResponseMessage(ErrorCodes.InternalError.userMessage);
                return new ResponseEntity<Object>(response, HttpStatus.OK);
            }
        } else {
            response.setResponseCode(ErrorCodes.InternalError.name);
            response.setResponseMessage(ErrorCodes.InternalError.userMessage);
            return new ResponseEntity<Object>(response, HttpStatus.OK);
        }
    }

}
