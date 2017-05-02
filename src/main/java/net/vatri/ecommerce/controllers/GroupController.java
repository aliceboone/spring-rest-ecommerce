package net.vatri.ecommerce.controllers;

import net.vatri.ecommerce.models.ProductGroup;
import net.vatri.ecommerce.services.EcommerceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/group")
public class GroupController {

    @Autowired
    EcommerceService ecommerceService;

    @RequestMapping(method = RequestMethod.GET)
    public List<ProductGroup> index() {
        return ecommerceService.getGroups();
    }

    @RequestMapping("/{id}")
    public ProductGroup view(@PathVariable("id") long id){
        return ecommerceService.getGroup(id);
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.POST)
    public ProductGroup edit(@PathVariable(value = "id", required = false) long id, @RequestBody ProductGroup group){

        ProductGroup updatedGroup = ecommerceService.getGroup(id);

        if(updatedGroup == null){
            return null;
        }

        updatedGroup.setGroupName(group.getGroupName());
        updatedGroup.setPrice(group.getPrice());

        return ecommerceService.saveGroup(updatedGroup);
    }

    @RequestMapping(method = RequestMethod.POST)
    public ProductGroup create(@RequestBody ProductGroup group){
        return  ecommerceService.saveGroup(group);
    }

    // Todo: add delete method

}