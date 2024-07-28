// State Interface
interface State {
    fun play(player: Player)
    fun pause(player: Player)
    fun stop(player: Player)
}

// Concrete States
class PlayingState : State {
    override fun play(player: Player) {
        println("Player is already playing.")
    }

    override fun pause(player: Player) {
        println("Player is pausing.")
        player.state = PausedState()
    }

    override fun stop(player: Player) {
        println("Player is stopping.")
        player.state = StoppedState()
    }
}

class PausedState : State {
    override fun play(player: Player) {
        println("Player is resuming.")
        player.state = PlayingState()
    }

    override fun pause(player: Player) {
        println("Player is already paused.")
    }

    override fun stop(player: Player) {
        println("Player is stopping from paused state.")
        player.state = StoppedState()
    }
}

class StoppedState : State {
    override fun play(player: Player) {
        println("Player is starting.")
        player.state = PlayingState()
    }

    override fun pause(player: Player) {
        println("Player is already stopped and cannot pause.")
    }

    override fun stop(player: Player) {
        println("Player is already stopped.")
    }
}

// Context
class Player {
    var state: State = StoppedState()

    fun play() {
        state.play(this)
    }

    fun pause() {
        state.pause(this)
    }

    fun stop() {
        state.stop(this)
    }
}

// Main Function
fun main() {
    val player = Player()

    player.play()   
    player.pause()  
    player.play()   
    player.stop()
    player.pause() 
}
