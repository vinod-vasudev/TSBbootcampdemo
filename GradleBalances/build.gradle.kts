val springBootVersion = "3.2.3"
val jakartaValidation = "3.0.2"
val springCloud = "3.1.0"
val lombok = "1.18.30"
val springDoc= "2.3.0"
val openApiTools = "0.2.6"
val jjwt = "0.12.5"
val mapstruct = "1.5.5.Final"
val swagger = "1.6.13"
val mockito = "5.11.0"

plugins {
	java
	id("org.springframework.boot") version "3.2.3"
	id("io.spring.dependency-management") version "1.1.4"
	kotlin("jvm")
}

group = "com.ob.tsb.balances"
version = "0.0.1-SNAPSHOT"

java {
}

repositories {
	mavenCentral()
}

dependencies {
	implementation("org.springframework.boot:spring-boot-starter-actuator")
	implementation("org.springframework.boot:spring-boot-starter-webflux")
	implementation("org.springframework.boot:spring-boot-starter-security")
	implementation("org.springframework.boot:spring-boot-starter-aop")
	implementation("org.springframework.boot:spring-boot-starter-validation")
	implementation("jakarta.validation:jakarta.validation-api:${jakartaValidation}")
	implementation("org.springframework.cloud:spring-cloud-starter-circuitbreaker-reactor-resilience4j:${springCloud}")
	compileOnly("org.projectlombok:lombok:${lombok}")
	annotationProcessor("org.projectlombok:lombok:${lombok}")
	implementation("org.springdoc:springdoc-openapi-starter-webflux-ui:${springDoc}")
	implementation("org.openapitools:jackson-databind-nullable:${openApiTools}")
	implementation("io.jsonwebtoken:jjwt:${jjwt}")
	implementation("org.mapstruct:mapstruct:${mapstruct}")
	implementation("org.mapstruct:mapstruct-processor:${mapstruct}")
	annotationProcessor("org.mapstruct:mapstruct-processor:${mapstruct}")
	implementation("io.swagger:swagger-annotations:${swagger}")
	testImplementation("org.mockito:mockito-junit-jupiter:${mockito}")
	testImplementation("org.springframework.boot:spring-boot-starter-test")
	testImplementation("io.projectreactor:reactor-test")
	implementation(kotlin("stdlib-jdk8"))
}

tasks.withType<Test> {
	useJUnitPlatform()

	testLogging {
		//showStandardStreams = true
		showExceptions = true
		addTestListener(object : TestListener {
			override fun afterTest(testDescriptor: TestDescriptor, result: TestResult) {
				logger.quiet("Executing test ${testDescriptor.name} [${testDescriptor}] with result: ${result.resultType}")
			}

			override fun beforeSuite(suite: TestDescriptor) {}

			override fun afterSuite(suite: TestDescriptor, result: TestResult) {
				//logger.quiet("Total Tests: ${result.testCount},  \"Passed: ${result.successfulTestCount},  \"Failed: ${result.failedTestCount},  \"Skipped: ${result.skippedTestCount}}")
			}

			override fun beforeTest(testDescriptor: TestDescriptor) {}
		})
	}
}
kotlin {
	jvmToolchain(21)
}