package technopark.base;

import java.util.concurrent.atomic.AtomicInteger;

public class Address {
    private static AtomicInteger abonentIdCreator = new AtomicInteger();
    private final int abonentId;

    public Address() {
        abonentId = abonentIdCreator.incrementAndGet();
    }

    @Override
    public int hashCode() {
        return abonentId;
    }
}
