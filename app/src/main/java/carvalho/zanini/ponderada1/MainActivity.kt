package carvalho.zanini.ponderada1

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import kotlin.random.Random
import androidx.compose.ui.tooling.preview.Preview

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            LancadorDeDadosApp()
        }
    }
}

@Preview(showBackground = true)
@Composable
fun LancadorDeDadosApp() {
    var dadoSelecionado by remember { mutableStateOf("D6") }
    var resultado by remember { mutableStateOf("Clique no botão para lançar o dado") }
    var img by remember { mutableStateOf<Int?>(null) }
    val dados = listOf("D6", "D10", "D20", "D100")

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(24.dp),
        horizontalAlignment = Alignment.Start,
        verticalArrangement = Arrangement.Center
    ) {
        Text(
            text = "Lançador de Dados",
            fontSize = 28.sp
        )

        Spacer(modifier = Modifier.height(24.dp))

        Text(text = "Escolha o tipo de dado:")

        dados.forEach { dado ->
            Row(
                verticalAlignment = Alignment.CenterVertically
            ) {
                RadioButton(
                    selected = dadoSelecionado == dado,
                    onClick = { dadoSelecionado = dado }
                )
                Text(text = dado)
            }
        }

        Spacer(modifier = Modifier.height(16.dp))

        Button(
            onClick = {
                val valorSorteado = when (dadoSelecionado) {
                    "D6"   -> Random.nextInt(1, 7)
                    "D10"  -> Random.nextInt(1, 11)
                    "D20"  -> Random.nextInt(1, 21)
                    "D100" -> Random.nextInt(1, 101)
                    else   -> 0
                }

                if (dadoSelecionado == "D6") {
                    img = when (valorSorteado) {
                        1 -> R.drawable.dado_1
                        2 -> R.drawable.dado_2
                        3 -> R.drawable.dado_3
                        4 -> R.drawable.dado_4
                        5 -> R.drawable.dado_5
                        6 -> R.drawable.dado_6
                        else -> null
                    }
                } else {
                    img = null
                }

                resultado = "Resultado do $dadoSelecionado: $valorSorteado"
            },
            modifier = Modifier.fillMaxWidth()
        ) {
            Text(text = "Lançar dado")
        }

        img?.let { imagemId ->
            Image(
                painter = painterResource(imagemId),
                contentDescription = "O valor que saiu foi",
                modifier = Modifier.size(64.dp)
            )
        }

        Spacer(modifier = Modifier.height(24.dp))

        Text(
            text = resultado,
            fontSize = 20.sp
        )
    }
}