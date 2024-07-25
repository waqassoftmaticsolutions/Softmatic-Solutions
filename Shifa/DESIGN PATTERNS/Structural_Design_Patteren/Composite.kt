// Component
interface FileSystemElement {
    fun display(indent: String = "")
}

// Leaf
class File(private val name: String) : FileSystemElement {
    override fun display(indent: String) {
        println("$indent- $name")
    }
}

// Composite
class Directory(private val name: String) : FileSystemElement {
    private val elements = mutableListOf<FileSystemElement>()

    fun add(element: FileSystemElement) {
        elements.add(element)
    }

    fun remove(element: FileSystemElement) {
        elements.remove(element)
    }

    override fun display(indent: String) {
        println("$indent+ $name")
        elements.forEach { it.display(indent + "  ") }
    }
}

// Client
fun main() {
    val root = Directory("root")
    val file1 = File("file1.txt")
    val file2 = File("file2.txt")

    val subDirectory = Directory("subdir")
    val file3 = File("file3.txt")

    root.add(file1)
    root.add(file2)
    root.add(subDirectory)

    subDirectory.add(file3)

    root.display()
}
