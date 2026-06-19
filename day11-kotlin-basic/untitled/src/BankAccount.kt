//Xây class `BankAccount` với yêu cầu:
//
//1. Constructor: `owner`, `initialBalance`.
//2. Nếu `initialBalance  thì đưa về `0.0`.
//3. `deposit(amount)` trả về `Boolean`.
//4. `withdraw(amount)` trả về `Boolean`.
//5. `getBalance()` và `statement()`.
//
//Rule bắt buộc
//1. Không cho amount <= 0.
//2. Không cho rút quá số dư.
//3. Không expose setter cho balance.
class BankAccount(
    private var owner:String,
    private var initialBalance: Double
) {
    private var balance = if (initialBalance > 0.0) initialBalance else 0.0
    fun deposit(amount: Double): Boolean{
        if (amount <=0.0) return false
        balance += amount
        return true
    }
    fun withdraw(amount: Double): Boolean{
        if (amount <= 0 || amount > balance) {
            return false
        }
        balance -= amount
        return true
    }

     fun statement(): String {
        return "BankAccount(owner='$owner', balance=$balance)"
    }
    fun getBalance() = balance

}


//
//fun main() {
////    initialBalance = -5 thì balance = 0.
////    deposit(100) thành công.
////    withdraw(30) thành công.
////    withdraw(100) thất bại nếu số dư không đủ.
////    deposit(0) thất bại.
//    val bank: BankAccount = BankAccount("a",-1.0)
//    bank.getBalance()
//    println(bank.deposit(5000.0))
//    println(bank.withdraw(3000.0))
//    println(bank.statement())
//    println(bank.deposit(0.0))
//    println(bank.withdraw(10000.0))
//}