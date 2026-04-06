package com.school.complaint_management.service;

import com.school.complaint_management.entity.Complaint;
import com.school.complaint_management.repository.ComplaintRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ComplaintService {

    private final ComplaintRepository complaintRepository;

    // 민원 등록
    public Complaint createComplaint(Complaint complaint) {
        if (complaint.getStatus() == null) {
            complaint.setStatus("pending");
        }
        return complaintRepository.save(complaint);
    }

    // 전체 조회
    public List<Complaint> getAllComplaints() {
        return complaintRepository.findAll();
    }

    // 단건 조회
    public Complaint getComplaint(Long id) {
        return complaintRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Complaint not found"));
    }

    // ✅ 핵심 수정: 부분 업데이트
    @Transactional
    public Complaint updateComplaint(Long id, Complaint patch) {
        Complaint origin = complaintRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Complaint not found"));

        // 필요한 값만 수정
        if (patch.getStatus() != null) {
            origin.setStatus(patch.getStatus());
        }
        if (patch.getRejectionReason() != null) {
            origin.setRejectionReason(patch.getRejectionReason());
        }
        if (patch.getCompletionMessage() != null) {
            origin.setCompletionMessage(patch.getCompletionMessage());
        }
        if (patch.getAssignedTo() != null) {
            origin.setAssignedTo(patch.getAssignedTo());
        }

        // ⚠ title, content, category, author, createdAt 건드리지 않음
        return origin;
    }

    // 삭제
    public void deleteComplaint(Long id) {
        complaintRepository.deleteById(id);
    }
}
