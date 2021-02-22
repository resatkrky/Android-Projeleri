package com.resatkarakaya.kotlinfundamental

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        println("hello kotlin")

        //var değer sonradan değişebilir değişken
        //val değer sonradan değişemez sabit define for c , final for java
        var x = 5 //değişken
        var y = 4

        println( x * y )

        val name = "Resat" //sabit

         val a = 5

        val myInteger : Int

        myInteger = 10

        val a : Int = 5

        val b : Double =3.5

        val firstName : String = "resat"

        firstName.capitalize() // ilk harfini büyük yapar

        var myBoolean : Boolean = true

        var myNumber = 5
        var myLongNumber = myNumber.toLong()
        println(myLongNumber)

        val myArray = arrayOf("Resat","Karakaya")
        myArray[0]

        val mixedArray = arrayOf("Resat",22)
        mixedArray[0]

        println("first element ${myArray[0]}")

        val mySet = setOf<Int>(1,1,2,3) // aynı olanlardan sadece birini alır

        mySet.forEach{ println(it) } // setOf un içinde olanların hepsini ayrı ayrı yazdırır

        val day = 1
        val dayString = ""

        when(day){
            1->dayString = "Monday"
            2->dayString = "Tuesday"
        }

        val myNumbers = arrayOf(1,2,3,4,5,6,7,8,9)

        for(number in myNumbers){
            val z = number / 3 * 5
            println(z)
        }

        for(i in myNumbers.indices){
            val qz = myNumbers[0] / 3 * 5
            println(qz)
        }

        for(b in 0..9){ //0-9 arası sayıları yazdırır
            println(b)
        }

        fun test(a : Int,b : Int){
            textView.text = "${a+b}"
            // return a+b
        } // fonk oluşturma

        fun helloKotlin(view : View){

        }

        //constructor oluşturma
        constuctor(ageInput : Int){
            this.age = ageInput
        }

        //nullable
        var myString4 : String? = null
        var myAge3 : Int? = null

        println(myAge3!! * 10)

        //List , Set(Unique) , Map(key => value)
    }
}