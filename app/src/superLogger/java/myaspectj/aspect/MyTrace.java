package myaspectj.aspect;

import android.Manifest;
import android.content.Context;
import android.content.pm.PackageInfo;
import android.os.Debug;
import android.os.Environment;
import android.support.compat.BuildConfig;
import android.support.v4.os.EnvironmentCompat;
import android.util.Log;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.AfterThrowing;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;

import java.io.File;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.util.logging.FileHandler;
import java.util.logging.Formatter;
import java.util.logging.Level;
import java.util.logging.LogManager;
import java.util.logging.LogRecord;
import java.util.logging.Logger;
import java.util.logging.SimpleFormatter;

import myaspectj.annotation.Ignore;

/**
 * Created by recntrek7 on 15/02/17.
 */

@Aspect
public class MyTrace {
    static final String LOGGER_NAME = "wishTrip.superLogger";
    private final static String TAG="superLogger";
    private static final String SUFFIX_TAG_EXCEPTION = ":ex";
    private static final String SUFFIX_TAG_INIT= ":init";
    private static final String PACKAGE_NAME= "adiel.appandroidinfra";
    private static final String BUILD_TYPE_NAME= ".superLogger";
    private static final String INTERNAL_PATH= "data/data/"+PACKAGE_NAME+BUILD_TYPE_NAME+"/files/";
    private static final String DIR_NAME= "SuperLoggerDir";
    private static final String EXTERNAL_PATH= Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_DOCUMENTS)+"/"+DIR_NAME;
    private static final String FILE_NAME= "superLogger.log";
    private static final int MAX_FILE_SIZE_IN_BYTES = 10000000;
    private static final int NUM_OF_FILES = 1;
    private static Logger fileLogger =null;




    @Pointcut("execution(* myaspectj.aspect.MyTrace.*(..)) && if()")
    public static boolean conditionalPointcut() {
        return false;
    }

    @Before("@annotation(myaspectj.annotation.Init)")
    public void init(JoinPoint joinPoint) throws IOException {
        //Debug.startMethodTracing("calc");
        Log.d(TAG+SUFFIX_TAG_INIT,"init @@@"+ joinPoint.getStaticPart().getSignature().toString());
        LogManager lManager = LogManager.getLogManager();
        //createSuperLoggerDir();
        FileHandler fileHandler = new FileHandler(INTERNAL_PATH+"/"+FILE_NAME, MAX_FILE_SIZE_IN_BYTES, NUM_OF_FILES);
      //  fileHandler.setFormatter(new SuperLoggerFormatter()); // cause performance issues
        fileHandler.setFormatter(new SimpleFormatter()); // cause performance issues
        fileHandler.setLevel(Level.ALL);
        fileLogger= Logger.getLogger(LOGGER_NAME);
        fileLogger.setUseParentHandlers(false);
        fileLogger.addHandler(fileHandler);
        fileLogger.setLevel(Level.ALL);
    }
    private static SuperLoggerFormatter fileFormatter = new SuperLoggerFormatter();

    private static class SuperLoggerFormatter extends Formatter {

        @Override
        public  synchronized String format(LogRecord logRecord) {
            return logRecord.getMessage()+" %"+logRecord.getMillis();
        }
    }

    @Before("execution(* *(..))"+"&& !@annotation(myaspectj.annotation.Ignore)")
  //  @Before("within(chat.*)")
    public void entering(JoinPoint joinPoint) {
        Log.d(TAG,"entering >>> " + joinPoint.getStaticPart().getSignature().toString()+" thread="+Thread.currentThread().getName());
        fileLogger.log(Level.ALL,"entering >>> " + joinPoint.getStaticPart().getSignature().toString()+" thread="+Thread.currentThread().getName());
    }
   //@After("execution(* *(..))"+"&& !@annotation(myaspectj.annotation.Ignore)")
   @After("within(chat.*)")
    public void exit(JoinPoint joinPoint) {
        Log.d(TAG,"exit <<< " + joinPoint.getStaticPart().getSignature().toString()+" thread="+Thread.currentThread().getName());
        fileLogger.log(Level.ALL,"exit <<< " + joinPoint.getStaticPart().getSignature().toString()+" thread="+Thread.currentThread().getName());
     StringBuilder stringBuilder = new StringBuilder();
     for (Object arg : joinPoint.getArgs()) {
         stringBuilder .append("Arg : " + arg);
     }
     Log.d(TAG,stringBuilder.toString());
        fileLogger.log(Level.ALL,stringBuilder.toString()+" thread="+Thread.currentThread().getName());

    }

    @AfterReturning(pointcut = "execution(* *(..))", returning = "string"+"&& !@annotation(myaspectj.annotation.Ignore)")
    public synchronized void logResult(String string) {
        Log.d(TAG,"result :" + string);
    }

    @AfterThrowing(pointcut = "execution(* *(..))", throwing = "ex"+"&& !@annotation(myaspectj.annotation.Ignore)")
    public  void logException(RuntimeException ex) {
        StackTraceElement[] stackTrace = ex.getStackTrace();
        StringBuilder stringBuilder = new StringBuilder();
        for (int i=0 ; i<stackTrace.length ; i++){
            StackTraceElement stackTraceElement = stackTrace[i];
            stringBuilder.append(stackTraceElement.toString());
            stringBuilder.append("\n");
        }
        Log.e(TAG+SUFFIX_TAG_EXCEPTION, stringBuilder.toString());
        fileLogger.log(Level.ALL,TAG+SUFFIX_TAG_EXCEPTION, stringBuilder.toString());
    }

    @Ignore
    public static File createSuperLoggerDir() {
//         Get the directory for the user's public pictures directory.
//        File docPath = new File(Environment.getExternalStoragePublicDirectory(
//                Environment.DIRECTORY_DOCUMENTS), dirName);
        File docPath = new File(INTERNAL_PATH);


     //   File docPath = new File(Environment.getExternalStorageDirectory() + "/Documents/"+dirName);


        Log.d(TAG,"docPath:"+docPath.getAbsolutePath());
        if (!docPath.mkdirs()) {
            Log.e(TAG, "Directory not created");
        }else {
            Log.e(TAG, "Directory created");
        }
        return docPath;
    }
    @Ignore
    public static void initFile(Context context){
        createSuperLoggerDir();
        try {
            OutputStreamWriter outputStreamWriter = new OutputStreamWriter(context.openFileOutput(EXTERNAL_PATH+"/"+FILE_NAME, Context.MODE_PRIVATE));
            outputStreamWriter.write("init log");
            outputStreamWriter.close();
        }
        catch (Exception e) {
            Log.e("Exception", "File write failed: " + e.toString());
            e.printStackTrace();
        }

    }


}
