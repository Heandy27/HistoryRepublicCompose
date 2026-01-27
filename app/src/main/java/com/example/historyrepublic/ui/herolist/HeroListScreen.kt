package com.example.historyrepublic.ui.herolist

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Card
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import coil3.compose.AsyncImage
import com.example.historyrepublic.R
import com.example.historyrepublic.domain.Hero

@Composable
fun HeroListScreen(hero: List<Hero>, modifier: Modifier = Modifier) {
    LazyColumn(Modifier.fillMaxSize()) {
      items(hero) {
          HeroListItem(hero = it)
      }
    }
}

@Preview(showSystemUi = true)
@Composable
fun HeroListScreen_Preview() {
    HeroListScreen(generateHeros())
}

@Composable
fun HeroListItem(hero: Hero, modifier: Modifier = Modifier) {
    Card(modifier = modifier.fillMaxWidth()) {
        Row(modifier = Modifier.fillMaxWidth().padding(8.dp), horizontalArrangement = Arrangement.spacedBy(10.dp)) {
            AsyncImage(model = hero.image, contentDescription = "Logo", modifier = Modifier.size(100.dp, 150.dp), contentScale = ContentScale.Crop)
            Column(verticalArrangement = Arrangement.spacedBy(10.dp)) {
                Text(hero.nameHero, style = MaterialTheme.typography.headlineLarge)
                Text(hero.information, style = MaterialTheme.typography.bodySmall)
            }
        }
    }
}

@Preview
@Composable
fun HeroListItem_Preview() {
   HeroListItem(hero = Hero(
       id = "1",
       "Cleopatra",
       "Cleopatra",
       "Faraona",
       "https://historyrepublic.com/wp-content/uploads/2026/01/Richard_I_of_England_in_the_Brief_Abridgement_of_the_Chronicles_of_England.jpg",
       "https://historyrepublic.com/richard-the-lionheart/"
   ))
}

private fun generateHeros() = (0 until 10).map { Hero("id$it", "Name$it", "Title$it", "Information$it","image$it", "Url$it") }