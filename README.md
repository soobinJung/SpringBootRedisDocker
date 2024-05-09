



Redis는 다양한 개발 상황에서 선호되는 오픈 소스, 인 메모리 키-값 데이터베이스로, 빠른 성능과 높은 확장성을 제공합니다. 또한, 최신 Spring Boot 3 프레임워크와의 통합을 통해 실제 어플리케이션 개발 상황을 시뮬레이션하며 기초적인 CRUD 작업을 수행하는 예제를 소개하고자 합니다.

![image](https://github.com/soobinJung/SpringBootRedisDocker/assets/66097044/84d9630b-cb86-4eb8-b60c-f9bba7eca834)


## Docker를 이용한 Redis 설치
docker 에서 redis 설치 후 실행 시키는 방법

1. Redis 이미지 다운로드
Redis Docker 이미지를 다운로드합니다. 터미널 또는 커맨드 라인을 열고 다음 명령을 실행합니다:


```
docker pull redis
```

2. Redis 컨테이너 실행
다운로드한 Redis 이미지를 기반으로 컨테이너를 생성하고 실행합니다. 다음 명령을 사용하여 Redis 서버를 시작할 수 있습니다


```
docker run --name some-redis -p 6379:6379 -d redis
```

--name some-redis: 컨테이너의 이름을 some-redis로 설정합니다.
-p 6379:6379: Docker 호스트의 6379 포트와 컨테이너의 6379 포트를 연결합니다. 이렇게 하면 로컬 컴퓨터에서 Redis 서버에 접근할 수 있습니다.
-d: 컨테이너를 백그라운드 모드에서 실행합니다.
redis: 사용할 이미지 이름입니다.


## Redis와 스프링 부트 통합하기
Redis는 빠른 데이터 처리 속도와 유연성으로 많은 개발자에게 사랑받는 오픈 소스 인-메모리 데이터 저장소입니다.

스프링 부트와 통합하면, 세션 관리, 캐싱 등 다양한 기능을 효과적으로 활용할 수 있습니다.

1. 의존성 추가

스프링 부트 프로젝트에서 Redis를 사용하기 위해서는 먼저 필요한 의존성을 프로젝트에 추가해야 합니다. 이를 위해 build.gradle 파일에 다음과 같은 코드를 포함시킵니다:


```
implementation 'org.springframework.boot:spring-boot-starter-data-redis'
implementation group: 'org.springframework.session', name: 'spring-session-data-redis', version: '3.2.2'
```

2. Redis 설정

Redis를 사용하기 위한 기본 설정은 매우 간단합니다. 스프링 부트의 설정 파일(application.yml 또는 application.properties)에 Redis 서버의 주소와 포트를 명시합니다

```
spring:
    redis:
      host: localhost
      port: 6379
```



이 설정은 로컬 컴퓨터에서 실행 중인 Redis 서버에 접속하도록 지시합니다. LettuceConnectionFactory는 이 정보를 사용하여 Redis 서버와의 연결을 생성하고 관리합니다.


```
@Configuration
public class RedisConfig  {

    @Bean
    public LettuceConnectionFactory redisConnectionFactory() {
        return new LettuceConnectionFactory();
    }
}
```



이 코드는 스프링의 설정 클래스 안에 위치하며, LettuceConnectionFactory 객체를 스프링 컨텍스트에 빈으로 등록합니다. 이 빈은 다른 스프링 컴포넌트가 Redis 연결을 필요로 할 때 사용됩니다.



로그인 성공 정보 저장
스프링 세션과 Redis를 사용하면, 사용자의 로그인 성공 정보를 자동으로 Redis에 저장할 수 있습니다.


이렇게 하면 사용자가 애플리케이션에 다시 접속할 때마다 로그인을 반복하지 않고 세션 정보를 통해 자동으로 인증을 받을 수 있습니다. 이 기능은 특히 웹 애플리케이션에서 유용하며, 사용자 경험을 크게 개선할 수 있습니다. 


