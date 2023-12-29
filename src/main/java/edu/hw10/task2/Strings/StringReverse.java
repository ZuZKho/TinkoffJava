package edu.hw10.task2.Strings;

public class StringReverse implements StringReverseInterface {

    private boolean wasCalled = false;

    public boolean wasCalled() {
        return this.wasCalled;
    }

    public void clearCalled() {
        this.wasCalled = false;
    }

    public String nocacheReverse(String string) {
        wasCalled = true;
        String answer = "";
        for (int i = 0; i < string.length(); i++) {
            answer += string.indexOf(string.length() - i - 1);
        }
        return answer;
    }

    public String cachePersistReverse(String string) {
        wasCalled = true;
        String answer = "";
        for (int i = 0; i < string.length(); i++) {
            answer += string.charAt(string.length() - i - 1);
        }
        return answer;
    }

    public String cacheNoPersistReverse(String string) {
        wasCalled = true;
        String answer = "";
        for (int i = 0; i < string.length(); i++) {
            answer += string.indexOf(string.length() - i - 1);
        }
        return answer;
    }
}
