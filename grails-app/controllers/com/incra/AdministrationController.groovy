package com.incra

import java.lang.management.ManagementFactory 
import java.lang.management.MemoryMXBean 
import java.lang.management.MemoryPoolMXBean 

/**
 * The <i>AdministrationController</i> class implements operations to show the
 * admin navigation screen and to show memory usage.
 *
 * @author Jeffrey Risberg
 * @since 11/09/10
 */
class AdministrationController {
	
	def index = { }
	
	/**
	 * This is based on example at 
	 * http://www.freshblurbs.com/explaining-java-lang-outofmemoryerror-permgen-space
	 */
	def memory = {
		MemoryMXBean memoryBean = ManagementFactory.getMemoryMXBean();
		List<MemoryPoolMXBean> poolBeans = ManagementFactory.getMemoryPoolMXBeans();
		
		[memoryBean: memoryBean, poolBeans: poolBeans]
	}
}
