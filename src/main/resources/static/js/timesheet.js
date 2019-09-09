function timesheet(yearMonth, state, entries, id){
    var timesheet = {
        id: id,
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