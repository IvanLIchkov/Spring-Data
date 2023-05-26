package softuni.exam.util;

import org.springframework.stereotype.Component;

import javax.validation.ConstraintViolation;
import java.util.Set;

@Component
public interface ValidatorUtil {

    <E> boolean isValid(E entity);

    <E> Set<ConstraintViolation<E>> violations (E entity);
}
