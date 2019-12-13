package com.example.customtabsnavigatorsample

import androidx.navigation.NavController

class NavHostFragment : androidx.navigation.fragment.NavHostFragment() {
    override fun onCreateNavController(navController: NavController) {
        super.onCreateNavController(navController)
        navController.navigatorProvider.addNavigator(CustomTabsNavigator(requireContext()))
    }
}