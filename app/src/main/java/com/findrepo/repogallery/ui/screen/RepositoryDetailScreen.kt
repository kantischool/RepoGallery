package com.findrepo.repogallery.ui.screen

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.SnackbarHostState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.derivedStateOf
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.findrepo.model.Repository
import com.findrepo.model.response.ContributorResponse
import com.findrepo.repogallery.R
import com.findrepo.repogallery.ui.comman.AppNetworkImage
import com.findrepo.repogallery.ui.comman.AppScaffold
import com.findrepo.repogallery.ui.comman.AppText
import com.findrepo.repogallery.ui.comman.AppTextSmall
import com.findrepo.repogallery.ui.comman.AppTextSmallTitle
import com.findrepo.repogallery.ui.comman.AppTextTitle
import com.findrepo.repogallery.ui.item.ContributorItem
import com.findrepo.repogallery.ui.theme.colorBlue
import com.findrepo.repogallery.viewmodel.RepositoryDetailViewModel
import com.findrepo.state.RepositoryDetailUiEvent

@Composable
fun RepositoryDetailScreen(
    repo: () -> Repository? = { null },
) {
    val viewModel = hiltViewModel<RepositoryDetailViewModel>()
    val viewState by viewModel.consumableState().collectAsState()

    repo()?.let { RepositoryDetailUiEvent.RepositoryData(it) }?.let { viewModel.onEvent(it) }

    val contributors by remember { derivedStateOf {  viewState.contributors } }

    RepositoryDetailContent(
        repo = { viewState.repo },
        contributors = { contributors },
        onLinkClick = { }
    )
}

@Composable
fun RepositoryDetailContent(
    repo: () -> Repository?,
    contributors: () -> List<ContributorResponse> = { emptyList() },
    onLinkClick: (String) -> Unit = {},
    snackBarHostState: SnackbarHostState = remember { SnackbarHostState() },
) {
    AppScaffold(
        showNavigationIcon = true,
        navigationIconResId = R.drawable.ic_back,
        snackbarHostState = snackBarHostState,
    ) { contentPadding ->
        Column(
            modifier = Modifier
                .padding(contentPadding)
                .padding(start = 16.dp, end = 16.dp, top = 16.dp)
        ) {
            Row(modifier = Modifier.padding(bottom = 16.dp)) {
                AppNetworkImage(
                    modifier = Modifier
                        .size(75.dp)
                        .clip(CircleShape),
                    contentScale = ContentScale.Crop,
                    model = repo()?.owner?.avatarUrl.toString(),
                    placeholder = R.drawable.profile_placeholder,
                )

                AppTextSmallTitle(
                    modifier = Modifier
                        .padding(horizontal = 16.dp)
                        .weight(1f)
                        .align(Alignment.CenterVertically),
                    text = repo()?.owner?.login.toString(),
                    textAlign = TextAlign.Start
                )
            }

            AppText(
                modifier = Modifier
                    .padding(bottom = 12.dp),
                text = repo()?.description.toString()
            )

            Row {
                AppTextSmall(
                    text = stringResource(R.string.desc_stars) + repo()?.stargazersCount.toString()
                )

                Spacer(modifier = Modifier.weight(1f))

                AppTextSmall(
                    text = stringResource(R.string.desc_folks) + repo()?.forksCount.toString()
                )
            }

            AppText(
                modifier = Modifier
                    .padding(bottom = 12.dp, top = 12.dp),
                text = repo()?.htmlUrl.toString(),
                textDecoration = TextDecoration.Underline,
                color = colorBlue
            )

            AppTextTitle(
                modifier = Modifier
                    .padding(vertical = 12.dp),
                text = stringResource(R.string.decs_contributors)
            )

            LazyColumn {
                itemsIndexed(contributors()) { index, contributor ->
                    ContributorItem(
                        contributor = contributor,
                        showDivider = contributors().size -1 != index
                    )
                }
            }
        }
    }
}