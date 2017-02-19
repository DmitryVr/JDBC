package model;

public class Movie {
    private int id;
    private String nameRussian;
    private String nameOriginal;

    public Movie() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNameRussian() {
        return nameRussian;
    }

    public void setNameRussian(String nameRussian) {
        this.nameRussian = nameRussian;
    }

    public String getNameOriginal() {
        return nameOriginal;
    }

    public void setNameOriginal(String nameOriginal) {
        this.nameOriginal = nameOriginal;
    }

    @Override
    public String toString() {
        return "Movie {" +
                "id=" + id +
                ", nameRussian='" + nameRussian + '\'' +
                ", nameOriginal='" + nameOriginal + '\'' +
                '}';
    }
}
