package domain;

import utils.FieldUtils;

public class Child4 extends Parent {

    private String field1 = "child4 f 1";
    private String field2 = "child4 f 2";
    private String field3 = "child4 f 3";

    public Child4() {
        FieldUtils.overrideFields(this);
    }
}
