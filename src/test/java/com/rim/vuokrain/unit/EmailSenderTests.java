package com.rim.vuokrain.unit;

import static com.rim.vuokrain.configuration.AppConfiguration.TEST_PROFILE;
import static org.junit.Assert.*;

import javax.annotation.Resource;
import javax.mail.Message;
import javax.mail.MessagingException;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;

import com.icegreen.greenmail.util.GreenMail;
import com.icegreen.greenmail.util.GreenMailUtil;
import com.icegreen.greenmail.util.ServerSetupTest;
import com.rim.vuokrain.configuration.AppConfiguration;

@RunWith(SpringJUnit4ClassRunner.class)
@ActiveProfiles( TEST_PROFILE )
@ContextConfiguration(classes = AppConfiguration.class)
@WebAppConfiguration
public class EmailSenderTests {

	// https://dimitrisli.wordpress.com/2012/09/17/spring-email-testing-using-greenmail/
	
	@Resource
    private JavaMailSender emailSender;
 
    private GreenMail testSmtp;
 
    @Before
    public void testSmtpInit(){
        testSmtp = new GreenMail(ServerSetupTest.SMTP);
        testSmtp.start();
     }
 
    @Test
    public void testEmail() throws InterruptedException, MessagingException {
        SimpleMailMessage message = new SimpleMailMessage();
         
        message.setFrom("test@sender.com");
        message.setTo("test@receiver.com");
        message.setSubject("test subject");
        message.setText("test message");
        emailSender.send(message);
         
        Message[] messages = testSmtp.getReceivedMessages();
        assertEquals(1, messages.length);
        assertEquals("test subject", messages[0].getSubject());
        String body = GreenMailUtil.getBody(messages[0]).replaceAll("=\r?\n", "");
        assertEquals("test message", body);
    }
 
    @After
    public void cleanup(){
        testSmtp.stop();
    }	
}