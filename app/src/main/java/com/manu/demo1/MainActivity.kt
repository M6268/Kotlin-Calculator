package com.manu.demo1

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.util.Log
import android.view.View
import android.widget.Button
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {
    var isfirsttime:Boolean =false
    var result:Float= 0.0f
    var last_operator_index=0
    var num=0.0f
    var numb:ArrayList<Float> = ArrayList()
    var oprs:ArrayList<String> = ArrayList()
    var opr=""

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
    }

    fun onControlClick(view: View)
    {
        var test:Button = view as Button
        if(test.text.toString()=="=") {
            if(last_operator_index!=-1) {
                num=displayTextView.text.substring(last_operator_index+1  , displayTextView.text.length ).toFloat()
                last_operator_index = -1
                numb.add(num)
                //calculate
                displayTextView.append("="+calc())

            }
        }
        else
        {
            if (isfirsttime == false) {
                isfirsttime = true
                num=displayTextView.text.toString().toFloat()
                opr=test.text.toString()
                numb.add(num)
                oprs.add(opr)
                last_operator_index = displayTextView.text.length

            } else {
                num=displayTextView.text.substring(last_operator_index+1, displayTextView.text.length).toFloat()
                opr=test.text.toString()
                numb.add(num)
                oprs.add(opr)
                last_operator_index = displayTextView.text.length
            }
            displayTextView.append(test.text.toString())

        }

    }

    fun calc():Any
    {
        Log.w("INIT","${oprs.toString()} ${numb.toString()}")
        var j=0
        lab@ for ((idx,opr) in oprs.withIndex())
        {
            for(i in j.. numb.lastIndex)
            {
                if(i+1 <=numb.lastIndex)
                {
                    Log.w("PRE $opr","$idx $i ${numb.get(i)} ${numb.get(i+1)}")
                    when(opr)
                    {
                        "+"->{
                            result=numb[i]+numb[i+1]
                            numb.set(i+1,result)
                        }
                        "-"->{
                            result=numb[i]-numb[i+1]
                            numb.set(i+1,result)
                        }
                        "*"->{
                            result=numb[i]*numb[i+1]
                            numb.set(i+1,result)
                        }
                        "/"->{
                            result=numb[i]/numb[i+1]
                            numb.set(i+1,result)
                        }
                    }
                    j++
                    Log.w("POST $opr","$idx $i ${numb.get(i)} ${numb.get(i+1)}")
                }
                continue@lab
            }
        }
        if(result-result.toInt()>0)
            return result
        else
            return result.toInt()
    }


    fun onNumberClick(view:View)
    {
        var test:Button =view as Button
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
        last_operator_index=-1
        isfirsttime=false
        result=0.0f
        opr=""
        num=0.0f
        numb= ArrayList()
        oprs=ArrayList()
    }

}
