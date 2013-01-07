package com.laidians.utils;

import com.laidians.DisallowConcurrentExecution;
import com.laidians.PersistJobDataAfterExecution;

import junit.framework.TestCase;

/**
 * @author Alex Snaps
 */
public class XylonClassUtilsTests extends TestCase{

    public void testIsAnnotationPresentOnSuperClass() throws Exception {
        assertTrue(XylonClassUtils.isAnnotationPresent(BaseJob.class, DisallowConcurrentExecution.class));
        assertFalse(XylonClassUtils.isAnnotationPresent(BaseJob.class, PersistJobDataAfterExecution.class));
        assertTrue(XylonClassUtils.isAnnotationPresent(ExtendedJob.class, DisallowConcurrentExecution.class));
        assertFalse(XylonClassUtils.isAnnotationPresent(ExtendedJob.class, PersistJobDataAfterExecution.class));
        assertTrue(XylonClassUtils.isAnnotationPresent(ReallyExtendedJob.class, DisallowConcurrentExecution.class));
        assertTrue(XylonClassUtils.isAnnotationPresent(ReallyExtendedJob.class, PersistJobDataAfterExecution.class));
    }
    
    @PersistJobDataAfterExecution
    public interface Job{
    	public void execute();
    }

    @DisallowConcurrentExecution
    private static class BaseJob implements Job {
        public void execute(){
            System.out.println(this.getClass().getSimpleName());
        }
    }

    private static class ExtendedJob extends BaseJob {
    }

    @PersistJobDataAfterExecution
    private static class ReallyExtendedJob extends ExtendedJob {

    }
}
