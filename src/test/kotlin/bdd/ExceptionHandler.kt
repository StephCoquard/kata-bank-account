package bdd

class ExceptionHandler {
    private val exceptions: MutableList<Exception> = ArrayList()

    fun add(e: Exception) {
        exceptions.add(e)
    }

    fun getExceptions(): List<Exception> {
        return exceptions
    }
}