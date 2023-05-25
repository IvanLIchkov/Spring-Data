package hiberspring.service.impl;

import com.google.gson.Gson;
import hiberspring.common.Constants;
import hiberspring.domain.dtos.ImportBranchDto;
import hiberspring.domain.entities.Branch;
import hiberspring.repository.BranchRepository;
import hiberspring.service.BranchService;
import hiberspring.util.FileUtil;
import hiberspring.util.ValidationUtil;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@Service
public class BranchServiceImpl implements BranchService {

    private static final String JSON_BRANCHES_PATH = "Exams/Hiberspring Inc Java DB Advanced Exam - 20 Dec 2018/src/main/resources/files/branches.json";

    private final BranchRepository branchRepository;
    private final FileUtil fileUtil;
    private final Gson gson;
    private final ModelMapper mapper;
    private final ValidationUtil validationUtil;

    public BranchServiceImpl(BranchRepository branchRepository, FileUtil fileUtil, Gson gson, ModelMapper mapper, ValidationUtil validationUtil) {
        this.branchRepository = branchRepository;
        this.fileUtil = fileUtil;
        this.gson = gson;
        this.mapper = mapper;
        this.validationUtil = validationUtil;
    }


    @Override
    public Boolean branchesAreImported() {
        return this.branchRepository.count() > 0;
    }

    @Override
    public String readBranchesJsonFile() throws IOException {
        return fileUtil.readFile(JSON_BRANCHES_PATH);
    }

    @Override
    public String importBranches(String branchesFileContent) {
        ImportBranchDto[] importBranchDtos = this.gson.fromJson(branchesFileContent, ImportBranchDto[].class);
        List<String> output = new ArrayList<>();
        for (ImportBranchDto importBranchDto : importBranchDtos) {

            if(!validationUtil.isValid(importBranchDto)){
                output.add(Constants.INCORRECT_DATA_MESSAGE);
                continue;
            }
            Branch branchToPersist = this.mapper.map(importBranchDto, Branch.class);

            this.branchRepository.save(branchToPersist);
            output.add(String.format(Constants.SUCCESSFUL_IMPORT_MESSAGE,"Branch", importBranchDto.getName()));
        }


        return String.join("\n", output);
    }
}
