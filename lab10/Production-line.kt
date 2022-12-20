import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.*
import kotlin.random.Random


fun main(args: Array<String>) = runBlocking{
    val bufferSize=12
    val maxBuffer=3

    val numberOfProducers=4
    val numberOfConsumers=2
    val numberOfProcess=3

    val buffer =Array<Channel<Int>>(bufferSize) { i ->
        val newChannel=Channel<Int>(1)
        newChannel.send(-1)
        newChannel
    }

    fun printBufferChange(index:Int,newValue:Int){
        print("[")
        for(i in 0 until bufferSize-1){
            if (i!=index) print("\t-\t,")
            else print("\t$newValue\t,")
        }
        if (bufferSize-1!=index) print("\t-\t]")
        else print("\t$newValue\t]")
    }

    suspend fun producer(name: String) {
        var i=0
        while (true){
            val value=buffer[i].receive()
            if(value==-1){
                buffer[i].send(0)
                printBufferChange(i,0)
                println("Producer $name made item at index $i")
                repeat(Random.nextInt(0,20)){ delay(100L) }
                i=(i+1)%bufferSize
            }
            else{
                buffer[i].send(value)
            }
            repeat(Random.nextInt(0,20)){ delay(100L) }
        }
    }

    suspend fun consumer(name: String) {
        var i=0
        while (true){
            val value=buffer[i].receive()
            if(value==maxBuffer){
                buffer[i].send(-1)
                printBufferChange(i,-1)
                println("Consumer $name receive item at index $i")
                repeat(Random.nextInt(0,20)){ delay(100L) }
                i=(i+1)%bufferSize
            }
            else{
                buffer[i].send(value)
            }
            repeat(Random.nextInt(0,20)){ delay(100L) }
        }
    }

    suspend fun processor(name: String) {
        var i=0
        while (true){
            val value=buffer[i].receive()
            if(value!=-1 && value !=maxBuffer){
                buffer[i].send(value+1)
                printBufferChange(i,value+1)
                println("Processor $name precessed item at index $i from value $value to "+(value+1))
                i=(i+1)%bufferSize
            }
            else{
                buffer[i].send(value)
            }
            repeat(Random.nextInt(0,20)){ delay(100L) }
        }
    }

    for(i in 0 until numberOfProducers){
        launch { producer(i.toString()) }
    }
    for(i in 0 until numberOfProcess){
        launch { processor(i.toString()) }
    }
    for(i in 0 until numberOfConsumers){
        launch { consumer(i.toString()) }
    }
}