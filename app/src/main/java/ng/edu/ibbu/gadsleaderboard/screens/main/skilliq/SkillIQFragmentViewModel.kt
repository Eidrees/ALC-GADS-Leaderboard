package ng.edu.ibbu.gadsleaderboard.screens.main.skilliq

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch
import ng.edu.ibbu.gadsleaderboard.network.ApiStatus
import ng.edu.ibbu.gadsleaderboard.network.LearnerSkillIQ
import ng.edu.ibbu.gadsleaderboard.network.Network

class SkillIQFragmentViewModel(application: Application) : AndroidViewModel(application) {


    private val viewModelJob = Job()
    private val uiScope = CoroutineScope(viewModelJob + Dispatchers.Main)

    private val _data = MutableLiveData<List<LearnerSkillIQ>>()
    private val _status = MutableLiveData<ApiStatus>()

    val topLearners: LiveData<List<LearnerSkillIQ>>
        get() = _data


    fun fetchLearnersBySkillIQ() {
        _status.value = ApiStatus.LOADING
        uiScope.launch {

            val request = Network.gadsService.getSkillLeaders()
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