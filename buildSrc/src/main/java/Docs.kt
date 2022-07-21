import org.gradle.api.Project
import org.gradle.api.tasks.Copy
import org.gradle.api.tasks.Sync
import org.gradle.kotlin.dsl.configure
import org.gradle.kotlin.dsl.provideDelegate
import org.gradle.kotlin.dsl.register
import java.io.File

object Docs {

    operator fun invoke(block: Docs.() -> Unit) {
        this.block()
    }

    fun Project.useEmptyJavadoc() {

        val VERSION_NAME: String by project

        tasks.findByName("dokkaHtml")?.let {
            it.enabled = false
        }

        tasks.register<Copy>("useEmptyJavadoc") {
            val fromDir = rootProject.projectDir
            val intoDir = File(buildDir, "/libs")
            val targetFile = "${project.name}-$VERSION_NAME-javadoc.jar"

//            println("useEmptyJavadoc")
//            println("from: $fromDir")
//            println("into: $intoDir")
//            println("targetFile: $targetFile")

            from(fromDir)
            into(intoDir)

            include("empty-javadoc.jar")
            rename("empty-javadoc.jar", targetFile)
        }
    }

    fun Project.distributeJsExample() {

        val taskName = "distributeJsExample"

        tasks.named("build") { finalizedBy(taskName) }

        tasks.register<Sync>(taskName) {
            dependsOn("assemble")
            dependsOn("browserProductionWebpack")

            val fromDir = File(buildDir, "distributions")
            val intoDir = File(rootProject.projectDir, "docs/examples/${project.name}")

            println("== distributeJsExample '${project.name}' ===========================================")
            println("from: $fromDir")
            println("into: $intoDir")

            from(fromDir)
            into(intoDir)
        }
    }
}
