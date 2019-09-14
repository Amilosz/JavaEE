package io.github.mat3e.todo;

import io.github.mat3e.HibernateUtil;

import java.util.List;
import java.util.Optional;

public class TodoRepository {
    List<Todo> findAll(){
        var session = HibernateUtil.getSessionFactory().openSession();
        var transaction = session.beginTransaction();

        var result = session.createQuery("from Todo", Todo.class).list();

        transaction.commit();
        session.close();
        return result;
    }

    public Todo toggleTodo(Integer id){
        var session = HibernateUtil.getSessionFactory().openSession();
        var transaction = session.beginTransaction();

        var result = session.get(Todo.class, id);
        result.setDone(!result.getDone());

        transaction.commit();
        session.close();
        return result;
    }
    public Todo addTodo(Todo newTodo){
        var session = HibernateUtil.getSessionFactory().openSession();
        var transaction = session.beginTransaction();

        session.persist(newTodo);

        transaction.commit();
        session.close();
        return newTodo;
    }

}
