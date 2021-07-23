package techbasics.common.aspect;

import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.annotation.Order;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

@Aspect
@Order(0)
@Slf4j
public class UncaughtRestExceptionHandlerAspect {

    @Value("${uncaught.rest.exception.ticket.generation:false}")
    private boolean generateTicketErrors;

    @Pointcut("@within(org.springframework.web.bind.annotation.RestController)")
    public void restController() {//No implementation required
    }

    @Pointcut("@annotation(org.springframework.web.bind.annotation.GetMapping)")
    public void getMapping() {//No implementation required
    }

    @Pointcut("@annotation(org.springframework.web.bind.annotation.PutMapping)")
    public void putMapping() {//No implementation required
    }

    @Pointcut("@annotation(org.springframework.web.bind.annotation.PostMapping)")
    public void postMapping() {//No implementation required
    }

    @Pointcut("@annotation(org.springframework.web.bind.annotation.DeleteMapping)")
    public void deleteMapping() {//No implementation required
    }

    @Pointcut("@annotation(org.springframework.web.bind.annotation.PatchMapping)")
    public void patchMapping() {//No implementation required
    }

    @Pointcut("@annotation(org.springframework.web.bind.annotation.RequestMapping)")
    public void requestMapping() {//No implementation required
    }

    @Around("restController() && (getMapping() || putMapping() || postMapping() || deleteMapping() || patchMapping() || requestMapping())")
    public Object processUncaughtRestException(ProceedingJoinPoint pjp) throws Throwable {
        try {
            return pjp.proceed();
        } catch (Throwable e) {
            if (generateTicketErrors) {
                String ticketError = Long.toHexString(System.currentTimeMillis());
                log.error("Uncaught internal fault. Ticket error returned to caller [ticketError={}]", ticketError, e);
                return new ResponseEntity<String>("TicketError=" + ticketError, HttpStatus.INTERNAL_SERVER_ERROR);
            }

            throw e;
        }
    }

}
