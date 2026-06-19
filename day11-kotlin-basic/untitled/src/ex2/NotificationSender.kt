package ex2

interface NotificationSender {
    fun sendNotification(user: User, message: String)
}