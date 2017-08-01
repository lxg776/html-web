package com.xwke.spider.web.controller;

import java.util.List;

import javax.annotation.Resource;
import javax.xml.soap.Node;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.github.pagehelper.util.StringUtil;
import com.xwke.spider.modle.ExecutorModle;
import com.xwke.spider.modle.NodeModle;
import com.xwke.spider.quartz.service.ScheduleJobService;
import com.xwke.spider.quartz.vo.ScheduleJobVo;
import com.xwke.spider.vo.NodeVo;
import com.xwke.spider.web.service.ExecutorService;
import com.xwke.spider.web.service.NodeService;

/**
 * author : fengjing createTime : 2016-08-04 description : 定时任务控制器 version : 1.0
 */
@Controller
public class ScheduleJobController {

	/** job service */
	@Autowired
	private ScheduleJobService scheduleJobService;
	@Resource
	private ExecutorService executorService;
	@Resource
	private NodeService nodeService;

	/**
	 * 任务页面
	 *
	 * @return
	 */
	@RequestMapping(value = "/job/input-schedule-job", method = RequestMethod.GET)
	public String inputScheduleJob(ScheduleJobVo scheduleJobVo, ModelMap modelMap) {
		List<ExecutorModle> executList = executorService.getAllList();
		modelMap.put("executList", executList);
		List<NodeVo> nodeList = nodeService.getNodeList();
		modelMap.put("nodeList", nodeList);
		String[] nodeArray = null;
		if (scheduleJobVo.getScheduleJobId() != null) {
			ScheduleJobVo scheduleJob = scheduleJobService.get(scheduleJobVo.getScheduleJobId());
			scheduleJob.setKeywords(scheduleJobVo.getKeywords());
			modelMap.put("scheduleJobVo", scheduleJob);
			String nodeIds = scheduleJob.getNodeIds();
			if (StringUtil.isNotEmpty(nodeIds)) {
				nodeArray = nodeIds.split(",");
			}
		}
		if (null != nodeArray && nodeArray.length > 0) {
			for (int i = 0; i < nodeArray.length; i++) {
				long nodeId = new Long(nodeArray[i]);
				for (NodeVo item : nodeList) {
					if (item.getId() == nodeId) {
						item.setSelectValue(1);
					}
				}
			}
		}

		return "quartz/input-schedule-job";
	}

	/**
	 * 删除任务
	 *
	 * @return
	 */
	@RequestMapping(value = "/job/delete-schedule-job", method = RequestMethod.GET)
	public String deleteScheduleJob(Long scheduleJobId) {

		scheduleJobService.delete(scheduleJobId);

		return "redirect:list-schedule-job";
	}

	/**
	 * 运行一次
	 *
	 * @return
	 */
	@RequestMapping(value = "/job/run-once-schedule-job", method = RequestMethod.GET)
	public String runOnceScheduleJob(Long scheduleJobId) {

		scheduleJobService.runOnce(scheduleJobId);

		return "redirect:list-schedule-job";
	}

	/**
	 * 暂停
	 *
	 * @return
	 */
	@RequestMapping(value = "/job/pause-schedule-job", method = RequestMethod.GET)
	public String pauseScheduleJob(Long scheduleJobId) {
		scheduleJobService.pauseJob(scheduleJobId);
		return "redirect:list-schedule-job";
	}

	/**
	 * 恢复
	 *
	 * @return
	 */
	@RequestMapping(value = "/job/resume-schedule-job", method = RequestMethod.GET)
	public String resumeScheduleJob(Long scheduleJobId) {
		scheduleJobService.resumeJob(scheduleJobId);
		return "redirect:list-schedule-job";
	}

	/**
	 * 保存任务
	 *
	 * @param scheduleJobVo
	 * @return
	 */
	@RequestMapping(value = "/job/save-schedule-job", method = RequestMethod.POST)
	public String saveScheduleJob(ScheduleJobVo scheduleJobVo,
			@RequestParam(defaultValue = "0") String[] nodeIdsArray) {

		// 测试用随便设个状态
		scheduleJobVo.setStatus("1");

		String nodeIds = "";
		if (null != nodeIdsArray && nodeIdsArray.length > 0) {
			for (int i = 0; i < nodeIdsArray.length; i++) {
				nodeIds = nodeIds + nodeIdsArray[i];
				if (i != nodeIdsArray.length - 1) {
					nodeIds = nodeIds + ",";
				}
			}
		}
		scheduleJobVo.setNodeIds(nodeIds);

		if (scheduleJobVo.getScheduleJobId() == null) {
			scheduleJobService.insert(scheduleJobVo);
		} else if (StringUtils.equalsIgnoreCase(scheduleJobVo.getKeywords(), "delUpdate")) {
			// 直接拿keywords存一下，就不另外重新弄了
			scheduleJobService.delUpdate(scheduleJobVo);
		} else {
			scheduleJobService.update(scheduleJobVo);
		}
		return "redirect:list-schedule-job";
	}

	/**
	 * 任务列表页
	 *
	 * @param modelMap
	 * @return
	 */
	@RequestMapping(value = "/job/list-schedule-job", method = RequestMethod.GET)
	public String listScheduleJob(ScheduleJobVo scheduleJobVo, ModelMap modelMap) {

		List<ScheduleJobVo> scheduleJobVoList = scheduleJobService.queryList(scheduleJobVo);
		modelMap.put("scheduleJobVoList", scheduleJobVoList);

		List<ScheduleJobVo> executingJobList = scheduleJobService.queryExecutingJobList();
		modelMap.put("executingJobList", executingJobList);

		return "quartz/list-schedule-job";
	}

}
