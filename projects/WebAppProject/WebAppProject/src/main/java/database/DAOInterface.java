package database;

import entity.EntityInterface;

import java.util.List;

/*
    Функционал, необходимый классам DAO для взаимодействия с БД
        - CRUD
 */
public interface DAOInterface<Entity extends EntityInterface, Key> {
    int create(Entity entity);         // insert, post

    List<Entity> getAll();              // select * , get
    Entity getById(Key id);             // select, get
    // получение сущностей по другим параметрам

    void update(Entity oldEntity, Entity newEntity);         // update, put
    void delete(Entity entity);         // delete
}
