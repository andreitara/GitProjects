package md.pharm.restservice.service;

import md.pharm.hibernate.product.ManageProduct;
import md.pharm.hibernate.product.Objective;
import md.pharm.hibernate.product.Product;
import md.pharm.restservice.util.ErrorCodes;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Set;

/**
 * Created by Andrei on 10/6/2015.
 */

@RestController
@RequestMapping("/toppharm/medical/product/{productID}/objective")
public class ProductObjectiveController {

    @RequestMapping("/all")
    public ResponseEntity<?> getAll(@PathVariable(value = "productID") int productID){
        Response response = new Response();
        ManageProduct manageProduct = new ManageProduct();
        Set<Objective> list = manageProduct.getObjectivesByProductID(productID);
        if(list!=null){
            response.setResponseCode(ErrorCodes.OK.name);
            response.setResponseMessage(ErrorCodes.OK.userMessage);
            response.addMapItem("objectives", list);
            return new ResponseEntity<Object>(response, HttpStatus.OK);
        }else{
            response.setResponseCode(ErrorCodes.ResourceNotFound.name);
            response.setResponseMessage(ErrorCodes.ResourceNotFound.userMessage);
            return new ResponseEntity<Object>(response, HttpStatus.OK);
        }
    }

    @RequestMapping(value = "/create", method = RequestMethod.POST)
    public ResponseEntity<?> create(@PathVariable(value = "productID") int productID, @RequestBody Objective objective){
        Response response = new Response();
        ManageProduct manage = new ManageProduct();
        Product product = manage.getProductByID(productID);
        if(product != null) {
            if (true) {//TODO condition if not exists this product in DB
                product.setObjectives(null);
                objective.setProduct(product);
                Integer id = manage.addProductObjective(objective);
                if (id != null) {
                    response.setResponseCode(ErrorCodes.Created.name);
                    response.setResponseMessage(ErrorCodes.Created.userMessage);
                    product.setId(id);
                    response.addMapItem("objective", objective);
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
    public ResponseEntity<?> createUser(@PathVariable(value = "productID") Integer productID, @RequestBody Objective objective) {
        Response response = new Response();
        ManageProduct manage = new ManageProduct();
        if (objective.getId() != null) {
            Product product = manage.getProductByObjectiveID(objective.getId());
            if (product.getId().equals(productID)) {
                Objective objectiveFromDB = manage.getObjectiveByID(objective.getId());
                if (objectiveFromDB != null) {
                    if (manage.updateObjective(objective)) {
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
            } else {
                response.setResponseCode(ErrorCodes.WriteConditionNotMet.name);
                response.setResponseMessage(ErrorCodes.WriteConditionNotMet.userMessage);
                return new ResponseEntity<Object>(response, HttpStatus.OK);
            }
        } else {
            response.setResponseCode(ErrorCodes.WriteConditionNotMet.name);
            response.setResponseMessage(ErrorCodes.WriteConditionNotMet.userMessage);
            return new ResponseEntity<Object>(response, HttpStatus.OK);
        }
    }

    @RequestMapping("/delete/{id}")
    public ResponseEntity<?> delete(@PathVariable(value = "productID") Integer productID, @PathVariable(value = "id") int id){
        Response response = new Response();
        ManageProduct manage = new ManageProduct();
        Product product = manage.getProductByObjectiveID(productID);
        Objective objective = manage.getObjectiveByID(id);
        if(product!=null && objective==null && product.getId().equals(productID)){
            objective.setProduct(null);
            if(manage.deleteObjective(objective)){
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
    public ResponseEntity<?> get(@PathVariable(value = "productID") Integer productID, @PathVariable(value = "id") int id){
        Response response = new Response();
        ManageProduct manageProduct = new ManageProduct();
        Product product = manageProduct.getProductByObjectiveID(id);
        Objective objective = manageProduct.getObjectiveByID(id);
        if(product!=null && product.getId().equals(productID)) {
            response.setResponseCode(ErrorCodes.OK.name);
            response.setResponseMessage(ErrorCodes.OK.userMessage);
            response.addMapItem("objective", objective);
            return new ResponseEntity<Object>(response, HttpStatus.OK);
        }else{
            response.setResponseCode(ErrorCodes.ResourceNotExists.name);
            response.setResponseMessage(ErrorCodes.ResourceNotExists.userMessage);
            return new ResponseEntity<Object>(response, HttpStatus.OK);
        }
    }
}
