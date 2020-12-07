package com.example.myapplication

import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import org.w3c.dom.Text
import org.xmlpull.v1.XmlPullParser
import org.xmlpull.v1.XmlPullParserFactory
import java.io.InputStream


class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val mSearch: EditText = findViewById<EditText>(R.id.searchbar)
        val mbutton: Button = findViewById<Button>(R.id.search)
        val mResult: TextView = findViewById<TextView>(R.id.result)
        mbutton.setOnClickListener(View.OnClickListener {
            val brandData = mSearch.text.toString()

            // https://www.tutorialspoint.com/android/android_xml_parsers.htm
            val xmlFactoryObject = XmlPullParserFactory.newInstance()
            val myParser = xmlFactoryObject.newPullParser()
            val inputStream: InputStream = resources.openRawResource(R.raw.test)
            myParser.setInput(inputStream, null)

            var event: Int = myParser.getEventType()
            while (event != XmlPullParser.END_DOCUMENT) {

                when (event) {
                    XmlPullParser.START_TAG -> if (myParser.name == "brand") {
                        val xmlName = myParser.getAttributeValue(null, "name")
                        if (xmlName.equals(brandData, true)) {
                            val xmlOwner = myParser.getAttributeValue(null, "owner")
                            Log.d("XML Parsing", xmlName + " " + xmlOwner)
                            mResult.text = xmlName + " " + xmlOwner
                        }
                    }
                    XmlPullParser.END_TAG -> {}//Log.d("XML Parsing", "End-tag")}
                    else -> Log.d("XML Parsing", "Error")
                }
                event = myParser.next()
            }
            Log.d("XML Parsing", "Exiting While Loop")
        })

//        try {
//            val inputStream: InputStream = resources.openRawResource(R.raw.test)
//            val bufferedReader = BufferedReader(InputStreamReader(inputStream))
//            var eachline: String = bufferedReader.readLine()
//            while (eachline != null) {
//                // `the words in the file are separated by space`, so to get each words
//                //            val words = eachline.split(" ".toRegex()).toTypedArray()
////                Log.d("File", "=====")
//                Log.d("File", eachline)
//                eachline = bufferedReader.readLine()
////                Log.d("File1", eachline)
//            }
//            Log.d("File", "Reached The End!")
//        } catch (e: Exception) {
//            Log.e("File", "IGNORE THIS USELESS error:" + e)
//        }
//        Log.d("File", "Reached The End?")


    }

}