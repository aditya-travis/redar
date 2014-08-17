package model;


/**
 * Created by meng on 6/3/14.
 */
public class ProcessContextHolder {

    private static ThreadLocal<ProcessContext> contextHolder = new ThreadLocal<ProcessContext>();

    public static ProcessContext getProcessContext(){
        ProcessContext processContext = contextHolder.get();

        if(processContext == null){
            processContext = createEmptyContext();
            contextHolder.set(processContext);
            System.out.println(Thread.currentThread().getName() + " get the Context: " + processContext);
            return processContext;
        }

        System.out.println(Thread.currentThread().getName() + " get the Context:" + contextHolder.get());
        return contextHolder.get();
    }

    public static void setProcessContext(ProcessContext processContext){
        System.out.println(Thread.currentThread().getName() + " set the Context:" + processContext);
        contextHolder.set(processContext);
    }

    public static void destroyProcessContext(){
        System.out.println(Thread.currentThread().getName() + " Removed the Context:" + contextHolder.get() );
        contextHolder.remove();

    }

    private static ProcessContext createEmptyContext() {

        ProcessContext processContext = new ProcessContext();
        return processContext;
    }
}
