package com.sg.jdbctcomplexexample.dao;

import com.sg.jdbctcomplexexample.entity.Employee;
import com.sg.jdbctcomplexexample.entity.Meeting;
import com.sg.jdbctcomplexexample.entity.Room;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;


import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.List;

@Repository
public class MeetingDaoDB implements MeetingDao {

    @Autowired
    JdbcTemplate jdbc;

    public static final class MeetingMapper implements RowMapper<Meeting> {

        @Override
        public Meeting mapRow(ResultSet rs, int index) throws SQLException {
            Meeting meet = new Meeting();
            meet.setId(rs.getInt("id"));
            meet.setName(rs.getString("name"));
            meet.setTime(rs.getTimestamp("time").toLocalDateTime());
            return meet;
        }
    }

    @Override
    public List<Meeting> getAllMeetings() {
        /*
        // 여기서 alternatively,
        String QUERY = "SELECT *" +
                "FROM meeting m " +
                "join room r " +
                "on m.roomId = r.id " +
                "join meeting_employee me" +
                "on m.id = me.meetingId" +
                "join employee e" +
                "on me.employeeId = e.id";
        이 쿼리를 사용해서 한번에 필요한 정보를 모두 끌어 낸 다음에 각 row를 iterate하면서 object를 만드는 방법이 더 효율적이다.
        대신에 이렇게 하면 첫째로, employee obj의 경우 리스트로 간편하게 aggregate하기가 힘들고 (groupby concat + parse data to make obj),
        둘째로, 한번에 column이 많이 return되는 결과로 이에 해당하는 rowMapper를 따로 만들어 줘야 한다.
        그래서 차선책으로 비효율 적이지만 각 meeting row를 읽으면서 계속해서 query하는 방법을 선택한 듯 하다.
        */
        final String SELECT_ALL_MEETINGS = "SELECT * FROM meeting";
        List<Meeting> meetings = jdbc.query(SELECT_ALL_MEETINGS, new MeetingMapper());

        addRoomAndEmployeesToMeetings(meetings);

        return meetings;
    }

    private void addRoomAndEmployeesToMeetings(List<Meeting> meetings) {
        for(Meeting meeting : meetings) {
            meeting.setRoom(getRoomForMeeting(meeting));
            meeting.setAttendees(getEmployeesForMeeting(meeting));
        }
    }

    @Override
    public Meeting getMeetingByid(int id) {
        try {
            final String SELECT_MEETING_BY_ID = "SELECT * FROM meeting WHERE id = ?";
            Meeting meeting = jdbc.queryForObject(SELECT_MEETING_BY_ID,
                    new MeetingMapper(), id);
            meeting.setRoom(getRoomForMeeting(meeting));
            meeting.setAttendees(getEmployeesForMeeting(meeting));
            return meeting;
        } catch(DataAccessException ex) {
            return null;
        }
    }

    private Room getRoomForMeeting(Meeting meeting) {
        final String SELECT_ROOM_FOR_MEETING = "SELECT r.* FROM room r "
                + "JOIN meeting m ON r.id = m.roomId WHERE m.id = ?";
        return jdbc.queryForObject(SELECT_ROOM_FOR_MEETING, new RoomMapper(),
                meeting.getId());
    }

    private List<Employee> getEmployeesForMeeting(Meeting meeting) {
        final String SELECT_EMPLOYEES_FOR_MEETING = "SELECT e.* FROM employee e "
                + "JOIN meeting_employee me ON e.id = me.employeeId WHERE me.meetingId = ?";
        return jdbc.query(SELECT_EMPLOYEES_FOR_MEETING, new EmployeeMapper(),
                meeting.getId());
    }

    @Override
    @Transactional
    public Meeting addMeeting(Meeting meeting) {
        final String INSERT_MEETING = "INSERT INTO meeting(name, time, roomId) VALUES(?,?,?)";
        jdbc.update(INSERT_MEETING,
                meeting.getName(),
                Timestamp.valueOf(meeting.getTime()),
                meeting.getRoom().getId());
        int newId = jdbc.queryForObject("SELECT LAST_INSERT_ID()", Integer.class);
        meeting.setId(newId);

        insertMeetingEmployee(meeting);

        return meeting;
    }

    private void insertMeetingEmployee(Meeting meeting) {
        final String INSERT_MEETING_EMPLOYEE = "INSERT INTO meeting_employee"
                + "(meetingId, employeeId) VALUES(?,?)";
        for(Employee employee : meeting.getAttendees()) {
            jdbc.update(INSERT_MEETING_EMPLOYEE, meeting.getId(), employee.getId());
        }
    }

    @Override
    @Transactional
    public void updateMeeting(Meeting meeting) {
        final String UPDATE_MEETING = "UPDATE meeting "
                + "SET name = ?, time = ?, roomId = ? WHERE id = ?";
        jdbc.update(UPDATE_MEETING,
                meeting.getName(),
                Timestamp.valueOf(meeting.getTime()),
                meeting.getRoom().getId(),
                meeting.getId());

        final String DELETE_MEETING_EMPLOYEE = "DELETE FROM meeting_employee "
                + "WHERE meetingId = ?";
        jdbc.update(DELETE_MEETING_EMPLOYEE, meeting.getId());
        insertMeetingEmployee(meeting);
    }

    @Override
    public void deleteMeetingById(int id) {
        final String DELETE_MEETING_EMPLOYEE = "DELETE FROM meeting_employee "
                + "WHERE meetingId = ?";
        jdbc.update(DELETE_MEETING_EMPLOYEE, id);

        final String DELETE_MEETING = "DELETE FROM meeting WHERE id = ?";
        jdbc.update(DELETE_MEETING, id);
    }

    @Override
    public List<Meeting> getMeetingsForRoom(Room room) {
        final String SELECT_MEETINGS_FOR_ROOM = "SELECT * FROM meeting WHERE roomId = ?";
        List<Meeting> meetings = jdbc.query(SELECT_MEETINGS_FOR_ROOM,
                new MeetingMapper(), room.getId());

        addRoomAndEmployeesToMeetings(meetings);

        return meetings;
    }

    @Override
    public List<Meeting> getMeetingsForEmployee(Employee employee) {
        final String SELECT_MEETINGS_FOR_EMPLOYEE = "SELECT * FROM meeting m "
                + "JOIN meeting_employee me ON m.id = me.meetingId WHERE me.employeeId = ?";
        List<Meeting> meetings = jdbc.query(SELECT_MEETINGS_FOR_EMPLOYEE,
                new MeetingMapper(), employee.getId());

        addRoomAndEmployeesToMeetings(meetings);

        return meetings;
    }

}
