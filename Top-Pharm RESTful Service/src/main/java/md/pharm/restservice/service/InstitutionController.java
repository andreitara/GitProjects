package md.pharm.restservice.service;

import md.pharm.hibernate.common.Address;
import md.pharm.hibernate.institution.Institution;
import md.pharm.hibernate.institution.ManageInstitution;
import md.pharm.restservice.util.ErrorCodes;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * Created by c-andrtara on 10/5/2015.
 */
@RestController
@RequestMapping("toppharm/medical/institution")
public class InstitutionController {

    @RequestMapping("/all")
    public ResponseEntity<?> getAll(){
        Response response = new Response();
        ManageInstitution manageInstitution = new ManageInstitution();
        List<Institution> list = manageInstitution.getInstitutions();
        if(list!=null){
            response.setResponseCode(ErrorCodes.OK.name);
            response.setResponseMessage(ErrorCodes.OK.userMessage);
            response.addMapItem("institutions", list);
            return new ResponseEntity<Object>(response, HttpStatus.OK);
        }else{
            response.setResponseCode(ErrorCodes.InternalError.name);
            response.setResponseMessage(ErrorCodes.InternalError.userMessage);
            return new ResponseEntity<Object>(response, HttpStatus.OK);
        }
    }

    @RequestMapping(value = "/create", method = RequestMethod.POST)
     public ResponseEntity<?> create(@RequestBody Institution institution){
        Response response = new Response();
        ManageInstitution manage = new ManageInstitution();
        Institution institutionFromDB = manage.getInstitutionByLongName(institution.getLongName());
        if(institutionFromDB == null) {
            Address address = institution.getAddress();
            address.setInstitution(institution);
            Integer id = manage.addInstitution(institution);
            if (id != null) {
                address.setInstitution(null);
                response.setResponseCode(ErrorCodes.Created.name);
                response.setResponseMessage(ErrorCodes.Created.userMessage);
                response.addMapItem("institution", institution);
                return new ResponseEntity<Object>(response, HttpStatus.CREATED);
            } else {
                response.setResponseCode(ErrorCodes.InternalError.name);
                response.setResponseMessage(ErrorCodes.InternalError.userMessage);
                return new ResponseEntity<Object>(response, HttpStatus.OK);
            }
        }else{
            response.setResponseCode(ErrorCodes.AccountAlreadyExists.name);
            response.setResponseMessage(ErrorCodes.AccountAlreadyExists.userMessage);
            return new ResponseEntity<>(null, HttpStatus.OK);
        }
    }

    @RequestMapping(value = "/update", method = RequestMethod.POST)
    public ResponseEntity<?> createUser(@RequestBody Institution institution){
        Response response = new Response();
        ManageInstitution manage = new ManageInstitution();
        if(institution.getId()>0) {
            Institution institutionFromDB = manage.getInstitutionByID(institution.getId());
            if (institutionFromDB != null) {
                int addressID = institutionFromDB.getAddress().getId();
                Address address = institution.getAddress();
                if(address!=null) address.setId(addressID);
                if (manage.updateInstitution(institution)) {
                    response.setResponseCode(ErrorCodes.OK.name);
                    response.setResponseMessage(ErrorCodes.OK.userMessage);
                    response.addMapItem("institution", institution);
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
        ManageInstitution manageInstitution = new ManageInstitution();
        Institution institution = manageInstitution.getInstitutionByID(id);
        if(institution!=null){
            if(manageInstitution.deleteInstitution(institution)){
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
        ManageInstitution manageInstitution = new ManageInstitution();
        Institution institution = manageInstitution.getInstitutionByID(id);
        if(institution!=null) {
            Address address = institution.getAddress();
            if(address!=null) address.setInstitution(null);
            response.setResponseCode(ErrorCodes.InternalError.name);
            response.setResponseMessage(ErrorCodes.InternalError.userMessage);
            response.addMapItem("institution", institution);
            return new ResponseEntity<Object>(response, HttpStatus.OK);
        }else{
            response.setResponseCode(ErrorCodes.ResourceNotExists.name);
            response.setResponseMessage(ErrorCodes.ResourceNotExists.userMessage);
            return new ResponseEntity<Object>(response, HttpStatus.OK);
        }
    }
}
