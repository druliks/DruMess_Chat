package ru.druliks.drumesschat.ui.account

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.view.View
import com.bumptech.glide.Glide
import kotlinx.android.synthetic.main.fragment_account.*
import ru.druliks.drumesschat.R
import ru.druliks.drumesschat.domain.account.AccountEntity
import ru.druliks.drumesschat.presentation.viewmodel.AccountViewModel
import ru.druliks.drumesschat.presentation.viewmodel.MediaViewModel
import ru.druliks.drumesschat.ui.App
import ru.druliks.drumesschat.ui.core.BaseFragment
import ru.druliks.drumesschat.ui.core.GlideHelper
import ru.druliks.drumesschat.ui.core.ext.onFailure
import ru.druliks.drumesschat.ui.core.ext.onSuccess

class AccountFragment : BaseFragment() {
    override val layoutId = R.layout.fragment_account

    override val titleToolbar = R.string.screen_account

    lateinit var accountViewModel: AccountViewModel
    lateinit var mediaViewModel: MediaViewModel

    var accountEntity: AccountEntity? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        App.appComponent.inject(this)

        accountViewModel = viewModel {
            onSuccess(accountData, ::handleAccount)
            onSuccess(editAccountData, ::handleEditingAccount)
            onFailure(failureData, ::handleFailure)
        }

        mediaViewModel = viewModel {
            onSuccess(cameraFileCreatedData, ::onCameraFileCreated)
            onSuccess(pickedImageData, ::onImagePicked)
            onSuccess(progressData, ::updateProgress)
            onFailure(failureData, ::handleFailure)
        }
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)

        mediaViewModel.onPickImageResult(requestCode, resultCode, data)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        showProgress()

        accountViewModel.getAccount()

        btnEdit.setOnClickListener {
            view.clearFocus()
            val fieldsValid = validateFields()
            if (!fieldsValid) {
                return@setOnClickListener
            }

            val passwordsValid = validatePasswords()
            if (!passwordsValid) {
                return@setOnClickListener
            }

            showProgress()

            val email = etEmail.text.toString()
            val name = etName.text.toString()
            val status = etStatus.text.toString()
            val password = etNewPassword.text.toString()

            accountEntity?.let {
                it.email = email
                it.name = name
                it.status = status

                if (password.isNotEmpty()) {
                    it.password = password
                }

                accountViewModel.editAccount(it)
            }
        }

        imgPhoto.setOnClickListener {
            base {
                navigator.showPickFromDialog(this) { fromCamera ->
                    if (fromCamera) {
                        mediaViewModel.createCameraFile()
                    } else {
                        navigator.showGallery(this)
                    }
                }
            }
        }
    }

    private fun validatePasswords(): Boolean {
        val currentPassword = etCurrentPassword.text.toString()
        val newPassword = etNewPassword.text.toString()

        return if (currentPassword.isNotEmpty() && newPassword.isNotEmpty()) {
            return if (currentPassword == accountEntity?.password) {
                true
            } else {
                showMessage(getString(R.string.error_wrong_password))
                false
            }
        } else if (currentPassword.isEmpty() && newPassword.isEmpty()) {
            true
        } else {
            showMessage(getString(R.string.error_empty_password))
            false
        }
    }

    private fun validateFields(): Boolean {
        hideSoftKeyboard()
        val allFields = arrayOf(etEmail, etName)
        var allValid = true
        for (field in allFields) {
            allValid = field.testValidity() && allValid
        }
        return allValid
    }

    private fun handleAccount(account: AccountEntity?) {
        hideProgress()
        accountEntity = account
        account?.let {
            GlideHelper.loadImage(requireActivity(), it.image, imgPhoto)
            etEmail.setText(it.email)
            etName.setText(it.name)
            etStatus.setText(it.status)

            etCurrentPassword.setText("")
            etNewPassword.setText("")
        }
    }


    private fun onCameraFileCreated(uri: Uri?) {
        base {
            if (uri != null) {
                navigator.showCamera(this, uri)
            }
        }
    }

    private fun onImagePicked(pickedImage: MediaViewModel.PickedImage?) {
        if (pickedImage != null) {
            accountEntity?.image = pickedImage.string

            val placeholder = imgPhoto.drawable
            Glide.with(this)
                .load(pickedImage.bitmap)
                .placeholder(placeholder)
                .error(placeholder)
                .into(imgPhoto)
        }
    }

    private fun handleEditingAccount(account: AccountEntity?) {
        showMessage(getString(R.string.success_edit_user))
        accountViewModel.getAccount()
    }


    private fun updateProgress(progress: Boolean?) {
        if (progress == true) {
            groupProgress.visibility = View.VISIBLE
        } else {
            groupProgress.visibility = View.GONE
        }
    }
}