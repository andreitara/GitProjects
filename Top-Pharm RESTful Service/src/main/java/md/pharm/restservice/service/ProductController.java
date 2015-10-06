package md.pharm.restservice.service;

import md.pharm.hibernate.doctor.Doctor;
import md.pharm.hibernate.doctor.ManageDoctor;
import md.pharm.hibernate.product.ManageProduct;
import md.pharm.hibernate.product.Product;
import md.pharm.restservice.util.ErrorCodes;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * Created by Andrei on 10/6/2015.
 */

@RestController
@RequestMapping("toppharm/medical/product")
public class ProductController {

    @RequestMapping("/all")
    public ResponseEntity<?> getAll(){
        Response response = new Response();
        ManageProduct manageProduct = new ManageProduct();
        List<Product> list = manageProduct.getProducts();
        if(list!=null){
            response.setResponseCode(ErrorCodes.OK.name);
            response.setResponseMessage(ErrorCodes.OK.userMessage);
            response.addMapItem("products", list);
            return new ResponseEntity<Object>(response, HttpStatus.OK);
        }else{
            response.setResponseCode(ErrorCodes.InternalError.name);
            response.setResponseMessage(ErrorCodes.InternalError.userMessage);
            return new ResponseEntity<Object>(response, HttpStatus.OK);
        }
    }

    @RequestMapping(value = "/create", method = RequestMethod.POST)
    public ResponseEntity<?> create(@RequestBody Product product){
        Response response = new Response();
        ManageProduct manage = new ManageProduct();
        if(product.getId()== null) {
            if (true) {//TODO condition if not exists this doctor in DB
                Integer id = manage.addProduct(product);
                if (id != null) {
                    response.setResponseCode(ErrorCodes.Created.name);
                    response.setResponseMessage(ErrorCodes.Created.userMessage);
                    product.setId(id);
                    response.addMapItem("product", product);
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
    public ResponseEntity<?> createUser(@RequestBody Product product){
        Response response = new Response();
        ManageProduct manage = new ManageProduct();
        if(product.getId()!=null) {
            Product productFromDB = manage.getProductByID(product.getId());
            if (productFromDB != null) {
                if (manage.updateProduct(product)) {
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
        ManageProduct manage = new ManageProduct();
        Product product = manage.getProductByID(id);
        if(product!=null){
            if(manage.delete(product)){
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
        ManageProduct manageProduct = new ManageProduct();
        Product product = manageProduct.getProductByID(id);
        if(product!=null) {
            response.setResponseCode(ErrorCodes.OK.name);
            response.setResponseMessage(ErrorCodes.OK.userMessage);
            response.addMapItem("product", product);
            return new ResponseEntity<Object>(response, HttpStatus.OK);
        }else{
            response.setResponseCode(ErrorCodes.ResourceNotExists.name);
            response.setResponseMessage(ErrorCodes.ResourceNotExists.userMessage);
            return new ResponseEntity<Object>(response, HttpStatus.OK);
        }
    }
}
