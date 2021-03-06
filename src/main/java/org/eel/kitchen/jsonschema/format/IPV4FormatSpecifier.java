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

package org.eel.kitchen.jsonschema.format;

import com.fasterxml.jackson.databind.JsonNode;
import com.google.common.net.InetAddresses;
import org.eel.kitchen.jsonschema.util.NodeType;

import java.util.List;

/**
 * Validator for the {@code ip-address} format specification, ie an IPv4 address
 *
 * <p>This uses Guava's {@link InetAddresses} to do the job.</p>
 */
public final class IPV4FormatSpecifier
    extends FormatSpecifier
{
    private static final FormatSpecifier instance = new IPV4FormatSpecifier();

    private static final int IPV4_LENGTH = 4;

    private IPV4FormatSpecifier()
    {
        super(NodeType.STRING);
    }

    public static FormatSpecifier getInstance()
    {
        return instance;
    }

    @Override
    void checkValue(final List<String> messages, final JsonNode value)
    {
        final String ipaddr = value.textValue();

        if (!InetAddresses.isInetAddress(ipaddr)) {
            messages.add("string is not a valid IPv4 address");
            return;
        }

        if (InetAddresses.forString(ipaddr).getAddress().length != IPV4_LENGTH)
            messages.add("string is not a valid IPv4 address");
    }
}
