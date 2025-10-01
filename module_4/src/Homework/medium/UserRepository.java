package Homework.medium;

public class UserRepository {
    private final UserDataCloudDataSource cloudDataSource;
    private UserData cache;

    public UserRepository(UserDataCloudDataSource cloudDataSource) {
        this.cloudDataSource = cloudDataSource;
    }

    public UserData getUser(int id) {
        if (cache == null || cache.getId() != id) {
            cache = cloudDataSource.fetchUserFromCloud(id);
        } else {
            System.out.println("Возвращаю данные из кэша...");
        }
        return cache;
    }
}
