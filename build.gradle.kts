import org.lwjgl.*

plugins {
    id("java")
    id("org.lwjgl.plugin") version "0.0.35"
}

group = "net.glasslauncher"
version = "1.0-SNAPSHOT"

repositories {
    mavenCentral()
}

dependencies {
    testImplementation(platform("org.junit:junit-bom:5.10.0"))
    testImplementation("org.junit.jupiter:junit-jupiter")

    implementation("org.lwjgl:lwjgl:3.3.6")
    implementation("org.lwjgl:lwjgl-opengl:3.3.6")

    lwjgl {
        version = object: Version {
            override val string: String
                get() = "3.3.6" // Why.
        }

        implementation(Lwjgl.Preset.minimalOpenGL)
    }
}

tasks.test {
    useJUnitPlatform()
}