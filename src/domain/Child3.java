package domain;

import utils.FieldUtils;

public class Child3 extends Child2 {

    private static final String finalField1 = "child 3 final field 1";
    private static String staticField1 = "child 3 static field 1";

    private String field1 = "child3 f 1";
    private String field2 = "child3 f 2";
    private String field3 = "child3 f 3";
    private MyAbstractUIObject field4 = new MyAbstractUIObject("child 3 name 2", "child 3 name 2");

    public Child3() {
        FieldUtils.overrideFields(this);
    }
}
