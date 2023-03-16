# id-generator

> - 분산 환경에서 DBMS에 독립적으로 auto id를 생성할 수 있는 라이브러리   
> - spring aop 와 java reflection을 사용해 기능 구현
> - JPA의 @Entity 객체에 @Id 필드를 찾아 id 생성함

## 해당 라이브러리를 개발하게된 이유

> 분산 환경에서 DBMS에 의존해 Auto Id를 생성하는 것은 성능상 이슈를 발생할 수 있었다.
> 뿐만 아니라, 대량의 데이터가 매번 DBMS에 접근하면 네트워크 비용도 많이 발생하기 때문에 성능 이슈가 발생한다.   
> 그래서 DBMS에 독립적인 id 생성을 고민했고 uuid와 snowflake 방식 중 시간순으로 정렬이 가능하고 크기도 64bit로 작은 snowflake 방식을 채택하였다.   
> 그 후, snowflake를 직접 구현해 커스터마이징했고 해당 id-generator를 어노테이션을 제공하므로 
> 편하게 사용하도록 개발했다.


## Getting Started

해당 라이브러리 다운 받은 후 아래 명령어를 실행

```
./gradlew publishToMavenLocal
```

그 후, 라이브러리를 적용할 프로젝트 실행 후 아래와 같이 dependency를 추가
### Maven

pom.xml
```maven
<dependency>
    <groupId>com.idgeneration</groupId>
    <artifactId>spring-boot-jpa-idgeneration</artifactId>
    <version>1.0</version>
</dependency>
```

### Gradle

build.gradle
```gradle
repositories {
    mavenLocal()  /* local maven repository 사용 */
}

implementation 'com.idgeneration:spring-boot-jpa-idgeneration:1.0'
```


## Properties

```
id-generation:
  nodeId: 1   # 실행하는 노드 번호
  class-path: com.lolsearcher   # entity.class 파일 탐색을 위한 루트 클래스 패스
```

## Usage Example

main 클래스에 @EnableIdGenerator 사용

![img.png](img.png)

이 때, 두가지 모드 사용 가능

![img_1.png](img_1.png)

실행시 아래와 같은 로그 생성

![img_4.png](img_4.png)

Entity를 persist하는 메소드에 @IdGeneration 사용

![img_5.png](img_5.png)

생성된 결과 id

![img_6.png](img_6.png)