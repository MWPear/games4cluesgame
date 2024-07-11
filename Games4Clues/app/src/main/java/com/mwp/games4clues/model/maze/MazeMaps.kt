package com.mwp.games4clues.model.maze

class MazeMaps {
   fun getMap(level: Int) : List<List<Int>>
   {
       return when (level) {
           1 -> listOf(
               listOf(2, 0, 1, 0, 0),
               listOf(1, 0, 1, 1, 0),
               listOf(0, 0, 0, 1, 0),
               listOf(0, 1, 1, 1, 0),
               listOf(0, 0, 0, 0, 3)
           )

           2 -> listOf(
               listOf(2, 1, 0, 1, 0, 0, 0),
               listOf(1, 0, 1, 0, 1, 1, 0),
               listOf(0, 0, 1, 0, 0, 1, 0),
               listOf(1, 0, 1, 1, 0, 1, 0),
               listOf(0, 0, 0, 0, 0, 0, 0),
               listOf(1, 1, 1, 1, 1, 1, 0),
               listOf(0, 0, 0, 0, 0, 0, 3)
           )

           3 -> listOf(
               listOf(2, 1, 0, 1, 0, 1, 0, 1, 0),
               listOf(0, 1, 0, 0, 0, 1, 0, 1, 0),
               listOf(0, 1, 1, 1, 0, 1, 0, 0, 0),
               listOf(0, 0, 0, 1, 0, 0, 1, 1, 0),
               listOf(1, 1, 0, 0, 0, 1, 1, 0, 0),
               listOf(0, 0, 0, 1, 0, 0, 0, 1, 0),
               listOf(0, 1, 0, 1, 1, 1, 0, 1, 0),
               listOf(0, 1, 0, 0, 0, 0, 0, 1, 0),
               listOf(0, 1, 1, 1, 1, 1, 1, 1, 3)
           )

           4 -> listOf(
               listOf(2, 1, 0, 0, 0, 1, 0, 1, 0, 1, 0, 1),
               listOf(0, 1, 0, 1, 0, 1, 0, 1, 0, 1, 0, 0),
               listOf(0, 0, 0, 1, 0, 0, 0, 0, 0, 0, 1, 0),
               listOf(1, 1, 0, 1, 1, 1, 0, 1, 1, 0, 1, 0),
               listOf(0, 0, 0, 0, 0, 1, 0, 1, 0, 0, 0, 0),
               listOf(0, 1, 1, 1, 0, 0, 0, 0, 0, 1, 1, 1),
               listOf(0, 0, 0, 1, 0, 1, 1, 1, 0, 0, 0, 0),
               listOf(1, 1, 0, 1, 0, 1, 0, 0, 0, 1, 1, 0),
               listOf(0, 1, 0, 0, 0, 0, 0, 1, 0, 0, 0, 0),
               listOf(0, 1, 1, 1, 1, 1, 0, 1, 0, 1, 1, 0),
               listOf(0, 0, 0, 0, 0, 0, 0, 1, 0, 1, 0, 0),
               listOf(1, 1, 1, 1, 1, 1, 1, 1, 0, 1, 0, 3)
           )

           5 -> listOf(
               listOf(2, 1, 0, 1, 0, 1, 0, 1, 0, 1, 0, 1, 0, 1, 0, 1),
               listOf(0, 1, 0, 1, 0, 1, 0, 0, 0, 1, 0, 1, 0, 0, 0, 0),
               listOf(0, 1, 0, 1, 0, 1, 0, 1, 0, 1, 0, 1, 0, 1, 1, 0),
               listOf(0, 0, 0, 1, 0, 0, 0, 1, 0, 0, 0, 0, 0, 1, 0, 0),
               listOf(0, 1, 1, 1, 1, 1, 0, 1, 1, 1, 1, 1, 0, 1, 0, 1),
               listOf(0, 0, 0, 0, 0, 1, 0, 1, 0, 0, 0, 0, 0, 1, 0, 0),
               listOf(1, 1, 1, 1, 0, 1, 0, 0, 0, 1, 1, 1, 1, 1, 0, 1),
               listOf(0, 0, 0, 1, 0, 0, 0, 1, 0, 0, 0, 0, 0, 0, 0, 0),
               listOf(0, 1, 0, 1, 1, 1, 0, 1, 1, 1, 1, 1, 1, 1, 1, 0),
               listOf(0, 1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1, 0),
               listOf(0, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 0, 1, 0),
               listOf(0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1, 0),
               listOf(1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 0),
               listOf(0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0),
               listOf(1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 3)
           )

           else -> listOf(
               listOf(2, 1, 0, 1, 0),
               listOf(1, 0, 1, 1, 0),
               listOf(0, 0, 1, 0, 1),
               listOf(1, 1, 1, 1, 0),
               listOf(0, 0, 0, 0, 3)
           )
       }
    }
}