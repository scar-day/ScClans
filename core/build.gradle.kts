plugins {
    id("de.eldoria.plugin-yml.bukkit") version "0.7.1"
}

repositories {
    maven("https://storehouse.okaeri.eu/repository/maven-public/")
    maven("https://repo.panda-lang.org/releases")
    maven("https://repo.xenondevs.xyz/releases")
}

dependencies {
    implementation(project(":api"))

    implementation("net.kyori:adventure-platform-bukkit:4.3.4")
    implementation("net.kyori:adventure-text-minimessage:4.20.0")

    implementation("eu.okaeri:okaeri-configs-yaml-bukkit:5.0.8")
    implementation("eu.okaeri:okaeri-configs-serdes-bukkit:5.0.8")

    implementation("dev.rollczi:litecommands-bukkit:3.9.7")

    compileOnly("com.zaxxer:HikariCP:6.3.0")
    compileOnly("org.mariadb.jdbc:mariadb-java-client:3.5.3")
    compileOnly("org.xerial:sqlite-jdbc:3.49.1.0")

    implementation("xyz.xenondevs.invui:invui-kotlin:1.45")

    bukkitLibrary("org.xerial:sqlite-jdbc:3.49.1.0")
    bukkitLibrary("org.mariadb.jdbc:mariadb-java-client:3.5.3")
    bukkitLibrary("com.zaxxer:HikariCP:6.3.0")
}

tasks.shadowJar {
    archiveBaseName.set("${rootProject.name}-v${rootProject.version}")
    archiveClassifier.set("")

    relocate("xyz.xenondevs", "dev.scarday.libs.xenondevs")
    relocate("net.kyori", "dev.scarday.libs.kyori")
    relocate("eu.okaeri", "dev.scarday.libs.okaeri")
    relocate("dev.rollczi", "dev.scarday.libs.rollczi")
    relocate("org.intellij", "dev.scarday.libs.intellij")
    relocate("org.jetbrains", "dev.scarday.libs.jetbrains")
}

tasks.compileJava {
    options.compilerArgs.add("-parameters")
}

bukkit {
    main = "${project.group}.${rootProject.name.lowercase()}.${rootProject.name}"
    author = "ScarDay"
}