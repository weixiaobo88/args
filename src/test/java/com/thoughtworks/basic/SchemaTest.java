package com.thoughtworks.basic;

import com.thoughtworks.basic.exception.FlagNotDefinedException;
import org.junit.Test;

import java.util.HashSet;
import java.util.Set;

import static org.junit.Assert.assertEquals;

public class SchemaTest {
    @Test
    public void should_return_flag_type_when_get_flag_type_given_schema() {
        //given
        Set<SchemaDefinition> schemaDefinitions = new HashSet<>();
        schemaDefinitions.add(new SchemaDefinition("l", ValueType.BOOLEAN));
        schemaDefinitions.add(new SchemaDefinition("p", ValueType.INTEGER));
        schemaDefinitions.add(new SchemaDefinition("d", ValueType.STRING));
        Schema schema = new Schema(schemaDefinitions);

        //when

        //then
        assertEquals("boolean", schema.getTypeOf("l"));
        assertEquals("integer", schema.getTypeOf("p"));
        assertEquals("string", schema.getTypeOf("d"));
    }
}
