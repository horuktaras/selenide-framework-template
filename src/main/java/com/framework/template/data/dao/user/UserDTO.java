package com.framework.template.data.dao.user;

import java.util.List;

/**
 * Created by Noop on 20.06.2017.
 */
public interface UserDTO {

    User getUserByID(boolean isSourceDB, int userId);
    List<User> getUsersByName(boolean isSourceDB, String name);
    List<User> getUsersByEmail(boolean isSourceDB, String email);
    List<User> getUsers(boolean isSourceDB);
}
