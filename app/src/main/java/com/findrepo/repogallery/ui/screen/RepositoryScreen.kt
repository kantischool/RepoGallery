package com.findrepo.repogallery.ui.screen

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.material3.SnackbarHostState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.derivedStateOf
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.findrepo.model.Repository
import com.findrepo.repogallery.R
import com.findrepo.repogallery.ui.comman.AppFloatingActionButton
import com.findrepo.repogallery.ui.comman.AppLoader
import com.findrepo.repogallery.ui.comman.AppOutlinedBasicTextField
import com.findrepo.repogallery.ui.comman.AppScaffold
import com.findrepo.repogallery.ui.comman.AppTextSmallTitle
import com.findrepo.repogallery.ui.item.RepositoryItem
import com.findrepo.repogallery.ui.theme.colorRed
import com.findrepo.repogallery.viewmodel.RepositoryViewModel
import com.findrepo.state.RepositoryScreenUiEvent
import com.findrepo.utility.util.ConnectionState
import com.findrepo.utility.util.connectivityState

@Composable
fun RepositoryScreen(
    onNavigateToRepoDetail: (Repository) -> Unit = {},
) {

    val viewModel = hiltViewModel<RepositoryViewModel>()
    val viewState by viewModel.consumableState().collectAsState()

    val connection by connectivityState()
    val isConnected = connection === ConnectionState.Connected

    val repositories by remember { derivedStateOf { viewState.repositories } }
    val query by remember { derivedStateOf { viewState.query } }
    val isCallApiAgain by remember { derivedStateOf { viewState.isCallApiAgain } }
    val isLoading by remember { derivedStateOf { viewState.isLoading } }
    val isDataLoading by remember { derivedStateOf { viewState.isDataLoading } }

    if (isCallApiAgain) {
        viewModel.onEvent(RepositoryScreenUiEvent.NextApiCall)
    }

    if (!isConnected) {
        viewModel.onEvent(RepositoryScreenUiEvent.DataFromRoomDB)
    } else {
        viewModel.onEvent(RepositoryScreenUiEvent.NetWorkAvailable)
    }

    RepositoryContent(
        repositories = { repositories },
        onRepositoryClick = { onNavigateToRepoDetail.invoke(it) },
        query = { query },
        onSearchRepo = { viewModel.onEvent(RepositoryScreenUiEvent.SearchRepo) },
        onCallApiAgain = { viewModel.onEvent(RepositoryScreenUiEvent.CallApiAgain) },
        onChangeQuery = { viewModel.onEvent(RepositoryScreenUiEvent.ChangeQuery(it)) },
        isLoading = { isLoading },
        isDataLoading = { isDataLoading },
        isConnected = { isConnected },
    )
}

@Composable
fun RepositoryContent(
    repositories: () -> List<Repository>,
    onRepositoryClick: (Repository) -> Unit = {},
    query: () -> String = { "" },
    onSearchRepo: () -> Unit = {},
    onCallApiAgain: () -> Unit = {},
    onChangeQuery: (String) -> Unit = {},
    snackBarHostState: SnackbarHostState = remember { SnackbarHostState() },
    isLoading: () -> Boolean = { false },
    isDataLoading: () -> Boolean = { false },
    isConnected: () -> Boolean = { true },
) {
    AppScaffold(
        title = stringResource(R.string.desc_repositories),
        showNavigationIcon = false,
        snackbarHostState = snackBarHostState
    ) { contentPadding ->
        if (isLoading()) {
            AppLoader()
        } else {
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(contentPadding)
                    .padding(horizontal = 16.dp)
            ) {
                val scrollState = rememberLazyListState()
                val lazyListState = remember { derivedStateOf { scrollState } }.value

                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(vertical = 16.dp),
                ) {
                    Box(
                        modifier = Modifier
                            .weight(1f)
                    ) {
                        AppOutlinedBasicTextField(
                            text = query(),
                            placeholder = stringResource(R.string.desc_search),
                            enabled = true,
                            isTextSetManually = true,
                            imeAction = ImeAction.Done,
                            onValueChange = { onChangeQuery(it) }
                        )
                    }

                    AppFloatingActionButton(
                        modifier = Modifier.padding(start = 10.dp),
                        resId = R.drawable.ic_search,
                        onClick = onSearchRepo,
                    )
                }

                LazyColumn {
                    items(
                        items = repositories(),
                    ) { repo ->
                        RepositoryItem(
                            repo = repo,
                            onRepositoryClick = onRepositoryClick
                        )
                    }

                    item {
                        val isForward = lazyListState.canScrollForward
                        if (!isForward) {
                            onCallApiAgain.invoke()
                        }
                        if (isDataLoading()) {
                            AppLoader()
                        }

                        if (!isConnected()) {
                            AppTextSmallTitle(
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .padding(top = 12.dp, bottom = 24.dp)
                                    .align(Alignment.CenterHorizontally),
                                text = stringResource(R.string.desc_may_be_your_internet_is_off),
                                textAlign = TextAlign.Center,
                                color = colorRed
                            )
                        }
                    }
                }
            }
        }
    }
}