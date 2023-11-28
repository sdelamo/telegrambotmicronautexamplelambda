import java.net.URI

plugins {
    id("io.micronaut.library") version "4.2.0"
    id("com.github.johnrengelman.shadow") version "8.1.1"
}

version = "0.1"
group = "telegrambot"

repositories {
    mavenCentral()
    maven { url = URI.create("https://s01.oss.sonatype.org/content/repositories/snapshots/") }
}

dependencies {
    annotationProcessor("io.micronaut.serde:micronaut-serde-processor")
    implementation("com.amazonaws:aws-lambda-java-events")
    implementation("io.micronaut.aws:micronaut-aws-apigateway")
    implementation("io.micronaut.aws:micronaut-aws-lambda-events-serde")
    implementation("io.micronaut.aws:micronaut-function-aws")
    implementation("io.micronaut.serde:micronaut-serde-jackson")

    annotationProcessor("io.micronaut.validation:micronaut-validation-processor")
    implementation("io.micronaut.validation:micronaut-validation")
    runtimeOnly("ch.qos.logback:logback-classic")
    implementation("io.micronaut.chatbots:micronaut-chatbots-telegram-lambda:2.0.0-SNAPSHOT")
}


java {
    sourceCompatibility = JavaVersion.toVersion("17")
    targetCompatibility = JavaVersion.toVersion("17")
}


micronaut {
    runtime("lambda_java")
    testRuntime("junit5")
    nativeLambda {
        lambdaRuntimeClassName.set("io.micronaut.function.aws.runtime.MicronautLambdaRuntime")
    }
    processing {
        incremental(true)
        annotations("telegrambot.*")
    }
}



