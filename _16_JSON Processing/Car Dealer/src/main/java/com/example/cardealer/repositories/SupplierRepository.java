package com.example.cardealer.repositories;

import com.example.cardealer.entities.supplier.Supplier;
import com.example.cardealer.entities.supplier.SupplierNoImportFromAbroadDto;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;

import java.util.List;

@Repository
public interface SupplierRepository extends JpaRepository<Supplier, Long> {

    @Query("SELECT new com.example.cardealer.entities.supplier.SupplierNoImportFromAbroadDto( " +
            "s.id, s.name, COUNT(s)) " +
            "FROM Supplier AS s " +
            "JOIN Part AS p " +
            "ON s.id = p.supplier.id " +
            "WHERE s.isImporter = false " +
            "GROUP BY s.id")
    List<SupplierNoImportFromAbroadDto> selectAllThatDontImportParts();
}
