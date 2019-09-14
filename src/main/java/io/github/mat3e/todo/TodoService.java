package io.github.mat3e.todo;

import io.github.mat3e.lang.LangRepository;

import java.util.List;
import java.util.stream.Collectors;

class TodoService {
    private TodoRepository repository;

    /**
     * Servlet needs it.
     */
    TodoService(){
        this(new TodoRepository());
    }

    TodoService(TodoRepository repository) {
        this.repository=repository;
    }

    List<TodoDTO> findAll(){
        return repository
                .findAll()
                .stream()
                .map(TodoDTO::new)
                .collect(Collectors.toList());
    }

}
