package md.pharm.restservice.service.task;

import md.pharm.hibernate.doctor.Doctor;
import md.pharm.hibernate.doctor.ManageDoctor;
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

import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * Created by Andrei on 10/10/2015.
 */

@RestController
@RequestMapping("/toppharm/task/{taskID}/doctor/")
public class DoctorTaskController {

    @RequestMapping(value = "/all", method = RequestMethod.GET)
    public ResponseEntity<?> getAll(@PathVariable(value = "taskID") int taskID){
        Response response = new Response();
        ManageTask manageTask = new ManageTask();
        Task task = manageTask.getTaskByID(taskID);
        if(task!=null){
            Set<Doctor> doctors = task.getDoctors();
            response.setResponseCode(ErrorCodes.OK.name);
            response.setResponseMessage(ErrorCodes.OK.userMessage);
            response.setObject(doctors);
            //response.addMapItem("doctors", list);
            return new ResponseEntity<Object>(response, HttpStatus.OK);
        }else{
            response.setResponseCode(ErrorCodes.InternalError.name);
            response.setResponseMessage(ErrorCodes.InternalError.userMessage);
            return new ResponseEntity<Object>(response, HttpStatus.OK);
        }
    }

    @RequestMapping(value = "/add/{doctorID}", method = RequestMethod.POST)
    public ResponseEntity<?> add(@PathVariable(value = "taskID") int taskID, @PathVariable(value = "doctorID") int doctorID){
        Response response = new Response();
        ManageTask manageTask = new ManageTask();
        Task task = manageTask.getTaskByID(taskID);
        if(task!=null){
            ManageDoctor manageDoctor = new ManageDoctor();
            Doctor doctor = manageDoctor.getDoctorByID(doctorID);
            if(doctor!=null) {
                //doctor.getTasks().add(task);
                task.getDoctors().add(doctor);
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

    @RequestMapping(value = "/delete/{doctorID}", method = RequestMethod.DELETE)
    public ResponseEntity<?> delete(@PathVariable(value = "taskID") int taskID, @PathVariable(value = "doctorID") int doctorID){
        Response response = new Response();
        ManageTask manageTask = new ManageTask();
        Task task = manageTask.getTaskByID(taskID);
        if(task!=null){
            ManageDoctor manageDoctor = new ManageDoctor();
            Doctor doctor = manageDoctor.getDoctorByID(doctorID);
            if(doctor!=null) {
                if(manageTask.deleteDoctorTask(taskID,doctorID)) {
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
