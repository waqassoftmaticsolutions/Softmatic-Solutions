//Single Responsibility Principle
class User {
  var username: String? = "waqas"
  var password: String? = "123"

  fun printData() {
    println("Username is : $username")
    println("Password is : $password")
  }
}

class Authentication(val userList: List<User>) {
  fun authenticateUser(username: String, password: String): Boolean {
    for (i in userList) {
      if (i.username == username && i.password == password) {
        return true
      }
    }
    return false
  }
}

fun main() {
  val user1 =
      User().apply {
        this.username = "waqas"
        this.password = "123"
      }
  val user2 =
      User().apply {
        this.username = "ehtisham"
        this.password = "12345"
      }
  val users = listOf(user1,user2)
  for (i in users){
    i.printData()
  }
  val authentication = Authentication(users)
  val result = authentication.authenticateUser("waqas", "1234")
  if(result){
    println("User is authenticated")
  }else{
    println("User is not authenticated")
  }
}
