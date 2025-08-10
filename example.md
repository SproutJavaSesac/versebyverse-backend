![header](https://capsule-render.vercel.app/api?type=waving&height=250&color=E9711C&text=BoardBuddy&textBg=false&fontColor=ffffff&fontSize=40&fontAlign=50&fontAlignY=39&section=header)


# 보드버디 BoardBuddy

## 카카오X구름 기업 연계 프로젝트 - Backend

- 개발 기간 : 2024/6/28 ~ 2024/8/14

<br><br>

## 배포 주소

> 🔗 [BoardBuddy](https://m.boardbuddyapp.com)

<br><br>

## 프로젝트 소개
BoardBuddy는 보드게임을 좋아하는 사람들이 주변에서 보드게임을 함께할 친구들을 쉽게 모집하고 참여할 수 있도록 돕는 커뮤니티 플랫폼입니다.

<br><br>

### Back-end 멤버 소개
|<img width="200" src="https://avatars.githubusercontent.com/u/147473025?v=4" alt="프로필 이미지">|<img width="200" src="https://avatars.githubusercontent.com/u/131665874?v=4" alt="프로필 이미지">|<img width="200" src="https://avatars.githubusercontent.com/u/159746126?v=4" alt="프로필 이미지">|<img width="200" src="https://avatars.githubusercontent.com/u/120402129?v=4" alt="프로필 이미지">|
|:---:|:---:|:---:|:---:|
|[**공태현**](https://github.com/runtime-zer0)|[**김근호**](https://github.com/geunhokinn)|[**이다솜**](https://github.com/serahissomi)|[**최민지**](https://github.com/meanzi3)|

<br><br>

### 역할 및 담당 기능
<table>
  <tr>
    <th width="10%">이름</th>
    <td width="20%">공태현</td>
    <td width="20%">김근호</td>
    <td width="20%">이다솜</td>
    <td width="20%">최민지</td>
  </tr>

  <tr>
    <th>역할</th>
    <td>백엔드 리더</td>
    <td>팀원</td>
    <td>팀원</td>
    <td>팀원</td>
  </tr>

  <tr>
    <th>기능</th>
    <td>
      <ul>
        <li>백엔드 CI/CD 구축 및 배포</li>
        <li>참가 유저 관리, 유저 인증 관련 기능</li>
        <li>로그인, 소셜 로그인, SMS 인증 기능</li>
        <li>실시간 채팅 기능 구현</li>
      </ul>
    </td>
    <td>
      <ul>
	      <li>프로젝트 아이디어 제공 및 기획 구체화</li>
        <li>행정 구역 데이터 수집, 전처리, 데이터베이스 구축</li> 
        <li>위치 검색, 내 동네 조회/설정, 내 반경 설정 기능</li> 
        <li>위치 기반 모집글 리스트 조회, 보드 게임 카페 찾기 기능</li>
        <li>댓글 및 대댓글 작성, 조회, 수정, 삭제 기능</li>
      </ul>
    </td>
    <td>
      <ul>
        <li>마이페이지 조회 및 프로필 조회, 수정 기능</li>
        <li>뱃지 조회 기능</li>
        <li>알림 기능</li>
        <li>유저간 리뷰 보내기 기능 및 리뷰 집계 기능</li>
      </ul>
    </td>
    <td>
      <ul>
        <li>모집글 작성, 조회, 수정, 삭제, 검색 기능</li>
        <li>모집글 상태 관리 스케줄링 기능</li>
        <li>랭킹 집계, 뱃지 부여 스케줄링 기능</li>
      </ul>
    </td>
  </tr>
</table>

<br><br>

## 주요기능

### 회원가입 및 본인인증
CoolSMS를 활용하여 SMS 인증 서비스를 구현하여 회원가입 시 본인 인증을 할 수 있습니다.
빠른 성능 및 자동 만료 기능(TTL) 활용을 위한 Redis를 활용하였습니다.

### 로그인 및 소셜 로그인
일반 회원가입, 로그인 기능 제공과 동시에 소셜 로그인 기능을 제공하여 사용자는 별도의 회원가입과 로그인 과정을 거치지 않고도 카카오, 네이버, 구글 계정을 통해 서비스를 이용할 수 있습니다.
Spring Security, OAuth2를 이용하였으며 인증된 사용자의 상태는 세션을 통해 유지하고, 세션 유효성 검사를 통해 자동 로그인 기능을 구현하였습니다.

### 내 동네 설정
위치 설정으로 내 동네를 설정할 수 있습니다. 가까운 곳부터 먼 곳까지 사용자가 원하는대로 반경을 선택할 수 있습니다.

### 랭킹 시스템
매월 1일에 사용자의 월별 서비스 이용 내역, 받은 후기에 따라 TOP3를 선정하며, 프로필에 특별한 뱃지(1-3등)를 함께 표시합니다. 또한 해당하는 월의 뱃지(1-12월)를 얻을 수 있습니다.
랭킹 순위 집계는 SpringBoot의 Scheduler를 이용하여 자동화하도록 스케줄링 됩니다.

### 보드게임 카페 찾기
사용자의 현재 위치에 따라 주변 보드게임 카페를 찾을 수 있습니다. 보드게임카페의 위치와 정보를 마커를 통해 제공합니다.

### 버디 모집 게시판
사용자는 설정한 위치를 기반으로 주변 동네의 모집글 리스트를 조회하며, 현재 모집 중인 글로 필터링 하거나 모임 시간이 빠른 글 순으로 정렬할 수 있습니다.
또한 페이지 하단에 도달할 때마다 API가 호출되어 모집글이 끊김 없이 계속 로드되는 무한 스크롤 방식의 사용자 경험을 제공합니다.

### 실시간 단체 채팅
모임에 참가하는 사용자들이 단체 채팅을 할 수 있도록 작성된 모집글마다 단체 채팅방이 자동으로 생성됩니다. 웹소켓을 활용하여 실시간 단체 채팅이 가능합니다.

### 모임 참가 및 승인, 거절
모집글을 작성한 사용자는 다른 사용자들이 모임에 대해 참가 신청을 했을 때, 승인 또는 거절을 할 수 있습니다. 또한, 참가 신청에 대한 취소가 가능합니다.

### 리뷰 및 평가 시스템
모집글 작성자가 모임 예상 종료시간을 입력하면 그 시간에 맞춰 각 참가자들에게 리뷰 리마인더 알림이 발송됩니다. 최고예요/좋아요/별로예요/노쇼 리뷰를 보낼 수 있습니다.
각 리뷰는 랭킹 및 버디 지수(보드버디 서비스 내에서의 매너점수)에 반영됩니다.

### 알림 기능
사용자는 내 동네 기반 새로운 모집글이 작성되었을 때 알림을 받을 수 있습니다.
또한 자신이 작성한 모집글에 새로운 댓글이 달리거나 자신이 작성한 댓글에 대댓글이 달리면 알림을 받으며, 참가 신청과 승인/거절에 대해 알림을 받을 수 있습니다.

<br><br>

## 아키텍쳐
![Architecture](https://github.com/user-attachments/assets/f2a00f79-f5d1-45e7-b315-b557a02638a4)

<br>

### 백엔드 주요 기술 스택
<img src="https://img.shields.io/badge/java-007396?style=for-the-badge&logo=java&logoColor=white"><img src="https://img.shields.io/badge/springboot-6DB33F?style=for-the-badge&logo=springboot&logoColor=white">
<img src="https://img.shields.io/badge/spring security-6DB33F?style=for-the-badge&logo=springsecurity&logoColor=white">
<img src="https://img.shields.io/badge/hibernate-59666C?style=for-the-badge&logo=hibernate&logoColor=white">
<img src="https://img.shields.io/badge/springdatajpa-6DB33F?style=for-the-badge&logo=springdatajpa&logoColor=white">
<img src="https://img.shields.io/badge/qeurydsl-1572B6?style=for-the-badge&logo=querydsl&logoColor=white">
<img src="https://img.shields.io/badge/mariadb-003545?style=for-the-badge&logo=mariadb&logoColor=white">
<img src="https://img.shields.io/badge/WebSockets-E9711C?style=for-the-badge&logo=websocket&logoColor=white">
<img src="https://img.shields.io/badge/STOMP-FF9900?style=for-the-badge&logo=stomp&logoColor=white">
<img src="https://img.shields.io/badge/Docker-2496ED?style=for-the-badge&logo=docker&logoColor=white">
<img src="https://img.shields.io/badge/Redis-DC382D?style=for-the-badge&logo=redis&logoColor=white">
<img src="https://img.shields.io/badge/Quartz-1572B6?style=for-the-badge&logo=quartz&logoColor=white">
<img src="https://img.shields.io/badge/Amazon EC2-FF9900?style=for-the-badge&logo=amazonec2&logoColor=white">
<img src="https://img.shields.io/badge/Amazon RDS-527FFF?style=for-the-badge&logo=amazonrds&logoColor=white">
<img src="https://img.shields.io/badge/Amazon S3-569A31?style=for-the-badge&logo=amazons3&logoColor=white">

<br><br>

### ERD
![board_buddy_db](https://github.com/user-attachments/assets/82238e0a-0fd8-4319-af69-6970a4a3532d)

<br><br>

## 시연 영상
[![보드버디 시연영상](https://github.com/user-attachments/assets/cb9f0f10-1847-4ce0-bd8d-0884df89221f)](https://youtu.be/GB8Cqv4oayo?feature=shared)

<br><br>

![footer](https://capsule-render.vercel.app/api?type=waving&height=150&color=E9711C&section=footer)
