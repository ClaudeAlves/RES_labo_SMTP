package ch.heigvd.res.smtplab;

import ch.heigvd.res.smtplab.config.ConfigParser;
import ch.heigvd.res.smtplab.model.prank.Pranker;

import java.io.IOException;

/**
 * Hello world!
 */
public class MailPranker {
    public static void main(String[] args) {
        ConfigParser cp = null;
        try {
            cp = new ConfigParser("src/main/resources/config.properties");
        } catch (IOException e) {
            e.printStackTrace();
        }

        Pranker mp = new Pranker(cp);
    }
}
