package orm;

import orm.anotations.Column;
import orm.anotations.Entity;
import orm.anotations.Id;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

public class EntityManager <E> implements DbContext<E>{
    private Connection connection;

    public EntityManager(Connection connection) {
        this.connection = connection;
    }

    @Override
    public boolean persist(E entity) throws SQLException, IllegalAccessException {
        String tableName = this.getTableName(entity.getClass());
        String fieldList = this.getDBFieldsWithoutIdentity(entity);
        String valueList = this.getValuesWithoutIdentity(entity);
        

        String sql =String.format("INSERT INTO %s(%s) values (%s)",tableName, fieldList, valueList);

        return this.connection.prepareStatement(sql).execute();
    }

    @Override
    public Iterable<E> find() {
        return null;
    }

    @Override
    public Iterable<E> find(String where) {
        return null;
    }


    @Override
    public Iterable<E> find(Class<E> entityType) throws SQLException, InvocationTargetException, NoSuchMethodException, InstantiationException, IllegalAccessException {
        return find(entityType, null);
    }

    @Override
    public Iterable<E> find(Class<E> entityType, String where) throws SQLException, InvocationTargetException, NoSuchMethodException, InstantiationException, IllegalAccessException {
        String tableName = this.getTableName(entityType);

        String sql = String.format("SELECT * FROM %s %s",tableName, Objects.equals(where, "") ? "" : "WHERE "+where);
        ResultSet resultSet = this.connection.prepareStatement(sql).executeQuery();
        List<E> result = new ArrayList<>();

        E entity = this.fillEntity(entityType, resultSet);
        while(entity != null){
            result.add(entity);
            entity = this.fillEntity(entityType, resultSet);
        }
        return result;
    }

    @Override
    public E findFirst() {
        return null;
    }

    @Override
    public E findFirst(String where) throws SQLException {
        return null;
    }

    @Override
    public E findFirst(Class<E> entityType) throws SQLException, InvocationTargetException, NoSuchMethodException, InstantiationException, IllegalAccessException {
        return findFirst(entityType, null);
    }

    @Override
    public E findFirst(Class<E> entityType, String where) throws SQLException, InvocationTargetException, NoSuchMethodException, InstantiationException, IllegalAccessException {
        String tableName = this.getTableName(entityType);

        String sql = String.format("SELECT * FROM %s %sLIMIT 1",tableName, Objects.equals(where, "") ? "" : "WHERE "+where);
        ResultSet resultSet = this.connection.prepareStatement(sql).executeQuery();

        return this.fillEntity(entityType, resultSet);
    }



    private String getTableName(Class<?> clazz) {
        Entity annotation = clazz.getAnnotation(Entity.class);

        if(annotation == null){
            throw new ORMException("Provided class does not have Entity annotation.");
        }
        return annotation.name();
    }

    private String getDBFieldsWithoutIdentity(E entity) {
       return Arrays.stream(entity
                .getClass()
                .getDeclaredFields())
                .filter(f -> f.getAnnotation(Column.class) !=null)
                .map(f -> f.getAnnotation(Column.class).name())
                .collect(Collectors.joining(","));
    }

    private String getValuesWithoutIdentity(E entity) throws IllegalAccessException {
        Field[] declaredFields = entity.getClass().getDeclaredFields();
        List<String> result = new ArrayList<>();

        for (Field declaredField : declaredFields) {
            if(declaredField.getAnnotation(Column.class)== null){
                continue;
            }
            declaredField.setAccessible(true);
            Object value = declaredField.get(entity);
            result.add(String.format("\"%s\"",value.toString()));
        }
        return String.join(",",result);
    }
    private E fillEntity(Class<E> entityType, ResultSet resultSet) throws SQLException, NoSuchMethodException, InvocationTargetException, InstantiationException, IllegalAccessException {
        if (!resultSet.next()){
            return null;
        }
        E entity = entityType.getDeclaredConstructor().newInstance();

        Field[] declaredFields = entityType.getDeclaredFields();

        for (Field declaredField : declaredFields) {
            if(!declaredField.isAnnotationPresent(Column.class) && declaredField.isAnnotationPresent(Id.class)){
                continue;
            }
            Column columnAnnotation = declaredField.getAnnotation(Column.class);
            String fieldName = columnAnnotation == null ? declaredField.getName() : columnAnnotation.name();

            String value = resultSet.getString(fieldName);
             this.fillDate(entity, declaredField, value);
        }


        return entity;
    }

    private E fillDate(E entity, Field field,String value) throws SQLException, IllegalAccessException {
        field.setAccessible(true);

        if(field.getType() == long.class || field.getType() ==Long.class){
            field.setLong(entity, Long.parseLong(value));
        }else if (field.getType() == LocalDate.class){
            field.set(entity, LocalDate.parse(value));
        }else if (field.getType() == String.class){
            field.set(entity, value);
        }else{
            throw new ORMException("Unsupported type "+ field.getType());
        }
        return entity;
    }
}
