package ua.kiev.prog;

import java.util.ArrayList;
import java.util.List;

public class Room {
    private String name;
    List<User> usersList = new ArrayList<User>();

    public Room() {
    }

    public Room(String name, List<User> userList) {
        this.name = name;
        this.usersList = userList;
    }

    public boolean findUser(String login){
        for(User user : usersList){
            if(user.getLogin().equals(login))
                return true;
        }
        return false;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<User> getUserList() {
        return usersList;
    }

    public void setUserList(List<User> userList) {
        this.usersList = userList;
    }
}
