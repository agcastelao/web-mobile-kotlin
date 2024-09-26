package com.example.pedrapapeltesoura

import android.content.Intent
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.TextView

class SobreArthur : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_sobre_arthur)

        // Referências para os TextViews
        val projeto1Link: TextView = findViewById(R.id.projeto1_link)
        val projeto2Link: TextView = findViewById(R.id.projeto2_link)
        val botaoVoltar: Button = findViewById(R.id.botao_voltar)

        // Referências para os TextViews das redes sociais
        val linkedinLink: TextView = findViewById(R.id.linkedin_link)
        val threadsLink: TextView = findViewById(R.id.treads_link)
        val githubLink: TextView = findViewById(R.id.github_link)

        // Listener para o projeto 1
        projeto1Link.setOnClickListener {
            openProject1()
        }

        // Listener para o projeto 2
        projeto2Link.setOnClickListener {
            openProject2()
        }

        // Listener para o LinkedIn
        linkedinLink.setOnClickListener {
            openLinkedIn()
        }

        // Listener para o Threads
        threadsLink.setOnClickListener {
            openThreads()
        }

        // Listener para o GitHub
        githubLink.setOnClickListener {
            openGitHub()
        }

        // Listener para o botão Voltar
        botaoVoltar.setOnClickListener {
            finish() // Volta para a atividade anterior
        }
    }

    private fun openProject1() {
        val url = "https://github.com/arthurbarbosaa/snake-game" // Substitua pelo link real
        val intent = Intent(Intent.ACTION_VIEW)
        intent.data = Uri.parse(url)
        startActivity(intent)
    }

    private fun openProject2() {
        val url = "https://github.com/arthurbarbosaa/script-switches" // Substitua pelo link real
        val intent = Intent(Intent.ACTION_VIEW)
        intent.data = Uri.parse(url)
        startActivity(intent)
    }

    private fun openLinkedIn() {
        val url = "https://www.linkedin.com/in/arthur-barbosa-b0429a302/" // Substitua pelo link do seu LinkedIn
        val intent = Intent(Intent.ACTION_VIEW)
        intent.data = Uri.parse(url)
        startActivity(intent)
    }

    private fun openThreads() {
        val url = "https://www.threads.net/@arthur__fferreira" // Substitua pelo link do seu Threads
        val intent = Intent(Intent.ACTION_VIEW)
        intent.data = Uri.parse(url)
        startActivity(intent)
    }

    private fun openGitHub() {
        val url = "https://github.com/arthurbarbosaa" // Substitua pelo link do seu GitHub
        val intent = Intent(Intent.ACTION_VIEW)
        intent.data = Uri.parse(url)
        startActivity(intent)
    }
}
