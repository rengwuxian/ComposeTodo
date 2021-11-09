package com.rengwuxian.composetodo.data

object DataCenter {
  val todos = mutableListOf(
    Todo().apply { name = "吃饭" },
    Todo().apply { name = "睡觉" },
    Todo().apply { name = "打豆豆" },
  )
}