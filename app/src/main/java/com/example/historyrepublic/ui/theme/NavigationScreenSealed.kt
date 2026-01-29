package com.example.historyrepublic.ui.theme

sealed class NavigationScreenSealed(val route: String) {

    object Screen1 : NavigationScreenSealed("Screen1")
    object Screen2 : NavigationScreenSealed("Screen2")

    object Screen3 : NavigationScreenSealed("Screen3") {
        fun createRoute(age: Int) = "pantalla/$age"
    }
}


//sealed class NavigationScreenSealed(val route: String) {
//
//    object Screen1 : NavigationScreenSealed("Screen1")
//    object Screen2 : NavigationScreenSealed("Screen2")
//
//    object Screen3 : NavigationScreenSealed(Screen3_ROUTE_TEMPLATE) {
//
//        const val ARG_DEV_NAME = "devName"
//        const val ARG_AGE = "age"
//
//        fun createRouteWithArgs(devName: String, age: Int): String {
//            return "Screen3/$devName/$age"
//        }
//    }
//
//    companion object {
//        const val Screen3_ROUTE_TEMPLATE =
//            "Screen3/${Screen3.ARG_DEV_NAME}/${Screen3.ARG_AGE}"
//    }
//}