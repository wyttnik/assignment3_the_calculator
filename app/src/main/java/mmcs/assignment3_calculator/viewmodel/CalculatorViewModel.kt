package mmcs.assignment3_calculator.viewmodel

import android.util.Log
import androidx.databinding.BaseObservable
import androidx.databinding.ObservableField
import mmcs.assignment3_calculator.R
import kotlin.math.log

class CalculatorViewModel: BaseObservable(), Calculator {
    override var display = ObservableField<String>()
    init {
        display.set("0")
    }

    private var flag = false
    private var left = "0"
    private var right = "0"
    private var currOp = ""

    override fun addDigit(dig: Int) {
        flag = true
        if (right == "0") right = dig.toString()
        else right += dig
        if (currOp == "") {
            if (left != "0") display.set("$left+$right")
            else display.set(right)
        }
        else display.set(left+currOp+right)
    }

    override fun addPoint() {
        if (!right.contains('.')) {
            flag = true
            right += "."
        }
        if (currOp == "") display.set(right)
        else display.set(left+currOp+right)
    }

    override fun addOperation(op: Operation) {
        if (right.toDouble() != 0.0) compute()
        currOp = when(op) {
            Operation.ADD -> "+"
            Operation.SUB -> "-"
            Operation.MUL -> "*"
            else -> "/"
        }
        display.set(left+currOp)
    }

    override fun compute() {
        var answer = 0.0
        when(currOp) {
            "+" -> answer = (left.toDouble() + right.toDouble())
            "-" -> answer = (left.toDouble() - right.toDouble())
            "*" -> answer = (left.toDouble() * right.toDouble())
            "/" -> {
                if(right.toDouble() == 0.0) {
                    flag = false
                    clear()
                    display.set("Cannot divide by zero")
                    return
                }
                else answer = (left.toDouble() / right.toDouble())
            }
            else -> answer = (left.toDouble() + right.toDouble())
        }
        left = if (answer * 10 % 10 == 0.0) answer.toInt().toString()
        else removeZeroes("%.5f".format(answer))
        right = "0"
        currOp = ""
        flag = false
        display.set(left)
    }

    override fun clear() {
        if (!flag) reset()
        flag = false
        right = "0"
        display.set(left+currOp)
    }

    override fun reset() {
        left = "0"
        currOp = ""
    }

    private fun removeZeroes(num:String):String {
        var res = num
        while(res[res.lastIndex] == '0'){
            res = res.substring(0,res.lastIndex)
        }
        return res
    }
}