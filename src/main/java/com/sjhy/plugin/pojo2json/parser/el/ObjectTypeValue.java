package com.sjhy.plugin.pojo2json.parser.el;

import java.util.Map;

public class ObjectTypeValue implements PresetTypeValue {
    @Override
    public Object getValue() {
        return Map.of();
    }
}
