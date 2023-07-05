package dev.mybatise.model;

import io.github.mybatise.annotation.Column;
import io.github.mybatise.annotation.GeneratedValue;
import io.github.mybatise.annotation.GenerationType;
import io.github.mybatise.annotation.Table;

/**
 * @author Wes Lin
 */
@Table(name = "vip_user")
public class VipUser {

    @Column(name = "user_id", id = true)
    @GeneratedValue(strategy = GenerationType.GENERATOR, generator = MyId.class)
    private String userId;

    @Column(name = "user_name")
    private String userName;

    @Column(name = "password")
    private String password;

    @Column(name = "vip_num")
    private String vipNum;

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getVipNum() {
        return vipNum;
    }

    public void setVipNum(String vipNum) {
        this.vipNum = vipNum;
    }
}
