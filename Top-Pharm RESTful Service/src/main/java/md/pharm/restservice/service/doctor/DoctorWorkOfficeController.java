package md.pharm.restservice.service.doctor;

import md.pharm.hibernate.doctor.Doctor;
import md.pharm.hibernate.doctor.ManageDoctor;
import md.pharm.hibernate.institution.Institution;
import md.pharm.hibernate.institution.ManageInstitution;
import md.pharm.hibernate.institution.ManageOffice;
import md.pharm.hibernate.institution.Office;
import md.pharm.restservice.service.Response;
import md.pharm.restservice.util.ErrorCodes;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Set;

/**
 * Created by Andrei on 10/5/2015.
 */
@RestController
@RequestMapping("/toppharm/medical/doctor/{doctorID}/office")
public class DoctorWorkOfficeController {

    @RequestMapping("/all")
    public ResponseEntity<?> getAll(@PathVariable("doctorID") Integer doctorID){
        Response response = new Response();
        ManageDoctor manageDoctor = new ManageDoctor();
        Doctor doctor = manageDoctor.getDoctorByID(doctorID);
        if(doctor!=null) {
            response.setResponseCode(ErrorCodes.OK.name);
            response.setResponseMessage(ErrorCodes.OK.userMessage);
            response.addMapItem("workOffices", doctor.getOffices());
            return new ResponseEntity<Object>(response, HttpStatus.OK);
        }else{
            response.setResponseCode(ErrorCodes.WriteConditionNotMet.name);
            response.setResponseMessage(ErrorCodes.WriteConditionNotMet.userMessage);
            return new ResponseEntity<Object>(response, HttpStatus.OK);
        }
    }

