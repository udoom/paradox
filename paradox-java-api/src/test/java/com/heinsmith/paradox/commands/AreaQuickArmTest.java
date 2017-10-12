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

import com.heinsmith.paradox.commands.area.arm.AreaQuickArm;
import com.heinsmith.paradox.commands.area.arm.AreaQuickArm;
import com.heinsmith.paradox.commands.area.arm.ArmType;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

/**
 * Created by Hein Smith on 2017/10/12.
 */
public class AreaQuickArmTest {

    private static char[] testCode = new char[]{'1', '2', '3', '4'};

    @Test
    public void testResponseCode() throws CommandValidationException {
        AreaQuickArm AreaQuickArm = new AreaQuickArm(1, ArmType.REGULAR_ARM, testCode);
        assertEquals("AQ001", AreaQuickArm.getResponseCode());
    }

    @Test
    public void testArmTypes() throws CommandValidationException {

        AreaQuickArm AreaQuickArm = new AreaQuickArm(1, ArmType.REGULAR_ARM, testCode);
        assertEquals("AQ001A1234\r", AreaQuickArm.getAscii());

        AreaQuickArm = new AreaQuickArm(2, ArmType.FORCE_ARM, testCode);
        assertEquals("AQ002F1234\r", AreaQuickArm.getAscii());

        AreaQuickArm = new AreaQuickArm(3, ArmType.INSTANT_ARM, testCode);
        assertEquals("AQ003I1234\r", AreaQuickArm.getAscii());

        AreaQuickArm = new AreaQuickArm(4, ArmType.STAY_ARM, testCode);
        assertEquals("AQ004S1234\r", AreaQuickArm.getAscii());
    }

    @Test(expected = CommandValidationException.class)
    public void testAreaLow() throws CommandValidationException {
        new AreaQuickArm(0, ArmType.REGULAR_ARM, testCode);
    }

    @Test(expected = CommandValidationException.class)
    public void testAreaHigh() throws CommandValidationException {
        new AreaQuickArm(9, ArmType.REGULAR_ARM, testCode);
    }

    @Test(expected = CommandValidationException.class)
    public void testLongCode() throws CommandValidationException {
        char[] longCode = new char[]{'1', '2', '3', '4', '5', '6', '7', '8'};
        new AreaQuickArm(1, ArmType.REGULAR_ARM, longCode);
    }

    @Test(expected = CommandValidationException.class)
    public void testNoCode() throws CommandValidationException {
        char[] shortCode = new char[]{};
        new AreaQuickArm(4, ArmType.REGULAR_ARM, shortCode);
    }
}