package Reader;

import Collection.*;
import File.CreationLabwork;


import java.time.LocalDate;
import java.util.Scanner;

/**
 * Класс собирающий объект класса Labwork из данных скрипта.
 */
public class FillerScriptFields implements Filler{
    Scanner scanner;
    boolean switcher = true;
    boolean labSwitcher = true;

    public FillerScriptFields(Scanner scanner){
        this.scanner = scanner;
    }

    @Override
    public String readName(){
        String line;
        if (scanner.hasNext()){
            line = scanner.nextLine();
            if (line.trim().equals("null")){
                System.out.println("Ошибка: имя не может быть null!\n");
                labSwitcher = false;
            }
            else if (line.trim().equals("")){
                System.out.println("Ошибка: имя не может быть пустой строкой!\n");
                labSwitcher = false;
            } else {
                return line;
            }
        } else {
            labSwitcher = false;
        }
        return " ";
    }
    @Override
    public long readX(){
        String line;
        if (scanner.hasNext()) {
            try {
                line = scanner.nextLine();
                return Long.parseLong(line.trim());
            } catch (NumberFormatException e){
                labSwitcher = false;
                System.out.println("Ошибка: координата Х должна быть задана числом!\n");
            }
        } else {labSwitcher = false;}
        return 0;
    }
    @Override
    public long readY(){
        String line;
        long y;
        if(scanner.hasNext()) {
            try {
                line = scanner.nextLine();
                y = Long.parseLong(line.trim());

                if (y <= -732){
                    labSwitcher = false;
                    throw new IllegalArgumentException();
                }
                return y;
            } catch (NumberFormatException e){
                System.out.println("Ошибка: координата Y должна быть задана числом\n");
                labSwitcher = false;
            } catch (IllegalArgumentException e1){
                System.out.println("Ошибка: координата Y должна быть задана числом больше -732!\n");
            }
        } else {labSwitcher = false;}
        return 0;

    }
    @Override
    public Coordinates readCoordinates(){
        return new Coordinates(readX(), readY());
    }
    public Double readMinimalPoint(){
        String line;
        Double minimalPoint;
        if (scanner.hasNext()){
            try{
                line = scanner.nextLine();
                if (line.trim().equals("")){
                    System.out.println("Ошибка: минимальный балл не может быть пустой строкой!");
                    labSwitcher = false;
                }
                if (line.trim().equals("null")){
                    System.out.println("Ошибка: минимальный балл не может быть null!");
                    labSwitcher = false;
                }
                minimalPoint = Double.parseDouble(line.trim());
                if (minimalPoint <= 0){
                    labSwitcher = false;
                    throw new IllegalArgumentException();

                }
                return minimalPoint;
            } catch (NumberFormatException e){
                System.out.println("Ошибка: минимальный балл должен быть задан числом!\n");
                labSwitcher = false;
            } catch (IllegalArgumentException e1){
                System.out.println("Ошибка: минимальный балл должен быть больше 0!\n");
            }
        } else {labSwitcher = false;}return null;

    }
    @Override
    public Difficulty readDifficulty(){
        String line;
        Difficulty difficulty;
        if (scanner.hasNext()){
            try{
                line = scanner.nextLine();
                if (line.trim().equals("null")){
                    return null;
                } else {
                    difficulty = Difficulty.valueOf(line.trim().toUpperCase());
                    return difficulty;
                }

            } catch (IllegalArgumentException e){
                System.out.println("Ошибка: такого варианта сложности нет!\n");
                labSwitcher = false;
            }
        } else {labSwitcher = false;}return null;

    }
    @Override
    public String readAuthorName(){
        String line;
        if (scanner.hasNext()){
            line = scanner.nextLine();
            if (line.trim().equals("null")){
                System.out.println("Ошибка: имя не может быть null!");
                labSwitcher = false;
            }
            if (line.trim().equals("")){
                System.out.println("Ошибка: имя не может быть пустой строкой!");
                labSwitcher = false;
            }
            return line;

        } else {labSwitcher = false;}return null;
    }
    @Override
    public Double readWeight(){
        String line;
        Double weight;
        if (scanner.hasNext()){
            try{
                line = scanner.nextLine();
                if (line.trim().equals("")){
                    System.out.println("Ошибка: вес не может быть пустой строкой!");
                    labSwitcher = false;

                }
                else if (line.trim().equals("null")){
                    System.out.println("Ошибка: вес не может быть null!");
                    labSwitcher = false;

                }
                weight = Double.parseDouble(line.trim());
                if (weight <= 0){
                    labSwitcher = false;
                    throw new IllegalArgumentException();
                }
                return weight;
            } catch (NumberFormatException e){
                labSwitcher = false;
                System.out.println("Ошибка: вес должен быть задан числом!\n ");
            } catch (IllegalArgumentException e1){
                System.out.println("Ошибка: вес должен быть больше 0!\n ");
            }
        }else {labSwitcher = false;}return null;

    }
    @Override
    public String readPassportId(){
        String line;
        if (scanner.hasNext()) {
            try {
                line = scanner.nextLine();
                if (line.trim().equals("")) {
                    System.out.println("Ошибка: номер паспорта не может быть пустой строкой!");
                    labSwitcher = false;

                }if (line.trim().equals("null")) {
                    System.out.println("Ошибка: номер паспорта не может быть null!");
                    labSwitcher = false;
                }
                if (line.trim().length() >30){
                    labSwitcher = false;
                    throw new IllegalArgumentException();
                } return line.trim();
            } catch (IllegalArgumentException e){
                System.out.println("Ошибка: номер паспорта не должен быть длиннее 30 символов!\n");
            }
        } else {labSwitcher = false;}return null;
    }
    @Override
    public Color readColor(){
        String line;
        Color color;
        if (scanner.hasNext()) {
            try {
                line = scanner.nextLine();
                if (line.trim().equals("null")) {
                    System.out.println("Некорректный цвет волос автора!");
                    labSwitcher = false;
                } else {
                    color = Color.valueOf(line.trim().toUpperCase());
                    return color;
                }

            } catch (IllegalArgumentException e) {
                labSwitcher = false;
                System.out.println("Ошибка: такого варианта цвета нет!\n");

            }
        } else {labSwitcher = false;}return null;

    }
    @Override
    public Country readNationality() {
        String line;
        Country country;
        if (scanner.hasNext()) {
            try {
                line = scanner.nextLine();
                if (line.trim().equals("null")) {
                    System.out.println("Некорректная национальность автора!");
                    labSwitcher = false;
                } else {
                    country = Country.valueOf(line.trim().toUpperCase());
                    return country;
                }
            } catch (IllegalArgumentException e) {
                System.out.println("Ошибка: такого варианта национальности нет!\n");
                labSwitcher = false;
            }
        } else {labSwitcher = false;}return null;
    }

    @Override
    public Person readPerson(){
        if (scanner.hasNext()) {
            String name = readAuthorName();
            Double weight = readWeight();
            String passportID = readPassportId();
            Color color = readColor();
            Country country = readNationality();
            if ((name == null)||(weight == null)||(passportID == null)||(color == null)||(country == null)){
                switcher = false;
                System.out.println("Поля автора некорректны!");
            } else {
                return new Person(name, weight, passportID, color, country);
            }
        } else {
            return null;
        }
        switcher = false;
        return null;
    }

    public LabWork creationsLabwork() {
        LabWork lab = new LabWork(CreationLabwork.getId(), readName(), readCoordinates(), LocalDate.now(), readMinimalPoint(), readDifficulty(), readPerson());
        if (switcher & labSwitcher) {
            return lab;
        } else {
            return null;
        }
    }
}

