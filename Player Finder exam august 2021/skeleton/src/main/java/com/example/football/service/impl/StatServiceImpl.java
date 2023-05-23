package com.example.football.service.impl;

import com.example.football.models.dto.ImportStatDto;
import com.example.football.models.dto.ImportStatsDto;
import com.example.football.models.entity.Stat;
import com.example.football.repository.StatRepository;
import com.example.football.service.StatService;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Unmarshaller;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Optional;

//ToDo - Implement all methods
@Service
public class StatServiceImpl implements StatService {

    private static final String XML_STATS_PATH = "/Users/scopi/Desktop/Spring-Data/Player Finder exam august 2021/skeleton/src/main/resources/files/xml/stats.xml";

    private final StatRepository statRepository;
    private final ModelMapper mapper;

    public StatServiceImpl(StatRepository statRepository) {
        this.statRepository = statRepository;
        this.mapper = new ModelMapper();
    }

    @Override
    public boolean areImported() {
        return this.statRepository.count() > 0;
    }

    @Override
    public String readStatsFileContent() throws IOException {
        return Files.readString(Path.of(XML_STATS_PATH));
    }

    @Override
    public String importStats() throws JAXBException, IOException {

        JAXBContext context = JAXBContext.newInstance(ImportStatsDto.class);
        Unmarshaller unmarshaller = context.createUnmarshaller();
        ImportStatsDto statsDto = (ImportStatsDto) unmarshaller.unmarshal(Files.newBufferedReader(Path.of(XML_STATS_PATH)));

        StringBuilder output = new StringBuilder();

        for (ImportStatDto stat : statsDto.getStats()) {
            if(!stat.isValidStat()){
                output.append("Invalid Stat\n");
                continue;
            }
            Optional<Stat> optionalStat = this.statRepository.findStatByPassingAndShootingAndEndurance(stat.getPassing(), stat.getShooting(), stat.getEndurance());
            if(optionalStat.isPresent()){
                output.append("Invalid Stat\n");
                continue;
            }
            Stat statToPersist = this.mapper.map(stat, Stat.class);
            this.statRepository.save(statToPersist);
            output.append(String.format("Successfully imported Stat %.2f - %.2f - %.2f%n",stat.getShooting(),stat.getPassing(), stat.getEndurance()));

        }


        return output.toString();
    }
}
