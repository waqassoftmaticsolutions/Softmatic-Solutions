//provides an interface for creating objects in a superclass, but allows subclasses to alter the type of objects that will be created.


//Common interface
interface Mobile{
    fun creation(message:String)
}
//Concrete Products
class Samsung:Mobile{
    override fun creation(message:String){
        println("Samsung $message")
    }
    
}
class OnePlus:Mobile{
    override fun creation(message:String){
        println("OnePlus $message ")
    }

}

//Abstract Factory
abstract class MobileFactory(){
    abstract fun createMobile() :Mobile
}

class SamsungMobileFactory():MobileFactory(){
    override fun createMobile():Mobile=Samsung()
}

class OnePlusMobileFactory():MobileFactory(){
    override fun createMobile():Mobile=OnePlus()
}

fun main(){
    val mobileType="Samsun"
    val mobileFactory:MobileFactory = when(mobileType){
        "Samsung" -> SamsungMobileFactory()
        "OnePlus" -> OnePlusMobileFactory()
        else->throw IllegalArgumentException("Not available this type of mobile at the moment")
    }
    val mobile=mobileFactory.createMobile()
    mobile.creation("Mobile is created")
}



