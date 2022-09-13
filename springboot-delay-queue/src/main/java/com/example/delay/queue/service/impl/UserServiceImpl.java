package com.example.delay.queue.service.impl;

import com.example.delay.queue.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.Date;

@Service
@Slf4j
public class UserServiceImpl implements UserService {
    @Override
    public String operate(String action) {
        log.info("action do at: {}",new Date());
        return action;
    }
}
