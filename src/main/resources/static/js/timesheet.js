function timesheet(yearMonth, state, entries){
    var timesheet = {
        yearMonth : yearMonth,
        state  : state,
        entries: entries
    };
    return timesheet;
}

function timesheetEntry(hoursSpent, dayOftheMonth, entryKind){
    var entry = {
        hoursSpent : hoursSpent,
        dayOfTheMonth  : dayOftheMonth,
        entryKind: entryKind
    };
    return entry;
}


var entries = [];
var ts1 = timesheet("2018-02", "OPEN", entries);
entries.push(timesheetEntry(8,30, "WORK"));
entries.push(timesheetEntry(8,31, "WORK"));
var jsonTimesheet = JSON.stringify(ts1);