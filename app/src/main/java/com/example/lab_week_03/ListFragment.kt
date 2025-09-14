package com.example.lab_week_03

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.View
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.navigation.Navigation
import com.example.lab_week_03.DetailFragment.Companion.COFFEE_ID

private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

class ListFragment : Fragment() {
    private var param1: String? = null
    private var param2: String? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            param1 = it.getString(ARG_PARAM1)
            param2 = it.getString(ARG_PARAM2)
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_list, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val coffeeList = listOf<View>(
            view.findViewById(R.id.affogato),
            view.findViewById(R.id.americano),
            view.findViewById(R.id.latte),
            view.findViewById(R.id.espresso),
            view.findViewById(R.id.cappuccino)
        )

        coffeeList.forEach { coffee ->
            val fragmentBundle = Bundle()
            when (coffee.id) {
                R.id.affogato -> fragmentBundle.putInt(COFFEE_ID, 1)
                R.id.americano -> fragmentBundle.putInt(COFFEE_ID, 2)
                R.id.latte -> fragmentBundle.putInt(COFFEE_ID, 3)
                R.id.espresso -> fragmentBundle.putInt(COFFEE_ID, 4)
                R.id.cappuccino -> fragmentBundle.putInt(COFFEE_ID, 5)
            }
            coffee.setOnClickListener(
                Navigation.createNavigateOnClickListener(
                    R.id.coffee_id_action, fragmentBundle
                )
            )
        }
    }

    companion object {
        const val COFFEE_ID = "COFFEE_ID"

        @JvmStatic
        fun newInstance(param1: String, param2: String) =
            ListFragment().apply {
                arguments = Bundle().apply {
                    putString(ARG_PARAM1, param1)
                    putString(ARG_PARAM2, param2)
                }
            }
    }
}
