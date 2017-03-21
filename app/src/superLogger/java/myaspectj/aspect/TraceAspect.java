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
public class TraceAspect {
  private final static String TAG="superLogger";
  private static final String SUFFIX_TAG_MEASURE_TIME = ":measureTime";


  private static final String POINTCUT_METHOD =
      "execution(@myaspectj.annotation.DebugTrace * *(..))";

  private static final String POINTCUT_CONSTRUCTOR =
      "execution(@myaspectj.annotation.DebugTrace *.new(..))";

  @Pointcut(POINTCUT_METHOD)
  public void methodAnnotatedWithDebugTrace() {}

  @Pointcut(POINTCUT_CONSTRUCTOR)
  public void constructorAnnotatedDebugTrace() {}

  @Around("methodAnnotatedWithDebugTrace() || constructorAnnotatedDebugTrace()")
  public Object weaveJoinPoint(ProceedingJoinPoint joinPoint) throws Throwable {
    MethodSignature methodSignature = (MethodSignature) joinPoint.getSignature();
    String className = methodSignature.getDeclaringType().getSimpleName();
    String methodName = methodSignature.getName();
    final StopWatch stopWatch = new StopWatch();
    stopWatch.start();
    Object result = joinPoint.proceed();
    stopWatch.stop();

    Log.d(TAG+SUFFIX_TAG_MEASURE_TIME, buildLogMessage(className+": "+methodName, stopWatch.getTotalTimeMillis()));

    return result;
  }

  /**
   * Create a log message.
   *
   * @param methodName A string with the method name.
   * @param methodDuration Duration of the method in milliseconds.
   * @return A string representing message.
   */
  private static String buildLogMessage(String methodName, long methodDuration) {
    StringBuilder message = new StringBuilder();
    message.append("DebugTrace --> ");
    message.append(methodName);
    message.append(" --> ");
    message.append("[");
    message.append(methodDuration);
    message.append("ms");
    message.append("]");

    return message.toString();
  }
}
