package org.eel.kitchen;

import com.fasterxml.jackson.databind.JsonNode;
import org.eel.kitchen.jsonschema.ValidationContext;
import org.eel.kitchen.jsonschema.schema.JsonSchema;
import org.eel.kitchen.jsonschema.schema.JsonSchemaFactory;
import org.eel.kitchen.jsonschema.util.JsonLoader;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.io.IOException;

import static org.testng.Assert.*;

public final class Issue7Test
{
    private JsonNode draftv3, schema1, schema2;

    @BeforeClass
    public void setUp()
        throws IOException
    {
        draftv3 = JsonLoader.fromResource("/schema-draftv3.json");
        schema1 = JsonLoader.fromResource("/schema1.json");
        schema2 = JsonLoader.fromResource("/schema2.json");
    }

    @Test
    public void testIssue7()
    {
        final JsonSchemaFactory factory = new JsonSchemaFactory();

        ValidationContext context;
        JsonSchema schema;

        context = factory.newContext();
        schema = factory.create(draftv3);

        schema.validate(context, schema1);
        assertTrue(context.isSuccess());

        context = factory.newContext();
        schema = factory.create(schema1);

        schema.validate(context, schema2);
        assertFalse(context.isSuccess());
    }
}
