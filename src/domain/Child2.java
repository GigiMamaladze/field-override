package domain;

import utils.FieldUtils;

public class Child2 extends Child1 {

    private static final String finalField1 = "child 2 final field 1";
    private static String staticField1 = "child 2 static field 1";

    private String field1 = "child2 f 1";
//    private String field2 = "child2 f 2";
    private int field2 = 2;
    private String field3 = "child2 f 3";
    private MyAbstractUIObject field4 = new MyAbstractUIObject("child 2 name 2", "child 2 name 2");

    public Child2() {
        FieldUtils.overrideFields(this);
    }
}
