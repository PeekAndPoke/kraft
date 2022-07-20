import org.gradle.api.Project
import org.gradle.api.tasks.Copy
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

//            println("from: $fromDir")
//            println("into: $intoDir")
//            println("targetFile: $targetFile")

            from(fromDir)
            into(intoDir)

            include("empty-javadoc.jar")
            rename("empty-javadoc.jar", targetFile)
        }
    }
}
