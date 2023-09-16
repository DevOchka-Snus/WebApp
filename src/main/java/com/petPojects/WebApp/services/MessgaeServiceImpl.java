package com.petPojects.WebApp.services;

import com.petPojects.WebApp.models.Message;
import com.petPojects.WebApp.models.MessageRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MessgaeServiceImpl implements MessageService{
    private MessageRepository messageRepository;

    public MessgaeServiceImpl(MessageRepository messageRepository) {
        this.messageRepository = messageRepository;
    }

    @Override
    public Iterable<Message> getAllMessages() {
        return messageRepository.findAll();
    }

    @Override
    public Message saveMessage(Message message) {
        return messageRepository.save(message);
    }

    @Override
    public Iterable<Message> getAllMessagesByTag(String tag) {
        Iterable<Message> messages;
        if (tag != null && !tag.isEmpty()) {
            messages = messageRepository.findByTag(tag);
        } else {
            messages = messageRepository.findAll();
        }
        return messages;
    }

    @Override
    public Message getMessageById(Long id) {
        return messageRepository.findById(id).get();
    }

    @Override
    public Message updateMessage(Message message) {
        return messageRepository.save(message);
    }

    @Override
    public void deleteMessageById(Long id) {
        messageRepository.deleteById(id);
    }
}
