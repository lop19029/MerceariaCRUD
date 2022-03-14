package com.gft.MerceariaCRUD.services;

import com.gft.MerceariaCRUD.entities.Supplier;
import com.gft.MerceariaCRUD.repositories.SupplierRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class SupplierService {

    @Autowired
    public SupplierRepository supplierRepository;

    public Supplier saveSupplier (Supplier supplier) {

        return supplierRepository.save(supplier);
    }

    public List<Supplier> listSuppliers() {
        return supplierRepository.findAll();
    }

    public Supplier getSupplier(Long id) throws Exception {
        Optional<Supplier> supplier = supplierRepository.findById(id);

        if(supplier.isEmpty()) {
            throw new Exception("Fornecedor não encontrado.");
        }
        return supplier.get();
    }

    public void deleteSupplier (Long id) {
        supplierRepository.deleteById(id);
    }

}
