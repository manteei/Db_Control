package DataBase;

import Collection.*;
import User.User;
import User.CommandLine;

import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.sql.*;
import java.time.LocalDate;
import java.util.Random;

public class DBManager {
    private static Connection connection = null;
    private static Statement statement = null;
    private static boolean connected;
    private static final String ADD = "INSERT INTO LABWORKS (username , name , x, y, date, minimalPoint, difficulty, authorName, weight, passportID, hairColor, nationality) VALUES (?,?,?,?,?,?,?,?,?,?,?, ?)";
    private static final String LOAD = "SELECT * from LABWORKS";
    private static final String FIND_BY_USERNAME = "SELECT (username) from users WHERE username=?";
    private static final String INSERT_USER = "INSERT into users (username, password, salt) VALUES (? , ?, ?)";
    private static final String GET_PASSWORD_BY_USERNAME = "select * FROM users where username=?";
    private static final String DELETE = "DELETE from LABWORKS WHERE username=?";
    private static final String DELETE_BY_ID = "DELETE from LABWORKS WHERE id=?";
    private static final String UPDATE = "UPDATE LABWORKS set (name , x, y, date, minimalPoint, difficulty, authorName, weight, passportID, hairColor, nationality) = (?,?,?,?,?,?,?,?,?,?,?) WHERE id=?";

    static {
        connect();
    }

