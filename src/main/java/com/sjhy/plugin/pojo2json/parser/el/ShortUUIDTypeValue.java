package com.sjhy.plugin.pojo2json.parser.el;

public class ShortUUIDTypeValue extends UUIDTypeValue {

    @Override
    public Object getValue() {
        String uuid = (String) super.getValue();
        return uuid.substring(uuid.lastIndexOf("-") + 1);
    }
}
