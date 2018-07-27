package com.manu.demo1

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.util.Log
import android.view.View
import android.widget.Button
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity2 : AppCompatActivity() {
    var isfirsttime:Boolean =false
    var result:Float= 0.0f
    var num=0.0f
    var copr=""
    var popr=""
    var isResult=false

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
    }

    fun onControlClick(view: View)
    {
        var test:Button = view as Button
        if(test.text.toString()=="=") {
            num=displayTextView.text.toString().toFloat()
            popr=copr
            calc()
            popr=""
        }
        else
        {
            if (isfirsttime == false) {
                isfirsttime = true
                num=displayTextView.text.toString().toFloat()
                copr=test.text.toString()
                popr=copr
                displayTextView.append(copr)
                if(result==0.0f)
                    result=num
               // displayTextView.text=""
            } else {
                num=displayTextView.text.toString().toFloat()
                popr=copr
                copr=test.text.toString()
                calc()
            }
        }
    }

    fun calc()
    {
        Log.w("-->$popr","$copr $result $num")
        when(popr)
        {
            "+"->result=result+num
            "-"->result=result-num
            "*"->result=result*num
            "/"->result=result/num
        }
        isResult=true
        isfirsttime=false

        displayTextView.text=""+getActualTypedResult(result)
        num=0.0f
    }

    fun onNumberClick(view:View)
    {
        var test:Button =view as Button
        if(isResult)
        {
            isResult=false
            if(!isResult) {
                displayTextView.text = ""
                isfirsttime=true
            }
        }
        if(isfirsttime)
            displayTextView.text=""
        if(test.text.toString()==".") {
            if (!displayTextView.text.contains('.'))
                displayTextView.append(test.text.toString())
        }
        else
            displayTextView.append(test.text.toString())
    }

    fun deleteButton(view:View)
    {
        displayTextView.text=""
        isfirsttime=false
        isResult=false
        result=0.0f
        copr=""
        popr=""
        num=0.0f
    }

    fun getActualTypedResult(result:Float):Any
    {
        if(result-result.toInt()>0)
            return result
        else
            return result.toInt()
    }

}