package com.rishav.service;

import com.rishav.model.Chat;
import org.springframework.stereotype.Service;


public interface ChatService {

    Chat createChat(Chat chat);
}
