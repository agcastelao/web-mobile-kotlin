package com.example.pedrapapeltesoura

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.pedrapapeltesoura.ui.theme.PedraPapelTesouraTheme
import kotlin.random.Random
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.navigation.NavController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            PedraPapelTesouraTheme {
                // Configure a navegação
                val navController = rememberNavController()
                NavHost(navController, startDestination = "game") {
                    composable("game") { GameScreen(navController) }
                    composable("about/{developerName}") { backStackEntry ->
                        val developerName = backStackEntry.arguments?.getString("developerName")
                        AboutDeveloper(developerName ?: "")
                    }
                }
            }
        }
    }
}

@Composable
fun GameScreen(navController: NavController) {
    var playerChoice by remember { mutableStateOf("") }
    var computerChoice by remember { mutableStateOf("") }
    var resultMessage by remember { mutableStateOf("") }
    var playerWins by remember { mutableStateOf(0) }
    var computerWins by remember { mutableStateOf(0) }

    // Função para converter escolha para emoji
    fun choiceToEmoji(choice: String): String {
        return when (choice) {
            "pedra" -> "🪨"
            "papel" -> "📄"
            "tesoura" -> "✂️"
            else -> ""
        }
    }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        // Título do jogo
        Text(
            text = "Pedra Papel Tesoura 🎮",
            fontSize = 32.sp,
            fontWeight = FontWeight.Bold,
            textAlign = TextAlign.Center
        )

        Spacer(modifier = Modifier.height(50.dp))

        Text(
            text = "Escolha: ",
            fontSize = 16.sp,
            textAlign = TextAlign.Start
        )

        Spacer(modifier = Modifier.height(20.dp))

        // Row para as jogadas com emojis
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceEvenly
        ) {
            Column(horizontalAlignment = Alignment.CenterHorizontally) {
                // Emoji de Pedra acima do botão
                Text(
                    text = "🪨",
                    fontSize = 50.sp,
                    textAlign = TextAlign.Center
                )
                Spacer(modifier = Modifier.height(8.dp))
                Button(
                    onClick = {
                        playerChoice = "pedra"
                        computerChoice = getComputerChoice()
                        resultMessage = playGame(playerChoice, computerChoice) { playerWon, computerWon ->
                            if (playerWon) playerWins++ else if (computerWon) computerWins++
                        }
                    },
                    shape = RoundedCornerShape(4.dp), // Menos arredondado
                    colors = ButtonDefaults.buttonColors(
                        containerColor = Color(0xFF585860) // Azul vivo
                    )
                ) {
                    Text("Pedra")
                }
            }

            Column(horizontalAlignment = Alignment.CenterHorizontally) {
                // Emoji de Papel acima do botão
                Text(
                    text = "📄",
                    fontSize = 50.sp,
                    textAlign = TextAlign.Center
                )
                Spacer(modifier = Modifier.height(8.dp))
                Button(
                    onClick = {
                        playerChoice = "papel"
                        computerChoice = getComputerChoice()
                        resultMessage = playGame(playerChoice, computerChoice) { playerWon, computerWon ->
                            if (playerWon) playerWins++ else if (computerWon) computerWins++
                        }
                    },
                    shape = RoundedCornerShape(4.dp), // Menos arredondado
                    colors = ButtonDefaults.buttonColors(
                        containerColor = Color(0xFF2196F3) // Azul vivo
                    )
                ) {
                    Text("Papel")
                }
            }

            Column(horizontalAlignment = Alignment.CenterHorizontally) {
                // Emoji de Tesoura acima do botão
                Text(
                    text = "✂️",
                    fontSize = 50.sp,
                    textAlign = TextAlign.Center
                )
                Spacer(modifier = Modifier.height(8.dp))
                Button(
                    onClick = {
                        playerChoice = "tesoura"
                        computerChoice = getComputerChoice()
                        resultMessage = playGame(playerChoice, computerChoice) { playerWon, computerWon ->
                            if (playerWon) playerWins++ else if (computerWon) computerWins++
                        }
                    },
                    shape = RoundedCornerShape(4.dp), // Menos arredondado
                    colors = ButtonDefaults.buttonColors(
                        containerColor = Color(0xFFE91E63) // Azul vivo
                    )
                ) {
                    Text("Tesoura")
                }
            }
        }

        Spacer(modifier = Modifier.height(20.dp))

        // Exibir as jogadas com emojis
        if (playerChoice.isNotEmpty() && computerChoice.isNotEmpty()) {
            Text(
                text = "${choiceToEmoji(playerChoice)} X ${choiceToEmoji(computerChoice)}",
                fontSize = 24.sp,
                fontWeight = FontWeight.Bold
            )
        }

        Spacer(modifier = Modifier.height(10.dp))

        // Exibir resultado do jogo
        if (resultMessage.isNotEmpty()) {
            Text(
                text = resultMessage,
                fontSize = 24.sp,
                fontWeight = FontWeight.Bold,
                textAlign = TextAlign.Center
            )
        }

        Spacer(modifier = Modifier.height(20.dp))

        // Exibir contadores de vitórias
        Text(text = "Vitórias do jogador: $playerWins")
        Text(text = "Vitórias do computador: $computerWins")

        Spacer(modifier = Modifier.height(100.dp))

        Footer { developerName ->
            navController.navigate("about/$developerName") // Navegando para a tela "Sobre"
        }
    }
}

@Composable
fun Footer(onDeveloperClick: (String) -> Unit) {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(
            text = "Desenvolvedores:",
            fontSize = 16.sp,
            fontWeight = FontWeight.Bold,
            textAlign = TextAlign.Center
        )
        // Tornando os nomes clicáveis
        Text(
            text = "Arthur Barbosa",
            fontSize = 14.sp,
            textAlign = TextAlign.Center,
            color = Color.Blue,
            modifier = Modifier.clickable { onDeveloperClick("Arthur Barbosa") } // Navegação
        )
        Text(
            text = "Maria Silva",
            fontSize = 14.sp,
            textAlign = TextAlign.Center,
            color = Color.Blue,
            modifier = Modifier.clickable { onDeveloperClick("Maria Silva") } // Navegação
        )
        Text(
            text = "João Pereira",
            fontSize = 14.sp,
            textAlign = TextAlign.Center,
            color = Color.Blue,
            modifier = Modifier.clickable { onDeveloperClick("João Pereira") } // Navegação
        )
    }
}

// Função para obter jogada do computador
fun getComputerChoice(): String {
    val options = arrayOf("pedra", "papel", "tesoura")
    return options[Random.nextInt(options.size)]
}

// Função para jogar
fun playGame(
    playerChoice: String,
    computerChoice: String,
    updateWins: (playerWon: Boolean, computerWon: Boolean) -> Unit
): String {
    return when {
        playerChoice == computerChoice -> "Empate!"
        (playerChoice == "pedra" && computerChoice == "tesoura") ||
                (playerChoice == "papel" && computerChoice == "pedra") ||
                (playerChoice == "tesoura" && computerChoice == "papel") -> {
            updateWins(true, false)
            "Você venceu!"
        }
        else -> {
            updateWins(false, true)
            "Você perdeu!"
        }
    }
}

@Preview(showBackground = true)
@Composable
fun GameScreenPreview() {
    PedraPapelTesouraTheme {
        GameScreen()
    }
}
