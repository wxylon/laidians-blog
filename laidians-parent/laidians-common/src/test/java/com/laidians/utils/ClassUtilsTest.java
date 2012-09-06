package com.laidians.utils;

import com.laidians.DisallowConcurrentExecution;
import com.laidians.PersistJobDataAfterExecution;

import junit.framework.TestCase;

/**
 * @author Alex Snaps
 */
public class ClassUtilsTest extends TestCase{

    public void testIsAnnotationPresentOnSuperClass() throws Exception {
        assertTrue(ClassUtils.isAnnotationPresent(BaseJob.class, DisallowConcurrentExecution.class));
        assertFalse(ClassUtils.isAnnotationPresent(BaseJob.class, PersistJobDataAfterExecution.class));
        assertTrue(ClassUtils.isAnnotationPresent(ExtendedJob.class, DisallowConcurrentExecution.class));
        assertFalse(ClassUtils.isAnnotationPresent(ExtendedJob.class, PersistJobDataAfterExecution.class));
        assertTrue(ClassUtils.isAnnotationPresent(ReallyExtendedJob.class, DisallowConcurrentExecution.class));
        assertTrue(ClassUtils.isAnnotationPresent(ReallyExtendedJob.class, PersistJobDataAfterExecution.class));
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
