package edu.hw3;

import java.util.HashMap;
import java.util.List;

public class Task3 {

    private Task3() {
    }

    public static HashMap<?, Integer> freqDict(List<?> list) {
        HashMap<Object, Integer> hashMap = new HashMap<>();
        for (int i = 0; i < list.size(); i++) {
            if (hashMap.containsKey(list.get(i))) {
                Integer cnt = hashMap.get(list.get(i)) + 1;
                hashMap.put(list.get(i), cnt);
            } else {
                hashMap.put(list.get(i), 1);
            }
        }
        return hashMap;
    }
}
