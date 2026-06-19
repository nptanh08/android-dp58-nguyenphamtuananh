package ex2

class ConsoleSender : NotificationSender {
    override fun sendNotification(user: User, message: String) {
        println(" gui den ${user.name}: $message")
    }
}