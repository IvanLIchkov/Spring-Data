package com.example.cardealer.services.supplier;

import com.example.cardealer.entities.supplier.SupplierNoImportFromAbroadDto;
import com.example.cardealer.repositories.SupplierRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SupplierServiceImpl implements SupplierService {

    private final SupplierRepository supplierRepository;

    @Autowired
    public SupplierServiceImpl(SupplierRepository supplierRepository) {
        this.supplierRepository = supplierRepository;
    }

    @Override
    public List<SupplierNoImportFromAbroadDto> selectAllSupplierWithNoImport() {
        ModelMapper mapper = new ModelMapper();
        List<SupplierNoImportFromAbroadDto> supplierNoImportFromAbroadDtos = this.supplierRepository.selectAllThatDontImportParts();
        return supplierNoImportFromAbroadDtos;
    }
}
