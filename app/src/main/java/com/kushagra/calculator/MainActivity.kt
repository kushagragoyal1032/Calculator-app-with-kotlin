package com.kushagra.calculator

import android.annotation.SuppressLint
import android.os.Bundle
import android.text.method.ScrollingMovementMethod
import android.widget.Button
import android.widget.EditText
import android.widget.ImageView
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import net.objecthunter.exp4j.Expression
import net.objecthunter.exp4j.ExpressionBuilder
import java.lang.Exception
import java.lang.NumberFormatException
import kotlin.reflect.typeOf


class MainActivity : AppCompatActivity()
{
    lateinit var calculationPart : EditText
    lateinit var AnsPart : TextView
    lateinit var Clear : Button
    lateinit var persentage : Button
    lateinit var backSpace : ImageView

    lateinit var button1 : Button
    lateinit var button2 : Button
    lateinit var button3 : Button
    lateinit var button4 : Button
    lateinit var button5 : Button
    lateinit var button6 : Button
    lateinit var button7 : Button
    lateinit var button8 : Button
    lateinit var button9 : Button
    lateinit var button0 : Button
    lateinit var button00 : Button
    lateinit var buttonDot : Button

    lateinit var divide : Button
    lateinit var multiply : Button
    lateinit var minus : Button
    lateinit var plus : Button
    lateinit var equal : Button

//    lateinit var a : String
//    lateinit var b : String

    var a : String = ""
    var b : String = ""
    var count : Int  = 0


    @SuppressLint("SetTextI18n")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        calculationPart = findViewById(R.id.calculationPart)
        AnsPart = findViewById(R.id.AnsPart)

        Clear = findViewById(R.id.Clear)
        persentage = findViewById(R.id.persentage)
        backSpace = findViewById(R.id.backSpace)

        button1 = findViewById(R.id.button1)
        button2 = findViewById(R.id.button2)
        button3 = findViewById(R.id.button3)
        button4 = findViewById(R.id.button4)
        button5 = findViewById(R.id.button5)
        button6 = findViewById(R.id.button6)
        button7 = findViewById(R.id.button7)
        button8 = findViewById(R.id.button8)
        button9 = findViewById(R.id.button9)
        button0 = findViewById(R.id.button0)
        button00 = findViewById(R.id.button00)
        buttonDot = findViewById(R.id.buttonDot)


        divide = findViewById(R.id.divide)
        multiply = findViewById(R.id.multiply)
        minus = findViewById(R.id.minus)
        plus = findViewById(R.id.plus)
        equal = findViewById(R.id.equal)



        button1.setOnClickListener {
           evalExp("1", clear = true)
        }
        button2.setOnClickListener {
            evalExp("2", clear = true)
        }
        button3.setOnClickListener {
            evalExp("3", clear = true)
        }
        button4.setOnClickListener {
            evalExp("4", clear = true)
        }
        button5.setOnClickListener {
            evalExp("5", clear = true)

        }
        button6.setOnClickListener {
            evalExp("6", clear = true)
        }
        button7.setOnClickListener {
            evalExp("7", clear = true)

        }
        button8.setOnClickListener {
            evalExp("8", clear = true)
        }
        button9.setOnClickListener {
            evalExp("9", clear = true)
        }
        button0.setOnClickListener {
            evalExp("0", clear = true)
        }
        button00.setOnClickListener {
            evalExp("00", clear = true)
        }
        buttonDot.setOnClickListener {
            val data = calculationPart.text.toString()
            if(data.isNotEmpty())
            {
                if (!data.endsWith(".") && !data.startsWith("."))
                {
                    evalExp(".", clear = true)
                }
            }
            else
            {
                if (!data.endsWith(".") && !data.startsWith("."))
                {
                    evalExp("0.", clear = true)
                }
            }

        }

        Clear.setOnClickListener {
            calculationPart.text.clear()
            AnsPart.text = ""
        }

        backSpace.setOnClickListener {
            AnsPart.text = ""
            val data = calculationPart.text.toString()
            if(!data.isEmpty())
            {
                 calculationPart.setText(data.substring(0, data.length - 1))
            }
        }

        plus.setOnClickListener {
            val data = calculationPart.text.toString()
            val ans = AnsPart.text.toString()

            if(data.isNotEmpty())
            {
                if (!data.endsWith("+") && !data.startsWith("+"))
                {
                    if (ans.isNotEmpty())
                    {
                            evalExp("+", clear = false)
                    }
                    else
                    {
                            evalExp("+", clear = true)
                    }
                }
            }
        }

        minus.setOnClickListener {
            val data = calculationPart.text.toString()
            val ans = AnsPart.text.toString()

            if(data.isNotEmpty())
            {
                if (!data.endsWith("-") && !data.startsWith("-"))
                {
                    if (ans.isNotEmpty())
                    {
                        evalExp("-", clear = false)
                    }
                    else
                    {
                        evalExp("-", clear = true)
                    }
                }
            }
        }

        multiply.setOnClickListener {
            val data = calculationPart.text.toString()
            val ans = AnsPart.text.toString()

            if(data.isNotEmpty())
            {
                if (!data.endsWith("×") && !data.startsWith("×"))
                {
                    if (ans.isNotEmpty())
                    {
                        evalExp("×", clear = false)
                    }
                    else
                    {
                        evalExp("×", clear = true)
                    }
                }
            }
        }

        divide.setOnClickListener {
            val data = calculationPart.text.toString()
            val ans = AnsPart.text.toString()

            if(data.isNotEmpty())
            {
                if (!data.endsWith("/") && !data.startsWith("/"))
                {
                    if (ans.isNotEmpty())
                    {
                        evalExp("/", clear = false)
                    }
                    else
                    {
                        evalExp("/", clear = true)
                    }
                }
            }
        }

        persentage.setOnClickListener {
            val data = calculationPart.text.toString()
            val ans = AnsPart.text.toString()

            if(data.isNotEmpty())
            {
                if (!data.endsWith("%") && !data.startsWith("%"))
                {
                    if (ans.isNotEmpty())
                    {
                        evalExp("%", clear = false)
                    }
                    else
                    {
                        evalExp("%", clear = true)
                    }
                }
            }
        }

        equal.setOnClickListener {
                var text = calculationPart.text.toString()
                text = text.replace("×","*")
            if (!text.matches("\\d+(\\.\\d+)?".toRegex()) &&
                text.isNotEmpty() && text.lastOrNull() !in(arrayOf('+', '-', '/', '*', '.')))
                {
                    val expression = ExpressionBuilder(text).build()
                    val result = expression.evaluate()
                    val longResult = result.toLong()

                    if(result == longResult.toDouble())
                    {
                        AnsPart.text = longResult.toString()
                    }
                    else
                    {
                        AnsPart.text = result.toString()
                    }
                }


        }
    }

    fun evalExp(string: String, clear : Boolean)
    {

        if(clear)
        {
            AnsPart.text = ""
            calculationPart.append(string)
        }
        else
        {
            calculationPart.text.clear()
            calculationPart.append(AnsPart.text)
            calculationPart.append(string)
            AnsPart.text = ""

        }
    }
}