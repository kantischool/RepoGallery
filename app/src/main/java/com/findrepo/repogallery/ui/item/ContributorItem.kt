package com.findrepo.repogallery.ui.item

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.HorizontalDivider
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.findrepo.model.response.ContributorResponse
import com.findrepo.repogallery.R
import com.findrepo.repogallery.ui.comman.AppNetworkImage
import com.findrepo.repogallery.ui.comman.AppText
import com.findrepo.repogallery.ui.comman.AppTextSmall

@Composable
fun ContributorItem(
    contributor: ContributorResponse,
    showDivider: Boolean = true,
) {
    Column {
        Row(modifier = Modifier.padding(bottom = 16.dp)) {
            AppNetworkImage(
                modifier = Modifier
                    .size(48.dp),
                contentScale = ContentScale.Crop,
                model = contributor.avatarUrl.toString(),
                placeholder = R.drawable.profile_placeholder,
            )

            Column(
                modifier = Modifier
                    .padding(start = 16.dp)
                    .align(Alignment.CenterVertically)
            ) {
                AppText(
                    text = contributor.login.toString(),
                )

                AppTextSmall(
                    text = stringResource(R.string.desc_contribution) +contributor.contributions.toString(),
                )
            }
        }

        if (showDivider) {
            HorizontalDivider(modifier = Modifier.padding(bottom = 16.dp))
        }
    }

}