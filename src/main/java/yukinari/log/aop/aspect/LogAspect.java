package yukinari.log.aop.aspect;

import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;

@Component
@Aspect
@Slf4j
public class LogAspect {

    @Pointcut("target(yukinari.log.aop.service.AppMessages)")
    public void appMessagesMethod() {
    }

    @Before("appMessagesMethod()")
    public void beforeAppMessagess() {
        log.info("before app messages method");
    }

    /*
     * Advice with parameter
     * for get class name and get method name
     * */

    @Before("appMessagesMethod()")
    public void beforeAppMessagesParam(JoinPoint joinPoint) {
        String className = joinPoint.getTarget().getClass().getName();
        String methodName = joinPoint.getSignature().getName();
        log.info("Before " + className + "." + methodName + "()");
    }

    /*
     * Advice Around
     * for Proceeding JointPoint before and after
     * */

    @Around("appMessagesMethod()")
    public Object aroundAppMessagesMethod(ProceedingJoinPoint joinPoint) throws Throwable {

        String className = joinPoint.getTarget().getClass().getName();
        String methodName = joinPoint.getSignature().getName();

        try {
            log.info("Around Before " + className + "." + methodName + "()");
            return joinPoint.proceed(joinPoint.getArgs());
        } catch (Throwable throwable) {
            log.info("Around error " + className + "." + methodName + "()");
            throw throwable;
        } finally {
            log.info("Around Finally " + className + "." + methodName + "()");
        }

    }

    /*
     * MULTIPLE POINTCUT
     * */

    @Pointcut("execution(* yukinari.log.aop.service.*.*(..))")
    public void pointcutServicePackage() {
    }

    @Pointcut("bean(*Messages)")
    public void pointcutServiceBean() {
    }

    @Pointcut("execution(public * *(..))")
    public void pointcutPublicMethod() {
    }

    @Pointcut("pointcutServicePackage() && pointcutServiceBean() && pointcutPublicMethod()")
    public void publicMethodForService() {
    }


    @Before("publicMethodForService()")
    public void logForAllServiceMethod() {
        log.info("log for all service methods");
    }
}
