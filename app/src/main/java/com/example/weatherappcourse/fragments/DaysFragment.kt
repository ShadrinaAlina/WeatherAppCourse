package com.example.weatherappcourse.fragments
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.activityViewModels
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.weatherappcourse.MaimViewModel
import com.example.weatherappcourse.R
import com.example.weatherappcourse.adapter.WeatherAdapter
import com.example.weatherappcourse.adapter.WeatherModel
import com.example.weatherappcourse.databinding.FragmentDaysBinding

class DaysFragment : Fragment() , WeatherAdapter.Listener{

    // Создаем переменные
    private lateinit var adapter: WeatherAdapter
    private lateinit var binding: FragmentDaysBinding
    private val model: MaimViewModel by activityViewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentDaysBinding.inflate(inflater, container, false)
        return binding.root
    }
    // запускаем функцию init
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        init()
        // инициализируем наш слушатель
        model.liveDataList.observe(viewLifecycleOwner){
            // передаем наш список в адаптер
            adapter.submitList(it.subList(1, it.size))
        }
    }
    // подключаем элементы
    private fun init() = with(binding){
        adapter = WeatherAdapter(this@DaysFragment)
        // указываем расположение списка
        rcView.layoutManager = LinearLayoutManager(activity)
        // подключаем адаптер
        rcView.adapter = adapter
    }

    companion object {
        @JvmStatic
        fun newInstance() = DaysFragment()
    }
    override fun onClick(item: WeatherModel){
        model.liveDataCurrent.value = item
    }
}