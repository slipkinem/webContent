package top.slipkinem.admin.interceptor;

import org.apache.shiro.subject.Subject;
import org.apache.shiro.web.filter.AccessControlFilter;
import org.apache.shiro.web.util.WebUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import top.slipkinem.admin.po.User;
import top.slipkinem.admin.util.SubjectUtil;

import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletResponse;

public class AuthShiroInterceptor extends AccessControlFilter {
    private static final Logger logger = LoggerFactory.getLogger(AuthShiroInterceptor.class);

    @Override
    protected boolean isAccessAllowed(ServletRequest servletRequest, ServletResponse servletResponse, Object o) throws Exception {
        HttpServletResponse response = WebUtils.toHttp(servletResponse);
        Subject subject = getSubject(servletRequest, servletResponse);

        if (subject == null) {
            response.sendError(401);
            return false;
        }

        User user = (User) SubjectUtil.getSession("user");

        if (user == null) {
            response.sendError(401);
            return false;
        }

        if (subject.isAuthenticated() || subject.isRemembered()) {
            return true;
        }

        response.sendError(401);
        return false;
    }

    @Override
    protected boolean onAccessDenied(ServletRequest servletRequest, ServletResponse servletResponse) throws Exception {
        logger.error("授权失败 === " + "access denied");
        return true;
    }
}
