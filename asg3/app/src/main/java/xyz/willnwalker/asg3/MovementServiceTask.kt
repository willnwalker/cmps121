package xyz.willnwalker.asg3

class MovementServiceTask: Runnable {

    private lateinit var resultCallback: ResultCallback
    private var running: Boolean = false
    private var moved: Boolean = false

    override fun run() {
        running = true
        while (running){
            Thread.sleep(30000)
        }
    }

    fun stopProcessing(){
        running = false
    }

    fun didItMove() {
        val result = MovementServiceResult()
        result.moved = moved
        resultCallback.onResultReady(result)
    }

    fun updateResultCallback(callback: ResultCallback) {
        resultCallback = callback
    }


    interface ResultCallback {
        fun onResultReady(result: MovementServiceResult)
    }

}