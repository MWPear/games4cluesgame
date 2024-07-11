import androidx.lifecycle.ViewModel
import com.mwp.games4clues.model.tictactoe.TicTacToeGameState
import com.mwp.games4clues.model.tictactoe.TicTacToeModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.update

class TicTacToeViewModel : ViewModel() {

    private val _state = MutableStateFlow(TicTacToeGameState())
    val state: StateFlow<TicTacToeGameState> = _state

    private var ticTacToeModel: TicTacToeModel = TicTacToeModel(level = 1) // Default level is 1 (easy)

    fun makePlayerMove(row: Int, col: Int) {
        ticTacToeModel.makePlayerMove(row, col)
        _state.update { ticTacToeModel.getState() }
    }

    fun makeComputerMove() {
        ticTacToeModel.makeComputerMove()
        _state.update { ticTacToeModel.getState() }
    }

    fun resetGame() {
        ticTacToeModel.reset()
        _state.update { ticTacToeModel.getState() }
    }
}
