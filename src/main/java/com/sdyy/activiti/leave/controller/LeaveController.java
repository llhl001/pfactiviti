package com.sdyy.activiti.leave.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.sdyy.activiti.leave.service.LeaveServiceImpl;

@Controller
@RequestMapping("/leave")
public class LeaveController {
	@Autowired
	private LeaveServiceImpl leaveService;
	private Logger logger = Logger.getLogger(this.getClass().getName());


    @RequestMapping("apply")
    public String createForm() {
        return "activiti/oa/leave/leaveApply";
    }
    /**
     * 启动请假流程
     *
     * @param leave
     */
    @RequestMapping( "/start")
    public String startWorkflow(HttpServletRequest request,	HttpServletResponse response) {
    	System.out.println(1111);
      /*  try {
            User user = UserUtil.getUserFromSession(session);
            // 用户未登录不能操作，实际应用使用权限框架实现，例如Spring Security、Shiro等
            if (user == null || StringUtils.isBlank(user.getId())) {
                return "redirect:/login?timeout=true";
            }
            leave.setUserId(user.getId());
            Map<String, Object> variables = new HashMap<String, Object>();
            ProcessInstance processInstance = workflowService.startWorkflow(leave, variables);
            redirectAttributes.addFlashAttribute("message", "流程已启动，流程ID：" + processInstance.getId());
        } catch (ActivitiException e) {
            if (e.getMessage().indexOf("no processes deployed with key") != -1) {
                logger.warn("没有部署流程!", e);
                redirectAttributes.addFlashAttribute("error", "没有部署流程，请在[工作流]->[流程管理]页面点击<重新部署流程>");
            } else {
                logger.error("启动请假流程失败：", e);
                redirectAttributes.addFlashAttribute("error", "系统内部错误！");
            }
        } catch (Exception e) {
            logger.error("启动请假流程失败：", e);
            redirectAttributes.addFlashAttribute("error", "系统内部错误！");
        }*/
        return "redirect:/leave/apply";
    }

}
