package ch.heigvd.res.smtplab.model.prank;

import ch.heigvd.res.smtplab.config.ConfigParser;

/**
 * Main pranker
 *
 * @author Claude-André Alves, Luc Wachter
 */
public class Pranker {
    public Pranker(ConfigParser config) {
        System.out.println(config.getVictims());
        System.out.println(config.getMails());
    }
}
