package com.example.customtabsnavigatorsample

import android.app.Activity
import android.content.Context
import android.os.Bundle
import android.util.AttributeSet
import androidx.browser.customtabs.CustomTabsIntent
import androidx.core.content.ContextCompat
import androidx.core.content.withStyledAttributes
import androidx.core.net.toUri
import androidx.navigation.NavDestination
import androidx.navigation.NavOptions
import androidx.navigation.Navigator

@Navigator.Name("customTabs")
class CustomTabsNavigator(
    private val context: Context
) : Navigator<CustomTabsNavigator.Destination>() {

    override fun navigate(
        destination: Destination,
        args: Bundle?,
        navOptions: NavOptions?,
        navigatorExtras: Extras?
    ): NavDestination? {
        val url = requireNotNull(destination.url)

        CustomTabsIntent.Builder()
            .setShowTitle(true)
            .addDefaultShareMenuItem()
            .setToolbarColor(ContextCompat.getColor(context, R.color.colorPrimary))
            .setSecondaryToolbarColor(ContextCompat.getColor(context, R.color.colorAccent))
            .build()
            .launchUrl(context, url.toUri())

        return null
    }

    override fun createDestination() = Destination(this)

    override fun popBackStack() = true

    @NavDestination.ClassType(Activity::class)
    class Destination(navigator: Navigator<out NavDestination>) : NavDestination(navigator) {

        var url: String? = null
            private set

        override fun onInflate(context: Context, attrs: AttributeSet) {
            super.onInflate(context, attrs)

            context.withStyledAttributes(attrs, R.styleable.CustomTabsNavigator, 0, 0) {
                url = getString(R.styleable.CustomTabsNavigator_url)
            }
        }
    }
}
