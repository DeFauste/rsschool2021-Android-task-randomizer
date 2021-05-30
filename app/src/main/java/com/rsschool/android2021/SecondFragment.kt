package com.rsschool.android2021

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import androidx.fragment.app.Fragment
import kotlin.random.Random

class SecondFragment : Fragment(), IOnBackPressed{
    private var listener:onActionSecondFragment? = null

    override fun onAttach(context: Context) {
        super.onAttach(context)
        listener = context as onActionSecondFragment
    }

    private var backButton: Button? = null
    private var result: TextView? = null

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_second, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        result = view.findViewById(R.id.result)
        backButton = view.findViewById(R.id.back)
        val min = arguments?.getInt(MIN_VALUE_KEY) ?: 0
        val max = arguments?.getInt(MAX_VALUE_KEY) ?: 0

        result?.text = generate(min, max).toString()
        backButton?.setOnClickListener {
            // TODO: implement back
            listener?.onActionPerformed(result?.text.toString().toInt())
        }
    }

    private fun generate(min: Int, max: Int): Int {
        // TODO: generate random number
        if(min!= 0 || max != 0)
        return Random.nextInt(min,max)
        return 0
    }

    companion object {

        @JvmStatic
        fun newInstance(min: Int, max: Int): SecondFragment {
            val fragment = SecondFragment()
                val args = Bundle()
            // TODO: implement adding arguments
            args.putInt(MIN_VALUE_KEY,min)
            args.putInt(MAX_VALUE_KEY,max)
            fragment.arguments = args
            return fragment
        }

        private const val MIN_VALUE_KEY = "MIN_VALUE"
        private const val MAX_VALUE_KEY = "MAX_VALUE"
    }

    //transfer to activity
    interface onActionSecondFragment{
        fun onActionPerformed(result:Int)
    }
    //click processing "<"
    override fun onBackPressed(): Int {
       return result?.text.toString().toInt()
    }
}