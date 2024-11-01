package com.lecspace.ictproject.service;

import com.lecspace.ictproject.entity.Booking;
import com.lecspace.ictproject.entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

@Service
public class EmailService {

    @Autowired
    private static JavaMailSender mailSender;

    public static void sendEmail(String to, String subject, String body) {
        SimpleMailMessage message = new SimpleMailMessage();
        message.setTo(to);
        message.setSubject(subject);
        message.setText(body);
        mailSender.send(message);
    }

    public void sendBookingConfirmation(User user, Booking booking)
    {
        String subject = "Booking Confirmation";
        String body = String.format("Your booking for room %s at %s has been confirmed.", booking.getRoomId(), booking.getStartTime());
        sendEmail(user.getEmail(), subject, body);
    }

}
