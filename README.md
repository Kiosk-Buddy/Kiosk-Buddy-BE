# Kiosk-Buddy-BE

> 디지털 소외계층을 위한 **키오스크 가이드 & 시뮬레이터** 백엔드 서버  
> Spring Boot 기반 RESTful API 서비스

> 프로젝트는 본래 김소정님과 함께 기획되었으나, 백엔드 개발이 어려워져 현재는 단독으로 개발을 이어가고 있습니다.

## 📌 프로젝트 개요

**KioskBuddy**는 디지털 소외계층(고령층, 장애인 등)을 대상으로  
키오스크 사용을 **가이드하고 테스트**할 수 있는 애플리케이션입니다.  

이 저장소는 백엔드 서버로서 다음 기능을 담당합니다.

- 사용자 계정 등록 및 인증
- 튜토리얼 진행 내역 저장
- 시뮬레이터 테스트 결과 기록
- 사용자 피드백 및 진행 현황 분석

## 프로젝트 구조
```
com.kioskbuddy.backend
├── member                                 ← 도메인 기준 하위 디렉토리
│   ├── domain                             ← 순수 도메인 모델
│   │   └── Member.java                          ← Entity, VO
│   │   
│   ├── application                        ← Use case 중심 로직
│   │   ├── MemberService                  ← 유스케이스 서비스
│   │   │ 
│   │   └── dto                            ← Request/Response DTO
│   │       ├── MemberCreateRequest.java
│   │       └── MemberDetailResponse.java
│   ├── repository                         ← 외부 기술 연계 (JPA, Redis, Kafka 등)
│   │   └── jpa
│   │       └── JpaMemberRepository.java
│   └── ui                                 ← API Layer (Controller)
│       └── MemberController.java
│
├── Tutorial                               ← 또 다른 도메인 (같은 구조)
│   └── ...
```

# API 명세

### Member
<img width="1513" alt="스크린샷 2025-05-11 오후 10 27 29" src="https://github.com/user-attachments/assets/c38c3cc2-b780-4903-ab11-212f309add96" />

### Tutorial
<img width="1507" alt="스크린샷 2025-05-11 오후 10 28 47" src="https://github.com/user-attachments/assets/5d67a1a5-4b46-41b3-8f39-2d364011b96c" />

### Progress
<img width="1508" alt="스크린샷 2025-05-15 오후 7 18 54" src="https://github.com/user-attachments/assets/d7ddf58a-c274-4629-999b-1dfbcd9eb1a8" />

# 테스트 

> JUnit5 + Mockito 기반 단위 테스트 작성

# ERD
<img width="1311" alt="스크린샷 2025-04-21 18 46 19" src="https://github.com/user-attachments/assets/9b7646cf-2602-4619-a1e2-5a20d53fd9dd" />
