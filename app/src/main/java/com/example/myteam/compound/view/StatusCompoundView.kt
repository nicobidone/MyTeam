package com.example.myteam.compound.view

import android.content.Context
import android.util.AttributeSet
import android.view.LayoutInflater
import android.widget.TextView
import androidx.constraintlayout.widget.ConstraintLayout
import com.example.myteam.R
import com.example.myteam.databinding.CompoundViewStatusBinding

class StatusCompoundView : ConstraintLayout {
    constructor(context: Context) : super(context) {
        init(null, 0)
    }

    constructor(context: Context, attrs: AttributeSet) : super(context, attrs) {
        init(attrs, 0)
    }

    constructor(context: Context, attrs: AttributeSet, defStyle: Int) : super(context, attrs, defStyle) {
        init(attrs, defStyle)
    }

    private lateinit var binding: CompoundViewStatusBinding
    private lateinit var textView: TextView

    private fun init(attrs: AttributeSet?, defStyle: Int) {

        val attributes = context.obtainStyledAttributes(attrs, R.styleable.StatusCompoundView, defStyle, 0)
        binding = CompoundViewStatusBinding.inflate(LayoutInflater.from(context), this, true)
        textView = binding.status

        textView.text = attributes.getString(R.styleable.StatusCompoundView_exampleString)

        attributes.recycle()
    }

    fun setStatus(status: Status) = when (status) {
        Status.SUCCESS -> {
            binding.status.text = context.getString(R.string.success)
            binding.icon.setImageResource(R.drawable.ic_success)
        }
        Status.ERROR -> {
            binding.status.text = context.getString(R.string.error)
            binding.icon.setImageResource(R.drawable.ic_error)
        }
    }

    enum class Status {
        SUCCESS,
        ERROR
    }
}
