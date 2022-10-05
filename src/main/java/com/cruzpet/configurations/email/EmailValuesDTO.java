package com.cruzpet.configurations.email;

public class EmailValuesDTO {

    public EmailValuesDTO() {
    }

    public EmailValuesDTO(String mailFrom, String mailTo, String subject, String userName, String passwordEncript) {
        this.mailFrom = mailFrom;
        this.mailTo = mailTo;
        this.subject = subject;
        this.userName = userName;
        this.passwordEncript = passwordEncript;
    }

    private String mailFrom;

    private String mailTo;

    private String subject;

    private String userName;

    private String passwordEncript;

    public String getMailFrom() {
        return mailFrom;
    }

    public void setMailFrom(String mailFrom) {
        this.mailFrom = mailFrom;
    }

    public String getMailTo() {
        return mailTo;
    }

    public void setMailTo(String mailTo) {
        this.mailTo = mailTo;
    }

    public String getSubject() {
        return subject;
    }

    public void setSubject(String subject) {
        this.subject = subject;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getPasswordEncript() {
        return passwordEncript;
    }

    public void setPasswordEncript(String passwordEncript) {
        this.passwordEncript = passwordEncript;
    }
}
