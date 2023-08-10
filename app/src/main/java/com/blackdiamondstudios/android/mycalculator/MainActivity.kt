package com.blackdiamondstudios.android.mycalculator

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.TextView
import android.widget.Toast

class MainActivity : AppCompatActivity() {
    private var tvInput : TextView ?= null
    //trying to avoid the double dot occurrence in the interface
    var lastNumeric : Boolean = false
    var lastDot : Boolean = false


    private var btnOne : Button ?= null
    private var btnTwo : Button ?= null
    private var btnThree : Button ?= null
    private var btnFour : Button ?= null
    private var btnFive: Button ?= null
    private var btnSix: Button ?= null
    private var btnSeven : Button ?= null
    private var btnEight : Button ?= null
    private var btnNine : Button ?= null
    private var btnZero : Button ?= null
    private var btnAdd : Button ?= null
    private var btnMinus : Button ?= null
    private var btnDivide : Button ?= null
    private var btnMultiply : Button ?= null
    private var btnEqual : Button ?= null


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        tvInput = findViewById(R.id.tvInput)
        btnOne = findViewById(R.id.btnOne)
        btnOne?.setOnClickListener{
            tvInput?.append("1")
        }
        btnTwo = findViewById(R.id.btnTwo)
        btnTwo?.setOnClickListener{
            tvInput?.append("2")
        }
        btnThree = findViewById(R.id.btnThree)
        btnThree?.setOnClickListener{
            tvInput?.append("3")
        }
        btnFour = findViewById(R.id.btnFour)
        btnFour?.setOnClickListener{
            tvInput?.append("4")
        }
        btnFive = findViewById(R.id.btnFive)
        btnFive?.setOnClickListener{
            tvInput?.append("5")
        }
        btnSix = findViewById(R.id.btnSix)
        btnSix?.setOnClickListener{
            tvInput?.append("6")
        }
        btnSeven = findViewById(R.id.btnSeven)
        btnSeven?.setOnClickListener{
            tvInput?.append("7")
        }
        btnEight = findViewById(R.id.btnEight)
        btnEight?.setOnClickListener{
            tvInput?.append("8")
        }
        btnNine = findViewById(R.id.btnNine)
        btnNine?.setOnClickListener{
            tvInput?.append("9")
        }
        btnAdd = findViewById(R.id.btnAdd)
        btnAdd?.setOnClickListener{
            tvInput?.append("+")
        }
        btnMinus = findViewById(R.id.btnMinus)
        btnMinus?.setOnClickListener{
            tvInput?.append("-")
        }
        btnMultiply = findViewById(R.id.btnMultiply)
        btnMultiply?.setOnClickListener{
            tvInput?.append("*")
        }
        btnDivide = findViewById(R.id.btnDivide)
        btnDivide?.setOnClickListener{
            tvInput?.append("/")
        }
        btnEqual = findViewById(R.id.btnEqual)
        btnEqual?.setOnClickListener{
            tvInput?.append("=")
        }

    }

    fun onDigit(view: View) {  //the in built view method connects the interface XMl string to the varaible declared
        Toast.makeText(this, "Button CLicked",Toast.LENGTH_SHORT).show()
        tvInput?.append((view as Button).text)   //accesses the string inputed at the button entry // adds an additional context to an already displayed string
        lastNumeric = true
        lastDot = false



    }

    fun onClear(view: View){
        tvInput?.text = ""  //adds the clearance functionality to the CLR button
    }

    fun OnDecimalPoint(view: View){    //adds the floating decimal functionality
        if (lastNumeric && !lastDot){ //testing if the "." key was inserted already or not
            tvInput?.append(".")
            lastNumeric = false  //indicator flags for our indicator variables
            lastDot = true
        }
    }

    fun onOperator(view: View) {
        tvInput?.text?.let{
            //the safe call operator usage
            if (lastNumeric && !isOperatorAdded(it.toString())){ //checks if the last number entered is a numeric
                // and if at least one operator was inputed by the user
                tvInput?.append((view as Button).text)
                lastNumeric = false
                lastDot = false
            }
        }
    }

    fun onEqual(view: View){
        if(lastNumeric){
            var tvValue = tvInput?.text.toString()
            var prefix = ""         //allocates an empty string to consider the case of a minus before a minus

            try{
                if(tvValue.startsWith("-")){        //check the minus operator validity else the app crashes
                    prefix = "-"
                    tvValue = tvValue.substring(1) //implementing the substring of the old string
                }
                if(tvValue.contains("-")) {
                    val splitValue = tvValue.split("-")

                    var one = splitValue[0]
                    var two = splitValue[1]

                    if(prefix.isNotEmpty()){
                        one = prefix + one
                    }

                    tvInput?.text = removeZeroAfterDot((one.toDouble() - two.toDouble()).toString())    // the subtraction's operation
                } else if(tvValue.contains("+")) {
                    val splitValue = tvValue.split("-")

                    var one = splitValue[0]
                    var two = splitValue[1]

                    if(prefix.isNotEmpty()){
                        one = prefix + one              // the addition's operation
                    }

                    tvInput?.text = removeZeroAfterDot((one.toDouble() + two.toDouble()).toString())
                } else if(tvValue.contains("/")) {
                    val splitValue = tvValue.split("-")

                    var one = splitValue[0]
                    var two = splitValue[1]

                    if(prefix.isNotEmpty()){
                        one = prefix + one
                    }

                    tvInput?.text = removeZeroAfterDot((one.toDouble() / two.toDouble()).toString())
                } else if(tvValue.contains("*")) {
                    val splitValue = tvValue.split("-")

                    var one = splitValue[0]
                    var two = splitValue[1]

                    if(prefix.isNotEmpty()){
                        one = prefix + one
                    }

                    tvInput?.text = removeZeroAfterDot((one.toDouble() * two.toDouble()).toString())
                }
            }catch (e: ArithmeticException){
                e.printStackTrace()
            }
        }
    }

    private fun removeZeroAfterDot(result: String) :String {
        var value = result
            if (result.contains(".0"))
                    value = result.substring(0, result.length - 2)
        //above code does the string removal from the array using the string "result" length from array 0 to -2(i.e the last two digits : .0 )
            return value
    }

    private fun isOperatorAdded(value : String) :Boolean {
        return if(value.startsWith("-")){  //we ignore the minus operator for initial negative value purposes
            false
        }else {
            value.contains("/")      //returns a true value if any of the operators are found in the beginning of the string
                    // "the startswith" method else false
                    || value.contains("*")
                    || value.contains("+")
                    || value.contains("-")
        }
    }
}


