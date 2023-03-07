package tech.stl.patient.constant;

public enum ConstantEnum {
    BOOK_APPOINTMENTS("http://localhost:1001/product");

    public String value;

    ConstantEnum(String inputValue) {

        this.value = inputValue;

    }

    public String getValue() {
        return value;
    }

}
