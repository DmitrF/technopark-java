package technopark.messageSystem;

import technopark.base.Abonent;
import technopark.base.AccountService;
import technopark.base.Address;
import technopark.base.Msg;

public abstract class MsgToAccountService extends Msg {
    protected MsgToAccountService(Address from, Address to) {
        super(from, to);
    }

    @Override
    public void exec(Abonent abonent) {
        if (abonent instanceof AccountService) {
            exec((AccountService) abonent);
        } else {
            //TODO logging error
        }
    }

    public abstract void exec(AccountService accountService);
}
