

# 📝 LikeLion Sogang Blog - Demo Project v2

> 서강대학교 멋쟁이사자처럼 백엔드 교육용 블로그 프로젝트 (v2).  
> 로그인 / 좋아요 기능이 포함된 버전이며, GitHub Actions 기반 CI/CD 파이프라인과 Docker로 배포됩니다.

---

## ✅ 주요 기능

- 🖋 게시글 CRUD (Create / Read / Update / Delete)
- 💬 댓글 작성 및 삭제
- 🔐 로그인 (JWT 기반 인증)
- ❤️ 좋아요 생성 및 취소
- 🐳 Docker 기반 컨테이너화
- ⚙️ GitHub Actions 기반 CI/CD 자동 배포


---

## 📦 프로젝트 구조

```plaintext

├── controller/   # REST API 엔드포인트
├── domain/       # 엔티티 모델
├── dto/          # 요청/응답 DTO
├── exception/    # 커스텀 예외 처리
├── repository/   # JPA Repository
├── security/     # JWT 인증/인가
└── service/      # 비즈니스 로직

```

---

## 🔐 API 명세

| 기능       | Method | URL                     | 설명        |
| -------- | ------ | ----------------------- | --------- |
| 게시글 생성   | POST   | `/articles`             | 게시글 작성    |
| 게시글 수정   | PUT    | `/articles/{id}`        | 게시글 수정    |
| 게시글 전체조회 | GET    | `/articles`             | 전체 게시글 목록 |
| 게시글 개별조회 | GET    | `/articles/{id}`        | 게시글 상세 조회 |
| 게시글 삭제   | DELETE | `/articles/{id}`        | 게시글 삭제    |
| 댓글 생성    | POST   | `/comments/{articleId}` | 댓글 작성     |
| 댓글 삭제    | DELETE | `/comments/{commentId}` | 댓글 삭제     |
| 로그인      | POST   | `/auth/login`           | JWT 토큰 발급 |
| 좋아요/취소   | POST   | `/likes/{id}`           | 좋아요 등록/취소 |

---

## 🔁 CI/CD: GitHub Actions + Docker + EC2

### 배포 흐름

```
1. deploy 브랜치에 push
2. GitHub Actions가 자동으로 빌드 및 Docker 이미지 생성
3. DockerHub에 이미지 푸시
4. EC2 서버에 SSH 접속하여 컨테이너 재배포
```


---

## 📊 기술 스택

| 항목         | 기술             |
| ---------- | -------------- |
| Backend    | Spring Boot    |
| Database   | PostgreSQL     |
| Deployment | Docker         |
| CI/CD      | GitHub Actions |

---

## 📂 .env.example

```env
# JWT 비밀 키 
SECRET_KEY=your_jwt_secret_key

# PostgreSQL 설정
POSTGRES_DB=blog_v2
POSTGRES_USER=your_db_username
POSTGRES_PASSWORD=your_db_password

# JDBC URL (서버 DB 주소에 맞게 수정)
DATABASE_URL=jdbc:postgresql://<DB_HOST>:5433/blog_v2
DATABASE_USERNAME=your_db_username
DATABASE_PASSWORD=your_db_password
```

---

## 🙋 프로젝트 정보

* 소속: 멋쟁이사자처럼 서강대학교
* 개발자: [@naaadang](https://github.com/naaadang)
* 목적: 백엔드 교육용 데모 프로젝트

---