    @RequestMapping(value = "/create/institution/{institutionID}", method = RequestMethod.POST)
    public ResponseEntity<?> create(@PathVariable("doctorID") Integer doctorID, @PathVariable("institutionID") Integer institutionID, @RequestBody Office office){
        Response response = new Response();
        ManageDoctor manageDoctor = new ManageDoctor();
        Doctor doctor = manageDoctor.getDoctorByID(doctorID);
        if(doctor!=null) {
            ManageInstitution manageInstitution = new ManageInstitution();
            Institution institution = manageInstitution.getInstitutionByID(institutionID);
            if(institution!=null) {
                if (office.getId() == null) {
                    if (true) {//TODO condition if not exists this office in DB
                        institution.setAddress(null);
                        doctor.setOffices(null);
                        office.setInstitution(institution);
                        office.setDoctor(doctor);
                        ManageOffice manageOffice = new ManageOffice();
                        Integer id = manageOffice.addOffice(office);
                        if (id != null) {
                            response.setResponseCode(ErrorCodes.Created.name);
                            response.setResponseMessage(ErrorCodes.Created.userMessage);
                            office.setId(id);
                            response.addMapItem("office", office);
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
                }else{
                    response.setResponseCode(ErrorCodes.WriteConditionNotMet.name);
                    response.setResponseMessage(ErrorCodes.WriteConditionNotMet.userMessage);
                    return new ResponseEntity<Object>(response, HttpStatus.OK);
                }
            }else{
                response.setResponseCode(ErrorCodes.WriteConditionNotMet.name);
                response.setResponseMessage(ErrorCodes.WriteConditionNotMet.userMessage + " - institution ID not exists");
                return new ResponseEntity<Object>(response, HttpStatus.OK);
            }
        }else{
            response.setResponseCode(ErrorCodes.WriteConditionNotMet.name);
            response.setResponseMessage(ErrorCodes.WriteConditionNotMet.userMessage + " - doctorID not exists");
            return new ResponseEntity<Object>(response, HttpStatus.OK);
        }
    }

    @RequestMapping(value = "/update/institution/{institutionID}", method = RequestMethod.POST)
    public ResponseEntity<?> update(@PathVariable("doctorID") Integer doctorID, @PathVariable("institutionID") Integer institutionID, @RequestBody Office office){
        Response response = new Response();
        ManageDoctor manageDoctor = new ManageDoctor();
        Doctor doctor = manageDoctor.getDoctorByID(doctorID);
        if(doctor!=null) {
            ManageInstitution manageInstitution = new ManageInstitution();
            Institution institution = manageInstitution.getInstitutionByID(institutionID);
            if (institution != null) {
                if (office.getId() != null) {
                    ManageOffice manageOffice = new ManageOffice();
                    Office officeFromDB = manageOffice.getOfficeByID(office.getId());
                    if (officeFromDB != null) {
                        boolean flag = false;
                        Set<Office> offices = doctor.getOffices();
                        for(Office off : offices){
                            if(off.getId().equals(office.getId()))
                                flag = true;
                        }
                        if (flag) {
                            institution.setAddress(null);
                            doctor.setOffices(null);
                            office.setInstitution(institution);
                            office.setDoctor(doctor);
                            if (manageOffice.updateOffice(office)) {
                                response.setResponseCode(ErrorCodes.OK.name);
                                response.setResponseMessage(ErrorCodes.OK.userMessage);
                                return new ResponseEntity<Object>(response, HttpStatus.OK);
                            } else {
                                response.setResponseCode(ErrorCodes.InternalError.name);
                                response.setResponseMessage(ErrorCodes.InternalError.userMessage);
                                return new ResponseEntity<Object>(response, HttpStatus.OK);
                            }
                        }else{
                            response.setResponseCode(ErrorCodes.WriteConditionNotMet.name);
                            response.setResponseMessage(ErrorCodes.WriteConditionNotMet.userMessage);
                            return new ResponseEntity<Object>(response, HttpStatus.OK);
                        }
                    } else {
                        response.setResponseCode(ErrorCodes.ResourceNotExists.name);
                        response.setResponseMessage(ErrorCodes.ResourceNotExists.userMessage);
                        return new ResponseEntity<Object>(response, HttpStatus.OK);
                    }
                } else {
                    response.setResponseCode(ErrorCodes.WriteConditionNotMet.name);
                    response.setResponseMessage(ErrorCodes.WriteConditionNotMet.userMessage);
                    return new ResponseEntity<Object>(response, HttpStatus.OK);
                }
            } else {
                response.setResponseCode(ErrorCodes.WriteConditionNotMet.name);
                response.setResponseMessage(ErrorCodes.WriteConditionNotMet.userMessage + " - institution ID not exists");
                return new ResponseEntity<Object>(response, HttpStatus.OK);
            }
        }else {
            response.setResponseCode(ErrorCodes.WriteConditionNotMet.name);
            response.setResponseMessage(ErrorCodes.WriteConditionNotMet.userMessage + " - doctorID not exists");
            return new ResponseEntity<Object>(response, HttpStatus.OK);
        }
    }

    @RequestMapping("/delete/{id}")
    public ResponseEntity<?> delete(@PathVariable("doctorID") Integer doctorID, @PathVariable(value = "id") int id){
        Response response = new Response();
        ManageDoctor manageDoctor = new ManageDoctor();
        Doctor doctor = manageDoctor.getDoctorByID(doctorID);
        if(doctor!=null){
            ManageOffice manageOffice = new ManageOffice();
            Office office = manageOffice.getOfficeByID(id);
            if (office != null) {
                if(office.getDoctor().getId()== doctorID) {
                    if (manageOffice.deleteOffice(office)) {
                        response.setResponseCode(ErrorCodes.OK.name);
                        response.setResponseMessage(ErrorCodes.OK.userMessage);
                        return new ResponseEntity<Object>(response, HttpStatus.OK);
                    } else {
                        response.setResponseCode(ErrorCodes.InternalError.name);
                        response.setResponseMessage(ErrorCodes.InternalError.userMessage);
                        return new ResponseEntity<Object>(response, HttpStatus.OK);
                    }
                }else{
                    response.setResponseCode(ErrorCodes.WriteConditionNotMet.name);
                    response.setResponseMessage(ErrorCodes.WriteConditionNotMet.userMessage + " - Doctor does not contain office with this OfficeID");
                    return new ResponseEntity<Object>(response, HttpStatus.OK);
                }
            }else{
                response.setResponseCode(ErrorCodes.ResourceNotExists.name);
                response.setResponseMessage(ErrorCodes.ResourceNotExists.userMessage);
                return new ResponseEntity<Object>(response, HttpStatus.OK);
            }

        }else{
            response.setResponseCode(ErrorCodes.WriteConditionNotMet.name);
            response.setResponseMessage(ErrorCodes.WriteConditionNotMet.userMessage + " - doctorID not exists");
            return new ResponseEntity<Object>(response, HttpStatus.OK);
        }
    }

    @RequestMapping("/{id}")
    public ResponseEntity<?> get(@PathVariable("doctorID") Integer doctorID, @PathVariable(value = "id") int id){
        Response response = new Response();
        ManageDoctor manageDoctor = new ManageDoctor();
        Doctor doctor = manageDoctor.getDoctorByID(doctorID);
        if(doctor!=null){
            ManageOffice manageOffice = new ManageOffice();
            Office office = manageOffice.getOfficeByID(id);
            if (office != null) {
                if(office.getDoctor().getId()==doctorID) {
                    response.setResponseCode(ErrorCodes.OK.name);
                    response.setResponseMessage(ErrorCodes.OK.userMessage);
                    response.addMapItem("office", office);
                    return new ResponseEntity<Object>(response, HttpStatus.OK);
                }else{
                    response.setResponseCode(ErrorCodes.WriteConditionNotMet.name);
                    response.setResponseMessage(ErrorCodes.WriteConditionNotMet.userMessage + " - Doctor does not contain office with this OfficeID");
                    return new ResponseEntity<Object>(response, HttpStatus.OK);
                }
            }else{
                response.setResponseCode(ErrorCodes.ResourceNotExists.name);
                response.setResponseMessage(ErrorCodes.ResourceNotExists.userMessage);
                return new ResponseEntity<Object>(response, HttpStatus.OK);
            }
        }else{
            response.setResponseCode(ErrorCodes.WriteConditionNotMet.name);
            response.setResponseMessage(ErrorCodes.WriteConditionNotMet.userMessage + " - doctorID not exists");
            return new ResponseEntity<Object>(response, HttpStatus.OK);
        }
    }
}
