package com.findrepo.repogallery.ui.item

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.findrepo.model.Repository
import com.findrepo.repogallery.R
import com.findrepo.repogallery.ui.comman.AppText
import com.findrepo.repogallery.ui.comman.AppTextSmall
import com.findrepo.repogallery.ui.comman.AppTextTitle

@Composable
fun RepositoryItem(
    repo: Repository,
    onRepositoryClick: (Repository) -> Unit = {},
) {
    Card(
        modifier = Modifier.padding(bottom = 8.dp),
        shape = RoundedCornerShape(8.dp),
    ) {
        Column(
            modifier = Modifier
                .padding(16.dp)
                .clickable {
                    onRepositoryClick.invoke(repo)
                }
        ) {
            Column {
                AppTextTitle(
                    modifier = Modifier.padding(bottom = 12.dp),
                    text = repo.name.toString()
                )

                AppText(
                    modifier = Modifier.padding(bottom = 12.dp),
                    text = repo.description.toString(),
                    maxLines = 4
                )

                Row {
                    AppTextSmall(
                        text = stringResource(R.string.desc_stars) +repo.stargazersCount.toString()
                    )

                    Spacer(modifier = Modifier.weight(1f))

                    AppTextSmall(
                        text = stringResource(R.string.desc_folks) + repo.forksCount.toString()
                    )
                }
            }
        }
    }
}

@Composable
@Preview(showBackground = true)
fun RepoItemPreview() {
    RepositoryItem(
        repo = Repository()
    )
}