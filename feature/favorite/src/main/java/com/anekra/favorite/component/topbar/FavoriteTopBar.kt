package com.anekra.favorite.component.topbar

import android.content.Context
import android.content.Intent
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material.icons.filled.ExitToApp
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.core.content.ContextCompat.startActivity
import com.anekra.domain.model.game.GameWithScreenShotsDomain
import com.anekra.util.showToast
import com.google.android.play.core.splitinstall.SplitInstallManagerFactory
import com.google.android.play.core.splitinstall.SplitInstallRequest

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun FavoriteTopBar(
    scrollBehavior: TopAppBarScrollBehavior,
    gameDetailsList: List<GameWithScreenShotsDomain>,
    showDialog: (Boolean) -> Unit,
    context: Context
) {
    TopAppBar(
        title = {
            Text(text = "Favorite")
        },
        scrollBehavior = scrollBehavior,
        actions = {
            if (gameDetailsList.isNotEmpty()) {
                IconButton(
                    onClick = {
                        showDialog(true)
                    }
                ) {
                    Icon(imageVector = Icons.Filled.Delete, contentDescription = "Delete Icon")
                }
                IconButton(
                    onClick = {
                        val manager = SplitInstallManagerFactory.create(context)
                        val module = "dynamicfeaturefavorite"
                        if (manager.installedModules.contains(module)) {
                            val intent = Intent().setClassName(
                                context.packageName,
                                "com.anekra.dynamicfeaturefavorite.DynamicFeatureActivity"
                            )
                            startActivity(context, intent, null)
                        } else {
                            val request = SplitInstallRequest.newBuilder()
                                .addModule(module)
                                .build()
                            manager.startInstall(request).addOnSuccessListener {
                            }.addOnFailureListener {
                                showToast("Feature not installed", context)
                            }
                        }
                    }
                ) {
                    Icon(imageVector = Icons.Filled.ExitToApp, contentDescription = "Dynamic Feature Icon")
                }
            }
        }
    )
}