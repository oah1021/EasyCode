package com.sjhy.plugin.pojo2json.parser.el;


public class IntegerTypeValue implements RandomTypeValue {
    @Override
    public Object getRandomValue() {
        return random.nextInt(0, 100);
    }

    @Override
    public Object getValue() {
        return 0;
    }
}
