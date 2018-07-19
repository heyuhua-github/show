package com.show.comm.utils.ext;

import java.lang.management.CompilationMXBean;
import java.lang.management.GarbageCollectorMXBean;
import java.lang.management.ManagementFactory;
import java.lang.management.MemoryMXBean;
import java.lang.management.MemoryPoolMXBean;
import java.lang.management.MemoryUsage;
import java.lang.management.OperatingSystemMXBean;
import java.lang.management.RuntimeMXBean;
import java.lang.management.ThreadMXBean;
import java.util.Arrays;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

/**
 * JVM信息
 * 
 * @author heyuhua
 * @date 2017年6月22日
 */
public class JvmInfo {

	private JvmInfo() {
	}

	private static final int MB_I = 1024 * 1024;
	private static final String MB = " MB";
	public static final char B = '_';

	public static Map<String, String> info() {
		Map<String, String> map = new LinkedHashMap<String, String>();
		map.put(B + "Memory Info", "Memory Info");
		map.put("MM_TotalMemory", Runtime.getRuntime().totalMemory() / MB_I + MB);
		map.put("MM_FreeMemory", Runtime.getRuntime().freeMemory() / MB_I + MB);
		map.put("MM_MaxMemory", Runtime.getRuntime().maxMemory() / MB_I + MB);

		// 获取操作系统相关信息
		map.put(B + "OperatingSystem Info", "OperatingSystem Info");
		OperatingSystemMXBean osm = (OperatingSystemMXBean) ManagementFactory.getOperatingSystemMXBean();
		map.put("OS_Arch", osm.getArch());// 操作系统结构
		map.put("OS_AvailableProcessors", String.valueOf(osm.getAvailableProcessors()));// 核心
		boolean isWindows = false;// 是否windows操作系统
		if (null != osm.getName()) {
			map.put("OS_Name", osm.getName());// 系统名称
			isWindows = osm.getName().toLowerCase().indexOf("windows") != -1;
		}
		map.put("OS_Version", osm.getVersion());// 系统版本

		// 获取整个虚拟机内存使用情况
		map.put(B + "MemoryHeap Info", "MemoryHeap Info");
		MemoryMXBean mm = (MemoryMXBean) ManagementFactory.getMemoryMXBean();
		// 堆
		MemoryUsage mu = mm.getHeapMemoryUsage();
		map.put("Memory_Heap_Init", mu.getInit() / MB_I + MB);
		map.put("Memory_Heap_Used", mu.getUsed() / MB_I + MB);
		map.put("Memory_Heap_Committed", mu.getCommitted() / MB_I + MB);
		map.put("Memory_Heap_Max", mu.getMax() / MB_I + MB);

		// 非堆
		map.put(B + "MemoryNonHeap Info", "MemoryNonHeap Info");
		mu = mm.getNonHeapMemoryUsage();
		map.put("Memory_NonHeap_Init", mu.getInit() / MB_I + MB);
		map.put("Memory_NonHeap_Used", mu.getUsed() / MB_I + MB);
		map.put("Memory_NonHeap_Committed", mu.getCommitted() / MB_I + MB);
		map.put("Memory_NonHeap_Max", mu.getMax() / MB_I + MB);
		mu = null;

		// 获取各个线程的各种状态，CPU 占用情况，以及整个系统中的线程状况
		map.put(B + "Threads Info", "Threads Info");
		ThreadMXBean tm = (ThreadMXBean) ManagementFactory.getThreadMXBean();
		map.put("TD_ThreadCount", String.valueOf(tm.getThreadCount()));
		map.put("TD_PeakThreadCount", String.valueOf(tm.getPeakThreadCount()));
		map.put("TD_CurrentThreadCpuTime", String.valueOf(tm.getCurrentThreadCpuTime()));
		map.put("TD_DaemonThreadCount", String.valueOf(tm.getDaemonThreadCount()));
		map.put("TD_CurrentThreadUserTime", String.valueOf(tm.getCurrentThreadUserTime()));

		// 当前编译器情况
		map.put(B + "Compilation Info", "Compilation Info");
		CompilationMXBean gm = (CompilationMXBean) ManagementFactory.getCompilationMXBean();
		map.put("Compilation_Name", gm.getName());
		map.put("Compilation_TotalTime", String.valueOf(gm.getTotalCompilationTime()));

		// 获取多个内存池的使用情况
		map.put(B + "MemoryPool Info", "MemoryPool Info");
		List<MemoryPoolMXBean> mpmList = ManagementFactory.getMemoryPoolMXBeans();
		for (MemoryPoolMXBean mpm : mpmList) {
			map.put("MemoryPool_Usage", mpm.getUsage().toString());
			map.put("MemoryPool_ManagerNames", Arrays.toString(mpm.getMemoryManagerNames()));
		}

		// 获取GC的次数以及花费时间之类的信息
		map.put(B + "GarbageCollector Info", "GarbageCollector Info");
		List<GarbageCollectorMXBean> gcmList = ManagementFactory.getGarbageCollectorMXBeans();
		for (GarbageCollectorMXBean gcm : gcmList) {
			map.put("GC_Name", gcm.getName());
			map.put("GC_MemoryPoolNames", Arrays.toString(gcm.getMemoryPoolNames()));
		}

		// 获取运行时信息
		map.put(B + "Runtime Info", "Runtime Info");
		RuntimeMXBean rmb = (RuntimeMXBean) ManagementFactory.getRuntimeMXBean();
		map.put("RunTime_ClassPath", parseLine(rmb.getClassPath(), isWindows));
		map.put("RunTime_LibraryPath", parseLine(rmb.getLibraryPath(), isWindows));
		map.put("RunTime_VmVersion", rmb.getVmVersion());
		return map;
	}

	/** 换行 */
	private static String parseLine(String str, boolean isWindows) {
		if (isWindows) {
			return str.replaceAll(";", "<br/>");
		}
		return str.replaceAll(":", "<br/>");
	}

}
