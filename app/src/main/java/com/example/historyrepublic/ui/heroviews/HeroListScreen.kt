package com.example.historyrepublic.ui.heroviews

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import coil3.compose.AsyncImage
import com.example.historyrepublic.domain.Hero

@Composable
fun HeroListScreen(
    hero: List<Hero>,
    modifier: Modifier = Modifier,
    onHeroClick: (String) -> Unit
) {
    LazyColumn(modifier.fillMaxSize(), verticalArrangement = Arrangement.spacedBy(20.dp), contentPadding = PaddingValues(20.dp)) {
        items(hero) {
            HeroListItem(
                hero = it,
                onClick = { heroId -> onHeroClick(heroId) }
            )
        }
    }
}


@Preview(showSystemUi = true)
@Composable
fun HeroListScreen_Preview() {
    HeroListScreen(
        generateHeros(),
        onHeroClick = { }
    )
}

@Composable
fun HeroListItem(
    hero: Hero,
    modifier: Modifier = Modifier,
    onClick: (String) -> Unit
) {
    Card(
        modifier = modifier
            .fillMaxWidth()
            .clickable { onClick(hero.id) },
        shape = RoundedCornerShape(20.dp),
        elevation = CardDefaults.cardElevation(defaultElevation = 6.dp)
    ) {
        Row(
            modifier = Modifier
                .padding(12.dp),
            horizontalArrangement = Arrangement.spacedBy(14.dp)
        ) {

            // ✅ Imagen más pro
            AsyncImage(
                model = hero.image,
                contentDescription = hero.nameHero,
                modifier = Modifier
                    .size(width = 100.dp, height = 140.dp)
                    .clip(RoundedCornerShape(16.dp)),
                contentScale = ContentScale.Crop
            )

            Column(
                modifier = Modifier
                    .fillMaxWidth(),
                verticalArrangement = Arrangement.spacedBy(8.dp)
            ) {

                // ✅ Nombre más elegante
                Text(
                    text = hero.nameHero,
                    style = MaterialTheme.typography.titleLarge,
                    maxLines = 1
                )

                // ✅ Info cortada con "..."
                Text(
                    text = hero.information,
                    style = MaterialTheme.typography.bodyMedium,
                    maxLines = 3,
                    overflow = TextOverflow.Ellipsis
                )
            }
        }
    }
}


@Preview
@Composable
fun HeroListItem_Preview() {
    HeroListItem(
        hero = Hero(
            id = "1",
            nameHero = "Cleopatra",
            title = "Queen of Egypt",
            information = "One of the most famous rulers of ancient Egypt.",
            image = "https://historyrepublic.com/wp-content/uploads/2026/01/example.jpg",
            url = "https://historyrepublic.com/"
        ),
        onClick = { }
    )
}

private fun generateHeros() = (0 until 10).map {
    Hero(
        "id$it",
        "Name$it",
        "Title$it",
        "Information$it",
        "image$it",
        "Url$it"
    )
}