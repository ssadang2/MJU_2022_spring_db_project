package com.DBproject.DBproject.controller;

import com.DBproject.DBproject.domain.Employee;
import com.DBproject.DBproject.domain.Project;
import com.DBproject.DBproject.domain.Works_for;
import lombok.RequiredArgsConstructor;
import org.apache.tomcat.jni.Local;
import org.hibernate.jdbc.Work;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import com.DBproject.DBproject.Repository.EmployeeRepository;
import com.DBproject.DBproject.Repository.ProjectRepository;

import java.time.LocalDate;
import java.util.List;

@Controller
@RequiredArgsConstructor
public class CeoController {

    // 조회는 service 거치지 않고 바로 repository 로 접근해서 출력만 해주는 형식이 깔끔합니다
    private final EmployeeRepository employeeRepository;
    private final ProjectRepository projectRepository;

    // ceo 전용 마이페이지
    @GetMapping("/log/ceo")
    public String goCEOPage(Model model){
        List<Employee> employeeList=employeeRepository.findAll();
        List<Works_for> projectWorkingList=projectRepository.findDoingProjectsInfoAll();
        LocalDate currentDate= LocalDate.now();

        model.addAttribute("emlist",employeeList);
        model.addAttribute("pjworkinglist",projectWorkingList);
        model.addAttribute("currentDate",currentDate);

        return "log/ceo";
    }





    // 직원 조회
    @PostMapping("log/ceo/employeeInfo")
    public String getEmployeeInfoById(@RequestParam("employeeId") int employeeId , Model model){

        Employee findEmployee =employeeRepository.findOne(employeeId);
        model.addAttribute("emInfo",findEmployee);
        return "log/ceo";
    }

    // 프로젝트 조회
    @PostMapping("log/ceo/projectInfo")
    public String getProejctInfoById(@RequestParam("projectId") String projectId,Model model){
        List<Works_for> findProject=projectRepository.findProjectById(projectId);
        LocalDate currentDate= LocalDate.now();
        model.addAttribute("pjInfo",findProject);
        model.addAttribute("currentDate",currentDate);
        return "log/ceo";
    }

}
