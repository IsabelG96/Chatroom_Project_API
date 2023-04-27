package com.example.chatroom_project.controllers;


import com.example.chatroom_project.dtos.ChatroomDTO;
import com.example.chatroom_project.models.Chatroom;
import com.example.chatroom_project.models.Message;
import com.example.chatroom_project.models.User;
import com.example.chatroom_project.services.ChatroomService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "/chatrooms")
public class ChatroomController {

    @Autowired
    ChatroomService chatroomService;


//    INDEX
    @GetMapping
    public ResponseEntity<List<Chatroom>> displayAllChatrooms(){
        return new ResponseEntity<>(chatroomService.getAllChatrooms(), HttpStatus.OK);
    }

//    SHOW
    @GetMapping( value = "/{id}")
    public ResponseEntity<Chatroom> displayChatroomById(@PathVariable Long id){
        return new ResponseEntity<>(chatroomService.getChatroomById(id), HttpStatus.OK);
    }

    // EDIT:
    @GetMapping(value = "/{chatroomId}/edit")
    public ResponseEntity<Chatroom> editChatroomName (@PathVariable Long chatroomId){
        Chatroom foundChatroomName = chatroomService.getChatroomById(chatroomId);
        return new ResponseEntity<>(foundChatroomName, HttpStatus.OK);
    }

    //    UPDATE:
    @PutMapping(value = "{chatroomId}")
    public ResponseEntity<Chatroom> updateChatroomName (@PathVariable Long chatroomId, @RequestBody ChatroomDTO chatroomDTO){
        Chatroom updateChatroomName =  chatroomService.updateChatroomName(chatroomDTO, chatroomId);
        return new ResponseEntity<>(updateChatroomName, HttpStatus.OK);
    }


    //    CREATE
    @PostMapping
    public ResponseEntity<Chatroom> createChatroom(@RequestBody Chatroom chatroom){
        return new ResponseEntity<>(chatroomService.createChatroom(chatroom), HttpStatus.OK);
    }

//    DELETE
    @DeleteMapping(value = "/{id}")
    public ResponseEntity<List<Chatroom>> deleteChatroom(@PathVariable Long id){
        if (chatroomService.deleteChatroom(id) == null){
            return new ResponseEntity<>(null, HttpStatus.BAD_REQUEST);
        } else {
            return new ResponseEntity<>(chatroomService.deleteChatroom(id), HttpStatus.OK);
        }
    }

// UPDATE
    @PatchMapping(value = "/addUser/{id}")
    public ResponseEntity<List<User>> addUserToChatroom(@PathVariable Long id, @RequestBody Long userId){
        if (chatroomService.addUserToChatroom(userId, id) == null){
            return new ResponseEntity<>(null, HttpStatus.ALREADY_REPORTED);
        } else {
            return new ResponseEntity<>(chatroomService.addUserToChatroom(userId, id), HttpStatus.OK);
        }
    }

//    UPDATE
    @PatchMapping(value = "/removeUser/{id}")
    public ResponseEntity<List<User>> removeUserFromChatroom(@PathVariable Long id, @RequestBody Long userId){
        try {
            return new ResponseEntity<>(chatroomService.removeUserFromChatroom(userId, id), HttpStatus.OK);
        } catch(NullPointerException e){
            return new ResponseEntity<>(null, HttpStatus.BAD_REQUEST);
        }
    }

//    SHOW
    @GetMapping(value = "/{id}/messages")
    public ResponseEntity<List<Message>> retrieveMessagesForChatroom(@PathVariable Long id){
        return new ResponseEntity<>(chatroomService.findMessagesForChatroomTimeDesc(id), HttpStatus.OK);

    }

}
