package com.sg.classroster.dao;

public interface ClassRosterAuditDao {
    /**
     * If anything is changed on ClassRosterDao, the change is logged via this method.
     *
     * @param entry: Text I want to add to the log file.
     */
    public void writeAuditEntry(String entry) throws ClassRosterPersistenceException;

}