// Class to represent a book
class Book(var id: Int, var title: String, var author: String)

// Class to manage books
class BookManager(private val books: MutableList<Book>) {
    fun addBook(title: String, author: String) {
        val book = Book(books.size + 1, title, author)
        books.add(book)
        println("Book added: $title by $author")
    }
}

// Class to print book details
class BookPrinter(private val books: List<Book>) {
    fun printBookDetails(id: Int) {
        val book = books.find { it.id == id }
        if (book != null) {
            println("Book ID: ${book.id} has title: ${book.title} and written by ${book.author}")
        } else {
            println("Does not have this book")
        }
    }
}

// Main function to test the classes
fun main() {
    val books = mutableListOf<Book>()

    val bookManager = BookManager(books)
    bookManager.addBook("1984", "George Orwell")
    bookManager.addBook("Brave New World", "Aldous Huxley")

    val bookPrinter = BookPrinter(books)
    bookPrinter.printBookDetails(1)
    bookPrinter.printBookDetails(2)
}
