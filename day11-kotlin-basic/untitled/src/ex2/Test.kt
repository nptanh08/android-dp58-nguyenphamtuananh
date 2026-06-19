package ex2

class Test {
    fun main() {
        val user = User("U01", "Anh")

        val taskService = TaskServices(ConsoleSender())
        println(
            "Task 1: ${
                taskService.createTask(
                    user,
                    "T01",
                    "Học Kotlin"
                )
            }"
        )

        println(
            "Task 2: ${
                taskService.createTask(
                    user,
                    "T01",
                    "Ca hát"
                )
            }"
        )

        println(
            "Task 3: ${
                taskService.createTask(
                    user,
                    "T02",
                    ""
                )
            }"
        )

        println(
            "Task 4: ${
                taskService.createTask(
                    user,
                    "T03",
                    "Ăn chơi"
                )
            }"
        )

        taskService.listOpenTask().forEach {
            println("${it.id} - ${it.title}")
        }
        println("T01: ${taskService.markDone("T01")}")
        println("T99: ${taskService.markDone("T99")}")

        taskService.listOpenTask().forEach {
            println("${it.id} - ${it.title}")
        }
    }
}