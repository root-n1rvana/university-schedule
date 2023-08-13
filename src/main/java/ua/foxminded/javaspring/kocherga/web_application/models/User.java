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
    private String firstname;

    @Column(name = "lastname",length = 50)
    private String lastname;

    @Column(name = "login", length = 20, nullable = false, unique = true)
    private String login;

    @Column(nullable = false)
    private String password;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "group_id", nullable = false)
    private Group ownerGroup;

//    @ManyToMany(fetch = FetchType.EAGER, cascade=CascadeType.ALL)
//    @JoinTable(
//            name="users_roles",
//            joinColumns={@JoinColumn(name="user_id", referencedColumnName="id")},
//            inverseJoinColumns={@JoinColumn(name="role_id", referencedColumnName="id")})
//    private Set<Role> roles;

    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(
            name = "users_courses",
            joinColumns = @JoinColumn(name = "user_id"),
            inverseJoinColumns = @JoinColumn(name = "course_id"))
    private Set<Course> userCourses;

    public User() {
    }

    public User(long id, String firstname, String lastname, String login, String password, Group ownerGroup) {
        this.id = id;
        this.firstname = firstname;
        this.lastname = lastname;
        this.login = login;
        this.password = password;
        this.ownerGroup = ownerGroup;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getFirstname() {
        return firstname;
    }

    public void setFirstname(String userName) {
        this.firstname = userName;
    }

    public String getLastname() {
        return lastname;
    }

    public void setLastname(String userLastname) {
        this.lastname = userLastname;
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String loginName) {
        this.login = loginName;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Group getOwnerGroup() {
        return ownerGroup;
    }

    public void setOwnerGroup(Group ownerGroup) {
        this.ownerGroup = ownerGroup;
    }

//    public Set<Role> getRoles() {
//        return roles;
//    }
//
//    public void setRoles(Set<Role> roles) {
//        this.roles = roles;
//    }

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
        return id == user.id && Objects.equals(firstname, user.firstname) && Objects.equals(lastname, user.lastname);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, firstname, lastname);
    }

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", userName='" + firstname + '\'' +
                ", userLastname='" + lastname + '\'' +
                ", ownerGroup=" + ownerGroup +
                ", userCourses=" + userCourses +
                '}';
    }
}
