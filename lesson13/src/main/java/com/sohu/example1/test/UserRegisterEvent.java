package com.sohu.example1.test;

import com.sohu.example1.AbstractEvent;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UserRegisterEvent extends AbstractEvent {
    private String userName;

    public UserRegisterEvent(Object source, String userName) {
        super(source);
        this.userName = userName;
    }
}
