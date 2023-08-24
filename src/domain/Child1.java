package domain;

import utils.FieldUtils;

public class Child1 extends Parent {

    private String field1 = "child1 f 1";
    private String field2 = "child1 f 2";
    private String field3 = "child1 f 3";

    public Child1() {
        FieldUtils.overrideFields(this);
    }
}
