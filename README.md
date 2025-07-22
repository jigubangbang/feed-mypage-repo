# User Profile and Feed Service Repository

이 레포지토리는 Jigubangbang MSA 아키텍처에서 **소셜 피드와 프로필 서비스**를 담당합니다.


## 서비스 개요
- **피드 서비스**: 게시물 작성 및 상호작용, 피드 탐색과 친구 추천 기능을 제공
- **프로필 서비스**: 사용자 정보 관리, 네트워크 기능, 지도 정보 조회 및 사용자 방문 국가 관리 지원


## 기술 스택
- **Core Framework**: Java 17, Spring Boot 3.4.6, Spring Cloud
- **Data & Persistence**: MyBatis 3.x, MySQL 8.x
- **Service Communication & Config**: Eureka Client, Feign Client, Spring Cloud Config

---
## Feed Service


### 주요 기능
- **소셜 피드 시스템**: 게시물 작성 및 좋아요, 북마크, 댓글 등의 상호작용 기능 제공
- **탐색 기능**: 해시태그 기반의 게시글 검색 및 인기 주제 조회 기능 지원
- **친구 추천 및 사용자 검색 기능**: 개인화된 친구 추천 및 사용자 검색 기능 제공

### REST API

| Method   | URL                                                      | Role   | 설명                           |
| -------- | -------------------------------------------------------- | ------ | ----------------------------- |
| `GET`    | `/profile/{userId}`                                    | PRIVATE | 사용자 프로필 정보 조회 (팔로우 상태, 뱃지 포함) |
| `PUT`    | `/profile/{userId}`                                    | PRIVATE | 프로필 이미지 업로드 (S3)              |
| `PUT`    | `/profile/{userId}/bio`                                | PRIVATE | 자기소개(Bio) 수정                  |
| `PUT`    | `/profile/{userId}/nationality`                        | PRIVATE | 국적 정보 수정                      |
| `PUT`    | `/profile/{userId}/travel-status?status={status}`      | PRIVATE | 현재 여행 상태 수정                   |
| `POST`   | `/profile/{userId}/network`                            | PRIVATE | 팔로우 요청                        |
| `DELETE` | `/profile/{userId}/network`                            | PRIVATE | 언팔로우 요청                       |
| `GET`    | `/profile/{userId}/favorites`                          | PUBLIC  | 즐겨찾기 국가 목록 조회                 |
| `POST`   | `/profile/{userId}/favorites`                          | PRIVATE | 즐겨찾기 국가 목록 수정                 |
| `GET`    | `/profile/{userId}/languages/search?keyword={keyword}` | PUBLIC  | 전체 언어 목록 검색 (키워드 필터링 가능)      |
| `GET`    | `/profile/{userId}/languages`                          | PUBLIC  | 사용자의 보유 언어 조회                 |
| `POST`   | `/profile/{userId}/languages`                          | PRIVATE | 언어 추가                         |
| `PUT`    | `/profile/{userId}/languages/{id}`                     | PRIVATE | 언어 능력 수정                      |
| `DELETE` | `/profile/{userId}/languages/{id}`                     | PRIVATE | 언어 삭제                         |
| `GET`    | `/profile/membership-status`                           | PRIVATE | 프리미엄 회원 여부 확인                 |
| `GET`    | `/profile/{userId}/bucketlist?status={completed \| incomplete}` | PUBLIC                                | 사용자 버킷리스트 조회 (전체 / 완료 / 미완료 필터링 가능) |
| `POST`   | `/profile/{userId}/bucketlist`                   | PRIVATE       | 버킷리스트 항목 추가                           |
| `DELETE` | `/profile/{userId}/bucketlist/{goalId}`          | PRIVATE       | 버킷리스트 항목 삭제                           |
| `PUT`    | `/profile/{userId}/bucketlist/{goalId}/status`   | PRIVATE       | 항목 완료/미완료 상태 토글 + 완료일자 응답 포함          |
| `PUT`    | `/profile/{userId}/bucketlist/reorder`           | PRIVATE       | 버킷리스트 항목 순서 변경 (drag & drop 등 UI 정렬용) |
| `GET`    | `/profile/{userId}/countries/search?keyword=`      | PRIVATE | 국가 검색 (사용자 즐겨찾기 기반 + 키워드 필터)   |
| `GET`    | `/profile/{userId}/countries/visited?continent=`   | PRIVATE | 방문 국가 목록 조회 (대륙 필터링 가능)        |
| `GET`    | `/profile/{userId}/countries/wishlist`             | PRIVATE | 위시리스트 국가 목록 조회                 |
| `POST`   | `/profile/{userId}/countries/visited`              | PRIVATE | 방문 국가 추가                       |
| `DELETE` | `/profile/{userId}/countries/visited/{countryId}`  | PRIVATE | 방문 국가 제거                       |
| `POST`   | `/profile/{userId}/countries/wishlist`             | PRIVATE | 위시리스트 국가 추가                    |
| `DELETE` | `/profile/{userId}/countries/wishlist/{countryId}` | PRIVATE | 위시리스트 국가 제거                    |
| `GET`    | `/profile/countries/{countryId}/cities`            | PUBLIC  | 특정 국가의 도시 목록 반환                |
| `GET`    | `/profile/{userId}/countries/{countryId}`          | PRIVATE | 특정 국가에 대해 사용자가 작성한 게시글 목록 (피드) |
| `PUT`    | `/profile/{userId}/map/settings?color=`            | PRIVATE | 사용자 지도 색상 설정 변경                |
| `GET`  | `/profile/{userId}/network?type=following` | PRIVATE | 사용자의 팔로잉 목록 조회 (페이징 지원) |
| `GET`  | `/profile/{userId}/network?type=followers` | PRIVATE | 사용자의 팔로워 목록 조회 (페이징 지원) |
| `GET`  | `/public/{userId}/map`             | PUBLIC | 유저의 지도 색상과 방문 국가 기본 정보 리스트 조회          |
| `GET`  | `/public/{userId}/map/settings`    | PUBLIC | 유저가 설정한 지도 색상 조회                       |
| `GET`  | `/public/{userId}/countries/stats` | PUBLIC | 유저의 방문 국가 통계 조회 (총 방문국 수, 대륙별 통계, 백분위) |


