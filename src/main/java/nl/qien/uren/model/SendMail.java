package nl.qien.uren.model;

import nl.qien.uren.entity.Timesheet;
import nl.qien.uren.entity.User;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Properties;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

public class SendMail {
    private String receiver;
    private String subject;
    private String message;
    public SendMail(String receiver, String subject, String message) {
        this.receiver = receiver;
        this.subject = subject;
        this.message = message;
    }

    public SendMail(){

    }

    public boolean sendMail(User user){
        receiver = user.getUsername();
        String url = "http://uren.cfapps.io/uren/changePassword/"+ user.getPasswordKey();
        subject = "html mail";
        String contentOfMessage = "Beste "+user.getFirstname()+" " + user.getLastname()+", <p> Klik op de onderstaande link om je wachtwoord te resetten.</p>" +
                "<a href='"+url+"'>klik hier om het wachtwoord te wijzigen.</a>";
        return sendMailHTML(receiver, "Qien wachtwoord vergeten :(", contentOfMessage);
    }

    public boolean sendMail(Timesheet timesheet){
        receiver = timesheet.getCustomerEmailAddress();
        String url = "http://uren.cfapps.io/uren/showTimesheetToCustomer/"+ timesheet.getCustomerKey();
        subject = "html mail";
        String contentOfMessage = "Beste "+timesheet.getCustomerName()+", <p> er staat een werkbriefje van "+timesheet.getEmployeeName()+" klaar om goed te keuren</p>" +
                "<a href='"+url+"'>klik hier om het in te zien.</a>";
        return sendMailHTML(receiver, "Goedkeuren urenformulier Qien", contentOfMessage);
    }

    public boolean sendApproveMail(Timesheet timesheet){
        receiver = timesheet.getEmployeeEmail();
        String url = "https://media1.tenor.com/images/305dd88cc6ad71068b776e0646d3f460/tenor.gif?itemid=3555043";
        subject = "html mail";
        String contentOfMessage = "Beste "+timesheet.getEmployeeName()+", <p> jouw urenformulier is goedgekeurd door: "+timesheet.getCustomerName() +
                "</p><p>Gefeliciteerd!</p><p>JoeeJoee Cora </p><img src='"+url+"'>";
        return sendMailHTML(receiver, "Urenformulier goedgekeurd!", contentOfMessage);

    }

    public boolean sendSadMail(Timesheet timesheet){
        receiver = timesheet.getEmployeeEmail();
        String url = "https://media1.tenor.com/images/9413ffc5a11722a3cc456a88810750bd/tenor.gif?itemid=14193216";
        subject = "html mail";
        String contentOfMessage = "Beste "+timesheet.getEmployeeName()+", <p> jouw urenformulier is FOUT en daarom afgewezen door: "+timesheet.getCustomerName()+".</p><p> Zou je het urenformulier willen nakijken en opnieuw opsturen?</p><p> Groetjes Cora!</p>" +
                "<img src='"+url+"'>";
        return sendMailHTML(receiver, "Urenformulier AFGEWEZEN!", contentOfMessage);
    }

    public boolean sendMailHTML(String receiver, String subject, String message) {
        return sendMail(receiver, subject,message, "html");
    }

    public boolean sendMailText(String receiver, String subject, String message) {
        return sendMail(receiver, subject,message, "text");
    }


    public boolean sendMail(String receiver, String subject, String message, String type) {
        //Setting up configurations for the email connection to the Google SMTP server using TLS
        Properties props = new Properties();
        props.put("mail.smtp.host", "true");
        props.put("mail.smtp.starttls.enable", "true");
        props.put("mail.smtp.host", "smtp.gmail.com");
        props.put("mail.smtp.port", "587");
        props.put("mail.smtp.auth", "true");
        //Establishing a session with required user details
        Session session = Session.getInstance(props, new javax.mail.Authenticator() {
            protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication("qienSMTP@gmail.com", "Qien0101");
            }
        });
        try {
            //Creating a Message object to set the email content
            MimeMessage msg = new MimeMessage(session);
            //Storing the comma seperated values to email addresses
            //"test@test.nl, test@test.com"
            String to = receiver;
            /*Parsing the String with defualt delimiter as a comma by marking the boolean as true and storing the email
            addresses in an array of InternetAddress objects*/
            InternetAddress[] address = InternetAddress.parse(to, true);
            //Setting the recepients from the address variable
            msg.setRecipients(Message.RecipientType.TO, address);
            String timeStamp = new SimpleDateFormat("yyyymmdd_hh-mm-ss").format(new Date());
            msg.setSubject(subject);
            msg.setSentDate(new Date());
            if(type.equalsIgnoreCase("text")){
                msg.setText(message);
            } else if(type.equalsIgnoreCase("html")){
                msg.setContent(message,"text/html");
            }
            msg.setHeader("XPriority", "1");
            Transport.send(msg);
            return true;
        } catch (MessagingException mex) {
            return false;
        }
    }

    public String getReceiver() {
        return receiver;
    }

    public void setReceiver(String receiver) {
        this.receiver = receiver;
    }

    public String getSubject() {
        return subject;
    }

    public void setSubject(String subject) {
        this.subject = subject;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
