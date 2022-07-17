import org.jetbrains.kotlin.gradle.tasks.KotlinCompile

plugins {
	val ktVersion = "1.6.21"
	id("org.springframework.boot") version "2.7.2-SNAPSHOT"
	id("io.spring.dependency-management") version "1.0.12.RELEASE"
	id("org.jetbrains.kotlin.plugin.jpa") version ktVersion
	kotlin("jvm") version ktVersion
	kotlin("plugin.spring") version ktVersion
	kotlin("kapt") version ktVersion //Querydsl
}

group = "tars.template"
version = "5.3.1"
java.sourceCompatibility = JavaVersion.VERSION_17

repositories {
	mavenCentral()
	maven { url = uri("https://repo.spring.io/milestone") }
	maven { url = uri("https://repo.spring.io/snapshot") }
}

dependencies {
//	implementation("org.springframework.boot:spring-boot-starter-data-mongodb-reactive")
	implementation("org.springframework.boot:spring-boot-starter-web")
	implementation("org.springframework.boot:spring-boot-starter-data-jpa")
	implementation("org.springframework.boot:spring-boot-starter-validation")
	implementation("org.springframework.security:spring-security-crypto")
	implementation("com.fasterxml.jackson.module:jackson-module-kotlin")
	implementation("io.projectreactor.kotlin:reactor-kotlin-extensions")
	implementation("org.jetbrains.kotlin:kotlin-reflect")
	implementation("org.jetbrains.kotlin:kotlin-stdlib-jdk8")
	implementation("org.jetbrains.kotlinx:kotlinx-coroutines-reactor")
	implementation("io.jsonwebtoken:jjwt-api:0.11.5")
	runtimeOnly("io.jsonwebtoken:jjwt-impl:0.11.5")
	runtimeOnly("io.jsonwebtoken:jjwt-jackson:0.11.5")
//	runtimeOnly("mysql:mysql-connector-java")
	runtimeOnly("org.postgresql:postgresql")
	testImplementation("org.springframework.boot:spring-boot-starter-test")
	testImplementation("io.projectreactor:reactor-test")
	// querydsl
	val querydslVersion = "5.0.0"
	implementation("com.querydsl:querydsl-jpa:$querydslVersion")
	kapt("com.querydsl:querydsl-apt:$querydslVersion:jpa")
	annotationProcessor("org.springframework.boot:spring-boot-configuration-processor:2.7.1")
	annotationProcessor(group = "com.querydsl", name = "querydsl-apt", classifier = "jpa")

}

tasks.withType<KotlinCompile> {
	kotlinOptions {
		freeCompilerArgs = listOf("-Xjsr305=strict")
		jvmTarget = "17"
	}
}

tasks.withType<Test> {
	useJUnitPlatform()
}

//kotlin.sourceSets.main {
//	setBuildDir("$buildDir")
//}
//sourceSets["main"].withConvention(org.jetbrains.kotlin.gradle.plugin.KotlinSourceSet::class) {
//	kotlin.srcDir("$buildDir/generated/source/kapt/main")
//}