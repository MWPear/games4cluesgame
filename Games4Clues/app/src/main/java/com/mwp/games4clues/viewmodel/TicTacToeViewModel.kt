import androidx.lifecycle.ViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.update

class TicTacToeViewModel : ViewModel() {

    private val _state = MutableStateFlow(TicTacToeModel())
    val state: StateFlow<TicTacToeModel> = _state

    private var ticTacToeGame: TicTacToeGame = TicTacToeGame(level = 1) // Default level is 1 (easy)

    fun makePlayerMove(row: Int, col: Int) {
        ticTacToeGame.makePlayerMove(row, col)
        _state.update { ticTacToeGame.getState() }
    }

    fun makeComputerMove() {
        ticTacToeGame.makeComputerMove()
        _state.update { ticTacToeGame.getState() }
    }

    fun resetGame() {
        ticTacToeGame.reset()
        _state.update { ticTacToeGame.getState() }
    }
}
