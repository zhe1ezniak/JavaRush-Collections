package com.javarush.task.task38.task3804;

public class ExceptionFactory {
    public static Throwable getException(Enum someEnum){
        if (someEnum == null) return new IllegalArgumentException();
        String message = someEnum.name().charAt(0) + someEnum.name().substring(1).toLowerCase().replace("_", " ");
        if (ApplicationExceptionMessage.SOCKET_IS_CLOSED.equals(someEnum)
                || ApplicationExceptionMessage.UNHANDLED_EXCEPTION.equals(someEnum)) {
            return new Exception(message);
        } else if (DatabaseExceptionMessage.NO_RESULT_DUE_TO_TIMEOUT.equals(someEnum)
                || DatabaseExceptionMessage.NOT_ENOUGH_CONNECTIONS.equals(someEnum)) {
            return new RuntimeException(message);
        } else if (UserExceptionMessage.USER_DOES_NOT_EXIST.equals(someEnum)
                || UserExceptionMessage.USER_DOES_NOT_HAVE_PERMISSIONS.equals(someEnum)) {
            return new Error(message);
        }
        return new IllegalArgumentException();
    }
}
