package md.pharm.restservice.service;

import md.pharm.hibernate.common.Address;
import md.pharm.hibernate.doctor.Doctor;
import md.pharm.hibernate.doctor.ManageDoctor;
import md.pharm.hibernate.institution.Institution;
import md.pharm.hibernate.institution.ManageInstitution;
import md.pharm.hibernate.institution.ManageOffice;
import md.pharm.hibernate.institution.Office;
import md.pharm.restservice.util.ErrorCodes;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

/**
 * Created by Andrei on 10/6/2015.
 */

@RestController
@RequestMapping("/toppharm/medical/institution/{institutionID}/address")
public class InstitutionAddressController {

    @RequestMapping("/")
    public ResponseEntity<?> get(@PathVariable("institutionID") Integer institutionID){
        Response response = new Response();
        ManageInstitution manageInstitution = new ManageInstitution();
        Institution institution = manageInstitution.getInstitutionByID(institutionID);
        if(institution!=null){
            Address address = manageInstitution.getInstitutionAddressByInstitutionID(institutionID);
            if(address != null) {
                response.setResponseCode(ErrorCodes.OK.name);
                response.setResponseMessage(ErrorCodes.OK.userMessage);
                response.addMapItem("address", address);
                return new ResponseEntity<Object>(response, HttpStatus.OK);
            }else{
                response.setResponseCode(ErrorCodes.ResourceNotExists.name);
                response.setResponseMessage(ErrorCodes.ResourceNotExists.userMessage);
                return new ResponseEntity<Object>(response, HttpStatus.OK);
            }
        }else{
            response.setResponseCode(ErrorCodes.WriteConditionNotMet.name);
            response.setResponseMessage(ErrorCodes.WriteConditionNotMet.userMessage + " - institutionID not exists");
            return new ResponseEntity<Object>(response, HttpStatus.OK);
        }
    }

    @RequestMapping(value = "/create", method = RequestMethod.POST)
    public ResponseEntity<?> create(@PathVariable("institutionID") Integer institutionID, @RequestBody Address address) {
        Response response = new Response();
        ManageInstitution manageInstitution = new ManageInstitution();
        Institution institution = manageInstitution.getInstitutionByID(institutionID);
        if (institution != null) {
            Address addressFromDB = manageInstitution.getInstitutionAddressByInstitutionID(institutionID);
            if (addressFromDB == null) {
                address.setInstitution(institution);
                Integer id = manageInstitution.addInstitutionAddress(address);
                if (id != null) {
                    response.setResponseCode(ErrorCodes.Created.name);
                    response.setResponseMessage(ErrorCodes.Created.userMessage);
                    address.setId(id);
                    response.addMapItem("address", address);
                    return new ResponseEntity<Object>(response, HttpStatus.CREATED);
                } else {
                    response.setResponseCode(ErrorCodes.InternalError.name);
                    response.setResponseMessage(ErrorCodes.InternalError.userMessage);
                    return new ResponseEntity<Object>(response, HttpStatus.OK);
                }
            } else {
                response.setResponseCode(ErrorCodes.WriteConditionNotMet.name);
                response.setResponseMessage(ErrorCodes.WriteConditionNotMet.userMessage + " - address exists");
                return new ResponseEntity<Object>(response, HttpStatus.OK);
            }
        } else {
            response.setResponseCode(ErrorCodes.WriteConditionNotMet.name);
            response.setResponseMessage(ErrorCodes.WriteConditionNotMet.userMessage + " - institutionID not exists");
            return new ResponseEntity<Object>(response, HttpStatus.OK);
        }
    }

    @RequestMapping(value = "/update", method = RequestMethod.POST)
    public ResponseEntity<?> update(@PathVariable("institutionID") Integer institutionID, @RequestBody Address address) {
        Response response = new Response();
        ManageInstitution manageInstitution = new ManageInstitution();
        Institution institution = manageInstitution.getInstitutionByID(institutionID);
        if (institution != null) {
            Address addressFromDB = manageInstitution.getInstitutionAddressByInstitutionID(institutionID);
            if (addressFromDB != null) {
                address.setInstitution(institution);
                address.setId(addressFromDB.getId());
                if (manageInstitution.updateAddress(address)) {
                    response.setResponseCode(ErrorCodes.OK.name);
                    response.setResponseMessage(ErrorCodes.OK.userMessage);
                    response.addMapItem("address", address);
                    return new ResponseEntity<Object>(response, HttpStatus.CREATED);
                } else {
                    response.setResponseCode(ErrorCodes.InternalError.name);
                    response.setResponseMessage(ErrorCodes.InternalError.userMessage);
                    return new ResponseEntity<Object>(response, HttpStatus.OK);
                }
            } else {
                response.setResponseCode(ErrorCodes.WriteConditionNotMet.name);
                response.setResponseMessage(ErrorCodes.WriteConditionNotMet.userMessage + " - address exists");
                return new ResponseEntity<Object>(response, HttpStatus.OK);
            }
        } else {
            response.setResponseCode(ErrorCodes.WriteConditionNotMet.name);
            response.setResponseMessage(ErrorCodes.WriteConditionNotMet.userMessage + " - institutionID not exists");
            return new ResponseEntity<Object>(response, HttpStatus.OK);
        }
    }
}
