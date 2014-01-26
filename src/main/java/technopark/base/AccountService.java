package technopark.base;

public interface AccountService extends Abonent {
    void getUserIdByName(Address from, Address to, String name, int sessionId);
}
