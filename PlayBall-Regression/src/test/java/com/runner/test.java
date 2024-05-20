package com.runner;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.IOException;

public class test {

        public static void main(String[] args) throws IOException {
//            String yopmailAddress = generateYOPmailAddress();
//            System.out.println(yopmailAddress);

            String inboxHtml = getInboxHtml("cp@yopmail.com");
            System.out.println(inboxHtml);

            // Step 3: Read Emails
            // Parse the HTML to extract email subjects, senders, and bodies
            // Example code to parse the inbox HTML using Jsoup:
            Document doc = Jsoup.parse(inboxHtml);
            Elements emailElements = doc.select("div.m")
                    .not(".s, .nui")
                    .not(":has(div.sby)");
            for (Element emailElement : emailElements) {
                // Extract email subject, sender, and body
                String subject = emailElement.select("span.lms").text();
                String sender = emailElement.select("span.lmth").text();
                String body = emailElement.select("div.lms").text();

                // Do something with the extracted email data
                System.out.println("Subject: " + subject);
                System.out.println("Sender: " + sender);
                System.out.println("Body: " + body);
                System.out.println("-----------------------------------");
            }
        }

        private static String generateYOPmailAddress() throws IOException {
            String yopmailHomePage = "https://yopmail.com/en/email-generator";
            Document doc = Jsoup.connect(yopmailHomePage).get();
            String address = doc.selectFirst("#geny > span:nth-child(1)").text();
            String domain = doc.selectFirst("#geny > span:nth-child(2)").text();
            return address+"@"+domain;
        }

        private static String getInboxHtml(String yopmailAddress) throws IOException {
            // Access the YOPmail inbox HTML for the generated address
            String inboxUrl = "http://www.yopmail.com/en/inbox.php?login=" + yopmailAddress+"&p=1&d=&ctrl=&yp=FAQVkAQNlZmR4Awx0ZQD4AGp&yj=EZwD3Amx5ZQHlBGV1AGDlBGL&v=9.2&r_c=&id=&ad=0";
            try (CloseableHttpClient httpClient = HttpClients.createDefault()) {
                HttpGet request = new HttpGet(inboxUrl);
                String inboxHtml = EntityUtils.toString(httpClient.execute(request).getEntity());
                return inboxHtml;
            }
        }

}
