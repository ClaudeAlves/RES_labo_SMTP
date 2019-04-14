# RES - Labo SMTP

_Wachter Luc, Alves Claude-André_

## Description
This application was developed as an assignment for the RES (network programming) course at HEIG-VD.
It consists in a TCP client application in java, with the ability to send mails through TCP with the
SMTP protocol (we implemented only a small portion of the protocol). The application uses the Socket
API to communicate with a given SMTP server.

The functionality is as follows: the user fills in a list of victim email addresses and a list of
prank email messages. He can then launch the application to send these pranks to the list of victims.
Groups of victims are formed automatically, with one being the sender and the rest being recipients.

## Setting up a Mock server
Dolor sit amet. euuuuuuu

## Quick start
1. Oui. vide rapide le commencement
2. \\\_(°-°)\_/

## Implementation choices
Our design is heavily inspired by the one shown by our professor in [this video](https://www.youtube.com/watch?v=OrSdRCt_6YQ). We have the same class hierarchy for the most part, as you can see in the following diagram.

[ClassDiagram](figures/ClassDiagram.png)
