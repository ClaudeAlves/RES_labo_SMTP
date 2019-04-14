package ch.heigvd.res.smtplab.smtp;

import ch.heigvd.res.smtplab.model.mail.Mail;

import java.io.*;
import java.net.Socket;
import java.util.Date;

/**
 * SMTPClient helps connecting to a
 */
public class SMTPClient implements InterfaceSMTPClient{
    Socket client;
    BufferedReader reader;
    PrintWriter writer;

    /**
     * SMTPClient constructor
     * @param address SMTP server address
     * @param port SMTP server port
     */
    public SMTPClient(String address, int port) {
        try {
            client = new Socket(address, port);
            reader = new BufferedReader(new InputStreamReader(client.getInputStream(), "UTF-8"));
            writer = new PrintWriter(client.getOutputStream());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * Sends mail with the SMTP structure
     * @param mail mail to be sent
     * @throws IOException
     */
    @Override
    public void sendMessage(Mail mail) throws IOException {
        for (String recipient : mail.getTo()) {
            write(writer, "EHLO superserver");

            write(writer, "MAIL FROM: " + mail.getFrom());
            write(writer, "RCPT TO: " + recipient);
            write(writer, "DATA");
            write(writer, "Date: " + new Date());
            write(writer, "Subject: " + mail.getSubject());
            write(writer, "From: " + mail.getFrom());
            write(writer, "To: " + recipient);
            writer.write("\r\n");
            write(writer, mail.getBody());

            writer.write("\r\n.\r\n");
            writer.flush();

            // Workaround the limit of mails in a certain amount of time
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    private void write(Writer writer, String data) throws IOException {
        writer.write(data);
        writer.write("\r\n");
        writer.flush();
    }
}
