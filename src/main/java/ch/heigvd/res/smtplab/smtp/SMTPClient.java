package ch.heigvd.res.smtplab.smtp;

import ch.heigvd.res.smtplab.model.mail.Mail;

import java.io.*;
import java.net.Socket;
import java.util.Date;

/**
 * Handles connection to SMTP server, and sends mails
 *
 * @author Claude-André Alves, Luc Wachter
 */
public class SMTPClient implements InterfaceSMTPClient{
    private String address;
    private int port;

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
    public void sendMail(Mail mail) throws IOException {
        // Open socket and streams
        Socket client = new Socket(address, port);
        BufferedReader reader = new BufferedReader(new InputStreamReader(client.getInputStream(), "UTF-8"));
        PrintWriter writer = new PrintWriter(client.getOutputStream());

        // Server greetings
//        checkResponse(reader, 220);
        write(writer, "EHLO superserver");
        checkResponse(reader, 250);

        // If we need auth
        // TODO Check for AUTH header
        if (address.equals("smtp.mailtrap.io")) {
            write(writer, "AUTH LOGIN");
            checkResponse(reader, 334);
            write(writer, "NGFkNzlkZDIyMDFmNjQ=");
            checkResponse(reader, 334);
            write(writer, "MWM0MTY0NDNmZjZlYTA=");
            checkResponse(reader, 235);
        }

        // For each recipient of the mail
        for (String recipient : mail.getTo()) {
            // Write commands
            write(writer, "MAIL FROM:<" + mail.getFrom() + ">");
            checkResponse(reader, 250);
            write(writer, "RCPT TO:<" + recipient + ">");
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
            checkResponse(reader, 250);

            // Workaround the limit of mails in a certain amount of time
            try {
                Thread.sleep(5000);
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

    /**
     * Reads the given stream and checks whether it prints the given response code
     * @param reader The stream from which to read
     * @param responseCode The code to search for
     * @throws IOException
     */
    private void checkResponse(BufferedReader reader, int responseCode) throws IOException {
        boolean ok = false;

        // Workaround the slowness of some servers
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        // Read every line printed by the server
        String line = "";
        while (reader.ready()) {
            line = reader.readLine();

            // If the line starts with the wanted response code followed by a space
            if (line.startsWith(responseCode + " ")) {
                ok = true;
            }
        }

        // If the response code wasn't printed by the server, throw
        if (ok != true) {
            throw new RuntimeException("The server didn't respond with a success code: " + line);
        }
    }
}
