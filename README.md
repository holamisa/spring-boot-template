# spring-boot-template

# Spring Boot 템플릿

### 멀티 모듈
* DB 모듈
* API 모듈

## Docker 설정
* Windows : Docker Client 설치
* 이외 :
  * sudo apt update \#\# 우분투 패키지 관리자 업데이트
  * sudo apt install apt-transport-https ca-certificates curl software-properties-common \#\# Docker를 설치하기 위한 의존성 패키지 설치
  * curl -fsSL https://download.docker.com/linux/ubuntu/gpg | sudo apt-key add - \#\# Docker 공식 GPG 키 추가
  * sudo add-apt-repository "deb [arch=amd64] https://download.docker.com/linux/ubuntu $(lsb_release -cs) stable" \#\# Docker 레포지토리 추가
  * sudo apt update \#\# 패키지 관리자 업데이트
  * sudo apt-get install docker-ce docker-ce-cli containerd.io docker-buildx-plugin docker-compose-plugin \#\# Docker 설치
  
## Jenkins 설정
* Jenkins 설치
  * sudo apt update \#\# 우분투 패키지 관리자 업데이트
  * sudo apt install openjdk-8-jdk \#\# JDK 설치
  * wget -q -O - https://pkg.jenkins.io/debian/jenkins.io.key | sudo apt-key add - \#\# Jenkins 공식 GPG 키 추가
  * sudo sh -c 'echo deb http://pkg.jenkins.io/debian-stable binary/ > /etc/apt/sources.list.d/jenkins.list' \#\# Jenkins 레포지토리 추가
  * sudo apt update \#\# 패키지 관리자 업데이트
  * sudo apt install jenkins \#\# Jenkins 설치
  * sudo systemctl start jenkins \#\# Jenkins 시작
  * sudo systemctl status jenkins \#\# Jenkins 상태 확인
  * sudo systemctl enable jenkins \#\# Jenkins 부팅 시 자동 시작 설정
  * sudo cat /var/lib/jenkins/secrets/initialAdminPassword \#\# Jenkins 초기 비밀번호 확인
  * 브라우저에서 http://localhost:8080 접속 후 Jenkins 초기 비밀번호 입력
  * Jenkins 플러그인 설치
    * Jenkins 관리 > 플러그인 관리 > 설치 가능 탭 > GitHub Integration Plugin 설치
    * Jenkins 관리 > 플러그인 관리 > 설치 가능 탭 > GitHub Branch Source Plugin 설치
    * Jenkins 관리 > 플러그인 관리 > 설치 가능 탭 > Docker Pipeline Plugin 설치
    * Jenkins 관리 > 플러그인 관리 > 설치 가능 탭 > Docker Plugin 설치
    * Jenkins 관리 > 플러그인 관리 > 설치 가능 탭 > Docker Commons Plugin 설치
    * Jenkins 관리 > 플러그인 관리 > 설치 가능 탭 > Docker Build Step Plugin 설치
    * Jenkins 관리 > 플러그인 관리 > 설치 가능 탭 > Docker Pipeline Plugin 설치
    * Jenkins 관리 > 플러그인 관리 > 설치 가능
* Jenkins 구성
  * Github에 계정 PAT 생성
  * Github에 레포지토리에 Webhooks 추가
    * Settings > Webhooks > Add webhook
    * Payload URL : http://localhost:8080/github-webhook/
    * Content type : application/json
    * Which events would you like to trigger this webhook? : Just the push event
    * Active : 체크
    * 참고 사항으로 로컬에서 진행 시 localhost 대신 외부 IP나 도메인을 입력해야 함.
    * 방화벽 막혀있으면 ngrok 같은 툴을 사용하여 포트 포워딩을 해야함. --> ngrok http http://124.111.55.41:8080
  * Jenkins 관리 > 시스템 설정 > GitHub > PAT 연결
  * 새로운 Item -> Pipeline -> Pipleline script from SCM -> Git -> Repository URL 입력 -> Credential 선택 -> Branches to build 입력 -> Save (레포지토리 상위 폴더에 Jenkinsfile 필수)
  

--> 이렇게 설정하면 Jenkins에서 레포지토리의 브랜치에 push 할 때마다 자동으로 빌드 및 배포가 이루어집니다.