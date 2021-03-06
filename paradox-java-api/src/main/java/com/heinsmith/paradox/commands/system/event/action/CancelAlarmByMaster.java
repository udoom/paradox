package com.heinsmith.paradox.commands.system.event.action;

import com.heinsmith.paradox.commands.system.EventGroupId;
import com.heinsmith.paradox.commands.system.ParadoxEvent;
import com.heinsmith.paradox.commands.system.SystemEvent;

@ParadoxEvent(EventGroupId.CANCEL_ALARM_BY_MASTER)
public class CancelAlarmByMaster extends SystemEvent {

    public CancelAlarmByMaster(Integer number, Integer area) {
        super(number, area);
    }
}
