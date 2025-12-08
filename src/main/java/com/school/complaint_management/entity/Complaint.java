package com.school.complaint_management.entity;

import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;

import java.time.LocalDateTime;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
public class Complaint {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String category;

    private String title;

    @Column(columnDefinition = "TEXT")
    private String content;

    private String author;

    @Builder.Default
    private String status = "pending";

    private String assignedTo;

    private String rejectionReason;

    private String completionMessage;

    @CreationTimestamp
    private LocalDateTime createdAt;
}
