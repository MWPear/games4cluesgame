import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.mwp.games4clues.viewmodel.HomeViewModel
import com.mwp.games4clues.viewmodel.MazeViewModel

class MazeViewModelFactory(
    private val mazeMatrix: List<List<Int>>,
    private val homeViewModel: HomeViewModel
) : ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(MazeViewModel::class.java)) {
            return MazeViewModel(mazeMatrix, homeViewModel) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}