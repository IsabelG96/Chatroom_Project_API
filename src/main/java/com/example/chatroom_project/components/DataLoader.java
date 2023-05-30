package com.example.chatroom_project.components;

import com.example.chatroom_project.models.Chatroom;
import com.example.chatroom_project.models.Message;
import com.example.chatroom_project.models.User;
import com.example.chatroom_project.repositories.ChatroomRepository;
import com.example.chatroom_project.repositories.MessageRepository;
import com.example.chatroom_project.repositories.UserRepository;
import com.example.chatroom_project.services.ChatroomService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Component;

@Component
public class DataLoader implements ApplicationRunner {

    @Autowired
    ChatroomRepository chatroomRepository;

    @Autowired
    UserRepository userRepository;

    @Autowired
    ChatroomService chatroomService;

    @Autowired
    MessageRepository messageRepository;


    public DataLoader(){

    }

    @Override
    public void run(ApplicationArguments args) throws Exception {

        Chatroom chatroom1 = new Chatroom("room1");
        Chatroom chatroom2 = new Chatroom("room2");
        Chatroom chatroom3 = new Chatroom("room3");
        Chatroom chatroom4 = new Chatroom("room4");

        User user1 = new User("Sujan", "sujan@gmail.com");
        User user2 = new User("Natasha", "natasha@gmail.com");
        User user3 = new User("Sarah", "sarah@gmail.com");
        User user4 = new User("Zaynah", "zaynah@gmail.com");

        Message message1 = new Message("Hello", user1, chatroom1);
        Message message2 = new Message("Hi, how are you?", user2, chatroom1);
        Message message3 = new Message("Not bad, you?", user1, chatroom1);

        Message message4 = new Message("Hello, do you want food?", user2, chatroom2);
        Message message5 = new Message("Yeah.", user3, chatroom2);
        Message message6 = new Message("Where do you want to go?", user2, chatroom2);

        Message message7 = new Message("Hello, where are you", user3, chatroom3);
        Message message8 = new Message("I am around the corner", user4, chatroom3);

        Message message9 = new Message("Hello?", user2, chatroom4);
        Message message10 = new Message("Pick up the phone", user4, chatroom4);



        chatroomRepository.save(chatroom1);
        chatroomRepository.save(chatroom2);
        chatroomRepository.save(chatroom3);
        chatroomRepository.save(chatroom4);

        userRepository.save(user1);
        userRepository.save(user2);
        userRepository.save(user3);
        userRepository.save(user4);
//        for chatroom 1:
        chatroomService.addUserToChatroom(1L, 1L);
        chatroomService.addUserToChatroom(2L, 1L);

//        for chatroom 2:
        chatroomService.addUserToChatroom(2L, 2L);
        chatroomService.addUserToChatroom(3L, 2L);

//        for chatroom 3:
        chatroomService.addUserToChatroom(3L, 3L);
        chatroomService.addUserToChatroom(4L, 3L);

//        for chatroom 4:
        chatroomService.addUserToChatroom(4L, 4L);
        chatroomService.addUserToChatroom(2L, 4L);


        messageRepository.save(message1);
        messageRepository.save(message2);
        messageRepository.save(message3);
        messageRepository.save(message4);
        messageRepository.save(message5);
        messageRepository.save(message6);
        messageRepository.save(message7);
        messageRepository.save(message8);
        messageRepository.save(message9);
        messageRepository.save(message10);



    }

}
