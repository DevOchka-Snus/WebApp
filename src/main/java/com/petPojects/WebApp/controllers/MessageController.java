package com.petPojects.WebApp.controllers;

import com.petPojects.WebApp.models.Message;
import com.petPojects.WebApp.services.MessageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
public class MessageController {
    @Autowired
    private MessageService messageService;
    @GetMapping("/greeting")
    public String greeting(
            @RequestParam(name = "name", required = false, defaultValue = "World") String name,
            Model model
    ) {
        model.addAttribute("name", name);
        return "greeting";
    }
    @GetMapping
    public String main(Model model) {
        Iterable<Message> messages = messageService.getAllMessages();
        model.addAttribute("messages", messages);
        return "main";
    }

    @GetMapping("/new")
    public String createMessageForm(Model model) {

        Message message = new Message();
        model.addAttribute("message", message);
        return "create_message";
    }

    @PostMapping
    public String saveMessage(@ModelAttribute("message") Message message) {
        messageService.saveMessage(message);
        return "redirect:/";
    }

   @PostMapping("/filter")
    public String filterMessages(@RequestParam("tag") String tag, Model model) {
        model.addAttribute("messages", messageService.getAllMessagesByTag(tag));
        return "main";
    }

    @GetMapping("/edit/{id}")
    public String editMessageForm(@PathVariable("id") Long id, Model model) {
        model.addAttribute("message", messageService.getMessageById(id));
        return "edit_message";
    }

    @PostMapping("/{id}")
    public String updateMessage(@PathVariable("id") Long id,
                                @ModelAttribute("message") Message message,
                                Model model) {
        Message messageToUpdate = messageService.getMessageById(id);
        messageToUpdate.setId(id);
        messageToUpdate.setText(message.getText());
        messageToUpdate.setTag(message.getTag());
        messageService.updateMessage(messageToUpdate);
        return "redirect:/";
    }

    @GetMapping("/{id}")
    public String deleteMessage(@PathVariable("id") Long id, Model model) {
        messageService.deleteMessageById(id);
        return "redirect:/";
    }
}
