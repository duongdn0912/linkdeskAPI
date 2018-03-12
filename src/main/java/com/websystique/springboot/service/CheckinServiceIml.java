package com.websystique.springboot.service;

import com.websystique.springboot.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;

@Service("checkinService")
public class CheckinServiceIml implements CheckinService{

    @Autowired
    UserService userService;

    private static final String seatDummyDatas[] = {"seat-1", "seat-2", "seat-3" , "seat-4", "seat-5"};
    private ArrayList<String> seatCheckedIn = new ArrayList<>();
    public Boolean checkIn (String seatNumber, String userId) {
        Boolean userExist = false;
        Boolean isCheckedInSuccess =false;
        for(User user : userService.findAllUsers()){
            if(user.getUserId().equals(userId)){
                userExist = true;
            }
        }

        if (userExist) {
            for(String seat : seatDummyDatas){
                if(seat.equals(seatNumber)){
                    if (seatCheckedIn.size() == 0) {
                        isCheckedInSuccess = true;
                        seatCheckedIn.add(seatNumber);
                    } else {
                        Boolean isSeatAvailable = true;
                        for (String checkedInSeat : seatCheckedIn) {
                            if (checkedInSeat.equals(seat)) {
                                isSeatAvailable = false;
                            }
                        }
                        if (isSeatAvailable) {
                            isCheckedInSuccess = true;
                            seatCheckedIn.add(seatNumber);
                        }
                    }
                }
            }
        }
        return isCheckedInSuccess;
    }

    public Boolean resetSeat () {
        seatCheckedIn.clear();
        return true;
    }

    public String[] getSeatDummyDatas () {
        return seatDummyDatas;
    }
}
