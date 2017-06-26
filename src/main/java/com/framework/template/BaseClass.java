package com.framework.template;

import com.framework.template.data.dao.user.User;
import com.framework.template.data.dao.user.UserDTOImpl;
import org.testng.Assert;
import org.testng.annotations.Test;

/**
 * Created by Noop on 21.06.2017.
 */
public class BaseClass {

    private static UserDTOImpl userDTO = new UserDTOImpl();

    public static void main(String[] args) {
/*        int userID = 1;
        User usr = userDTO.getUserByID(userID);
        System.out.println("User ID is: " + userID + ";");
        System.out.println("User name is: " + usr.getFirstName() + " " + usr.getLastName() + ";");
        System.out.println("User email is: " + usr.getEmail() + ".");*/

        int userID = 1;
        User userFromSourceDB = userDTO.getUserByID(true, userID);
        User userFromTargetDB = userDTO.getUserByID(false, userID);
        System.out.println("User from source DB: " + userFromSourceDB.getFirstName() + " " + userFromSourceDB.getLastName());
        System.out.println("User from target DB: " + userFromTargetDB.getFirstName() + " " + userFromTargetDB.getLastName());

    }

    @Test
    public void testLastNames_fail(){
        int userID_1 = 1;
        int userID_2 = 1;
        User userFromSourceDB = userDTO.getUserByID(true, userID_1);
        User userFromTargetDB = userDTO.getUserByID(false, userID_2);
        Assert.assertEquals(userFromSourceDB.getFirstName(), userFromTargetDB.getLastName());
    }

    @Test
    public void testLastNames_pass(){
        int userID_1 = 2;
        int userID_2 = 3;
        User userFromSourceDB = userDTO.getUserByID(true, userID_1);
        User userFromTargetDB = userDTO.getUserByID(false, userID_2);
        Assert.assertEquals(userFromSourceDB.getFirstName(), userFromTargetDB.getFirstName());
    }
}
