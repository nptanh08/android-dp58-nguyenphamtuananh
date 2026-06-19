package ex2

class TaskServices(private val notificationSender: NotificationSender) {
    private val listTask = mutableListOf<Task>()

    fun createTask(user: User, id: String, title: String): Boolean {
        if (title.isEmpty()) {
            return false
        }
        if (findTask(id)) return false
        listTask.add(Task(id, title))
        return true
    }

    fun markDone(id: String): Boolean {
        if (!findTask(id)) return false
        listTask.forEach {
            if (it.id == id) {
                it.markDone()
            }
        }
        return true
    }

    fun listOpenTask(): List<Task> {
        val list = listTask.filter { !it.isComplete() }
        return list
    }

    fun findTask(id: String): Boolean {
        listTask.forEach {
            if (it.id == id) {
                return false
            }
        }
        return true
    }
}