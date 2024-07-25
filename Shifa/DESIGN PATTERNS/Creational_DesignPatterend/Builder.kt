//Builder pattern builds a complex object using simple objects and using a step by step approach. 

//It has methods for building different parts of the product 

//Product
class Computer{
  
  var cpu:String=""
  var ram:String=""
  var storage:String=""
  var porttype=""


  constructor(builder:Builder){
    this.cpu=builder.cpu
    this.ram=builder.ram
    this.storage=builder.storage
    this.porttype=builder.porttype
  }


  fun displayInfo(){
    println("Compuer configuarations : ")
    println("CPU : $cpu")
    println("RAM : $ram")
    println("Storage : $storage")
    println("Port Type is : $porttype")
    
  }

}
//Builder
abstract class Builder{
  var cpu:String=""
  var ram:String=""
  var storage:String=""
  var porttype=""

  


  fun setCPU(cpu:String):Builder=apply{
    this.cpu=cpu
    return this
  }
  fun setRAM(ram:String):Builder=apply{
    this.ram=ram
    return this
  }
  
  fun setStorage(storage:String):Builder=apply{
    this.storage=storage
    return this
  }

  open fun setPorttype():Builder{
    return this
  }

  fun build():Computer{
    return Computer(this)
  }
}

//Concrete Builder 1
class WindowsComputer:Builder(){
  override fun setPorttype():Builder{
    this.porttype="Regular"
    return this
  }
}

//Concrete Builder 2
class MacComputer:Builder(){
  override fun setPorttype():Builder{
    this.porttype="C-TYPE"
    return this
  }
}

//Director
class ComputerDirector{
  var builder:Builder?=null;
  fun createComputer():Computer{
    return builder!!.build()
  }
}

fun main(){
  
    val director = ComputerDirector()
    director.builder = WindowsComputer().setCPU("Gaming CPU").setRAM("16GB").setStorage("1TB").setPorttype()
    val computer1 = director.createComputer()
    computer1.displayInfo()

    val director2 = ComputerDirector()
    director2.builder = MacComputer().setCPU("High Power CPU").setRAM("32GB").setStorage("1TB").setPorttype()
    val computer2 = director2.createComputer()
    computer2.displayInfo()
  
}


