package softuni.exam.config;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import org.modelmapper.Converter;
import org.modelmapper.ModelMapper;
import org.modelmapper.TypeMap;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import softuni.exam.models.Car.Car;
import softuni.exam.models.Car.dto.ImportCarsDto;
import softuni.exam.models.Picture.Picture;
import softuni.exam.models.Picture.dto.ImportPictureDto;
import softuni.exam.util.ValidationUtil;
import softuni.exam.util.ValidationUtilImpl;

import java.text.ParseException;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;


@Configuration
public class ApplicationBeanConfiguration {

    private final ModelMapper mapper;

    public ApplicationBeanConfiguration() {
        this.mapper = new ModelMapper();
    }

    //ToDo

    @Bean
    public Gson gson() {
        return new GsonBuilder()
                .setPrettyPrinting()
                .create();
    }

    @Bean
    public ValidationUtil validationUtil() {
        return new ValidationUtilImpl();
    }

    private static LocalDate formatDateToString(String v) throws ParseException {
        DateTimeFormatter dateFormat = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        if(v!=null)
            return LocalDate.parse(v, dateFormat);
        else return null;
    }

    private static LocalDateTime formatDateTimeToString(String v) throws ParseException {
        DateTimeFormatter dateFormat = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        if(v!=null)
            return LocalDateTime.parse(v, dateFormat);
        else return null;
    }

    @Bean
    public ModelMapper modelMapper() {
        TypeMap<ImportCarsDto, Car> importCarsTypeMap = this.mapper.createTypeMap(ImportCarsDto.class, Car.class);
        Converter<String, LocalDate> convertDate = ctx ->{
            String source = ctx.getSource();
            try {
                return formatDateToString(source);
            } catch (ParseException e) {
                throw new RuntimeException(e);
            }
        };
        importCarsTypeMap.addMappings(mapper -> mapper.using(convertDate).map(ImportCarsDto::getRegisteredOn,Car::setRegisteredOn));

        TypeMap<ImportPictureDto, Picture> importPicturesTypeMap = this.mapper.createTypeMap(ImportPictureDto.class, Picture.class);
        Converter<String, LocalDateTime> convertDateTime = ctx ->{
            String source = ctx.getSource();
            try {
                return formatDateTimeToString(source);
            } catch (ParseException e) {
                throw new RuntimeException(e);
            }
        };
        importPicturesTypeMap.addMappings(mapper -> mapper.using(convertDateTime).map(ImportPictureDto::getDateAndTime,Picture::setDateAndTime));

        return mapper;
    }

}
