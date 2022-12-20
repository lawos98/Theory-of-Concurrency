import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.*
import kotlin.random.Random


fun main(args: Array<String>) = runBlocking{
    val bufferSize=4
    val numberOfProducers=10
    val numberOfConsumers=2

    val sender= Channel<String>(bufferSize)

    suspend fun producer(name: String) {
        while (true){
            sender.send("producer: $name")
            println("Producer $name made item")
            repeat(Random.nextInt(0,200)){
                delay(100L)
            }
        }
    }

    suspend fun consumer(name: String) {
        while (true) {
            val msg = sender.receive()
            println("Consumer $name get item made by $msg")
            repeat(Random.nextInt(0, 200)) {
                delay(100L)
            }
        }
    }

    for(i in 0 until numberOfProducers){
        launch { producer(i.toString()) }
    }
    for(i in 0 until numberOfConsumers){
        launch { consumer(i.toString()) }
    }
}