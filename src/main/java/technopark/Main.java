package technopark;

import org.eclipse.jetty.server.Handler;
import org.eclipse.jetty.server.Server;
import technopark.accountService.AccountServiceImpl;
import technopark.base.AccountService;
import technopark.base.FrontendService;
import technopark.base.MessageSystem;
import technopark.frontend.FrontendImpl;
import technopark.messageSystem.MessageSystemImpl;

public class Main {

    private static final int PORT = 8080;

    //Todo shutdown server
    public static void main(String[] args) throws Exception {
        MessageSystem ms = new MessageSystemImpl();

        FrontendService frontend = new FrontendImpl(ms);
        AccountService accountService = new AccountServiceImpl(ms);

        new Thread((Runnable) frontend).start();
        new Thread((Runnable) accountService).start();

        Server server = new Server(PORT);
        server.setHandler((Handler) frontend);

        server.start();
        server.join();
    }
}