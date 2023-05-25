package hiberspring.service.impl;

import hiberspring.common.Constants;
import hiberspring.domain.dtos.ImportProductDto;
import hiberspring.domain.dtos.ImportProductsDto;
import hiberspring.domain.entities.Branch;
import hiberspring.domain.entities.Product;
import hiberspring.repository.BranchRepository;
import hiberspring.repository.ProductRepository;
import hiberspring.service.ProductService;
import hiberspring.util.FileUtil;
import hiberspring.util.ValidationUtil;
import hiberspring.util.XmlParser;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import javax.xml.bind.JAXBException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class ProductServiceImpl implements ProductService {

    private static final String XML_PRODUCTS_PATH = "Exams/Hiberspring Inc Java DB Advanced Exam - 20 Dec 2018/src/main/resources/files/products.xml";

    private final ProductRepository productRepository;
    private final BranchRepository branchRepository;
    private final FileUtil fileUtil;
    private final ValidationUtil validationUtil;
    private final ModelMapper mapper;
    private final XmlParser xmlParser;

    public ProductServiceImpl(ProductRepository productRepository, BranchRepository branchRepository, FileUtil fileUtil, ValidationUtil validationUtil, ModelMapper mapper, XmlParser xmlParser) {
        this.productRepository = productRepository;
        this.branchRepository = branchRepository;
        this.fileUtil = fileUtil;
        this.validationUtil = validationUtil;
        this.mapper = mapper;
        this.xmlParser = xmlParser;
    }

    @Override
    public Boolean productsAreImported() {
        return this.productRepository.count() > 0;
    }

    @Override
    public String readProductsXmlFile() {
        return fileUtil.readFile(XML_PRODUCTS_PATH);
    }

    @Override
    public String importProducts() throws JAXBException, IOException {
        ImportProductsDto importProductsDto = this.xmlParser.parseXml(ImportProductsDto.class, readProductsXmlFile());
        List<String> output = new ArrayList<>();

        for (ImportProductDto product : importProductsDto.getProducts()) {
            if(!validationUtil.isValid(product)){
                output.add(Constants.INCORRECT_DATA_MESSAGE);
                continue;
            }
            Optional<Product> optProduct = this.productRepository.findByName(product.getName());
            Optional<Branch> optBranch = this.branchRepository.findByName(product.getBranchName());

            if(optProduct.isPresent() || optBranch.isEmpty()){
                output.add(Constants.INCORRECT_DATA_MESSAGE);
                continue;
            }
            Product productToPersist = this.mapper.map(product, Product.class);
            productToPersist.setBranch(optBranch.get());
            this.productRepository.save(productToPersist);
            output.add(String.format(Constants.SUCCESSFUL_IMPORT_MESSAGE,"Product", product.getName()));
        }

        return String.join("\n", output);
    }
}
