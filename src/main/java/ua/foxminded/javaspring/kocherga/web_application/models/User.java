package ua.foxminded.javaspring.kocherga.web_application.models;

import jakarta.persistence.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Entity
@Table(name = "users")
public class User {

    @Id
    @Column(name = "user_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(name = "user_name")
    private String userName;

    @Column(name = "user_lastname")
    private String userLastname;

    @ManyToOne
    @JoinColumn(name = "group_id")
    private Group ownerGroup;

    @ManyToOne
    @JoinColumn(name = "role_id")
    private Role ownerRole;

    @ManyToMany(cascade = {CascadeType.PERSIST, CascadeType.MERGE})
    @JoinTable(
            name = "users_courses",
            joinColumns = @JoinColumn(name = "user_id"),
            inverseJoinColumns = @JoinColumn(name = "course_id")
    )
    private List<Course> userCourses = new ArrayList<>();

    public User() {
    }

    public User(String userName, String userLastname, Group ownerGroup, Role ownerRole) {
        this.userName = userName;
        this.userLastname = userLastname;
        this.ownerGroup = ownerGroup;
        this.ownerRole = ownerRole;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getUserLastname() {
        return userLastname;
    }

    public void setUserLastname(String userLastname) {
        this.userLastname = userLastname;
    }

    public Group getOwnerGroup() {
        return ownerGroup;
    }

    public void setOwnerGroup(Group ownerGroup) {
        this.ownerGroup = ownerGroup;
    }

    public Role getOwnerRole() {
        return ownerRole;
    }

    public void setOwnerRole(Role ownerRole) {
        this.ownerRole = ownerRole;
    }

    public List<Course> getUserCourses() {
        return userCourses;
    }

    public void setUserCourses(List<Course> userCourses) {
        this.userCourses = userCourses;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        User user = (User) o;
        return id == user.id && Objects.equals(userName, user.userName) && Objects.equals(userLastname, user.userLastname) && Objects.equals(ownerGroup, user.ownerGroup) && Objects.equals(ownerRole, user.ownerRole);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, userName, userLastname, ownerGroup, ownerRole);
    }

//    @Override
//    public String toString() {
//        return "User{" +
//                "id=" + id +
//                ", userName='" + userName + '\'' +
//                ", userLastname='" + userLastname + '\'' +
//                ", ownerGroup=" + ownerGroup +
//                ", ownerRole=" + ownerRole +
//                ", userCourses=" + userCourses +
//                '}';
//    }
}
