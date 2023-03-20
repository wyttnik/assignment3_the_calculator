package mmcs.assignment3_calculator.view

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.databinding.DataBindingUtil
import mmcs.assignment3_calculator.R
import mmcs.assignment3_calculator.databinding.ActivityMainBinding
import mmcs.assignment3_calculator.viewmodel.Calculator
import mmcs.assignment3_calculator.viewmodel.CalculatorViewModel
import mmcs.assignment3_calculator.viewmodel.Operation

class MainActivity : AppCompatActivity() {

    private lateinit var mainBinding : ActivityMainBinding
    private lateinit var viewModel: Calculator

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        mainBinding = DataBindingUtil.setContentView(this, R.layout.activity_main)
        viewModel = CalculatorViewModel()

        mainBinding.viewModel = viewModel

        mainBinding.divButton.setOnClickListener {
            viewModel.addOperation(Operation.DIV)
        }
        mainBinding.prodButton.setOnClickListener {
            viewModel.addOperation(Operation.MUL)
        }
        mainBinding.plusButton.setOnClickListener {
            viewModel.addOperation(Operation.ADD)
        }
        mainBinding.minusButton.setOnClickListener {
            viewModel.addOperation(Operation.SUB)
        }
    }
}