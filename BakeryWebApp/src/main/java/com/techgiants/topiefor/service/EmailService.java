package com.techgiants.topiefor.service;

import com.techgiants.topiefor.model.EmailMessage;


public interface EmailService {
    
    public void sendMail(EmailMessage mime)throws Exception;
}
