package Homework.medium;

public class UserDataCloudDataSource {
    public UserData fetchUserFromCloud(int id) {
        System.out.println("Загрузка пользователя из сети...");
        return new UserData(id, "Иван", "ivan@example.com");
    }
}
