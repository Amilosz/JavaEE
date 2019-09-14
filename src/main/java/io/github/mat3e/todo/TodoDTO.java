package io.github.mat3e.todo;

class TodoDTO {
    private Integer id;
    private String text;
    private Boolean done;

    TodoDTO(Todo todo){
        this.id = todo.getId();
        this.text = todo.getText();
        this.done = todo.getDone();
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public Boolean getDone() {
        return done;
    }

    public void setDone(Boolean done) {
        this.done = done;
    }
}
