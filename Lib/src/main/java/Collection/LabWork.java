package Collection;

import java.io.Serializable;
import java.time.LocalDate;

/**
 * Класс описывающий Лабораторную работу.
 */

public class LabWork implements Comparable<LabWork>, Serializable {
    private int id; //Значение поля должно быть больше 0, Значение этого поля должно быть уникальным, Значение этого поля должно генерироваться автоматически
    private String name; //Поле не может быть null, Строка не может быть пустой
    private Coordinates coordinates; //Поле не может быть null
    private LocalDate creationDate; //Поле не может быть null, Значение этого поля должно генерироваться автоматически
    private Double minimalPoint; //Поле не может быть null, Значение поля должно быть больше 0
    private Difficulty difficulty; //Поле может быть null
    private Person author; //Поле может быть null


    public LabWork(int id, String name,Coordinates coordinates, LocalDate creationDate, Double minimalPoint, Difficulty difficulty, Person author) {
        this.id = id;
        this.name = name;
        this.coordinates = coordinates;
        this.creationDate = LocalDate.now();
        this.minimalPoint = minimalPoint;
        this.difficulty = difficulty;
        this.author = author;
    }
    public LabWork(int id, String name,Coordinates coordinates, LocalDate creationDate, Double minimalPoint, Difficulty difficulty, Person author, String username) {
        this.id = id;
        this.name = name;
        this.coordinates = coordinates;
        this.creationDate = LocalDate.now();
        this.minimalPoint = minimalPoint;
        this.difficulty = difficulty;
        this.author = author;
        this.username = username;
    }
    public LabWork(String name,Coordinates coordinates, LocalDate creationDate, Double minimalPoint, Difficulty difficulty, Person author) {
        this.name = name;
        this.coordinates = coordinates;
        this.creationDate = LocalDate.now();
        this.minimalPoint = minimalPoint;
        this.difficulty = difficulty;
        this.author = author;
    }
    public LabWork(String name,Coordinates coordinates, LocalDate creationDate, Double minimalPoint, Difficulty difficulty, Person author, String username) {
        this.name = name;
        this.coordinates = coordinates;
        this.creationDate = LocalDate.now();
        this.minimalPoint = minimalPoint;
        this.difficulty = difficulty;
        this.author = author;
        this.username = username;
    }
    public LabWork(){
    }
    public String username;

    public void setUsername(String username) {
        this.username = username;
    }

    public String getUsername() {
        return username;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setCoordinates(Coordinates coordinates) {
        this.coordinates = coordinates;
    }


    public void setCreationDate(LocalDate creationDate) {
        this.creationDate = creationDate;
    }

    public void setMinimalPoint(Double minimalPoint) {
        this.minimalPoint = minimalPoint;
    }

    public void setDifficulty(Difficulty difficulty) {
        this.difficulty = difficulty;
    }

    public void setAuthor(Person author) {
        this.author = author;
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public Coordinates getCoordinates() {
        return coordinates;
    }

    public LocalDate getCreationDate() {
        return creationDate;
    }

    public Double getMinimalPoint() {
        return minimalPoint;
    }

    public Difficulty getDifficulty() {
        return difficulty;
    }

    public Person getAuthor() {
        return author;
    }

    public static boolean isNull(LabWork labWork){
        return (String.valueOf(labWork.getAuthor().getName()).equals("null")&&String.valueOf(labWork.getAuthor().getWeight()).equals("null") &&String.valueOf(labWork.getAuthor().getPassportID()).equals("null")&&String.valueOf(labWork.getAuthor().getNationality()).equals("null")&&String.valueOf(labWork.getAuthor().getHairColor()).equals("null"));
    }
    @Override
    public String toString() {
        return "LabWork{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", coordinates=" + coordinates +
                ", creationDate=" + creationDate +
                ", minimalPoint=" + minimalPoint +
                ", difficulty=" + difficulty +
                ", author=" + author +
                '}';
    }


    @Override
    public int compareTo(LabWork o) {
        return 0;
    }

}
