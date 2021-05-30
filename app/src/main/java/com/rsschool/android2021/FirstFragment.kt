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

        // TODO: min = ...
        var min:Int
        // TODO: max = ...
        var max:Int

        val maxInt = Int.MAX_VALUE

        generateButton?.setOnClickListener {
            val checkMin = view.findViewById<EditText>(R.id.min_value).text
            val checkMax = view.findViewById<EditText>(R.id.max_value).text

            if(!checkMin.isEmpty() && !checkMax.isEmpty())
                try {
                min = checkMin.toString().toInt()
                max = checkMax.toString().toInt()
                    if(min < max)
                        listener?.onActionPerformed(min,max)
                    else if (min == max)
                        Toast.makeText(getActivity(), "Введите разные значения",Toast.LENGTH_LONG).show()
                    else
                        Toast.makeText(getActivity(), "Поменяйте значения местами",Toast.LENGTH_LONG).show()
                }catch (e:Exception){
                    Toast.makeText(getActivity(), "Введите значение до ${maxInt}",Toast.LENGTH_LONG).show()
                }
            else{
                Toast.makeText(getActivity(), "Заполните все поля",Toast.LENGTH_LONG).show()
            }
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
    //transfer to activity
    interface onActionFirstFragment{
        fun onActionPerformed(min:Int, max:Int)
    }
}