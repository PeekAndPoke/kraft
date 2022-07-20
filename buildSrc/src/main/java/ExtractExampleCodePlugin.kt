import org.gradle.api.Plugin
import org.gradle.api.Project
import java.io.File

class ExtractExampleCodePlugin : Plugin<Project> {

    private data class Entry(
        val file: File,
        val prefix: String,
        val name: String,
        val content: String,
    )

    override fun apply(target: Project) {

//        val ktFiles = FileUtils.listFiles(
//            target.projectDir,
//            new RegexFileFilter("^(.*?)\.kt"),
//            DirectoryFileFilter.DIRECTORY
//        );

        val sourcesDir = "/src/main/kotlin"
        val outputDir = "/build/generated/extracted-code-blocks"
        val outputFileName = "extracted-code-blocks.kt"

        val projectDir = target.projectDir.absoluteFile

        val ktFiles = projectDir.walkTopDown()
            .filter { it.isFile && it.extension == "kt" }

        val startExampleRegex = "// <CodeBlock ([^>]+)>(.*?)".toRegex()

        val entries = ktFiles.flatMap { file ->

            val entryPrefix = file.absoluteFile.toString()
                .replaceFirst(projectDir.absolutePath, "")
                .replaceFirst(sourcesDir, "")
                .replace("[^a-zA-Z0-9]+".toRegex(), "_")
                .trim('_')

            val fileContent = file.readText()
            val matches = startExampleRegex.findAll(fileContent)

//            println("found file: ${file.absolutePath}")

            matches.mapNotNull { match ->
                val name = match.groupValues[1]
                val startIdx = match.range.last
                val endIdx = fileContent.indexOf("// </CodeBlock>", startIdx)

                if (endIdx != -1) {
                    val content = fileContent.substring(startIdx, endIdx)
                        .lines()
                        .drop(1)
                        .dropLast(1)
                        .joinToString("\n")
                        .trimIndent()

                    Entry(file = file, prefix = entryPrefix, name = name, content = content)
                } else {
                    null
                }
            }
        }

        entries.forEach { entry ->
            println("==== Found entry ${entry.prefix} ${entry.name} ================================")
            println(entry.content)
        }

        val examplesCodeFile = StringBuilder().apply {

            appendLine("@file:Suppress(\"unused\", \"PackageDirectoryMismatch\")")
            appendLine("package generated")
            appendLine()

            appendLine("object ExtractedCodeBlocks {")

            entries.forEach { entry ->
                appendLine("    const val ${entry.prefix}_${entry.name} = \"\"\"")
                append(entry.content)
                appendLine("\"\"\"")
                appendLine()
            }

            appendLine("}")
        }

        val exampleFileContent = examplesCodeFile.toString()

        val targetDir = File(projectDir, outputDir).also {
            if (!it.exists()) {
                it.mkdirs()
            }
        }

        val targetFile: File = File(targetDir, outputFileName)

        targetFile.writeText(exampleFileContent)
    }
}
