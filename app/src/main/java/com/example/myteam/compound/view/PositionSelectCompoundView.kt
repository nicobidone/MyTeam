package com.example.myteam.compound.view

import android.content.Context
import android.util.AttributeSet
import android.view.LayoutInflater
import android.widget.LinearLayout
import com.example.domain.entity.Position
import com.example.domain.entity.toPosition
import com.example.myteam.R
import com.example.myteam.databinding.CompoundViewPositionSelectBinding
import com.google.android.material.button.MaterialButtonToggleGroup

class PositionSelectCompoundView : LinearLayout {
    constructor(context: Context) : super(context) {
        init(null, 0)
    }

    constructor(context: Context, attrs: AttributeSet) : super(context, attrs) {
        init(attrs, 0)
    }

    constructor(context: Context, attrs: AttributeSet, defStyle: Int) : super(context, attrs, defStyle) {
        init(attrs, defStyle)
    }

    private lateinit var binding: CompoundViewPositionSelectBinding
    private lateinit var toggleList: List<MaterialButtonToggleGroup>
    private var selectedList: MutableList<String> = mutableListOf()

    private fun init(attrs: AttributeSet?, defStyle: Int) {

        val attributes = context.obtainStyledAttributes(attrs, R.styleable.PositionSelectCompoundView, defStyle, 0)
        binding = CompoundViewPositionSelectBinding.inflate(LayoutInflater.from(context), this, true)
        with(binding) { toggleList = listOf(tbPo, tbD, tbDm, tbM, tbMo, tbMp) }
        setTextForAll()

        for (tbg in toggleList)
            tbg.addOnButtonCheckedListener { _, checkedId, isChecked ->
                if (isChecked) {
                    selectedList.add(getText(checkedId).toString())
                } else {
                    selectedList.remove(getText(checkedId).toString())
                }
            }

        attributes.recycle()
    }

    private fun getText(id: Int) = when (id) {
        binding.gk.id -> binding.gk.text
        binding.dc.id -> binding.dc.text
        binding.dl.id -> binding.dl.text
        binding.dr.id -> binding.dr.text
        binding.dmc.id -> binding.dmc.text
        binding.mc.id -> binding.mc.text
        binding.ml.id -> binding.ml.text
        binding.mr.id -> binding.mr.text
        binding.amc.id -> binding.amc.text
        binding.aml.id -> binding.aml.text
        binding.amr.id -> binding.amr.text
        else -> binding.st.text
    }

    private fun setTextForAll() {
        with(binding) {
            gk.text = Position.GK.toString()
            dc.text = Position.DC.toString()
            dl.text = Position.DL.toString()
            dr.text = Position.DR.toString()
            dmc.text = Position.DMC.toString()
            mc.text = Position.MC.toString()
            ml.text = Position.ML.toString()
            mr.text = Position.MR.toString()
            amc.text = Position.AMC.toString()
            aml.text = Position.AML.toString()
            amr.text = Position.AMR.toString()
            st.text = Position.ST.toString()
        }
    }

    fun getSelectedList(): List<Position> = selectedList.map { it.toPosition() }
}
