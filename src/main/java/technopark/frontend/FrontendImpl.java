package technopark.frontend;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.eclipse.jetty.server.Request;
import org.eclipse.jetty.server.handler.AbstractHandler;
import technopark.base.AccountService;
import technopark.base.Address;
import technopark.base.FrontendService;
import technopark.base.MessageSystem;
import technopark.util.TimeHelper;

public class FrontendImpl extends AbstractHandler implements FrontendService, Runnable {

    private Address address;
    private MessageSystem ms;
    private Map<Integer, UserSession> sessionIdToSession = new HashMap<Integer, UserSession>();

    public FrontendImpl(MessageSystem ms) {
        this.ms = ms;
        address = new Address();
        ms.addService(FrontendService.class, this);
    }

    @Override
    public void run() {
        while (true) {
            ms.execForAbonent(this);
            TimeHelper.sleep(10);
        }
    }

    @Override
    public void handle(String s, Request request, HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse) throws IOException, ServletException {
        httpServletResponse.setContentType("text/html;charset=utf-8");
        httpServletResponse.setStatus(HttpServletResponse.SC_FOUND);
        request.setHandled(true);
        StringBuilder page = new StringBuilder();

        UserSession userSession;

        String userName = request.getParameter("userName");
        String sessionId = request.getParameter("userSession");

        if (sessionId == null) {
            userSession = new UserSession();
            sessionId = String.valueOf(userSession.getSessionId());
            sessionIdToSession.put(userSession.getSessionId(), userSession);
        } else {
            userSession = sessionIdToSession.get(Integer.valueOf(sessionId));
            if (userName != null) {
                if (!userName.equals("")) {
                    //TODO Unexpected session number eg 6 on start platform
                    userSession.setUsername(userName);
                    ms.sendMessage(new MsgGetUserId(address, ms.getAddressService().getAddress(AccountService.class), userSession.getSessionId(), userName));
                }
            }

        }

        page.append("<HTML>\n <HEAD>\n <script language = 'javascript'>\n");
        page.append("document.write(\"Username: " + userSession.getUsername() + "    \");\n");
        page.append("document.write(\"UserId:   " + userSession.getUserId() + "    \");\n");
        page.append("document.write(\"Session:  " + sessionId + "    \");\n");
        page.append("</script>\n </HEAD>\n <BODY>\n");
        page.append("<form name=\"input\" method=\"post\">\n");
        page.append("Username: <input type=\"text\" name=\"userName\" value = \"" + userSession.getUsername() + "\">\n");
        page.append("<input type=\"hidden\" name=\"userSession\" value =\"" + sessionId + "\">\n");
        page.append("<input type=\"submit\" value=\"Submit\">\n");
        page.append("</form>\n </BODY>\n <HTML>\n");

        httpServletResponse.getWriter().println(page.toString());
    }

    @Override
    public Address getAddress() {
        return address;
    }

    @Override
    public void updateUserId(int sessionId, int userId) {
        UserSession userSession = sessionIdToSession.get(sessionId);
        userSession.setUserId(userId);
    }
}
