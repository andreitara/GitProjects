package md.pharm.restservice.service.doctor;

import md.pharm.hibernate.doctor.Doctor;
import md.pharm.hibernate.doctor.ManageDoctor;
import md.pharm.restservice.service.Response;
import md.pharm.restservice.util.ErrorCodes;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * Created by Andrei on 10/5/2015.
 */

@RestController
@RequestMapping("toppharm/medical/doctor")
public class DoctorController {

    @RequestMapping("/all")
  public ResponseEntity<?> getAll(){
    Response response = new Response();
    ManageDoctor manageDoctor = new ManageDoctor();
    List<Doctor> list = manageDoctor.getDoctors();
    if(list!=null){
        response.setResponseCode(ErrorCodes.OK.name);
        response.setResponseMessage(ErrorCodes.OK.userMessage);
        response.setObject(list);
        //response.addMapItem("doctors", list);
        return new ResponseEntity<Object>(response, HttpStatus.OK);
    }else{
        response.setResponseCode(ErrorCodes.InternalError.name);
        response.setResponseMessage(ErrorCodes.InternalError.userMessage);
        return new ResponseEntity<Object>(response, HttpStatus.OK);
    }
}

    @RequestMapping(value = "/create", method = RequestMethod.POST)
    public ResponseEntity<?> create(@RequestBody Doctor doctor){
        Response response = new Response();
        ManageDoctor manage = new ManageDoctor();
        if(doctor.getId()== null) {
            if (true) {//TODO condition if not exists this doctor in DB
                Integer id = manage.addDoctor(doctor);
                if (id != null) {
                    response.setResponseCode(ErrorCodes.Created.name);
                    response.setResponseMessage(ErrorCodes.Created.userMessage);
                    response.setObject(id);
                    //doctor.setId(id);
                    //response.addMapItem("doctor", doctor);
                    return new ResponseEntity<Object>(response, HttpStatus.CREATED);
                } else {
                    response.setResponseCode(ErrorCodes.InternalError.name);
                    response.setResponseMessage(ErrorCodes.InternalError.userMessage);
                    return new ResponseEntity<Object>(response, HttpStatus.OK);
                }
            } else {
                response.setResponseCode(ErrorCodes.AccountAlreadyExists.name);
                response.setResponseMessage(ErrorCodes.AccountAlreadyExists.userMessage);
                return new ResponseEntity<>(null, HttpStatus.OK);
            }
        }{
            response.setResponseCode(ErrorCodes.WriteConditionNotMet.name);
            response.setResponseMessage(ErrorCodes.WriteConditionNotMet.userMessage);
            return new ResponseEntity<Object>(response, HttpStatus.OK);
        }
    }

    @RequestMapping(value = "/update", method = RequestMethod.POST)
    public ResponseEntity<?> createUser(@RequestBody Doctor doctor){
        Response response = new Response();
        ManageDoctor manage = new ManageDoctor();
        if(doctor.getId()!=null) {
            Doctor doctorFromDB = manage.getDoctorByID(doctor.getId());
            if (doctorFromDB != null) {
                if (manage.updateDoctor(doctor)) {
                    response.setResponseCode(ErrorCodes.OK.name);
                    response.setResponseMessage(ErrorCodes.OK.userMessage);
                    return new ResponseEntity<Object>(response, HttpStatus.OK);
                } else {
                    response.setResponseCode(ErrorCodes.InternalError.name);
                    response.setResponseMessage(ErrorCodes.InternalError.userMessage);
                    return new ResponseEntity<Object>(response, HttpStatus.OK);
                }
            } else {
                response.setResponseCode(ErrorCodes.ResourceNotExists.name);
                response.setResponseMessage(ErrorCodes.ResourceNotExists.userMessage);
                return new ResponseEntity<Object>(response, HttpStatus.OK);
            }
        }else{
            response.setResponseCode(ErrorCodes.WriteConditionNotMet.name);
            response.setResponseMessage(ErrorCodes.WriteConditionNotMet.userMessage);
            return new ResponseEntity<Object>(response, HttpStatus.OK);
        }
    }

    @RequestMapping("/delete/{id}")
    public ResponseEntity<?> delete(@PathVariable(value = "id") int id){
        Response response = new Response();
        ManageDoctor manage = new ManageDoctor();
        Doctor doctor = manage.getDoctorByID(id);
        if(doctor!=null){
            if(manage.deleteDoctor(doctor)){
                response.setResponseCode(ErrorCodes.OK.name);
                response.setResponseMessage(ErrorCodes.OK.userMessage);
                return new ResponseEntity<Object>(response, HttpStatus.OK);
            }else{
                response.setResponseCode(ErrorCodes.InternalError.name);
                response.setResponseMessage(ErrorCodes.InternalError.userMessage);
                return new ResponseEntity<Object>(response, HttpStatus.OK);
            }
        }else{
            response.setResponseCode(ErrorCodes.ResourceNotExists.name);
            response.setResponseMessage(ErrorCodes.ResourceNotExists.userMessage);
            return new ResponseEntity<Object>(response, HttpStatus.OK);
        }
    }

    @RequestMapping("/{id}")
    public ResponseEntity<?> get(@PathVariable(value = "id") int id){
        Response response = new Response();
        ManageDoctor manageDoctor = new ManageDoctor();
        Doctor doctor = manageDoctor.getDoctorByID(id);
        if(doctor!=null) {
            response.setResponseCode(ErrorCodes.OK.name);
            response.setResponseMessage(ErrorCodes.OK.userMessage);
            response.setObject(doctor);
            //response.addMapItem("doctor", doctor);
            return new ResponseEntity<Object>(response, HttpStatus.OK);
        }else{
            response.setResponseCode(ErrorCodes.ResourceNotExists.name);
            response.setResponseMessage(ErrorCodes.ResourceNotExists.userMessage);
            return new ResponseEntity<Object>(response, HttpStatus.OK);
        }
    }
}
