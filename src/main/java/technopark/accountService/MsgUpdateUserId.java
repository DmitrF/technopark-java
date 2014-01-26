package technopark.accountService;

import technopark.base.Address;
import technopark.base.FrontendService;
import technopark.messageSystem.MsgToFrontend;

public class MsgUpdateUserId extends MsgToFrontend {

    private final int sessionId;
    private final int userId;

    public MsgUpdateUserId(Address from, Address to, int sessionId, int userId) {
        super(from, to);
        this.sessionId = sessionId;
        this.userId = userId;
    }

    @Override
    public void exec(FrontendService frontend) {
        frontend.updateUserId(sessionId, userId);
    }
}
