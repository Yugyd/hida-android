package com.yugyd.hida.ui.feature

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.SnackbarHostState
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.yugyd.hida.core.emptystate.LoadingContent
import com.yugyd.hida.core.emptystate.WarningContent
import com.yugyd.hida.core.toolbar.Toolbar
import com.yugyd.hida.ui.feature.FeatureView.Action
import com.yugyd.hida.ui.feature.FeatureView.State
import com.yugyd.hida.ui.feature.FeatureView.State.NavigationState
import com.yugyd.hida.designsystem.core.R as UiKitR

@Composable
fun FeatureRoute(
    viewModel: FeatureViewModel = hiltViewModel(),
    snackbarHostState: SnackbarHostState,
    onNavigateToNext: () -> Unit,
) {
    val state = viewModel.state.collectAsStateWithLifecycle()

    FeatureScreen(
        stateProvider = { state.value },
        snackbarHostState = snackbarHostState,
        onButtonClicked = {
            viewModel.onAction(Action.OnButtonClicked)
        },
        onErrorDismissState = {
            viewModel.onAction(Action.OnSnackbarDismissed)
        },
        onNavigateToNext = onNavigateToNext,
        onNavigationHandled = {
            viewModel.onAction(Action.OnNavigationHandled)
        },
    )
}

@Composable
internal fun FeatureScreen(
    stateProvider: () -> State,
    snackbarHostState: SnackbarHostState,
    onButtonClicked: () -> Unit,
    onErrorDismissState: () -> Unit,
    onNavigateToNext: () -> Unit,
    onNavigationHandled: () -> Unit,
) {
    val state = stateProvider()

    Column {
        Toolbar(
            title = stringResource(id = R.string.feature_tolbar_title),
        )

        when {
            state.isLoading -> {
                LoadingContent()
            }

            state.isWarning -> {
                WarningContent()
            }

            else -> {
                FeatureContent(
                    data = state.data,
                    onButtonClicked = onButtonClicked,
                )
            }
        }
    }

    val errorMessage = stringResource(id = UiKitR.string.uikit_msg_base_error)
    LaunchedEffect(key1 = state.showErrorMessage) {
        if (state.showErrorMessage) {
            snackbarHostState.showSnackbar(message = errorMessage)

            onErrorDismissState()
        }
    }

    NavigationHandler(
        navigationState = state.navigationState,
        onNavigateToNext = onNavigateToNext,
        onNavigationHandled = onNavigationHandled,
    )
}

@Composable
fun FeatureContent(
    data: String,
    onButtonClicked: () -> Unit
) {
    Column(
        modifier = Modifier
            .padding(16.dp)
            .fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally,
    ) {
        Text(
            text = data,
            style = MaterialTheme.typography.headlineSmall,
            textAlign = TextAlign.Center,
            color = MaterialTheme.colorScheme.onBackground,
        )

        Spacer(modifier = Modifier.height(16.dp))

        Button(
            onClick = onButtonClicked,
        ) {
            Text(text = stringResource(id = R.string.feature_content_button_title))
        }
    }
}

@Composable
internal fun NavigationHandler(
    navigationState: NavigationState?,
    onNavigateToNext: () -> Unit,
    onNavigationHandled: () -> Unit,
) {
    LaunchedEffect(key1 = navigationState) {
        when (navigationState) {
            NavigationState.NavigateToNext -> onNavigateToNext()
            null -> Unit
        }

        navigationState?.let { onNavigationHandled() }
    }
}

@Preview
@Composable
private fun ContentPreview() {
    MaterialTheme {
        Surface {
            FeatureContent(
                data = "Foo",
                onButtonClicked = {},
            )
        }
    }
}
