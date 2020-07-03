package com.bugly.common.sms;

import com.amazonaws.auth.AWSStaticCredentialsProvider;
import com.amazonaws.auth.BasicAWSCredentials;
import com.amazonaws.regions.Regions;
import com.amazonaws.services.simpleemail.AmazonSimpleEmailService;
import com.amazonaws.services.simpleemail.AmazonSimpleEmailServiceClientBuilder;
import com.amazonaws.services.simpleemail.model.*;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;

/**
 * @author no_f
 * @since 2020-07-03
 */
@Component
public class EmailUtil {

    private static AmazonSimpleEmailService client = null;

    @Value("${aws.sns.accessKey}")
    private String accessKey;

    @Value("${aws.sns.secretKey}")
    private String secretKey;

    private static String langTemplateName = "异常日统计报告";

    @PostConstruct
    public void init() {
        client = AmazonSimpleEmailServiceClientBuilder
                .standard()
                .withCredentials(new AWSStaticCredentialsProvider(new BasicAWSCredentials(accessKey, secretKey)))
                .withRegion(Regions.US_WEST_2)
                .build();
    }

    /**
     *
     * @param emailMsg 邮件内容
     * @param to 邮件接受者
     */
    public static void sendEmail(String emailMsg, String emailSender, String to) {
        Destination destination = new Destination().withToAddresses(to.split(","));
        Content subject = new Content().withData(langTemplateName);
        Content textBody = new Content().withData(emailMsg);
        Body body = new Body().withHtml(textBody);
        Message message = new Message().withSubject(subject).withBody(body);
        SendEmailRequest request = new SendEmailRequest().withSource(emailSender)
                .withDestination(destination).withMessage(message);
        SendEmailResult result = client.sendEmail(request);
    }

}
