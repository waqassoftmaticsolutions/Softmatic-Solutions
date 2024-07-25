//The Singleton Design Pattern ensures that a class has only one instance and provides a global point of access to that instance. 
//It is commonly used for managing global states, shared resources, or configurations where only one instance is needed throughout the application's lifecycle.

class Singleton private constructor(private val data:String){
    companion object{
        
        private var instance :Singleton?=null;

        fun getInstance(data:String) :Singleton{
            if(instance==null){
                instance=Singleton(data)
            }
            return instance!!
        }
    }
    fun doSomething(){
        println("Instance is  $data")
    }
    
}
fun main(){
    var instance=Singleton.getInstance("Created successfully")
    instance.doSomething()
}
