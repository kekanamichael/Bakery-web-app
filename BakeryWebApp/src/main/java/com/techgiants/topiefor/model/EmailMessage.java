/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.techgiants.topiefor.model;

/**
 *
 * @author VM JELE
 */
public class EmailMessage {
    
    private String message;
    private String messageType;
    private String subject; 
    private String title;
    private String receiver;

    public EmailMessage(String message, String messageType, String subject, String title, String receiver) {
        this.message = message;
        this.messageType = messageType;
        this.subject = subject;
        this.title = title;
        this.receiver = receiver;
    }

    public EmailMessage() {
    }
    
    

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getMessageType() {
        return messageType;
    }

    public void setMessageType(String messageType) {
        this.messageType = messageType;
    }

    public String getSubject() {
        return subject;
    }

    public void setSubject(String subject) {
        this.subject = subject;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getReceiver() {
        return receiver;
    }

    public void setReceiver(String receiver) {
        this.receiver = receiver;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("EmailMessage{message=").append(message);
        sb.append(", messageType=").append(messageType);
        sb.append(", subject=").append(subject);
        sb.append(", title=").append(title);
        sb.append(", receiver=").append(receiver);
        sb.append('}');
        return sb.toString();
    }

    
    
    
    
}
