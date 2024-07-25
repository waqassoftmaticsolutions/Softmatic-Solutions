// Structural design patterns in software engineering focus on how classes and objects are composed to form larger structures while maintaining flexibility and efficiency. These patterns typically deal with relationships between objects, simplifying the design and making the system more manageable.
// 1) Adaptor
// 2) Bridge
// 3) Composite
// 4) Decorator
// 5) Facade
// 6) Flyweight
// 7)Proxy


//Composite --> It is a structural design pattern that lets you compose objects into tree structures and then work with these structures as if they were individual objects.

interface EmployeeComponent{
  fun showDetails()
}

data class Employee(private val name: String, private val position:String):EmployeeComponent{
  override fun showDetails(){
    println("Employee Name is $name and Postion is $position")
  }
}

class Department(private val depName:String):EmployeeComponent{
  private val employeeList = mutableListOf<EmployeeComponent>()
  fun addEmployee(employee: EmployeeComponent) {
      employeeList.add(employee)
  }
  fun removeEmployee(employee: EmployeeComponent) {
      employeeList.remove(employee)
  }
  override fun showDetails(){
    println("Department: $depName")
    employeeList.forEach { it.showDetails() }
  }
}

fun main() {
  val itDepartment = Department("IT")
  val employee1 = Employee("Waqas", "Android Developer")
  val employee2 = Employee("Ehtisham", "IOS Developer")

  itDepartment.addEmployee(employee1)
  itDepartment.addEmployee(employee2)

  // Show details of the Department and its employees
  itDepartment.showDetails()
}
