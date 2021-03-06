/*
 *     Copyright (C) 2017 Hein Smith
 *
 *     This program is free software: you can redistribute it and/or modify
 *     it under the terms of the GNU General Public License as published by
 *     the Free Software Foundation, either version 3 of the License, or
 *     (at your option) any later version.
 *
 *     This program is distributed in the hope that it will be useful,
 *     but WITHOUT ANY WARRANTY; without even the implied warranty of
 *     MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 *     GNU General Public License for more details.
 *
 *     You should have received a copy of the GNU General Public License
 *     along with this program.  If not, see <https://www.gnu.org/licenses/>.
 */

package com.heinsmith.paradox.commands;

import com.heinsmith.paradox.commands.area.disarm.AreaDisarm;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertSame;
import static org.junit.jupiter.api.Assertions.assertThrows;


/**
 * Created by Hein Smith on 2017/10/12.
 */
class AreaDisarmTest implements TxCommandTest {

    private static final char[] testCode = new char[]{'4', '2', '3', '4'};

    @Override
    public void positiveConstructionTest() throws CommandValidationException {
        AreaDisarm areaDisarm = new AreaDisarm(1, testCode);
        assertEquals(1, areaDisarm.getArea());
        assertSame(testCode, areaDisarm.getPassword(false));
    }

    @Override
    public void responseCodeTest() throws CommandValidationException {
        AreaDisarm areaDisarm = new AreaDisarm(8, testCode);
        assertEquals("AD008", areaDisarm.getResponseCode());
    }

    @Test
    void areasTest() throws CommandValidationException {
        AreaDisarm areaDisarm = new AreaDisarm(1, testCode);
        assertEquals("AD0014234\r", areaDisarm.getAscii());

        areaDisarm = new AreaDisarm(4, testCode);
        assertEquals("AD0044234\r", areaDisarm.getAscii());

        assertThrows(CommandValidationException.class, () -> new AreaDisarm(9, testCode));
    }

    @Test
    void codeTest() throws CommandValidationException {
        assertThrows(CommandValidationException.class, () -> {
            char[] longCode = new char[]{'1', '2', '3', '4', '5', '6', '7'};
            new AreaDisarm(1, longCode);
        });

        assertThrows(CommandValidationException.class, () -> {
            char[] shortCode = new char[]{'1', '2'};
            new AreaDisarm(1, shortCode);
        });
    }
}
