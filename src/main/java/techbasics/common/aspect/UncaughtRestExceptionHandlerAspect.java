package techbasics.common.aspect;

import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

@Aspect
@Slf4j
public class UncaughtRestExceptionHandlerAspect {

    @Pointcut("@within(org.springframework.web.bind.annotation.RestController)")
    private void restController() {
    }

    @Pointcut("@within( techbasics.common.annotaiton.UncaughtExceptionHandler)")
    private void uncaughtExceptionHandler() {
    }

    @Pointcut("execution(public * *(..))")
    private void allPublicMethods() {
    }

    @Around("uncaughtExceptionHandler() && restController() && allPublicMethods()")
    public Object processUncaughtRestException(ProceedingJoinPoint pjp) throws Throwable {
        try {
            return pjp.proceed();
        } catch (Throwable e) {
            String ticketError = Long.toHexString(System.currentTimeMillis());
            log.error("Internal Server Error. Ticket error code returned to caller [ticketError={}]", ticketError, e);
            return new ResponseEntity<String>("TicketError=" + ticketError, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

}
