package com.gft.MerceariaCRUD.controllers;

import com.gft.MerceariaCRUD.entities.Supplier;
import com.gft.MerceariaCRUD.services.SupplierService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.validation.Valid;

@Controller
@RequestMapping("supplier")
public class SupplierController {

    @Autowired
    private SupplierService supplierService;

    @RequestMapping("/edit")
    public ModelAndView editSupplier(@RequestParam(required = false) Long id) {
        ModelAndView mv = new ModelAndView("supplier/form.html");

        Supplier supplier;

        //Filter edit or save actions
        if(id==null){

            supplier = new Supplier();

        } else {
            try{
                supplier = supplierService.getSupplier(id);

            } catch (Exception e){
                supplier = new Supplier();
                mv.addObject("message", e.getMessage());
            }
        }
        mv.addObject("supplier", supplier);

        return mv;
    }

    @RequestMapping(method = RequestMethod.POST, path = "edit")
    public ModelAndView saveSupplier(@Valid Supplier supplier, BindingResult bindingResult) {

        ModelAndView mv = new ModelAndView("supplier/form.html");

        if(bindingResult.hasErrors()){
            mv.addObject("supplier", supplier);
            return mv;
        }

        //Filter edit from create
        if(supplier.getId() == null){
            mv.addObject("supplier", new Supplier());
        }
        else {
            mv.addObject("supplier", supplier);
        }

        //save projeto
        supplierService.saveSupplier(supplier);

        mv.addObject("message", "Fornecedor salvo com sucesso.");

        return mv;
    }

    @RequestMapping //http://localhost:8080/supplier
    public ModelAndView listSuppliers() {

        ModelAndView mv = new ModelAndView("supplier/list.html");
        mv.addObject("list", supplierService.listSuppliers());
        return mv;
    }

    @RequestMapping("/delete")
    public ModelAndView deleteSupplier(@RequestParam Long id, RedirectAttributes redirectAttributes) {

        ModelAndView mv = new ModelAndView("redirect:/supplier");

        try{

            supplierService.deleteSupplier(id);
            redirectAttributes.addFlashAttribute("message", "Fornecedor excluido com sucesso.");

        } catch (Exception e){

            redirectAttributes.addFlashAttribute("message", "Ups! Algo deu errado: "+ e.getMessage());
        }

        return mv;
    }




}
