package com.ma.development.a5oddam_archieve_app.presentation.utils

import android.Manifest
import android.app.Activity
import android.app.AlertDialog
import android.content.Context
import android.content.Intent
import android.content.pm.PackageManager
import android.net.Uri
import android.view.View
import androidx.core.app.ActivityCompat
import androidx.core.app.ActivityCompat.requestPermissions
import androidx.core.view.ViewCompat
import androidx.fragment.app.Fragment
import com.google.android.material.dialog.MaterialAlertDialogBuilder
import com.google.android.material.snackbar.Snackbar
import com.ma.development.a5oddam_archieve_app.R
import com.ma.development.a5oddam_archieve_app.databinding.PhoneSelectionDialogeBinding
import java.text.NumberFormat
import java.util.*

fun convertToArabicNum(num: Int): String {
    val numberFormat = NumberFormat.getInstance(Locale("ar"))
    return numberFormat.format(num)
}

const val CALL_PHONE_DIALOG_TITLE = "الاتصال بالهاتف ؟"
const val DIALOG_POSITIVE_TEXT = "نعم"
const val DIALOG_NEGATIVE_TEXT = "لا"


fun getPhoneCallDialogMessage(name: String): String {
    return "هل تريد الاتصال ب $name"
}

fun makePhoneCall(activity: Activity, phone: String) {
    val intent = Intent(Intent.ACTION_CALL)
    intent.data = Uri.parse("tel:${phone}")
    activity.startActivity(intent)
}

fun showPhoneCallingDialog(
    activity: Activity,
    phone: String,
    phone2: Int?,
    receiverName: String
) {
    val dialog = MaterialAlertDialogBuilder(activity)
        .setTitle(CALL_PHONE_DIALOG_TITLE)
        .setMessage(getPhoneCallDialogMessage(receiverName))
        .setIcon(R.drawable.ic_baseline_local_phone_24)
        .setPositiveButton(DIALOG_POSITIVE_TEXT) { _, _ ->
            phone2?.let {
                showTwoPhoneSelectionDialog(activity, phone, "0$it")
            } ?: run { makePhoneCall(activity, phone) }
        }.setNegativeButton(DIALOG_NEGATIVE_TEXT) { dialogInterface, _ ->
            dialogInterface.cancel()
        }.create()
    dialog.window?.let {
        ViewCompat.setLayoutDirection(it.decorView, ViewCompat.LAYOUT_DIRECTION_RTL)
    }
    dialog.show()
}

fun showTwoPhoneSelectionDialog(
    activity: Activity,
    phone1: String,
    phone2: String
) {
    val binding = PhoneSelectionDialogeBinding.inflate(activity.layoutInflater)
    binding.phone1Num.text = phone1
    binding.phone2Num.text = phone2
    val dialog = AlertDialog.Builder(activity).apply {
        setView(binding.root)
    }.create()

    dialog.show()

    binding.phone1Icon.setOnClickListener {
        makePhoneCall(activity, phone1)
        dialog.dismiss()
    }
    binding.phone2Icon.setOnClickListener {
        makePhoneCall(activity, phone2)
        dialog.dismiss()

    }
}

fun checkPhoneCallPermission(context: Context) = ActivityCompat.checkSelfPermission(
    context,
    Manifest.permission.CALL_PHONE
) != PackageManager.PERMISSION_GRANTED

fun requestPermission(fragment: Fragment) {
    fragment.requestPermissions(
        arrayOf(Manifest.permission.CALL_PHONE),
        0
    )
}


fun showSnackBar(view: View, message: String, duration: Int = Snackbar.LENGTH_SHORT) {
    val snackbar = Snackbar.make(
        view,
        message,
        duration
    )
    ViewCompat.setLayoutDirection(snackbar.view, ViewCompat.LAYOUT_DIRECTION_RTL)
    snackbar.show()
}