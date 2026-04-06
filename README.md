# 🎓 학생 고충·민원 처리 시스템 (Student Complaint Management System) - Backend

본 프로젝트는 학교 구성원(학생, 학생회, 교사)의 원활한 소통과 투명한 민원 처리를 지원하는 웹 기반 시스템의 백엔드(API) 레포지토리입니다. RBAC(역할 기반 접근 제어)와 단일 테이블 전략을 활용하여 효율적이고 안전한 시스템을 구축했습니다.

## 📌 프로젝트 개요
* **개발 기간:** 2024.09 ~ 2024.12 (3개월)
* **개발자:** 노재혁 (Full Stack Engineer)
* **아키텍처 구조:** React -> REST API -> Spring Boot -> MySQL

## 🛠 기술 스택
* **Framework:** Spring Boot
* **Database:** MySQL
* **Security:** Spring Security, JWT (JSON Web Token)

## 🌟 시스템 아키텍처 및 핵심 설계

### 1. 보안 및 권한 제어 (RBAC)
* **JWT 기반 인증/인가:** Spring Security와 JWT를 결합하여 사용자 역할(학생, 학생회, 교사)에 따른 엄격한 접근 권한 분리.
* **보안 검증:** 서명이 올바르지 않거나 변조된 토큰 접근 시 `401 Unauthorized` 반환 및 차단.

### 2. 데이터베이스 설계 (ERD)
* **단일 테이블 전략 (`complaint_db`):** 데이터의 일관성 보장과 유지보수성을 높이기 위해 다수의 테이블로 나누지 않고 하나의 핵심 테이블로 논리적 관계를 표현했습니다.
* **상태 의존적 무결성 규칙:**
  * `WAITING` (대기): 초기 작성자(author), 제목, 내용 필수.
  * `IN_PROGRESS` (진행중): 담당자(assigned_to) 배정 필수.
  * `COMPLETED` (완료): 처리 완료 메시지(completion_message) 필수.
  * `REJECTED` (반려): 반려 사유(rejection_reason) 필수.

### 3. RESTful API 설계
* 민원 접수, 조회, 상태 변경(PATCH) 등 리소스 중심의 API를 설계하여 프론트엔드와 독립적이고 효율적인 통신 환경 구축.

## 🧪 테스트 및 품질 검증 (QA)
* **테스트 케이스:** 100개 이상의 유즈케이스 시나리오(로그인, 권한별 민원 등록/조회/처리 등)를 기반으로 Unit Test 및 Integration Test 병행 수행.
* **데이터 무결성 검증:** 민원 상태가 전이될 때마다 변경 주체와 변경 시각(`created_at`)이 정확히 기록되도록
