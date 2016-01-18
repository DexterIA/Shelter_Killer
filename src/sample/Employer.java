package sample;

import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

public class Employer {
    private StringProperty tabNo;
    private StringProperty login;
    private StringProperty pass;


    public void setTabNo(String value) {
        tabNoProperty().set(value);
    }

    public String getTabNo() {
        return tabNoProperty().get();
    }

    public StringProperty tabNoProperty() {
        if (tabNo == null) tabNo = new SimpleStringProperty(this, "tabNo");
        return tabNo;
    }

    public void setLogin(String value) {
        loginProperty().set(value);
    }

    public String getLogin() {
        return loginProperty().get();
    }

    public StringProperty loginProperty() {
        if (login == null) login = new SimpleStringProperty(this, "login");
        return login;
    }

    public void setPass(String value) {
        passProperty().set(value);
    }

    public String getPass() {
        return passProperty().get();
    }

    public StringProperty passProperty() {
        if (pass == null) pass = new SimpleStringProperty(this, "pass");
        return pass;
    }
}
