package com.incra


/**
 * This class provides a lock for the various Quartz jobs so that they do not overlap 
 */
class JobLock {

  static Integer lock = new Integer(0)
}
