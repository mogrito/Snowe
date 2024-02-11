# 스키장 편의기능 & 강사예약 통합 앱

본 프로젝트는 장안대학교 캡스톤디자인(졸업작품) 프로젝트로,  
  
스키장에 관련된 편의기능과 강사등록, 강사조회, 강습예약등의 기능을 통합하여 서비스를 제공하는 모바일&웹 앱 입니다.


## Framework
- [Springboot](https://spring.io/projects/spring-boot)
- [Springsecurity](https://spring.io/projects/spring-security)
- [Mybatis](https://mybatis.org/mybatis-3/)
- [Redis]

What things you need to install the software and how to install them

```
plugins {
    id 'java'
    id 'org.springframework.boot' version '3.0.2'
    id 'io.spring.dependency-management' version '1.0.15.RELEASE'
}

dependencies {
    implementation 'org.springframework.boot:spring-boot-starter-validation'
    implementation 'org.springframework.boot:spring-boot-starter-security'
    implementation 'org.mybatis.spring.boot:mybatis-spring-boot-starter:3.0.1'
    implementation 'io.jsonwebtoken:jjwt-api:0.11.2'  // API 의존성 추가
    implementation 'net.coobird:thumbnailator:0.4.14'    /* 썸네일 */
    runtimeOnly 'io.jsonwebtoken:jjwt-impl:0.11.2' // 구현 의존성 추가
    runtimeOnly group: 'io.jsonwebtoken', name: 'jjwt-jackson', version: '0.11.5'
    implementation 'org.springframework.boot:spring-boot-starter-thymeleaf'
}