    public static boolean connect() {
        try {
            connection = ConnectionManager.open();
            if (connection != null) {
                System.out.println("Успешное подключение к базе данных!");
                statement = connection.createStatement();
                connected = true;
                return true;
            }
            if (connection == null) {
                System.out.println("Connection is null!");
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return false;
    }
    public static boolean addLabwork(LabWork labWork, User user) {
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(ADD, Statement.RETURN_GENERATED_KEYS);
            preparedStatement.setString(1, user.getUsername());
            preparedStatement.setString(2, labWork.getName());
            preparedStatement.setLong(3, labWork.getCoordinates().getX());
            preparedStatement.setLong(4, labWork.getCoordinates().getY());
            preparedStatement.setDate(5, DateConverter.convertToDate(labWork.getCreationDate()));
            preparedStatement.setDouble(6, labWork.getMinimalPoint());
            preparedStatement.setString(7, labWork.getDifficulty().toString());
            try{
                preparedStatement.setString(8, labWork.getAuthor().getName());
            }catch (NullPointerException e){
                preparedStatement.setString(8,null);
            }
            try {
                preparedStatement.setDouble(9, labWork.getAuthor().getWeight());
            }catch (NullPointerException e){
                preparedStatement.setDouble(9,0);
            }
            try{
                preparedStatement.setString(10, labWork.getAuthor().getPassportID());
            }catch (NullPointerException e){
                preparedStatement.setString(10,null);
            }
            try{
                preparedStatement.setString(11, labWork.getAuthor().getHairColor().toString());
            }catch (NullPointerException e){
                preparedStatement.setString(11,null);
            }
            try{
                preparedStatement.setString(12, labWork.getAuthor().getNationality().toString());
            }catch (NullPointerException e){
                preparedStatement.setString(12,null);
            }

            if (preparedStatement.executeUpdate() != 0) {
                ResultSet resultSet = preparedStatement.getGeneratedKeys();
                if (resultSet.next()) {
                    int id = resultSet.getInt("id");
                    labWork.setId(id);
                }
                System.out.println("Элемент добавлен!");
                return true;
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return false;
    }

    public static void load(CommandLine commandLine) {
            try {
                ResultSet resultSet = getStatement().executeQuery(LOAD);
                while (resultSet.next()) {
                    String username = resultSet.getString("username");
                    String name = resultSet.getString("name");
                    int id = resultSet.getInt("id");
                    Coordinates coordinates = new Coordinates(
                            resultSet.getLong("x"),
                            resultSet.getLong("y")
                    );
                    LocalDate localDate = DateConverter.convertToLocalDate(resultSet.getDate("date"));
                    Double minimalPoint = resultSet.getDouble("minimalPoint");
                    Difficulty difficulty = Difficulty.valueOf(resultSet.getString("difficulty").toUpperCase());
                    String nameA = resultSet.getString("authorName");
                    if (String.valueOf(nameA).equals("null")){
                        LabWork labWork = new LabWork(
                                id,
                                name,
                                coordinates,
                                localDate,
                                minimalPoint,
                                difficulty,
                               null,
                                username
                        );
                        commandLine.getLabworks().add(labWork);

                    } else {
                        Person author = new Person(
                                nameA,
                                resultSet.getDouble("weight"),
                                resultSet.getString("passportID"),
                                Color.valueOf(resultSet.getString("hairColor")),
                                Country.valueOf(resultSet.getString("nationality"))
                        );
                        LabWork labWork = new LabWork(
                                id,
                                name,
                                coordinates,
                                localDate,
                                minimalPoint,
                                difficulty,
                                author,
                                username
                        );
                        commandLine.getLabworks().add(labWork);
                    }

                }
            } catch(SQLException e){
                e.printStackTrace();
            }
    }

    public static boolean isThatUserNameContains(String username) {
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(FIND_BY_USERNAME);
            preparedStatement.setString(1, username);
            ResultSet resultSet = preparedStatement.executeQuery();
            if (resultSet.next()) return true;
            return false;
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return false;
    }

    public static boolean addUserToDataBase(String username, String password) {
        if (isThatUserNameContains(username)) {
            return false;
        }
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(INSERT_USER);
            MessageDigest messageDigest;
            String salt = generateRandomString();
            messageDigest = MessageDigest.getInstance("SHA-384");
            messageDigest.update((password+salt).getBytes());
            preparedStatement.setString(1, username);
            preparedStatement.setString(2, toHexBytes(messageDigest.digest()));
            preparedStatement.setString(3, salt);
            if (preparedStatement.executeUpdate() != 0) {
                return true;
            }
        } catch (SQLException | NoSuchAlgorithmException e) {
            System.out.println(e.getMessage());
        }
        return false;
    }

    public static String authorize(String username, String password) {
        if (!isThatUserNameContains(username)) return "Пользователя с таким именем нет!";
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(GET_PASSWORD_BY_USERNAME);
            preparedStatement.setString(1, username);
            ResultSet resultSet = preparedStatement.executeQuery();
            resultSet.next();
            String salt = resultSet.getString(3);
            MessageDigest messageDigest = MessageDigest.getInstance("SHA-384");
            messageDigest.update((password + salt).getBytes());
            if (toHexBytes(messageDigest.digest()).equals(resultSet.getString(2))) {
                    return "Авторазиция прошла успешно!";
                } else return "Неправильно введен пароль!";
        } catch (SQLException | NoSuchAlgorithmException e) {
            e.printStackTrace();
        }
        return "";
    }

    public static boolean clear(String username) {
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(DELETE);
            preparedStatement.setString(1, username);
            if (preparedStatement.executeUpdate() != 0) {
                System.out.println("Элементы удалены!");
                return true;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    public static boolean removeById(int id) {
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(DELETE_BY_ID);
            preparedStatement.setInt(1, id);
            if (preparedStatement.executeUpdate() != 0) {
                System.out.println("Элемент удален!");
                return true;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    public static boolean updateById(LabWork labWork, int id) {
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(UPDATE);
            preparedStatement.setString(1, labWork.getName());
            preparedStatement.setLong(2, labWork.getCoordinates().getX());
            preparedStatement.setLong(3, labWork.getCoordinates().getY());
            preparedStatement.setDate(4, DateConverter.convertToDate(labWork.getCreationDate()));
            preparedStatement.setDouble(5, labWork.getMinimalPoint());
            preparedStatement.setString(6, labWork.getDifficulty().toString());
            try{
                preparedStatement.setString(7, labWork.getAuthor().getName());
            } catch (NullPointerException e){
                preparedStatement.setString(7, null);
            }
            try{
                preparedStatement.setDouble(8, labWork.getAuthor().getWeight());
            } catch (NullPointerException e){
            preparedStatement.setDouble(8, 0);
        }
            try{
                preparedStatement.setString(9, labWork.getAuthor().getPassportID());
            } catch (NullPointerException e){
                preparedStatement.setString(9, null);
            }
            try{
                preparedStatement.setString(10, labWork.getAuthor().getHairColor().toString());
            } catch (NullPointerException e){
                preparedStatement.setString(10, null);
            }
            try{
                preparedStatement.setString(11, labWork.getAuthor().getNationality().toString());
            } catch (NullPointerException e){
                preparedStatement.setString(11, null);
            }
            try {
                preparedStatement.setInt(12, id);
            } catch (NullPointerException e){
            preparedStatement.setInt(12, 0);
            }
            if (preparedStatement.executeUpdate() != 0) {
                System.out.println("Элемент обновлен!");
                return true;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }
    private static String generateRandomString() {
        byte[] array = new byte[10];
        new Random().nextBytes(array);
        return new String(array, StandardCharsets.UTF_8);
    }
    private static String toHexBytes(byte[] bytes) {
        StringBuilder result = new StringBuilder();
        for (byte aByte : bytes) {
            result.append(String.format("%02x", aByte));
        }
        return result.toString();
    }

    public static Statement getStatement() {
        return statement;
    }

    public static boolean isConnected() {
        return connected;
    }

}
