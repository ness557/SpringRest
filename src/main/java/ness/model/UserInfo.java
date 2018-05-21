package ness.model;

import javax.persistence.*;
import java.util.Objects;

@Entity
@Table(name = "user_info")
public class UserInfo {

    public UserInfo() {
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(name = "email")
    private String email;

    @Column(name = "phone")
    private int phone;

//    @OneToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL, mappedBy = "users")
//    private User user;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public int getPhone() {
        return phone;
    }

    public void setPhone(int phone) {
        this.phone = phone;
    }

//    public User getUser() {
//        return user;
//    }
//
//    public void setUser(User user) {
//        this.user = user;
//    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        UserInfo userInfo = (UserInfo) o;
        return id == userInfo.id &&
                phone == userInfo.phone &&
                Objects.equals(email, userInfo.email) ;
//                && Objects.equals(user, userInfo.user);
    }

    @Override
    public String toString() {
        return "UserInfo{" +
                "id=" + id +
                ", email='" + email + '\'' +
                ", phone=" + phone +
//                ", user_id=" + user.getId() +
                '}';
    }
}
