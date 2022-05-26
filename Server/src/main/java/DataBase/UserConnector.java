package DataBase;

import User.User;

public class UserConnector {

    public String connectUser(User user, Response response){
        if (user.isRegistration()){
            if (DBManager.addUserToDataBase(user.getUsername(),user.getPassword())){
                response.setRegistered(true);
                return "Пользователь зарегистрирован!";
            } else {
                response.setRegistered(false);
                return "Пользователь с таким именем уже есть!";
            }
        }
        if (user.isAuthorization()){
            String resp = DBManager.authorize(user.getUsername(),user.getPassword());
            if (resp.equals("Авторазиция прошла успешно!")) {
                response.setAuthorized(true);
                return "Авторазиция прошла успешно!";
            }
            return resp;
        }
        return "Authorize";
    }
}
