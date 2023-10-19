package edu.hw2.task4;

final class Calling {

    private Calling() {
    }

    public static CallingInfo callingInfo() {

        try {
            throw new Exception();
        } catch (Exception e) {
            var trace = e.getStackTrace();
            String str = trace[1].toString();

            String[] arguments = str.split("\\(");
            arguments = arguments[0].split("\\.");

            int argumentsLen = arguments.length;

            return new CallingInfo(arguments[argumentsLen - 2], arguments[argumentsLen - 1]);
        }
    }

}

