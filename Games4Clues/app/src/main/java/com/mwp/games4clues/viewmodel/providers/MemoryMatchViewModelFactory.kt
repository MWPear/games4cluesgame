import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.mwp.games4clues.viewmodel.HomeViewModel
import com.mwp.games4clues.viewmodel.MemoryMatchViewModel

class MemoryMatchViewModelFactory(
    private val level: Int,
    private val homeViewModel: HomeViewModel
) : ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(MemoryMatchViewModel::class.java)) {
            return MemoryMatchViewModel(level, homeViewModel) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}