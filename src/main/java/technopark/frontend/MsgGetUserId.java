package technopark.frontend;

import technopark.base.AccountService;
import technopark.base.Address;
import technopark.messageSystem.MsgToAccountService;

public class MsgGetUserId extends MsgToAccountService {
    private String name;
    private int sessionId;

    public MsgGetUserId(Address from, Address to, int sessionId, String name) {
        super(from, to);
        this.name = name;
        this.sessionId = sessionId;
    }

    @Override
    public void exec(AccountService accountService) {
        accountService.getUserIdByName(getFrom(), getTo(), name, sessionId);
    }
}
