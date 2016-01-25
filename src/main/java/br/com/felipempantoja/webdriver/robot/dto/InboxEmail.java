package br.com.felipempantoja.webdriver.robot.dto;

public class InboxEmail {

    private String sender;
    private String title;

    public String getSender() {
        return sender;
    }

    public void setSender(String sender) {
        this.sender = sender;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    @Override
    public String toString() {
        return "InboxEmail{" +
                "sender='" + sender + '\'' +
                ", title='" + title + '\'' +
                '}';
    }
}
