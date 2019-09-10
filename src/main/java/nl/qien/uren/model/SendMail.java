package nl.qien.uren.model;

import nl.qien.uren.entity.Timesheet;

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


    public boolean sendMail(Timesheet timesheet){
        receiver = timesheet.getCustomerEmail();
        String url = "http://localhost:8080/uren/approveTimesheet?id="+timesheet.getCustomerKey();
        subject = "html mail";
        String contentOfMessage = "Beste "+timesheet.getCustomerName()+", <p> er staat een werkbriefje van "+timesheet.getEmployeeName()+" klaar om goed te keuren</p>" +
                "<a href='"+url+"'>klik hier om het in te zien</a>";
        return sendMailHTML(receiver, "Goedkeuren urenformulier Qien", contentOfMessage);
    }

    public boolean sendMailHTML(String receiver, String subject, String message) {
        return sendMail(receiver, subject,message, "html");
    }

    public boolean sendMailText(String receiver, String subject, String message) {
        return sendMail(receiver, subject,message, "text");
    }


    public boolean sendMail(String receiver, String subject, String message, String type) {
        System.out.println("\"test\" = " + "test");
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
