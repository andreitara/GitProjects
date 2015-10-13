package md.pharm.restservice.service.product;

import md.pharm.hibernate.product.ManageProduct;
import md.pharm.hibernate.product.Objective;
import md.pharm.hibernate.product.Product;
import md.pharm.restservice.service.Response;
import md.pharm.restservice.util.ErrorCodes;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.lang.reflect.Method;
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
            Product product = manage.getProductByID(productID);
            Objective objectiveFromDB = manage.getObjectiveByID(objective.getId());
            Set<Objective> objectives = product.getObjectives();
            boolean flag = false;
            if (product != null && objectiveFromDB != null) {
                for (Objective productObjective : objectives) {
                    if (objectiveFromDB.getId() == productObjective.getId())
                        flag = true;
                }
            }
            if (flag) {
                product.setObjectives(null);
                objective.setProduct(product);
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

    @RequestMapping(value = "/delete/{id}", method = RequestMethod.DELETE)
    public ResponseEntity<?> delete(@PathVariable(value = "productID") Integer productID, @PathVariable(value = "id") int id){
        Response response = new Response();
        ManageProduct manage = new ManageProduct();
        Product product = manage.getProductByID(productID);
        Objective objective = manage.getObjectiveByID(id);
        Set<Objective> objectives = product.getObjectives();
        boolean flag = false;
        if (product != null && objective != null) {
            for (Objective productObjective : objectives) {
                if (objective.getId() == productObjective.getId())
                    flag = true;
            }
        }
        if(flag){
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
        Product product = manageProduct.getProductByID(productID);
        Objective objective = manageProduct.getObjectiveByID(id);
        Set<Objective> objectives = product.getObjectives();
        boolean flag = false;
        if(product!=null && objective!=null) {
            for (Objective productObjective : objectives) {
                if(objective.getId()==productObjective.getId())
                    flag=true;
            }
        }
        if(flag) {
            response.setResponseCode(ErrorCodes.OK.name);
            response.setResponseMessage(ErrorCodes.OK.userMessage);
            response.setObject(objective);
            //response.addMapItem("objective", objective);
            return new ResponseEntity<Object>(response, HttpStatus.OK);
        }else{
            response.setResponseCode(ErrorCodes.ResourceNotExists.name);
            response.setResponseMessage(ErrorCodes.ResourceNotExists.userMessage);
            return new ResponseEntity<Object>(response, HttpStatus.OK);
        }
    }
}