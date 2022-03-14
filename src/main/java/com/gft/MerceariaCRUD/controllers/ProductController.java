package com.gft.MerceariaCRUD.controllers;

import com.gft.MerceariaCRUD.entities.Product;
import com.gft.MerceariaCRUD.entities.Supplier;
import com.gft.MerceariaCRUD.services.ProductService;
import com.gft.MerceariaCRUD.services.SupplierService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.validation.Valid;
import java.util.Base64;
import java.util.List;

@Controller
@RestController
@RequestMapping("product")
public class ProductController {


    @Autowired
    private ProductService productService;

    @Autowired
    private SupplierService supplierService;

    @RequestMapping("/edit")
    public ModelAndView editProduct(@RequestParam(required = false) Long id) {
        ModelAndView mv = new ModelAndView("product/form.html");

        Product product;

        //Filter edit or save actions
        if(id==null){

            product = new Product();

        } else {
            try{
                product = productService.getProduct(id);

            } catch (Exception e){
                product = new Product();
                mv.addObject("message", e.getMessage());
            }
        }
        mv.addObject("product", product);
        mv.addObject("suppliersList", supplierService.listSuppliers());

        return mv;
    }

    @RequestMapping(method = RequestMethod.POST, path = "edit")
    public ModelAndView saveProduct(@Valid Product product, BindingResult bindingResult) {

        ModelAndView mv = new ModelAndView("product/form.html");

        if(bindingResult.hasErrors()){
            mv.addObject("product", product);
            mv.addObject("suppliersList", supplierService.listSuppliers());
            return mv;
        }

        //Filter edit from create
        if(product.getId() == null){
            mv.addObject("product", new Product());
        }
        else {
            mv.addObject("product", product);
        }

        //save product
        productService.saveProduct(product);

        mv.addObject("message", "Produto salvo com sucesso.");
        mv.addObject("suppliersList", supplierService.listSuppliers());

        return mv;
    }

    @RequestMapping //http://localhost:8080/product
    public ModelAndView listProducts() {
        ModelAndView mv = new ModelAndView("product/list.html");
        mv.addObject("list", productService.listProducts(null));
        return mv;
    }

    @RequestMapping(method = RequestMethod.GET, path = "/json")
    public List<Product> listProductsJson(String name) {
        byte[] decodedBytes = Base64.getDecoder().decode(name);
        String nameConvert = new String(decodedBytes);
        return productService.listProducts(nameConvert);
    }

    @RequestMapping("/details")
    public ModelAndView viewProductDetails(@RequestParam Long id) {
        ModelAndView mv = new ModelAndView("product/details.html");
        Product product;
        try {
            product = productService.getProduct(id);

        } catch (Exception e) {

            product = new Product();
            mv.addObject("message", e.getMessage());

        }
        mv.addObject("product", product);
        return mv;
    }

    @RequestMapping("/delete")
    public ModelAndView deleteProducts(@RequestParam Long id, RedirectAttributes redirectAttributes) {

        ModelAndView mv = new ModelAndView("redirect:/product");

        try{

            productService.deleteProduct(id);
            redirectAttributes.addFlashAttribute("message", "Produto excluido com sucesso.");

        } catch (Exception e){

            redirectAttributes.addFlashAttribute("message", "Ups! Algo deu errado: "+ e.getMessage());
        }

        return mv;
    }
}
