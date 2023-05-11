package com.example.cardealer.services.supplier;

import com.example.cardealer.entities.supplier.SupplierNoImportFromAbroadDto;

import java.util.List;

public interface SupplierService {
    List<SupplierNoImportFromAbroadDto> selectAllSupplierWithNoImport();
}
