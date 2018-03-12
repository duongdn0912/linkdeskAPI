package com.websystique.springboot.service;

import com.websystique.springboot.model.User;

public interface CheckinService {

    Boolean checkIn (String seatNumber, String userId);
    Boolean resetSeat ();
    String[] getSeatDummyDatas ();

}
