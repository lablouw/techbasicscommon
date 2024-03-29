package techbasics.common.aspect;

import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import techbasics.common.Exception.InternalServerTicketError;

@Aspect
@Slf4j
public class UncaughtSoapExceptionHandlerAspect {

    @Pointcut("@within(org.springframework.ws.server.endpoint.annotation.Endpoint)")
    private void endpoint() {
    }

    @Pointcut("@within(techbasics.common.annotaiton.UncaughtExceptionHandler)")
    private void uncaughtExceptionHandler() {
    }

    @Pointcut("execution(public * *(..))")
    private void allPublicMethods() {
    }

    @Around("uncaughtExceptionHandler() && allPublicMethods()")
    public Object processUncaughtSoapException(ProceedingJoinPoint pjp) throws Throwable {
        try {
            return pjp.proceed();
        } catch (Throwable e) {
            String ticketError = Long.toHexString(System.currentTimeMillis());
            log.error("Uncaught internal fault. Ticket error returned to caller [ticketError={}]", ticketError, e);
            throw new InternalServerTicketError(ticketError);
        }
    }

}
