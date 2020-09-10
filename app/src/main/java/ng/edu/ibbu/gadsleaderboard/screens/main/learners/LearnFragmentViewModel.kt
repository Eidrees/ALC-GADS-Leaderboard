package ng.edu.ibbu.gadsleaderboard.screens.main.learners

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch
import ng.edu.ibbu.gadsleaderboard.network.ApiStatus
import ng.edu.ibbu.gadsleaderboard.network.Learner
import ng.edu.ibbu.gadsleaderboard.network.Network

class LearnFragmentViewModel(application: Application) : AndroidViewModel(application) {

    private val viewModelJob = Job()
    private val uiScope = CoroutineScope(viewModelJob + Dispatchers.Main)

    private val _data = MutableLiveData<List<Learner>>()
    private val _status = MutableLiveData<ApiStatus>()

    val topLearners: LiveData<List<Learner>>
        get() = _data


    fun fetchLearners() {
        _status.value = ApiStatus.LOADING
        uiScope.launch {

            val request = Network.gadsService.getLearningLeaders()
            try {
                val response = request.await()
                _status.value = ApiStatus.DONE
                _data.value = response
            } catch (t: Throwable) {
                t.printStackTrace()
                _status.value = ApiStatus.FAILED
            }
        }
    }


    override fun onCleared() {
        super.onCleared()
        viewModelJob.cancel()
    }
}