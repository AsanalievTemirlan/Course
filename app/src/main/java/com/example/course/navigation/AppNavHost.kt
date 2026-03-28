package com.example.course.navigation

import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Icon
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.NavigationBarItemDefaults
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavDestination.Companion.hasRoute
import androidx.navigation.NavDestination.Companion.hierarchy
import androidx.navigation.NavGraph.Companion.findStartDestination
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.example.presentation.screens.auth.AuthScreen
import com.example.presentation.screens.courses.CoursesScreen
import com.example.presentation.screens.courses.detail.DetailCourseScreen
import com.example.presentation.screens.favorites.FavoritesScreen
import com.example.presentation.screens.profile.ProfileScreen
import com.example.presentation.uitils.Routs
import com.example.presentation.uitils.dark_gray
import com.example.presentation.uitils.green
import com.example.presentation.uitils.white

@Composable
fun AppNavHost() {
    val navController = rememberNavController()
    val navBackStackEntry by navController.currentBackStackEntryAsState()
    val currentDestination = navBackStackEntry?.destination

    val bottomNavScreens = listOf(
        ScreensBottom.Main,
        ScreensBottom.Favorites,
        ScreensBottom.Profile
    )

    val showBottomBar = bottomNavScreens.any { screen ->
        currentDestination?.hierarchy?.any { it.hasRoute(screen.route::class) } == true
    }

    Scaffold(
        bottomBar = {
            if (showBottomBar) {
                NavigationBar(
                    containerColor = dark_gray,
                    contentColor = Color.White,
                    tonalElevation = 0.dp,
                    modifier = Modifier
                ) {
                    bottomNavScreens.forEach { screen ->
                        val isSelected = currentDestination?.hierarchy?.any {
                            it.hasRoute(screen.route::class)
                        } == true

                        NavigationBarItem(
                            selected = isSelected,
                            onClick = {
                                navController.navigate(screen.route) {
                                    popUpTo(navController.graph.findStartDestination().id) {
                                        saveState = true
                                    }
                                    launchSingleTop = true
                                    restoreState = true
                                }
                            },
                            icon = {
                                Icon(
                                    painter = painterResource(id = screen.iconRes),
                                    contentDescription = screen.title,
                                    modifier = Modifier.size(24.dp)
                                )
                            },
                            label = {
                                Text(
                                    text = screen.title,
                                    fontSize = 12.sp,
                                    fontWeight = FontWeight.Bold,
                                    color = if (isSelected) green else white
                                )
                            },
                            colors = NavigationBarItemDefaults.colors(
                                selectedIconColor = green,
                                unselectedIconColor = white,
                                selectedTextColor = green,
                                unselectedTextColor = white,
                                indicatorColor = Color.Transparent
                            )
                        )
                    }
                }
            }
        }
    ) { innerPadding ->
        NavHost(
            navController = navController,
            startDestination = Routs.CoursesRout,
            modifier = Modifier.padding(innerPadding)
        ) {
            composable<Routs.AuthRout> { AuthScreen(navController) }
            composable<Routs.CoursesRout> { CoursesScreen(navController = navController) }
            composable<Routs.FavoritesRout> { FavoritesScreen(navController = navController) }
            composable<Routs.ProfileRout> { ProfileScreen(navController = navController) }
            composable<Routs.DetailCourseRout> { DetailCourseScreen(navController = navController) }
        }
    }
}

sealed class ScreensBottom(val route: Routs, val title: String, val iconRes: Int) {
    data object Main :
        ScreensBottom(Routs.CoursesRout, "Главная", com.example.presentation.R.drawable.ic_home)

    data object Favorites :
        ScreensBottom(
            Routs.FavoritesRout,
            "Избранное",
            com.example.presentation.R.drawable.ic_favorite_empty
        )

    data object Profile :
        ScreensBottom(Routs.ProfileRout, "Аккаунт", com.example.presentation.R.drawable.ic_profile)
}