package com.petPojects.WebApp.services;

import com.petPojects.WebApp.models.Message;

import java.util.List;

public interface MessageService {
    Iterable<Message> getAllMessages();

    Message saveMessage(Message message);

    Iterable<Message> getAllMessagesByTag(String tag);
    Message getMessageById(Long id);
    Message updateMessage(Message message);
    void deleteMessageById(Long id);
}
