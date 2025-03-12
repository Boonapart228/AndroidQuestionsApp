package com.balan.androidquestionsapp.presentation.score.components.contents

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import com.balan.androidquestionsapp.ui.theme.LocalDimen
import com.balan.androidquestionsapp.ui.theme.LocalProperty


@Composable
fun ShimmerLoadingContent(modifier: Modifier = Modifier) {
    Card(
        elevation = CardDefaults.cardElevation(LocalDimen.current.cardElevation),
        shape = RoundedCornerShape(LocalDimen.current.cardShape),
        modifier = modifier
            .fillMaxWidth()
            .padding(
                horizontal = LocalDimen.current.cardHorizontalPadding,
                vertical = LocalDimen.current.cardVerticalPadding
            )
    ) {
        Row(
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceBetween,
            modifier = Modifier
                .fillMaxWidth()
                .padding(LocalDimen.current.rowPadding),
        ) {
            Column(
                verticalArrangement = Arrangement.spacedBy(LocalDimen.current.verticalSpaced),
                modifier = Modifier.weight(LocalProperty.current.largeContentWeight)
            ) {
                ShimmerEffect(
                    modifier = Modifier
                        .size(
                            width = LocalDimen.current.shimmerShortWidth,
                            height = LocalDimen.current.shimmerHeight
                        )
                        .padding(
                            horizontal = LocalDimen.current.shimmerHorizontalPadding,
                            vertical = LocalDimen.current.shimmerVerticalPadding
                        )
                        .fillMaxWidth()
                )
                ShimmerEffect(
                    modifier = Modifier
                        .height(LocalDimen.current.shimmerHeight)
                        .padding(
                            horizontal = LocalDimen.current.shimmerHorizontalPadding,
                            vertical = LocalDimen.current.shimmerVerticalPadding
                        )
                        .fillMaxWidth()
                )
            }
            ShimmerEffect(
                modifier = Modifier
                    .height(LocalDimen.current.shimmerHeight)
                    .padding(horizontal = LocalDimen.current.shimmerHorizontalPadding)
                    .weight(LocalProperty.current.largeContentWeight)
                    .fillMaxWidth()
            )
            ShimmerEffect(
                modifier = Modifier
                    .size(LocalDimen.current.shimmerIconSize)
                    .clip(CircleShape)
                    .padding(),
            )
        }
    }

}