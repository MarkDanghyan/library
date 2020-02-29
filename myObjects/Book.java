package src.library.myObjects;

import java.util.Objects;

//Book (name, description, numberOfPages, state (NOT_TAKEN, TAKEN)
public class Book {
    private String name;
    private String description;
    private int pages;
    private State state;

    public Book(String name, String description, int pages) {
        this.name = name;
        this.description = description;
        this.pages = pages;
        this.state = State.NOT_TAKEN;
    }

    public Book(String name, String description, int pages, State state) {
        this.name = name;
        this.description = description;
        this.pages = pages;
        this.state = state;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public int getPages() {
        return pages;
    }

    public void setPages(int pages) {
        this.pages = pages;
    }

    public State getState() {
        return state;
    }

    public void setState(State state) {
        this.state = state;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Book book = (Book) o;
        return pages == book.pages &&
                Objects.equals(name, book.name) &&
                Objects.equals(description, book.description) &&
                state == book.state;
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, description, pages, state);
    }

    @Override
    public String toString() {
        return "Book{" +
                "name='" + name + '\'' +
                ", description='" + description + '\'' +
                ", pages=" + pages +
                ", state=" + state +
                '}';
    }
}
