//The Iterator design pattern is a behavioral pattern that provides a way to access the elements of a collection object sequentially without exposing its underlying representation. 
//This pattern is particularly useful for navigating through complex data structures like lists, trees, or graphs.

//Iterator interface

interface Iterator<T>{
  fun hasNext():Boolean
  fun next():T
}

//Aggregate interface
interface IterableCollection<T>{
  fun createIterator():Iterator<T>
}

//Concrete Iterator
class BookIterator(private val books:List<String>):Iterator<String>{
  private var index=0
  override fun hasNext():Boolean{
    return index<books.size
  }

  override fun next():String{
    if(!hasNext()) throw NoSuchElementException()
    return books[index++]
  }
}

//Concrete aggregation
class BookCollection(private val books:List<String>):IterableCollection<String>{
  override fun createIterator():Iterator<String>{
    return BookIterator(books)
  }
}

//clinet Code
fun main(){
  val books=listOf("Book 1","Books 2","Book 3")
  val bookCollection=BookCollection(books)
  val iterator=bookCollection.createIterator()

  while(iterator.hasNext()){
    println(iterator.next())
  }
}
