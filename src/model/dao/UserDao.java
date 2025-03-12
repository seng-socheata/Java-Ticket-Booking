package model.dao;
import model.entity.User;

import java.util.List;

public interface UserDao {

    int addNewUser(User user);
    int getAllUser(User user);
    int deleteUser(Integer id);
    int updateUser(Integer id);
    List<User> queryAllUsers();
}
