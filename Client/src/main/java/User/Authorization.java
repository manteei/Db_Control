package User;

import DataBase.Response;

import java.util.Locale;
import java.util.Scanner;

public class Authorization {
    private LoginPassword loginPassword;
    private User user;
    private Scanner scanner;

    public Authorization(User user, Scanner scanner) {
        this.user = user;
        this.loginPassword = new LoginPassword(this.user);
        this.scanner = scanner;
    }
    public void userAuthReg(){
        System.out.println("Введите register, если хотите зарегистрироваться\n" +
                "Введите authorize , если хотите войти!");
        while (true){
            String userInput = scanner.nextLine();
            if (userInput.trim().toLowerCase(Locale.ROOT).equals("register")){
                userRegistration();
                break;
            }
            if (userInput.trim().toLowerCase(Locale.ROOT).equals("authorize")){
                authorize();
                break;
            }
            System.err.println("Введите register или authorize!");
        }
    }
    public void authorize() {
        user.setRegistration(false);
        user.setAuthorization(true);
        System.out.println("Авторизация");
        user.setUsername(loginPassword.readUsername());
        user.setPassword(loginPassword.readPassword());
    }

    public void userRegistration() {
        user.setRegistration(true);
        System.out.println("Регистрация пользователя!\n");
        user.setUsername(loginPassword.readUsername());
        user.setPassword(loginPassword.readPassword());
    }

    public boolean userExist(Response response){
        if (response.getBody().equals("Пользователя с таким именем нет!")) {
            return false;
        } else if (response.getBody().equals("Авторазиция прошла успешно!")) {
            return true;
        }
        return false;
    }
}
