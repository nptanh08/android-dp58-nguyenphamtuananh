package ex2

class EmailSender:NotificationSender {
    override fun sendNotification(user: User, message: String) {
        println("gui email den user ${user.name}: $message")

    }
}