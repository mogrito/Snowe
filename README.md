# 스키장 편의기능 & 강사예약 통합 앱

본 프로젝트는 장안대학교 캡스톤디자인(졸업작품) 프로젝트로,  
  
스키장에 관련된 편의기능과 강사등록, 강사조회, 강습예약등의 기능을 통합하여 서비스를 제공하는 모바일&웹 앱 입니다.


## Framework
- [Springboot](https://spring.io/projects/spring-boot)
- [Springsecurity](https://spring.io/projects/spring-security)
- [Mybatis](https://mybatis.org/mybatis-3/)

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
```

### Installing

A step by step series of examples that tell you how to get a development env running

Say what the step will be

```
Give the example
```

And repeat

```
until finished
```

End with an example of getting some data out of the system or using it for a little demo

## Running the tests

Explain how to run the automated tests for this system

### Break down into end to end tests

Explain what these tests test and why

```
Give an example
```

### And coding style tests

Explain what these tests test and why

```
Give an example
```

## Deployment

Add additional notes about how to deploy this on a live system

## Built With

* [Dropwizard](http://www.dropwizard.io/1.0.2/docs/) - The web framework used
* [Maven](https://maven.apache.org/) - Dependency Management
* [ROME](https://rometools.github.io/rome/) - Used to generate RSS Feeds

## Contributing

Please read [CONTRIBUTING.md](https://gist.github.com/PurpleBooth/b24679402957c63ec426) for details on our code of conduct, and the process for submitting pull requests to us.

## Versioning

We use [SemVer](http://semver.org/) for versioning. For the versions available, see the [tags on this repository](https://github.com/your/project/tags). 

## Authors

* **Billie Thompson** - *Initial work* - [PurpleBooth](https://github.com/PurpleBooth)

See also the list of [contributors](https://github.com/your/project/contributors) who participated in this project.

## License

This project is licensed under the MIT License - see the [LICENSE.md](LICENSE.md) file for details

## Acknowledgments

* Hat tip to anyone whose code was used
* Inspiration
* etc
