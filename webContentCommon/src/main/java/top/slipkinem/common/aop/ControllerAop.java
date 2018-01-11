package top.slipkinem.common.aop;

import org.aspectj.lang.ProceedingJoinPoint;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import top.slipkinem.common.beans.ResultBean;
import top.slipkinem.common.excepitons.CheckException;
import top.slipkinem.common.excepitons.NoPermissionException;
import top.slipkinem.common.excepitons.UnloginException;

/**
 * Created by slipkinem on 1/10/2018.
 */
public class ControllerAop {
    private static final Logger logger = LoggerFactory.getLogger(ControllerAop.class);

    public Object handlerControllerMethod(ProceedingJoinPoint joinPoint) {
        long startTime = System.currentTimeMillis();
        ResultBean<?> result;

        try {
            result = (ResultBean<?>) joinPoint.proceed();
            Object[] args = joinPoint.getArgs();

            logger.info(joinPoint.getSignature() + " use time " + (System.currentTimeMillis() - startTime));
        } catch (Throwable throwable) {
            result = handlerException(joinPoint, throwable);
        }

        return result;
    }

    private ResultBean<?> handlerException(ProceedingJoinPoint joinPoint, Throwable e) {
        ResultBean<?> result = new ResultBean();

        logger.error(joinPoint.getSignature() + "error ", e);

        if (e instanceof CheckException || e instanceof IllegalArgumentException) {
            result.setErrorMessage(e.getLocalizedMessage());
            result.setErrorCode(ResultBean.CHECK_FAIL);
            return result;
        }

        if (e instanceof UnloginException) {
            result.setErrorMessage("Unlogin");
            result.setErrorCode(ResultBean.NO_LOGIN);
            return result;
        }

        if (e instanceof NoPermissionException) {
            result.setErrorMessage("NO PERMISSION");
            result.setErrorCode(ResultBean.NO_PERMISSION);
            return result;
        }

        result.setErrorMessage(e.toString());
        result.setErrorCode(ResultBean.UNKNOWN_EXCEPTION);

        return result;
    }
}
