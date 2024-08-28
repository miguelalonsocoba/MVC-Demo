package com.cjava.demo.aop;

import com.cjava.demo.domain.entities.Audit;
import com.cjava.demo.domain.entities.Course;
import com.cjava.demo.domain.entities.Student;
import com.cjava.demo.domain.persistence.AuditoriaDao;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import java.util.*;

@Component
@Aspect
public class Login {

    private Long tx;

    private final AuditoriaDao auditoriaDao;

    public Login(AuditoriaDao auditoriaDao) {
        this.auditoriaDao = auditoriaDao;
    }

    @Around("execution(* com.cjava.demo.services.impl.*ServiceImpl.*(..))")
    public Object logAround(ProceedingJoinPoint joinPoint) {
        Object result =  null;
        long currTime = System.currentTimeMillis();
        tx = System.currentTimeMillis();
        Logger logger = LoggerFactory.getLogger(joinPoint.getTarget().getClass());
        String method = "tx[" + tx + "] - " + joinPoint.getSignature().getName();
        //logger.info(method + "()");
        if(joinPoint.getArgs().length > 0)
            logger.info("{}() INPUT: {}", method, Arrays.toString(joinPoint.getArgs()));
        try {
            result = joinPoint.proceed();
        } catch (Throwable e) {
            logger.error(e.getMessage());
        }
        logger.info("{}(): Time elapsed {} ms.", method, System.currentTimeMillis() - currTime);
        return result;
    }

   @After("execution(* com.cjava.demo.controllers.*Controller.save*(..)) ||" +
           "execution(* com.cjava.demo.controllers.*Controller.update*(..)) ||" +
           "execution(* com.cjava.demo.controllers.*Controller.delete*(..))")
   public void audit(JoinPoint joinPoint)  {
       Logger logger = LoggerFactory.getLogger(joinPoint.getTarget().getClass());
       String method = joinPoint.getSignature().getName();
       Integer id = null;
       Object[] parameters;
       if(method.startsWith("save")){
           parameters = joinPoint.getArgs();
           id = getId(parameters);
       }
       else if(method.startsWith("update")){
           parameters = joinPoint.getArgs();
           id = (Integer)parameters[0];
       }
       else if(method.startsWith("delete")){
           parameters = joinPoint.getArgs();
           id = (Integer)parameters[0];
       }
       String trace = "tx[" + tx + "] - " + method;
       logger.info("{}(): Recording audit...", trace);
       auditoriaDao.save(new Audit(getTable(joinPoint.getTarget().getClass().getSimpleName()), id, Calendar.getInstance().getTime(), "user", method));
   }

   private String getTable(String typeClass) {
       final List<String> CLASSES = Arrays.asList("CourseController", "StudentController", "TeacherController");
       final List<String> TABLES = Arrays.asList("courses", "students");
       return TABLES.get(CLASSES.indexOf(typeClass));
   }

    private Integer getId(Object[] parameters) {
        if (parameters[0] instanceof Course course) {
            return course.getId();
        } else if (parameters[0] instanceof Student student) {
            return student.getId();
        }
        return null;
    }
}
