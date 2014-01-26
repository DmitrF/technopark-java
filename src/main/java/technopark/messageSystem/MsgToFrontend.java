package technopark.messageSystem;

import technopark.base.Abonent;
import technopark.base.Address;
import technopark.base.FrontendService;
import technopark.base.Msg;

public abstract class MsgToFrontend extends Msg {

    protected MsgToFrontend(Address from, Address to) {
        super(from, to);
    }

    @Override
    public void exec(Abonent abonent) {
        if (abonent instanceof FrontendService) {
            exec((FrontendService) abonent);
        } else {
            //TODO logging error
        }
    }

    public abstract void exec(FrontendService frontend);
}
