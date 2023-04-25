package orm;

import java.lang.reflect.InvocationTargetException;
import java.sql.SQLException;

public interface DbContext<E> {
    boolean persist(E entity) throws SQLException, IllegalAccessException;

    Iterable<E> find();
    Iterable<E> find(String where);

    Iterable<E> find(Class<E> entityType) throws SQLException, InvocationTargetException, NoSuchMethodException, InstantiationException, IllegalAccessException;

    Iterable<E> find(Class<E> entityType, String where) throws SQLException, InvocationTargetException, NoSuchMethodException, InstantiationException, IllegalAccessException;

    E findFirst();
    E findFirst(String where) throws SQLException;

    E findFirst(Class<E> entityType) throws SQLException, InvocationTargetException, NoSuchMethodException, InstantiationException, IllegalAccessException;

    E findFirst(Class<E> entityType, String where) throws SQLException, InvocationTargetException, NoSuchMethodException, InstantiationException, IllegalAccessException;
}
