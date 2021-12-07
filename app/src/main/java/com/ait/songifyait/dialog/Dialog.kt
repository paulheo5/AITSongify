package com.ait.songifyait.dialog

import android.app.AlertDialog
import android.app.Dialog
import android.content.Context
import android.os.Bundle
import androidx.fragment.app.DialogFragment
import com.ait.songifyait.data.URL
import com.ait.songifyait.databinding.ActivityDialogBinding
import java.lang.RuntimeException

class Dialog : DialogFragment() {



    public interface URLHandler{
        fun urlCreated(url: URL)
    }

    lateinit var dialogBinding: ActivityDialogBinding
    lateinit var urlHandler : URLHandler

    override fun onAttach(context: Context) {
        super.onAttach(context)

        if (context is URLHandler){
            urlHandler = context
        }else {
            throw RuntimeException("The handler is not being implemented correctly")
        }
    }

    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {

        val builder = AlertDialog.Builder(requireContext())

        builder.setTitle("Enter Spotify track URLs to compare")

        dialogBinding = ActivityDialogBinding.inflate(layoutInflater)
        builder.setView(dialogBinding.root)



        builder.setPositiveButton("Search"){
            dialog, which ->
            //Put the entries as string extras to result activity


        }

        builder.setNegativeButton("Cancel"){
            dialog, which ->
        }

        return builder.create()
    }

}