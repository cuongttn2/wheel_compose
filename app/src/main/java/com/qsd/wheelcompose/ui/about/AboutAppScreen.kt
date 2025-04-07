package com.qsd.wheelcompose.ui.about

import android.content.Intent
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.core.net.toUri
import com.qsd.wheelcompose.R
import com.qsd.wheelcompose.utils.POLICY_URL

@Composable
fun AboutAppScreen() {
    val context = LocalContext.current
    val appName = stringResource(R.string.app_name)
    val packageInfo = remember {
        context.packageManager.getPackageInfo(context.packageName, 0)
    }
    val versionName = packageInfo.versionName

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        verticalArrangement = Arrangement.Top,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        // App Logo
        Image(
            painter = painterResource(id = R.drawable.ic_launcher_foreground),
            contentDescription = "App Logo",
            modifier = Modifier.size(100.dp)
        )

        Spacer(modifier = Modifier.height(16.dp))

        // App Name & Version
        Text(
            text = appName,
            style = MaterialTheme.typography.titleLarge
        )
        Text(
            text = buildString {
                append(stringResource(R.string.version))
                append(versionName)
            },
            style = MaterialTheme.typography.bodyMedium
        )

        Spacer(modifier = Modifier.height(24.dp))

        // About App Description
        Text(
            text = stringResource(R.string.app_about_desc),
            style = MaterialTheme.typography.bodyLarge,
            textAlign = TextAlign.Center
        )

        Spacer(modifier = Modifier.height(24.dp))

        // Privacy Policy Button
        Button(onClick = {
            val intent = Intent(Intent.ACTION_VIEW, POLICY_URL.toUri())
            context.startActivity(intent)
        }) {
            Text(stringResource(R.string.privacy_and_policy))
        }

        Spacer(modifier = Modifier.height(12.dp))

        // Contact
        Button(onClick = {
            val intent = Intent(Intent.ACTION_SENDTO).apply {
                data = context.getString(R.string.develop_mail).toUri()
                putExtra(Intent.EXTRA_SUBJECT, context.getString(R.string.feedback_on, appName))
            }
            context.startActivity(intent)
        }) {
            Text(stringResource(R.string.contact_the_developer))
        }
    }
}
