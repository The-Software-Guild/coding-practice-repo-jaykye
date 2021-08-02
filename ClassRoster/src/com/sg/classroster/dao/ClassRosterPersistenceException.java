package com.sg.classroster.dao;

public class ClassRosterPersistenceException extends Exception{
    // Two constructors.

    public ClassRosterPersistenceException(String message) {
        // 다른 exception이 아닌 내가 정한 rule에 위배될 때 내가 사용할 exception.
        super(message);
    }

    public ClassRosterPersistenceException(String message, Throwable cause) {
        // 다른 exception이 발생해서 그 이유를 알기 위해서 사용할 exception. -- reporting purpose.
        // catch 로 e를 잡고 그걸 넣겠지?
        // Exception이 Throwable을 extend하므로 Throwable을 쓰면 더 많은 에러를 catch 할 수 있다는데...
        // 음 그렇지. 모든지 parent로 만드는 것은 되지만 child로 casting하는 것은 불법.
        super(message, cause);
    }

}
