package org.issam.ecommerceweb.utilize;

import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.AddressException;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import javax.net.ssl.SSLContext;
import javax.net.ssl.TrustManager;
import javax.net.ssl.X509TrustManager;
import java.security.KeyManagementException;
import java.security.NoSuchAlgorithmException;
import java.security.cert.CertificateException;
import java.security.cert.X509Certificate;
import java.util.Properties;


public class MailModel {


    private Properties mailServerProperties;
    private Session getMailSession;
    private MimeMessage generateMailMessage;

    //-------------- local vars ------------------
    final private String SENDER_MAIL = "kamalsidki.dev@gmail.com.com";
    final private String PASSWORD = "";
    final private String SMTP = "smtp.gmail.com";

    //-------------- object vars ------------------
    private String to;
    private String subject;
    private String emailBody;

    public MailModel(String to, String subject, String emailBody) {
        this.to = to;
        this.subject = subject;
        this.emailBody = emailBody;
    }

    // Ajout de votre TrustManager personnalisé
    private TrustManager[] trustManagers = new TrustManager[]{
            new X509TrustManager() {
                @Override
                public void checkClientTrusted(X509Certificate[] chain, String authType) {
                    // Implémentez si nécessaire
                }

                @Override
                public void checkServerTrusted(X509Certificate[] chain, String authType) throws CertificateException {
                    // Implémentez vos propres vérifications du certificat ici
                    // Par exemple, comparez le certificat avec vos attentes
                }

                @Override
                public X509Certificate[] getAcceptedIssuers() {
                    return null;
                }
            }
    };

    public boolean sendMail() {
        try {

            //-------------- setup Mail Server Properties ------------------
            mailServerProperties = System.getProperties();
            mailServerProperties.put("mail.smtp.port", "587");
            mailServerProperties.put("mail.smtp.auth", "true");
            mailServerProperties.put("mail.smtp.starttls.enable", "true");
            // Configurer le SSLContext avec votre TrustManager
            SSLContext sslContext = SSLContext.getInstance("TLS");
            try {
                sslContext.init(null, trustManagers, null);
            } catch (KeyManagementException e) {
                e.printStackTrace();
            }
            mailServerProperties.put("mail.smtp.ssl.socketFactory", sslContext.getSocketFactory());


            //-------------- get Mail Session ------------------

            getMailSession = Session.getDefaultInstance(mailServerProperties, null);
            generateMailMessage = new MimeMessage(getMailSession);
            generateMailMessage.addRecipient(Message.RecipientType.TO, new InternetAddress(to));
            generateMailMessage.setSubject("Company Name | " + subject);
            emailBody += "<br><br>Your Chat Team <br> <a href='#'>visit us</a>";
            generateMailMessage.setContent(emailBody, "text/html");


            //-------------- Get Session and Send mail ------------------
            Transport transport = getMailSession.getTransport("smtp");
            transport.connect(SMTP, SENDER_MAIL, PASSWORD);
            transport.sendMessage(generateMailMessage, generateMailMessage.getAllRecipients());
            transport.close();

        } catch (AddressException ex) {
            ex.printStackTrace();
            return false;
        } catch (MessagingException ex) {
            ex.printStackTrace();
            return false;
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }
        return true;

    }

}
