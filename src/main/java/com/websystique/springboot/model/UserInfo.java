package com.websystique.springboot.model;

public class UserInfo {
    private String username;

    private String password;

    public UserInfo(){
        username = "";
        password = "";
    }

    public UserInfo(String username, String password){
        this.username = username;
        this.password = password;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }



//    @Override
//    public boolean equals(Object obj) {
//        if (this == obj)
//            return true;
//        if (obj == null)
//            return false;
//        if (getClass() != obj.getClass())
//            return false;
//        User other = (User) obj;
//        return id == other.id;
//    }

    @Override
    public String toString() {
        return "UserInfo [username=" + username + ", password=" + password + "]";
    }
}
