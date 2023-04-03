package com.Team_Project.woktlog.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.Team_Project.entity.WorkLog;
import com.Team_Project.woktlog.service.WorkLogService;

import lombok.RequiredArgsConstructor;

@Controller
@RequestMapping("/worklogs")
@RequiredArgsConstructor
public class WorkLogController {

    private final WorkLogService workLogService;

    @GetMapping
    public String getAllWorkLogs(Model model) {
        List<WorkLog> workLogs = workLogService.getAllWorkLogs();
        model.addAttribute("workLogs", workLogs);
        return "worklog/workLoglist";
    }

    @PostMapping
    @PreAuthorize("hasAuthority('EMPLOYEE') or hasAuthority('ADMIN')")
    public String createWorkLog(WorkLog workLog) {
        workLogService.createWorkLog(workLog);
        return "redirect:/worklog";
    }

    @PostMapping("/{id}/update")
    @PreAuthorize("hasAuthority('ADMIN')")
    public String updateWorkLog(@PathVariable Long id, WorkLog workLog) {
        Optional<WorkLog> workLogOpt = workLogService.getWorkLogById(id);
        if (workLogOpt.isPresent()) {
            WorkLog existingWorkLog = workLogOpt.get();
            existingWorkLog.setTitle(workLog.getTitle());
            existingWorkLog.setContent(workLog.getContent());
            workLogService.updateWorkLog(existingWorkLog);
        }
        return "redirect:/worklog";
    }

    @PostMapping("/{id}/delete")
    @PreAuthorize("hasAuthority('ADMIN')")
    public String deleteWorkLog(@PathVariable Long id) {
        workLogService.deleteWorkLog(id);
        return "redirect:/worklog";
    }
}