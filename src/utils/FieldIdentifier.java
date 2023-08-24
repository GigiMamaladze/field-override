package utils;

public class FieldIdentifier {

    private String name;
    private String type;

    public FieldIdentifier(String name, String type) {
        this.name = name;
        this.type = type;
    }

    @Override
    public int hashCode() {
        return (name + type).hashCode();
    }

    @Override
    public boolean equals(Object o) {
        if (o == this) {
            return true;
        }
        if (!(o instanceof FieldIdentifier)) {
            return false;
        }
        FieldIdentifier fieldId = (FieldIdentifier) o;
        return fieldId.name.equals(this.name) && fieldId.getType().equals(this.type);
    }

    public String getName() {
        return name;
    }

    public String getType() {
        return type;
    }
}
