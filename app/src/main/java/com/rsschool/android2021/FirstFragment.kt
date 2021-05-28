package com.rsschool.android2021

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentTransaction
import androidx.fragment.app.setFragmentResult
import androidx.navigation.fragment.findNavController
import java.lang.Exception
import java.math.BigInteger

class FirstFragment : Fragment() {
    private var listener:onActionFirstFragment? = null

    override fun onAttach(context: Context) {
        super.onAttach(context)
        listener = context as onActionFirstFragment
    }

    private var generateButton: Button? = null
    private var previousResult: TextView? = null

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_first, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        previousResult = view.findViewById(R.id.previous_result)
        generateButton = view.findViewById(R.id.generate)

        val result = arguments?.getInt(PREVIOUS_RESULT_KEY)
        previousResult?.text = "Previous result: ${result.toString()}"

        // TODO: val min = ...
        var min:Int
        // TODO: val max = ...
        var max:Int

        val maxInt = Int.MAX_VALUE

        generateButton?.setOnClickListener {
            try {
            min = if(view.findViewById<EditText>(R.id.min_value).text.isEmpty()) 0 else view.findViewById<EditText>(
            R.id.min_value
        ).text.toString().toInt()
            max = if(view.findViewById<EditText>(R.id.max_value).text.isEmpty()) 0 else view.findViewById<EditText>(
            R.id.max_value
            ).text.toString().toInt()
                if(min < max)
                    listener?.onActionPerformed(min,max)
            }catch (e:Exception){
                Toast.makeText(getActivity(), "Введите значение до ${maxInt}",Toast.LENGTH_LONG).show()
            }
            val maxInt = Int.MAX_VALUE
            // TODO: send min and max to the SecondFragment
        }
    }

    companion object {

        @JvmStatic
        fun newInstance(previousResult: Int): FirstFragment {
            val fragment = FirstFragment()
                val args = Bundle()
            args.putInt(PREVIOUS_RESULT_KEY, previousResult)
            fragment.arguments = args
            return fragment
        }

        private const val PREVIOUS_RESULT_KEY = "PREVIOUS_RESULT"
    }
    interface onActionFirstFragment{
        fun onActionPerformed(min:Int, max:Int)
    }
}