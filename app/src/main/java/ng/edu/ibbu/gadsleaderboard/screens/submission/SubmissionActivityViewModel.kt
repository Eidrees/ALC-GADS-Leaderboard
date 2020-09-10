package ng.edu.ibbu.gadsleaderboard.screens.submission

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
import retrofit2.await

class SubmissionActivityViewModel(application: Application) : AndroidViewModel(application) {

    private val viewModelJob = Job()
    private val uiScope = CoroutineScope(viewModelJob + Dispatchers.Main)

    private val _status = MutableLiveData<ApiStatus>()

    val status: LiveData<ApiStatus>
        get() = _status


    fun submit(email: String, firstName: String, lastName: String, gitHubLink: String) {
        _status.value = ApiStatus.LOADING
        uiScope.launch {

            val request = Network.submissionService.submit(email, firstName, lastName, gitHubLink)
            try {
                val response = request.await()
                _status.value = ApiStatus.DONE
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