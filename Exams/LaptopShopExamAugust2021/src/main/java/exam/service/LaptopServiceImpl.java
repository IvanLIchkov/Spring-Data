package exam.service;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import exam.model.Laptop.ImportJsonLaptopsDto;
import exam.model.Laptop.Laptop;
import exam.model.Laptop.TheBestLaptopDto;
import exam.model.Laptop.WarrantyType;
import exam.model.Shop.Shop;
import exam.repository.LaptopRepository;
import exam.repository.ShopRepository;
import org.modelmapper.ModelMapper;
import org.modelmapper.PropertyMap;
import org.modelmapper.TypeMap;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

@Service
public class LaptopServiceImpl implements LaptopService {

    private static final Path JSON_LAPTOPS_PATH = Path.of("/Users/scopi/Downloads/LaptopShopExamAugust2021/src/main/resources/files/json/laptops.json");

    private final LaptopRepository laptopRepository;
    private final ShopRepository shopRepository;
    private final ModelMapper mapper;
    private final Gson gson;

    @Autowired
    public LaptopServiceImpl(LaptopRepository laptopRepository, ShopRepository shopRepository) {
        this.laptopRepository = laptopRepository;
        this.shopRepository = shopRepository;
        this.mapper = new ModelMapper();

        TypeMap<ImportJsonLaptopsDto, Laptop> map = this.mapper.createTypeMap(ImportJsonLaptopsDto.class, Laptop.class);
        map.addMappings(m-> m.map(ImportJsonLaptopsDto::getWarrantyType, Laptop::setWarrantyType));

        this.gson = new GsonBuilder()
                .setPrettyPrinting()
                .create();
    }

    @Override
    public boolean areImported() {
        return this.laptopRepository.count() > 0;
    }

    @Override
    public String readLaptopsFileContent() throws IOException {
        return Files.readString(JSON_LAPTOPS_PATH);
    }

    @Override
    public String importLaptops() throws IOException {
        ImportJsonLaptopsDto[] laptops = this.gson.fromJson(readLaptopsFileContent(), ImportJsonLaptopsDto[].class);

        List<String> result = new ArrayList<>();
        for (ImportJsonLaptopsDto laptop : laptops) {
            if(!laptop.isValidLaptop()){
                result.add("Invalid Laptop");
                continue;
            }
            Optional<Laptop> optLaptop = this.laptopRepository.findByMacAddress(laptop.getMacAddress());
            if(optLaptop.isPresent()){
                result.add("Invalid Laptop");
                continue;
            }
            Laptop laptopToPersist = this.mapper.map(laptop, Laptop.class);
            Optional<Shop> optionalShop = shopRepository.findByName(laptop.getShop().getName());
            laptopToPersist.setShop(optionalShop.get());
            this.laptopRepository.save(laptopToPersist);
            result.add(String.format("Successfully imported Laptop %s - %.2f - %d - %d",laptop.getMacAddress(),laptop.getCpuSpeed(),laptop.getRam(), laptop.getStorage()));
        }


        return String.join("\n", result);
    }

    @Override
    public String exportBestLaptops() {
        List<Laptop> bestLaptopsRequest = this.laptopRepository.findAllByOrderByCpuSpeedDescRamDescStorageDescMacAddress();

        TheBestLaptopDto[] bestLaptops = this.mapper.map(bestLaptopsRequest, TheBestLaptopDto[].class);
        List<String> result = new ArrayList<>();

        Arrays.stream(bestLaptops).forEach(laptop -> {
            result.add(laptop.toString());
        });


        return String.join("\n", result);
    }
}
