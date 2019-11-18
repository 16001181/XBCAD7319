package com.example.user.troyecomputersystems;

public class Messages {
    private String Name;
    private String Date;
    private String CommunicationMessage;
    private String Time;


    public Messages(String CommunicationMessage, String Date,String Name, String Time)
    {
        this.Name = Name;
        this.Date = Date;
        this.CommunicationMessage = CommunicationMessage;
        this.Time = Time;

    }

    public String getName() {
        return Name;
    }

    public void setName(String Name) {
        this.Name = Name;
    }

    public String getDate() {
        return Date;
    }

    public void setDate(String Date) {
        this.Date = Date;
    }

    public String getCommunicationMessage() {
        return CommunicationMessage;
    }

    public void setCommunicationMessage(String CommunicationMessage) {
        this.CommunicationMessage = CommunicationMessage;
    }

    public String getTime() {
        return Time;
    }

    public void setTime(String Time) {
        this.Time = Time;
    }
}
