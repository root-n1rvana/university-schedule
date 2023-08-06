package ua.foxminded.javaspring.kocherga.web_application.models;

import java.util.List;

public class UserListWrapper {

    private List<User> users;

    public List<User> getUsers() {
        return users;
    }

    public void setUsers(List<User> users) {
        this.users = users;
    }
}
