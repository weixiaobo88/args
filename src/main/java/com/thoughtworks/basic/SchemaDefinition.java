package com.thoughtworks.basic;

import java.util.Objects;

public class SchemaDefinition {
    private final String flag;
    private final ValueType valueType;

    SchemaDefinition(String flag, ValueType valueType) {
        this.flag = flag;
        this.valueType = valueType;
    }

    String getFlag() {
        return flag;
    }

    String getType() {
        return valueType.getType();
    }

    Object getDefaultValue() {
        return valueType.getDefaultValue();
    }

    boolean isMatch(String flag) {
        return this.flag.equals(flag);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        SchemaDefinition that = (SchemaDefinition) o;
        return Objects.equals(flag, that.flag) &&
                valueType == that.valueType;
    }

    @Override
    public int hashCode() {
        return Objects.hash(flag, valueType);
    }
}
