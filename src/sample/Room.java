package sample;

import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

public class Room {

    private StringProperty Type;
    private StringProperty Num;
    private StringProperty Price;


    public void setType(String value) {
        typeProperty().set(value);
    }

    public String getType() {
        return typeProperty().get();
    }

    public StringProperty typeProperty() {
        if (Type == null) Type = new SimpleStringProperty(this, "Type");
        return Type;
    }

    public void setNum(String value) {
        numProperty().set(value);
    }

    public String getNum() {
        return numProperty().get();
    }

    public StringProperty numProperty() {
        if (Num == null) Num = new SimpleStringProperty(this, "Num");
        return Num;
    }

    public void setPrice(String value) {
        priceProperty().set(value);
    }

    public String getPrice() {
        return priceProperty().get();
    }

    public StringProperty priceProperty() {
        if (Price == null) Price = new SimpleStringProperty(this, "Price");
        return Price;
    }
}
