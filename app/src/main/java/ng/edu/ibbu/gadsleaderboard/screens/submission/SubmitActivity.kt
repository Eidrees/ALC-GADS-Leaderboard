package ng.edu.ibbu.gadsleaderboard.screens.submission

import android.annotation.SuppressLint
import android.app.Dialog
import android.os.Bundle
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import kotlinx.android.synthetic.main.activity_submit.*
import kotlinx.android.synthetic.main.content_submit.*
import kotlinx.android.synthetic.main.layout_confirm_dialogue.*
import kotlinx.android.synthetic.main.layout_status_dialog.*
import kotlinx.coroutines.flow.callbackFlow
import ng.edu.ibbu.gadsleaderboard.R
import ng.edu.ibbu.gadsleaderboard.network.ApiStatus
import ng.edu.ibbu.gadsleaderboard.network.Network
import retrofit2.Callback

class SubmitActivity : AppCompatActivity() {

    private val viewModel: SubmissionActivityViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContentView(R.layout.activity_submit)
        setSupportActionBar(findViewById(R.id.toolbar))

        viewModel.status.observe(this, Observer {
            if (it == ApiStatus.DONE) {
                showStatusDialog(false)
            } else if (it == ApiStatus.FAILED) {
                showStatusDialog(true)
            }
        })

        ivBack.setOnClickListener {
            this.finish()
        }

        btnSubmit.setOnClickListener {
            showConfirmDialog()
        }

    }

    @SuppressLint("UseCompatLoadingForDrawables")
    private fun showStatusDialog(isError: Boolean) {

        val dialog = Dialog(this)
        dialog.setContentView(R.layout.layout_status_dialog)

        if (isError) {
            dialog.ivStatusImage.setImageDrawable(
                resources.getDrawable(
                    R.drawable.ic_baseline_warning_24,
                    null
                )
            )
            dialog.tvStatusText.text = "Submission not Successfull"
        }

        dialog.show()

    }

    private fun showConfirmDialog() {

        val firstName = txtFirstName.text.toString()
        val lastName = txtLastName.text.toString()
        val email = txtEmail.text.toString()
        val githubUrl = txtGitLink.text.toString()

        if (firstName.isEmpty()) {
            txtFirstName.setError("Field is required")
            return
        }
        if (lastName.isEmpty()) {
            txtLastName.setError("Field is required")
            return
        }
        if (email.isEmpty()) {
            txtEmail.setError("Field is required")
            return
        }
        if (firstName.isEmpty()) {
            txtGitLink.setError("Field is required")
            return
        }

        val dialog = Dialog(this)
        dialog.setContentView(R.layout.layout_confirm_dialogue)
        dialog.tvYes.setOnClickListener {
            viewModel.submit(email, firstName, lastName, githubUrl)

            dialog.dismiss()
        }
        dialog.ivClose.setOnClickListener {
            dialog.dismiss()
        }

        dialog.show()

    }
}