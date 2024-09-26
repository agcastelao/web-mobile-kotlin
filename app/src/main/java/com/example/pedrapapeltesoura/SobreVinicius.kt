package com.example.pedrapapeltesoura

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Person
import androidx.compose.material.icons.filled.Star
import androidx.compose.material.icons.filled.Info
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import com.example.pedrapapeltesoura.ui.theme.PedraPapelTesouraTheme
import android.app.Activity
import android.content.Intent
import androidx.compose.ui.draw.clip
import androidx.compose.ui.platform.LocalContext

class SobreVinicius : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            PedraPapelTesouraTheme {
                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                    TelaSobre(
                        modifier = Modifier.padding(innerPadding)
                    )
                }
            }
        }
    }
}

@Composable
fun TelaSobre(modifier: Modifier = Modifier) {
    Card(
        modifier = modifier
            .fillMaxSize()
            .padding(16.dp),
        shape = RoundedCornerShape(16.dp),
        colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.surface),
        elevation = CardDefaults.cardElevation(8.dp)
    ) {
        LazyColumn(
            modifier = Modifier
                .fillMaxSize()
                .padding(16.dp),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {
            item {
                Image(
                    painter = painterResource(id = R.drawable.foto_vinicius),
                    contentDescription = "Foto de Vinícius",
                    modifier = Modifier
                        .size(250.dp)
                        .padding(bottom = 16.dp)
                )
                Text(
                    text = "Sobre Mim",
                    style = MaterialTheme.typography.headlineMedium,
                    modifier = Modifier.padding(bottom = 16.dp)
                )
            }

            item { InfoRow(icon = Icons.Filled.Person, info = "Nome: Vinícius Ribau") }
            item { InfoRow(icon = Icons.Filled.Star, info = "Idade: 21 anos") }
            item { InfoRow(icon = Icons.Filled.Info, info = "Curso: Ciência da Computação") }
            item { InfoRow(icon = Icons.Filled.Star, info = "Interesses: Kotlin, Android, Desenvolvimento Mobile") }

            item {
                val context = LocalContext.current
                Button(
                    onClick = {

                        context.startActivity(Intent(context, MainActivity::class.java))

                        (context as Activity).finish()
                    },
                    colors = ButtonDefaults.buttonColors(containerColor = Color(0xFF6200EE)),
                    modifier = Modifier
                        .padding(top = 16.dp)
                        .height(56.dp)
                        .fillMaxWidth()
                        .clip(RoundedCornerShape(0.dp))
                ) {
                    Text(text = "Voltar", style = MaterialTheme.typography.labelLarge)
                }
            }
        }
    }
}

@Composable
fun InfoRow(icon: ImageVector, info: String) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 8.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Icon(
            imageVector = icon,
            contentDescription = null,
            tint = Color(0xFF1976D2),
            modifier = Modifier.size(24.dp)
        )
        Spacer(modifier = Modifier.width(16.dp))
        Text(
            text = info,
            style = MaterialTheme.typography.bodyLarge
        )
    }
}
