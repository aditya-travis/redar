package service;

import model.ProcessContextHolder;

/**
 * Created by meng on 6/3/14.
 */
public class ProcessingServiceImpl implements ProcessingService {
    @Override
    public void Process() {
        System.out.println(Thread.currentThread().getName() + " Processing " + ProcessContextHolder.getProcessContext());
    }
}