---
## Profile Service


### 주요 기능
- **사용자 정보 조회**: 여행 성향, 경험치, 레벨, 등의 사용자 정보 조회
- **네트워크 시스템**: 사용자 간 팔로우/언팔로우 기능 지원
- **프로필 관리**: 사용자 국적, 언어, 소개글 등 사용자 정보 수정 기능 및 상태 설정 기능 지원
- **S3 파일 업로드**: S3 파일 업로드 기능 지원
- **국가 관련 기능**: 방문/희망 국가 추가 및 삭제 기능, 피드와 국가 정보 연동
- **지도 기능**: 사용자 지도 설정 및 조회, 국가 통계 정보 제공
- **버킷리스트 관리**: 버킷리스트 추가, 삭제 및 정렬 기능 제공


### REST API

| Method   | URL                                                      | Role   | 설명                           |
| -------- | -------------------------------------------------------- | ------ | ----------------------------- |
| `GET`    | `/public/countries`                                      | PUBLIC | 모든 국가 목록 조회               |
| `GET`    | `/public/countries/{countryId}/cities`                   | PUBLIC | 특정 국가의 도시 목록 조회 |
| `GET`    | `/public/countries/top?limit={limit}`                    | PUBLIC | 인기 순으로 정렬된 상위 국가 목록 조회 |
| `GET`    | `/feed/following`                                        | AUTH   | 팔로잉한 사용자의 피드 목록 조회 |
| `GET`    | `/feed/bookmark`                                         | AUTH   | 북마크한 게시글 목록 조회 |
| `GET`    | `/feed/{feedId}`                                         | AUTH   | 특정 피드 상세 정보 조회 |
| `POST`   | `/feed`                                                  | AUTH   | 피드 작성 |
| `POST`   | `/feed/images`                                           | AUTH   | 피드 이미지 업로드 (S3 저장) |
| `POST`   | `/feed/{feedId}/images`                                  | AUTH   | 피드에 이미지 정보 추가 |
| `PUT`    | `/feed/{feedId}`                                         | AUTH   | 피드 수정                             |
| `PUT`    | `/feed/{feedId}/public?status`                           | AUTH   | 피드 공개/비공개 상태 변경                |
| `DELETE` | `/feed/{feedId}`                                         | AUTH   | 피드 삭제                             |
| `GET`    | `/feed/{feedId}/comments`                                | AUTH   | 피드의 댓글 목록 조회 (페이징 지원) |
| `GET`    | `/feed/{feedId}/comments/{commentId}/replies`            | AUTH   | 특정 댓글의 대댓글 목록 조회      |
| `POST`   | `/feed/{feedId}/comments`                                | AUTH   | 댓글 작성                 |
| `DELETE` | `/feed/{feedId}/comments/{commentId}`                    | AUTH   | 댓글 삭제                 |
| `POST`   | `/feed/{feedId}/comments/{commentId}/like`               | AUTH   | 댓글 좋아요                |
| `DELETE` | `/feed/{feedId}/comments/{commentId}/like`               | AUTH   | 댓글 좋아요 취소             |
| `POST`   | `/feed/{feedId}/like`                                    | AUTH   | 피드 좋아요    |
| `DELETE` | `/feed/{feedId}/like`                                    | AUTH   | 피드 좋아요 취소 |
| `POST`   | `/feed/{feedId}/bookmark`                                | AUTH   | 피드 북마크    |
| `DELETE` | `/feed/{feedId}/bookmark`                                | AUTH   | 피드 북마크 취소 |
| `GET`    | `/public/posts`                                          | PUBLIC | 공개 피드 목록 조회 |
| `GET`    | `/public/posts/top?limit={limit}`                        | PUBLIC | 인기 피드 목록 조회 (기본값: 5) |
| `GET`    | `/public/trending?limit={limit}`                         | PUBLIC | 인기 해시태그 목록 조회 |
| `GET`    | `/public/trending/{tag}?pageSize={size}&offset={offset}` | PUBLIC | 특정 해시태그의 피드 목록 조회 |
| `GET`    | `/public/search?keyword={keyword}&limit={limit}`         | PUBLIC | 키워드 기반 해시태그 검색 |
| `PUT`    | `/style/{styleId}`                                       | PRIVATE | 사용자의 여행 성향 업데이트              |
| `GET`    | `/public/styles`                                         | PUBLIC  | 여행 성향 기본 목록 조회               |
| `GET`    | `/public/styles/{styleId}`                               | PUBLIC  | 특정 여행 성향 상세 |
| `GET`    | `/feed/users/{userId}`                                   | PRIVATE | 특정 사용자의 피드 목록 조회 |
| `GET`    | `/feed/recommended/users`                                | PRIVATE | 친구 추천 목록 조회 |
| `GET`    | `/feed/search/users?keyword={keyword}`                   | PUBLIC  | 키워드 기반 사용자 검색                         |



---
## 관련 리포지토리

- **전체 프로젝트**: [Jigubangbang Organization](https://github.com/jigubangbang)
- **인프라 서비스**: [infra-platform](https://github.com/jigubangbang/infra-platform)
- **관련 서비스**: [chat-service](https://github.com/jigubangbang/chat-service)
- **프론트엔드**: [msa-front](https://github.com/jigubangbang/msa-front)