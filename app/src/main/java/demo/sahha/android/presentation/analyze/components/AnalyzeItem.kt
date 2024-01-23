package demo.sahha.android.presentation.analyze.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Card
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.capitalize
import androidx.compose.ui.text.intl.Locale
import androidx.compose.ui.unit.dp
import demo.sahha.android.domain.model.dto.SahhaAnalysisDto
import demo.sahha.android.presentation.ui.theme.rubikFamily

@OptIn(ExperimentalMaterialApi::class)
@Composable
fun AnalyzeItem(
    analysis: SahhaAnalysisDto,
    formattedTime: String,
    onClick: (SahhaAnalysisDto) -> Unit
) {
    Card(
        onClick = { onClick(analysis) },
        backgroundColor = MaterialTheme.colors.primary,
        shape = RoundedCornerShape(15),
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(20.dp)
        ) {
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                Text(
                    "Type",
                    fontFamily = rubikFamily,
                    color = MaterialTheme.colors.onPrimary
                )
                Text(
                    analysis.type.capitalize(Locale.current),
                    fontFamily = rubikFamily,
                    color = MaterialTheme.colors.onPrimary
                )
            }
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                Text(
                    "State",
                    fontFamily = rubikFamily,
                    color = MaterialTheme.colors.onPrimary
                )
                Text(
                    analysis.state.capitalize(Locale.current),
                    fontFamily = rubikFamily,
                    color = MaterialTheme.colors.onPrimary
                )
            }
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                Text(
                    "Score",
                    fontFamily = rubikFamily,
                    color = MaterialTheme.colors.onPrimary
                )
                Text(
                    analysis.score.toString(),
                    fontFamily = rubikFamily,
                    color = MaterialTheme.colors.onPrimary
                )
            }
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                Text(
                    "Created at",
                    fontFamily = rubikFamily,
                    color = MaterialTheme.colors.onPrimary
                )
                Text(
                    formattedTime,
                    fontFamily = rubikFamily,
                    color = MaterialTheme.colors.onPrimary
                )
            }
        }
    }
    Spacer(modifier = Modifier.padding(10.dp))
}