package com.example.mathsforfun

class GenerateEquation {
    var total:Int=0
    var output: String = ""

    private fun evaluation(x: Int, y: Int, num: Int): Int {
        val expression = intArrayOf(x.div(y), x.times(y), x.plus(y), x.minus(y))
        return expression[num]
    }

    fun createEquation():String{
        //num of terms
        val arr = arrayListOf(1, 2, 3, 4)
        //shuffling the array
        arr.shuffle()
        //initializing numbers
        val a = 0
        val b = 0
        val c = 0
        val d = 0
        val e=0
        var x: Int
        var y: Int

        var isIterate = true
        while (isIterate) {
            isIterate = false
            //adding numbers to array
            val numbers = intArrayOf(a, b, c, d,e)
            //assigning random number to numbers
            for (i in 0..arr.size) {
                numbers[i] = (1..20).random()
            } //add numbers

            //getting a random operator
            var num = (0..3).random()

            val operator = arrayOf("/", "*", "+", "-")

            //if num of term =1
            if (arr[0] == 1) {
                this.total = numbers[0]
                output = numbers[0].toString()
            } else {
                x = numbers[0]
                y = numbers[1]

                //validating
                if (num == 0 && (x % y) != 0) {
                    isIterate = true
                }

                this.total = evaluation(x, y, num)
                //validating
                if (this.total > 100) {
                    isIterate = true
                }
                output = if (arr[0] == 2) {
                    (x.toString() + operator[num] + y.toString())
                } else {
                    ("(" + x.toString() + operator[num] + y.toString() + ")")
                }
                //checking the number of terms
                if (arr[0] > 2) {
                    //condition is true starting from 3rd term
                    var i = 2
                    while (i != arr[0]) {
                        y = numbers[i]
                        x = this.total
                        //getting a random operator
                        num = (0..3).random()
                        if (num == 0 && (x % y != 0)) {
                            isIterate = true
                        }
                        this.total = evaluation(x, y, num)
                        if (this.total > 100) {
                            isIterate = true
                        }
                        //create equation to 3 and terms
                        if (arr[0] == 3) {
                            output += (operator[num] + y.toString())
                        } else if (arr[0] == 4 && i == 2) {
                            output = "(" + output + (operator[num] + y.toString() + ")")
                        } else {
                            output += (operator[num] + y.toString())
                        }
                        i++
                    }
                }
            }
        }
        return output
    }

}

