package ness.model;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.Objects;

@Document(collection = "userinfo")

public class UserInfo {

    public UserInfo() {
    }

    private String email;

    private int phone;

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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        UserInfo userInfo = (UserInfo) o;
        return phone == userInfo.phone &&
                Objects.equals(email, userInfo.email) ;
    }

    @Override
    public String toString() {
        return "UserInfo{" +
                ", email='" + email + '\'' +
                ", phone=" + phone +
                '}';
    }
}
