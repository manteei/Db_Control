package Reader;

import Collection.*;

import java.util.Scanner;

/**
 * Класс для проверки данных введенных с клавиатуры
 */
public class FillerFields implements Filler {
    Scanner scanner = new Scanner(System.in);
    LineReader lineReader = new LineReader(scanner);
    public FillerFields(){}

    @Override
    public String readName(){
        String line;
        while (true){
            lineReader.printEscortMessage("Введите название лабораторной работы (не может быть null и пустой строкой)\n");
            line = lineReader.getLine();
             if (line.trim().equals("null")){
                 lineReader.printErrorMessage("Ошибка: имя не может быть null!\n");
             }
             else if (line.trim().equals("")){
                 lineReader.printErrorMessage("Ошибка: имя не может быть пустой строкой!\n");
             } else {
             return line;
            }
        }
    }
    @Override
    public long readX(){
        String line;
        while (true) {
            try {
                lineReader.printEscortMessage("Введите координату X\n");
                line = lineReader.getLine();
                return Long.parseLong(line.trim());
            } catch (NumberFormatException e){
                lineReader.printErrorMessage("Ошибка: координата Х должна быть задана числом!\n");
            }
        }
    }
    @Override
    public long readY(){
        String line;
        long y;
        while (true) {
            try {
                lineReader.printEscortMessage("Введите координату Y (Должно быть больше -732)\n");
                line = lineReader.getLine();
                y = Long.parseLong(line.trim());

                if (y <= -732){
                    throw new IllegalArgumentException();
                }
                return y;
            } catch (NumberFormatException e){
                lineReader.printErrorMessage("Ошибка: координата Y должна быть задана числом\n");
            } catch (IllegalArgumentException e1){
                lineReader.printErrorMessage("Ошибка: координата Y должна быть задана числом больше -732!\n");
            }
        }
    }
    @Override
    public Coordinates readCoordinates(){
        return new Coordinates(readX(), readY());
    }
    public Double readMinimalPoint(){
        String line;
        Double minimalPoint;
        while (true){
            try{
                lineReader.printEscortMessage("Введите минимальный балл(не может быть null и должно быть больше 0)!\n");
                line = lineReader.getLine();
                if (line.trim().equals("")){
                    lineReader.printErrorMessage("Ошибка: минимальный балл не может быть пустой строкой!\n");
                }
                if (line.trim().equals("null")){
                    lineReader.printErrorMessage("Ошибка: минимальный балл не может быть null\n!");
                }
                minimalPoint = Double.parseDouble(line.trim());
                if (minimalPoint <= 0){
                    throw new IllegalArgumentException();
                }
                return minimalPoint;
            } catch (NumberFormatException e){
                lineReader.printErrorMessage("Ошибка: минимальный балл должен быть задан числом!\n");
            } catch (IllegalArgumentException e1){
                lineReader.printErrorMessage("Ошибка: минимальный балл должен быть больше 0!\n");
            }
        }

    }
    @Override
    public Difficulty readDifficulty(){
        String line;
        Difficulty difficulty;
        lineReader.printEscortMessage("Выберите из списка и введите сложность лабораторной работы:!\n");
        for (Difficulty diff: Difficulty.values()){
            System.out.println(diff.name());
        }
        while (true){
            try{
                line = lineReader.getLine();
                if (line.trim().equals("null")){
                    return null;
                } else {
                    difficulty = Difficulty.valueOf(line.trim().toUpperCase());
                    return difficulty;
                }

            } catch (IllegalArgumentException e){
                lineReader.printErrorMessage("Ошибка: такого варианта сложности нет!\n");
            }
        }

    }
    @Override
    public String readAuthorName(){
        String line;
        while (true){
            lineReader.printEscortMessage("Введите имя автора лабораторной работы  (не может быть null и пустой строкой)\n");
            line = lineReader.getLine();
            if (line.trim().equals("null")){
                lineReader.printErrorMessage("Ошибка: имя не может быть null!\n ");
            }
             if (line.trim().equals("")){
                lineReader.printErrorMessage("Ошибка: имя не может быть пустой строкой!\n ");
            }
                return line;

        }
    }
    @Override
    public Double readWeight(){
        String line;
        Double weight;
        while (true){
            try{
                lineReader.printEscortMessage("Введите вес (не может быть null и должно быть больше 0)\n");
                line = lineReader.getLine();
                if (line.trim().equals("")){
                    lineReader.printErrorMessage("Ошибка: вес не может быть пустой строкой!\n");
                }
                else if (line.trim().equals("null")){
                    lineReader.printErrorMessage("Ошибка: вес не может быть null!\n ");
                }
                weight = Double.parseDouble(line.trim());
                if (weight <= 0){
                    throw new IllegalArgumentException();
                }
                return weight;
            } catch (NumberFormatException e){
                lineReader.printErrorMessage("Ошибка: вес должен быть задан числом!\n ");
            } catch (IllegalArgumentException e1){
                lineReader.printErrorMessage("Ошибка: вес должен быть больше 0!\n ");
            }
        }

    }
    @Override
    public String readPassportId(){
        String line;
        while (true) {
            try {
                lineReader.printEscortMessage("Введите номер паспорта (Не больше 30 символов, не может быть null и пустой строкой)\n");
                line = lineReader.getLine();
                if (line.trim().equals("")) {
                    lineReader.printErrorMessage("Ошибка: номер паспорта не может быть пустой строкой!\n");
                } else if (line.trim().equals("null")) {
                    lineReader.printErrorMessage("Ошибка: номер паспорта не может быть null!\n");
                } else if (line.trim().length() >30){
                    throw new IllegalArgumentException();
                } else return line.trim();
            } catch (IllegalArgumentException e){
                lineReader.printErrorMessage("Ошибка: номер паспорта не должен быть длиннее 30 символов!\n");
            }
        }
    }
    @Override
    public Color readColor(){
        String line;
        Color color;
        lineReader.printEscortMessage("Выберите из списка и введите цвет волос автора работы:\n");
        for (Color col: Color.values()){
            System.out.println(col.name());
        }
        while (true){
            try{
                line = lineReader.getLine();
                if (line.trim().equals("null")){
                    lineReader.printErrorMessage("Ошибка: цвет волос не может быть null\n");
                } else {
                    color = Color.valueOf(line.trim().toUpperCase());
                    return color;
                }

            } catch (IllegalArgumentException e){
                lineReader.printErrorMessage("Ошибка: такого варианта цвета нет!\n");
            }
        }

    }
    @Override
    public Country readNationality(){
        String line;
        Country country;
        lineReader.printEscortMessage("Выберите из списка и введите национальность автора работы:\n");
        for (Country con: Country.values()){
            System.out.println(con.name());
        }
        while (true){
            try{
                line = lineReader.getLine();
                if (line.trim().equals("null")){
                    lineReader.printErrorMessage("Ошибка: национальность не может быть null\n");
                } else {
                    country = Country.valueOf(line.trim().toUpperCase());
                    return country;
                }

            } catch (IllegalArgumentException e){
                lineReader.printErrorMessage("Ошибка: такого варианта национальности нет!\n");
            }
        }

    }
    @Override
    public Person readPerson(){
        String line;
        System.out.println("У лабораторной работы известен автор? (yes/no)");
        while (true) {
            line = lineReader.getLine();
            if (!line.trim().equals("yes") && !line.trim().equals("no")){
                lineReader.printErrorMessage("Ошибка: такого варианта ответа нет!\n");
            }
            if (line.trim().equals("yes")) {
                return new Person(readAuthorName(), readWeight(), readPassportId(), readColor(), readNationality());
            }if (line.trim().equals("no")) {
                return new Person(null, null, null, null, null);
            }
        }
    }


}
