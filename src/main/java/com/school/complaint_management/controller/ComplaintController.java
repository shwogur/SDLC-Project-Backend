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

    // 전체 조회
    @GetMapping("/")
    public List<Complaint> getAll() {
        return complaintService.getAllComplaints();
    }

    // 단건 조회
    @GetMapping("/{id}")
    public Complaint getOne(@PathVariable Long id) {
        return complaintService.getComplaint(id);
    }

    // ✅ 핵심 수정: id + patch 방식
    @PutMapping("/{id}")
    public Complaint update(
            @PathVariable Long id,
            @RequestBody Complaint complaint
    ) {
        return complaintService.updateComplaint(id, complaint);
    }

    // 삭제
    @DeleteMapping("/{id}")
    public void delete(@PathVariable Long id) {
        complaintService.deleteComplaint(id);
    }
}
