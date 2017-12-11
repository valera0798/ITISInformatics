package util;

import javax.servlet.http.HttpServletRequest;

public class ServletUtil {
    public static final String PARAMETER_ACTION_STATUS = "actionStatus";
    public static String createRequestUri(HttpServletRequest request, String location, String status) {
        return request.getScheme() + "://" +
                request.getServerName() +
                ":" +
                request.getServerPort() +
                location +
                "?" +
                PARAMETER_ACTION_STATUS + "=" + status;
    }

    public static String createRequestUri(HttpServletRequest request, String location, int userId) {
        return request.getScheme() + "://" +
                request.getServerName() +
                ":" +
                request.getServerPort() +
                location +
                "?" +
                "id=" + userId;
    }
}
