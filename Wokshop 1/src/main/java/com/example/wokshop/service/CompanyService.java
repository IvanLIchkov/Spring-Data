package com.example.wokshop.service;

import org.springframework.stereotype.Service;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;

@Service
public class CompanyService {

    public boolean areImported() {
        return false;
    }

    public String getCompaniesText() throws IOException {
        return Files.readString(Path.of("/Users/scopi/Desktop/Spring-Data/Wokshop 1/src/main/resources/files/xmls/companies.xml"));
    }
}
