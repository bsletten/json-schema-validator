/*
 * Copyright (c) 2012, Francis Galiegue <fgaliegue@gmail.com>
 *
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the Lesser GNU General Public License as
 * published by the Free Software Foundation, either version 3 of the
 * License, or (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * Lesser GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with this program.  If not, see <http://www.gnu.org/licenses/>.
 */

package org.eel.kitchen.jsonschema.keyword;

import com.fasterxml.jackson.databind.JsonNode;
import org.eel.kitchen.jsonschema.ValidationContext;
import org.eel.kitchen.jsonschema.util.NodeType;

/**
 * Validator for the {@code maxLength} keyword
 */
public final class MaxLengthKeywordValidator
    extends PositiveIntegerKeywordValidator
{
    public MaxLengthKeywordValidator(final JsonNode schema)
    {
        super("maxLength", schema, NodeType.STRING);
    }

    @Override
    public void validate(final ValidationContext context,
        final JsonNode instance)
    {
        if (instance.textValue().length() > intValue)
            context.addMessage("string is longer than the required maximum");
    }
}
