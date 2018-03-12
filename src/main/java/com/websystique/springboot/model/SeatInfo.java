package com.websystique.springboot.model;

public class SeatInfo {
    private String seatNumber;

    private String userNumber;

    public SeatInfo(){
        seatNumber = "";
        userNumber = "";
    }

    public SeatInfo(String seatNumber, String userNumber){
        this.seatNumber = seatNumber;
        this.userNumber = userNumber;
    }

    public String getSeatNumber() {
        return seatNumber;
    }

    public void setSeatNumber(String seatNumber) {
        this.seatNumber = seatNumber;
    }

    public String getUserNumber() {
        return userNumber;
    }

    public void setUserNumber(String userNumber) {
        this.userNumber = userNumber;
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
        return "SeatInfo [seatNumber=" + seatNumber + ", userNumber=" + userNumber + "]";
    }
}
