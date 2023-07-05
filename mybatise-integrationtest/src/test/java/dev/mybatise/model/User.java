package dev.mybatise.model;

import dev.mybatise.typehander.AddressTypeHandler;
import io.github.mybatise.annotation.Column;
import io.github.mybatise.annotation.GeneratedValue;
import io.github.mybatise.annotation.GenerationType;
import io.github.mybatise.annotation.Table;

/**
 * @author Wes Lin
 */
@Table(name = "user")
public class User extends AbstractEntity {

    @Column(name = "user_id", id = true)
    @GeneratedValue(strategy = GenerationType.STATEMENT, keyStatement = "SELECT LAST_INSERT_ROWID();")
    private String userId;
    @Column(name = "password")
    private String password;
    @Column(name = "user_name")
    private String userName;
    @Column(name = "gender")
    private Boolean gender;

    @Column(name = "address", typeHandler = AddressTypeHandler.class)
    private Address address;

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public Boolean getGender() {
        return gender;
    }

    public void setGender(Boolean gender) {
        this.gender = gender;
    }

    public Address getAddress() {
        return address;
    }

    public void setAddress(Address address) {
        this.address = address;
    }
}
