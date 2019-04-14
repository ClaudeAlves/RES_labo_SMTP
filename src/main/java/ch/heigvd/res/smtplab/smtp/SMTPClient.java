package ch.heigvd.res.smtplab.smtp;

import ch.heigvd.res.smtplab.model.mail.Mail;

import java.io.*;
import java.net.Socket;
import java.util.Date;

/**
 * SMTPClient helps connecting to a
 */
public class SMTPClient implements InterfaceSMTPClient{
    private String address;
    private int port;

    Socket client;
    BufferedReader reader;
    PrintWriter writer;

    /**
     * SMTPClient constructor
     * @param address SMTP server address
     * @param port SMTP server port
     */
    public SMTPClient(String address, int port) {
        this.address = address;
        this.port = port;
    }

    /**
     * Sends mail with the SMTP structure
     * @param mail mail to be sent
     * @throws IOException
     */
    @Override
    public void sendMessage(Mail mail) throws IOException {
        // Open socket and streams
        try {
            client = new Socket(address, port);
            reader = new BufferedReader(new InputStreamReader(client.getInputStream(), "UTF-8"));
            writer = new PrintWriter(client.getOutputStream());
        } catch (IOException e) {
            e.printStackTrace();
        }

        // Server greetings
        checkResponse(reader, 220);
        write(writer, "EHLO superserver");
        checkResponse(reader, 250);

        // For each recipient of the mail
        for (String recipient : mail.getTo()) {
            // Write commands
            write(writer, "MAIL FROM: " + mail.getFrom());
            checkResponse(reader, 250);
            write(writer, "RCPT TO: " + recipient);
            checkResponse(reader, 250);
            write(writer, "DATA");
            checkResponse(reader, 354);

            // Write headers
            write(writer, "Date: " + new Date());
            write(writer, "Subject: " + mail.getSubject());
            write(writer, "From: " + mail.getFrom());
            write(writer, "To: " + recipient);

            // Write body
            writer.write("\r\n");
            write(writer, mail.getBody());

            // End mail
            writer.write("\r\n.\r\n");
            writer.flush();

            // Workaround the limit of mails in a certain amount of time
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

        // Close streams, and then socket
        reader.close();
        writer.close();
        client.close();
    }

    /**
     * Writes the data to the given writer, ends line correctly and flushes
     * @param writer The writer stream to write to
     * @param data The data to write to the stream
     * @throws IOException
     */
    private void write(Writer writer, String data) throws IOException {
        writer.write(data);
        writer.write("\r\n");
        writer.flush();
    }

    private void checkResponse(BufferedReader reader, int responseCode) throws IOException {
        boolean ok = false;

        try {
            Thread.sleep(1);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        String line = "";
        while (reader.ready()) {
            line = reader.readLine();
            System.out.println(line); // TODO remove

            if (line.startsWith(Integer.toString(responseCode) + " ")) {
                ok = true;
            }
        }

        if (ok != true) {
            throw new RuntimeException("The server didn't respond with a success code: " + line);
        }
    }
}
