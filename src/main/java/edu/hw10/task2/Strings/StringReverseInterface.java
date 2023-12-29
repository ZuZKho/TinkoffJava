package edu.hw10.task2.Strings;

import edu.hw10.task2.Cache.Cache;

public interface StringReverseInterface {

    boolean wasCalled();

    void clearCalled();

    String nocacheReverse(String string);

    @Cache(persist = true)
    String cachePersistReverse(String string);

    @Cache
    String cacheNoPersistReverse(String string);
}
