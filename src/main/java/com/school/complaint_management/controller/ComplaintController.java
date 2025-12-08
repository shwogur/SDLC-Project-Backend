package com.school.complaint_management.controller;

import com.school.complaint_management.entity.Complaint;
import com.school.complaint_management.service.ComplaintService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/complaints")
public class ComplaintController {

    private final ComplaintService complaintService;

    // 민원 등록
    @PostMapping("/")
    public Complaint create(@RequestBody Complaint complaint) {
        return complaintService.createComplaint(complaint);
    }

    // 모든 민원 조회
    @GetMapping("/")
    public List<Complaint> getAll() {
        return complaintService.getAllComplaints();
    }

    // 특정 민원 조회
    @GetMapping("/{id}")
    public Complaint getOne(@PathVariable String id) {
        Long complaintId = Long.parseLong(id);
        return complaintService.getComplaint(complaintId);
    }

    // 민원 업데이트
    @PutMapping("/{id}")
    public Complaint update(@PathVariable String id, @RequestBody Complaint complaint) {
        Long complaintId = Long.parseLong(id);
        complaint.setId(complaintId);
        return complaintService.updateComplaint(complaint);
    }

    // 민원 삭제
    @DeleteMapping("/{id}")
    public void delete(@PathVariable String id) {
        Long complaintId = Long.parseLong(id);
        complaintService.deleteComplaint(complaintId);
    }
}
