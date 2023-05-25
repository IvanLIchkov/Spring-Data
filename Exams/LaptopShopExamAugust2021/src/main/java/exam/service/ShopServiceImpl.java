package exam.service;

import exam.model.Shop.ImportShopDto;
import exam.model.Shop.ImportShopsDto;
import exam.model.Shop.Shop;
import exam.model.Town.Town;
import exam.repository.ShopRepository;
import exam.repository.TownRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Unmarshaller;
import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class ShopServiceImpl implements ShopService {

    private static final String XML_SHOPS_PATH = "/Users/scopi/Downloads/LaptopShopExamAugust2021/src/main/resources/files/xml/shops.xml";

    private final ShopRepository shopRepository;
    private final TownRepository townRepository;

    private final ModelMapper mapper;

    @Autowired
    public ShopServiceImpl(ShopRepository shopRepository, TownRepository townRepository) {
        this.shopRepository = shopRepository;
        this.townRepository = townRepository;
        this.mapper = new ModelMapper();
    }

    @Override
    public boolean areImported() {
        return this.shopRepository.count() > 0;
    }

    @Override
    public String readShopsFileContent() throws IOException {
        String output = new String(Files.readAllBytes(Path.of(XML_SHOPS_PATH)));
        return output;
    }

    @Override
    public String importShops() throws JAXBException, IOException {
        List<String> result = new ArrayList<>();

        JAXBContext context = JAXBContext.newInstance(ImportShopsDto.class);
        Unmarshaller unmarshaller = context.createUnmarshaller();
        ImportShopsDto shops = (ImportShopsDto) unmarshaller.unmarshal(new FileReader(XML_SHOPS_PATH));

        for (ImportShopDto shop : shops.getShops()) {
            if(!shop.isValidShop()){
                result.add("Invalid Shop");
                continue;
            }
            Optional<Shop> optionalShop = this.shopRepository.findByName(shop.getName());
            if(optionalShop.isPresent()){
                result.add("Invalid Shop");
                continue;
            }
            Optional<Town> optionalTown = this.townRepository.findByName(shop.getTown().getName());
            Shop shopToPersist = this.mapper.map(shop, Shop.class);
            shopToPersist.setTown(optionalTown.get());
            this.shopRepository.save(shopToPersist);
            result.add(String.format("Successfully imported Shop %s - %.0f ",shop.getName(), shop.getIncome()));
        }


        return String.join("\n", result);
    }
}
