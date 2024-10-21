package com.findrepo.repogallery.ui.item

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.findrepo.repogallery.model.item.Repository
import com.findrepo.repogallery.ui.comman.AppText
import com.findrepo.repogallery.ui.comman.AppTextTitle

@Composable
fun RepositoryItem(
    repo: Repository,
    onRepositoryClick: (Repository) -> Unit = {},
) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(bottom = 8.dp)
            .clickable { onRepositoryClick.invoke(repo) },
        shape = RoundedCornerShape(8.dp),
    ) {
        Column(
            modifier = Modifier
                .padding(16.dp)
        ) {
            Column {
                AppTextTitle(
                    modifier = Modifier.padding(bottom = 12.dp),
                    text = repo.name.toString()
                )

                AppText(
                    modifier = Modifier.padding(bottom = 12.dp),
                    text = repo.description.toString(),
                    maxLines = 2
                )
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