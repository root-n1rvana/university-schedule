package ua.foxminded.javaspring.kocherga.web_application.models;

import jakarta.persistence.*;

import java.util.Objects;
import java.util.Set;

@Entity
@Table(name = "users")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(name = "firstname", length = 50)
    private String userName;

    @Column(name = "lastname",length = 50)
    private String userLastname;

    @ManyToOne
    @JoinColumn(name = "group_id", nullable = false)
    private Group ownerGroup;

    @ManyToOne
    @JoinColumn(name = "role_id", nullable = false)
    private Role ownerRole;

    @ManyToMany
    @JoinTable(
            name = "users_courses",
            joinColumns = @JoinColumn(name = "user_id"),
            inverseJoinColumns = @JoinColumn(name = "course_id")
    )
    private Set<Course> userCourses;

    public User() {
    }

    public User(String userName, String userLastname, Group ownerGroup, Role ownerRole) {
        this.userName = userName;
        this.userLastname = userLastname;
        this.ownerGroup = ownerGroup;
        this.ownerRole = ownerRole;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
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

    public Set<Course> getUserCourses() {
        return userCourses;
    }

    public void setUserCourses(Set<Course> userCourses) {
        this.userCourses = userCourses;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        User user = (User) o;
        return id == user.id && Objects.equals(userName, user.userName) && Objects.equals(userLastname, user.userLastname);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, userName, userLastname);
    }

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", userName='" + userName + '\'' +
                ", userLastname='" + userLastname + '\'' +
                ", ownerGroup=" + ownerGroup +
                ", ownerRole=" + ownerRole +
                ", userCourses=" + userCourses +
                '}';
    }
}
