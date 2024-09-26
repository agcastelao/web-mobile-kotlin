package com.example.pedrapapeltesoura

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import com.example.pedrapapeltesoura.ui.theme.PedraPapelTesouraTheme

class SobreAntonio : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            PedraPapelTesouraTheme {
                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                    TelaSobreAntonio(
                        modifier = Modifier.padding(innerPadding),
                        onBackClick = { finish() },
                        onGitHubClick = { openGitHub() }
                    )
                }
            }
        }
    }

    private fun openGitHub() {
        val url = "https://github.com/antonio-castelao"
        val intent = Intent(Intent.ACTION_VIEW)
        intent.data = Uri.parse(url)
        startActivity(intent)
    }
}

@Composable
fun TelaSobreAntonio(modifier: Modifier = Modifier, onBackClick: () -> Unit, onGitHubClick: () -> Unit) {
    Card(
        modifier = modifier
            .fillMaxSize()
            .padding(16.dp),
        shape = RoundedCornerShape(20.dp),
        colors = CardDefaults.cardColors(containerColor = Color(0xFFFFF3E0)),
        elevation = CardDefaults.cardElevation(8.dp)
    ) {
        LazyColumn(
            modifier = Modifier
                .fillMaxSize()
                .padding(16.dp),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Top
        ) {
            item {
                Image(
                    painter = painterResource(id = R.drawable.foto_antonio),
                    contentDescription = "Foto de Antonio Castelao",
                    modifier = Modifier
                        .size(200.dp)
                        .padding(bottom = 8.dp)
                )
                Text(
                    text = "Sobre Antonio Castelao",
                    style = MaterialTheme.typography.headlineLarge.copy(fontWeight = FontWeight.Bold),
                    color = Color(0xFFEF6C00),
                    modifier = Modifier.padding(bottom = 8.dp)
                )
                Text(
                    text = "Olá! Sou apaixonado por tecnologia e sempre busco aprender mais sobre o mundo digital.",
                    style = MaterialTheme.typography.bodyLarge,
                    color = Color(0xFF444444), // Cor de texto padrão
                    modifier = Modifier.padding(bottom = 16.dp),
                    textAlign = androidx.compose.ui.text.style.TextAlign.Center // Alinhando o texto ao centro
                )
            }

            // Informações
            item {
                Text(
                    text = "Nome: Antonio Castelao",
                    style = MaterialTheme.typography.bodyLarge
                )
            }
            item {
                Text(
                    text = "Curso: Ciência da Computação",
                    style = MaterialTheme.typography.bodyLarge
                )
            }

            item {
                Button(
                    onClick = onBackClick,
                    modifier = Modifier.padding(top = 16.dp)
                ) {
                    Text(text = "Voltar")
                }
            }

            item {
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(top = 16.dp)
                        .clickable { onGitHubClick() },
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Image(
                        painter = painterResource(id = R.drawable.icon_github),
                        contentDescription = "Logo do GitHub",
                        modifier = Modifier.size(24.dp)
                    )
                    Spacer(modifier = Modifier.width(8.dp))
                    Text(
                        text = "GitHub: antonio-castelao",
                        color = Color(0xFFEF6C00),
                        style = MaterialTheme.typography.bodyLarge
                    )
                }
            }
        }
    }


}
