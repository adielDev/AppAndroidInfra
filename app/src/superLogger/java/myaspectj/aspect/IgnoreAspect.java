package myaspectj.aspect; /**
 * Copyright (C) 2014 android10.org. All rights reserved.
 * @author Fernando Cejas (the android10 coder)
 */

import android.util.Log;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;

import myaspectj.internal.StopWatch;

/**
 * Aspect representing the cross cutting-concern: Method and Constructor Tracing.
 */
@Aspect
public class IgnoreAspect {
  private final static String TAG="superLogger";
  private static final String SUFFIX_TAG_MEASURE_TIME = ":ignore";


  private static final String POINTCUT_METHOD =
      "execution(@myaspectj.annotation.Ignore * *(..))";

  private static final String POINTCUT_CONSTRUCTOR =
      "execution(@myaspectj.annotation.Ignore *.new(..))";

  @Pointcut(POINTCUT_METHOD)
  public void methodAnnotatedWithIgnore() {}

  @Pointcut(POINTCUT_CONSTRUCTOR)
  public void constructorAnnotatedIgnore() {}

  @Around("methodAnnotatedWithIgnore() || constructorAnnotatedIgnore()")
  public Object weaveJoinPoint(ProceedingJoinPoint joinPoint) throws Throwable {
    MethodSignature methodSignature = (MethodSignature) joinPoint.getSignature();
    String className = methodSignature.getDeclaringType().getSimpleName();
    String methodName = methodSignature.getName();
    Object result = joinPoint.proceed();

    Log.d(TAG+SUFFIX_TAG_MEASURE_TIME, className+": "+methodName+ ",ignoring!!!");

    return result;
  }


}
