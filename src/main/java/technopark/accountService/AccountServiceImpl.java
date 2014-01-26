package technopark.accountService;

import technopark.base.AccountService;
import technopark.base.Address;
import technopark.base.MessageSystem;
import technopark.util.TimeHelper;

public class AccountServiceImpl implements AccountService, Runnable {

//    Map<String, String> usernameToUserId = new HashMap<String, String>();

    private final Address address;
    private MessageSystem ms;

    public AccountServiceImpl(MessageSystem ms) {
        this.ms = ms;
        address = new Address();
        ms.addService(AccountService.class, this);
    }

    @Override
    public Address getAddress() {
        return address;
    }

    @Override
    public void run() {
        while (true) {
            ms.execForAbonent(this);
            TimeHelper.sleep(10);
        }
    }

    public MessageSystem getMessageSystem() {
        return ms;
    }

    public Integer getUserId(String name) {
        return name.length();
    }

    @Override
    public void getUserIdByName(Address from, Address to, String name, int sessionId) {
        Integer userId = getUserId(name);
        getMessageSystem().sendMessage(new MsgUpdateUserId(to, from, sessionId, userId));

    }
}
