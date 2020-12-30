package com.e.docinfamily17nov.Utils

import android.content.Context
import android.content.DialogInterface
import android.graphics.PorterDuff
import android.os.Build
import android.view.View
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.view.ContextThemeWrapper
import com.e.docinfamily17nov.R

import com.google.android.material.snackbar.Snackbar
import java.lang.ref.WeakReference

object ViewUtils {
    fun showMessage(context: Context, message: String?) {
        try {
            val contextWeakReference =
                WeakReference(context)
            val toast =
                Toast.makeText(contextWeakReference.get(), message, Toast.LENGTH_SHORT)
            val view = toast.view
            //Gets the actual oval background of the Toast then sets the colour filter
            view.background.setColorFilter(
                getColorWrapper(
                    context,
                    R.color.white_black
                ), PorterDuff.Mode.SRC_IN
            )

//Gets the TextView from the Toast so it can be editted
            val text = view.findViewById<TextView>(android.R.id.message)
            //    text.setCompoundDrawablesWithIntrinsicBounds(R.drawable.ic_notification_icon, 0, 0, 0);
            text.setTextColor(
                getColorWrapper(
                    context,
                    R.color.white
                )
            )
            text.compoundDrawablePadding =
                context.resources.getDimensionPixelSize(R.dimen.padding_toast)
            toast.show()
        } catch (e: Exception) {
            e.printStackTrace()
            println("toast error")
        }
    }

    fun showMessagewithCustomTime(
        context: Context,
        message: String?
    ) {
        try {
            val contextWeakReference =
                WeakReference(context)
            val toast =
                Toast.makeText(contextWeakReference.get(), message, Toast.LENGTH_LONG)
            val view = toast.view
            //Gets the actual oval background of the Toast then sets the colour filter
            view.background.setColorFilter(
                getColorWrapper(
                    context,
                    R.color.black
                ), PorterDuff.Mode.SRC_IN
            )

//Gets the TextView from the Toast so it can be editted
            val text = view.findViewById<TextView>(android.R.id.message)
            // text.setCompoundDrawablesWithIntrinsicBounds(R.drawable.ic_notification_icon, 0, 0, 0);
            text.setTextColor(
                getColorWrapper(
                    context,
                    R.color.white
                )
            )
            text.compoundDrawablePadding =
                context.resources.getDimensionPixelSize(R.dimen.padding_toast)
            toast.duration = 5000
            toast.show()
        } catch (e: Exception) {
            e.printStackTrace()
            println("toast error")
        }
    }


    fun showSnackBar(
        view: View?,
        context: Context,
        message: String?
    ) {
        try {
            val contextWeakReference =
                WeakReference(context)
//            Snackbar.make(
//                view!!, message!!,
//                Snackbar.LENGTH_SHORT
//            )
//                .show()
            val snackbar: Snackbar = Snackbar.make(view!!, message!!, Snackbar.LENGTH_LONG)
            val snackbarView = snackbar.view
            val snackTextView =
                snackbarView.findViewById<View>(com.google.android.material.R.id.snackbar_text) as TextView

            snackTextView.maxLines = 3
            snackbar.show()

        } catch (e: Exception) {
            e.printStackTrace()
        }
    }



    fun getColorWrapper(context: Context, id: Int): Int {
        return if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            context.getColor(id)
        } else {
            context.resources.getColor(id)
        }
    }
}